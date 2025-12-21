package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import config.Database;
import model.*;

public class TransaksiRepo implements TransaksiDAO {

    private Connection conn;

    private final String insert = "INSERT INTO transaksi (tanggal, nominal, jenis, deskripsi, user_id, kategori) " + "VALUES (?, ?, ?, ?, ?, ?)";

    private final String select = "SELECT * FROM transaksi WHERE user_id = ?";

    private final String update = "UPDATE transaksi SET tanggal=?, nominal=?, jenis=?, deskripsi=?, kategori=? WHERE id_t=?";

    private final String delete = "DELETE FROM transaksi WHERE id_t=?";

    public TransaksiRepo() {
        conn = Database.koneksi(); 
    }

    @Override
    public void save(Transaksi tr) {
        try (PreparedStatement ps = conn.prepareStatement(insert)) {
            ps.setString(1, tr.getTanggal());
            ps.setDouble(2, tr.getNominal());
            ps.setString(3, tr instanceof Pemasukan ? "PEMASUKAN" : "PENGELUARAN");
            ps.setString(4, tr.getDeskripsi());
            ps.setInt(5, tr.getUserId());
            ps.setString(6, tr.getKategori());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Transaksi> show(int userId) {
        List<Transaksi> list = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(select)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaksi tr = new TransaksiBuilder()
                    .setId(rs.getInt("id_t"))
                    .setTanggal(rs.getString("tanggal"))
                    .setNominal(rs.getDouble("nominal"))
                    .setKategori(rs.getString("kategori"))
                    .setDeskripsi(rs.getString("deskripsi"))
                    .setUserId(rs.getInt("user_id"))
                    .setIsIncome(rs.getString("jenis").equals("PEMASUKAN"))
                    .build();

                list.add(tr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public void update(Transaksi tr) {
        try (PreparedStatement ps = conn.prepareStatement(update)) {
            ps.setString(1, tr.getTanggal());
            ps.setDouble(2, tr.getNominal());
            ps.setString(3, tr instanceof Pemasukan ? "PEMASUKAN" : "PENGELUARAN");
            ps.setString(4, tr.getDeskripsi());
            ps.setString(5, tr.getKategori());
            ps.setInt(6, tr.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = conn.prepareStatement(delete)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

