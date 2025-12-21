package model;

public class Pemasukan extends Transaksi {

    public Pemasukan(int id, String tanggal, double nominal,
            String deskripsi, int userId, String kategori) {
    	super(id, tanggal, nominal, deskripsi, userId, kategori);
    }

    @Override
    public double hitungSaldoBaru(double saldoSebelumnya) {
        return saldoSebelumnya + nominal;
    }
}