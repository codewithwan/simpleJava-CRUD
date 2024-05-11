package com;

import java.sql.Connection;
import java.sql.DriverManager;

public class KoneksiDB {

    private static Connection koneksi;

    public static Connection getKoneksi() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mysql://localhost:3306/coba"; //url database
            String user = "root"; //user database
            String pass = ""; //password database
            koneksi = DriverManager.getConnection(url, user, pass);
            System.out.println("Berhasil Koneksi");
        } catch (Exception e) {
            System.err.println("koneksi gagal " + e.getMessage());
        }
        return koneksi;
    }

    public static void main(String[] args) {
        //private Connection koneksi2;
        koneksi = getKoneksi();
    }
}
    