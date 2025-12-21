package model;

public class User {
	private int id;
	private String nama;
	private double saldoAwal;

	public User(int id, String nama, double saldoAwal) {
		this.id = id;
		this.nama = nama;
		this.saldoAwal = saldoAwal;
	}

	public int getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}

	public double getSaldoAwal() {
		return saldoAwal;
	}
	public void setName(String nama) {
        this.nama = nama;
    }

    public void setSaldoAwal(double saldoAwal) {
        this.saldoAwal = saldoAwal;
    }
}