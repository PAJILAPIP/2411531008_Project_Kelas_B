package model;

public class TransaksiBuilder {
    private int id;
    private String tanggal;
    private double nominal;
    private String deskripsi;
    private int userId;
    private String kategori;
    private boolean isIncome;

    public TransaksiBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public TransaksiBuilder setTanggal(String tanggal) {
        this.tanggal = tanggal;
        return this;
    }

    public TransaksiBuilder setNominal(double nominal) {
        this.nominal = nominal;
        return this;
    }

    public TransaksiBuilder setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
        return this;
    }

    public TransaksiBuilder setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public TransaksiBuilder setKategori(String kategori) {
        this.kategori = kategori;
        return this;
    }

    public TransaksiBuilder setIsIncome(boolean isIncome) {
        this.isIncome = isIncome;
        return this;
    }

    public Transaksi build() {
        if (isIncome) {
            return new Pemasukan(id, tanggal, nominal, deskripsi, userId, kategori);
        }
        return new Pengeluaran(id, tanggal, nominal, deskripsi, userId, kategori);
    }
}
