package dao;

import java.util.List;
import model.Transaksi;

public interface TransaksiDAO {
    void save(Transaksi tr);
    void update(Transaksi tr);
    void delete(int id);
    List<Transaksi> show(int userId);
}
