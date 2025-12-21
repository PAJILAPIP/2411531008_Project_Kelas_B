package model;

public class UserBuilder {
    private int id;
    private String nama;
    private double saldoAwal;

    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder setNama(String nama) {
        this.nama = nama;
        return this;
    }

    public UserBuilder setSaldoAwal(double saldoAwal) {
        this.saldoAwal = saldoAwal;
        return this;
    }

    public User build() {
        return new User(id, nama, saldoAwal);
    }
}
