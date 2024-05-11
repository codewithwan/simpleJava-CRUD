package com;

public class Data {

    private String nama;
    private String kota;

    public Data(String nama, String kota) {
        this.nama = nama;
        this.kota = kota;
    }

    public String getNama() {
        return nama;
    }

    public String getKota() {
        return kota;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public void info() {
        System.out.println("Nama: " + nama);
        System.out.println("Kota: " + kota);
    }
}
