package com;

public class Data {

    private String nama;
    private String kota;
    private String poin;
    private String member;

    public Data(String nama, String kota, String poin, String member) {
        this.nama = nama;
        this.kota = kota;
        this.poin = poin;
        this.member = member;
    }

    public String getNama() {
        return nama;
    }

    public String getKota() {
        return kota;
    }

    public String getPoin() {
        return poin;
    }

    public String getMember() {
        return member;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public void info() {
        System.out.println("Nama: " + nama);
        System.out.println("Kota: " + kota);
        System.out.println("Poin: " + poin);
        System.out.println("Member: " + member);
    }
}
