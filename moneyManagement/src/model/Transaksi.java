package model;

import error.ValidationException;

public abstract class Transaksi {
	private int id;
	private String tanggal;
	protected double nominal;
	private String kategori;
	private String deskripsi;
	private int userId;
	
	public Transaksi(int id, String tanggal, double nominal,
            String deskripsi, int userId, String kategori) {
		this.id = id;
		this.tanggal = tanggal;
		this.nominal = nominal;
		this.deskripsi = deskripsi;
		this.userId = userId;
		this.kategori = kategori;
	}
	
	public int getId() {
        return id;
	}
	public void setId(int id) {
        this.id = id;
    }

	
	public String getTanggal() {
		return tanggal;
	}
	
	public double getNominal() {
		return nominal;
	}
	
	public String getKategori() {
		return kategori;
	}
	
	public String getDeskripsi() {
		return deskripsi;
	}
	
	public int getUserId() {
		return userId;
	}
	
	
	
	public abstract double hitungSaldoBaru(double saldoSebelumnya);
	public void validasiNominal() throws ValidationException {
        if (nominal <= 0) {
            throw new ValidationException("Nominal harus lebih dari 0");
        }
    }
}
