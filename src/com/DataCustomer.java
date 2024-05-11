package com;

public class DataCustomer {
    private String kode;
    private String nama;
    private String jenisid;
    private String alamat;
    private String telp;
    private String fax;
    private String kota;
    private String jt;
    private String disc;
    private String Awal;
    private String Piutang;
    private String Bayar;
    private String Akhir;
    private String tgl;

    public DataCustomer(String kode, String nama, String jenisid, String alamat, String telp, String fax, String kota, String jt, String disc, String Awal, String Piutang, String Bayar, String Akhir, String tgl) {
        this.kode = kode;
        this.nama = nama;
        this.jenisid = jenisid;
        this.alamat = alamat;
        this.telp = telp;
        this.fax = fax;
        this.kota = kota;
        this.jt = jt;
        this.disc = disc;
        this.Awal = Awal;
        this.Piutang = Piutang;
        this.Bayar = Bayar;
        this.Akhir = Akhir;
        this.tgl = tgl;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisid() {
        return jenisid;
    }

    public void setJenisid(String jenisid) {
        this.jenisid = jenisid;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getJt() {
        return jt;
    }

    public void setJt(String jt) {
        this.jt = jt;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getAwal() {
        return Awal;
    }

    public void setAwal(String awal) {
        Awal = awal;
    }

    public String getPiutang() {
        return Piutang;
    }

    public void setPiutang(String piutang) {
        Piutang = piutang;
    }

    public String getBayar() {
        return Bayar;
    }

    public void setBayar(String bayar) {
        Bayar = bayar;
    }

    public String getAkhir() {
        return Akhir;
    }

    public void setAkhir(String akhir) {
        Akhir = akhir;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public void info() {
        System.out.println("Kode: " + kode);
        System.out.println("Nama: " + nama);
        System.out.println("Jenis ID: " + jenisid);
        System.out.println("Alamat: " + alamat);
        System.out.println("Telepon: " + telp);
        System.out.println("Fax: " + fax);
        System.out.println("Kota: " + kota);
        System.out.println("Jenis Transaksi: " + jt);
        System.out.println("Diskon: " + disc);
        System.out.println("Awal: " + Awal);
        System.out.println("Piutang: " + Piutang);
        System.out.println("Bayar: " + Bayar);
        System.out.println("Akhir: " + Akhir);
        System.out.println("Tanggal: " + tgl);
    }
}
