package com;

//import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager extends Data {

    private String telp;
    private String id;

    public DataBaseManager(String nama, String kota, String telp) {
        super(nama, kota);
        this.telp = telp;
    }

    public String getTelp() {
        return telp;
    }

    public String getId() {
        return id;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static List<DataBaseManager> loadDataToTable() {
        List<DataBaseManager> data = new ArrayList<>();
        String sql = "SELECT id, nama, telp, kota FROM cobaajadulu ORDER BY id DESC";
        try (Connection conn = KoneksiDB.getKoneksi(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String nama = rs.getString("nama");
                String telp = rs.getString("telp");
                String kota = rs.getString("kota");
                DataBaseManager record = new DataBaseManager(nama, kota, telp);
                record.setId(id);
                data.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void Add(DataBaseManager orang) throws SQLException {
        Connection db = KoneksiDB.getKoneksi();
        String sql = "INSERT INTO cobaajadulu (nama, kota, telp) VALUES (?, ?, ?)";
        try (PreparedStatement q = db.prepareStatement(sql)) {
            q.setString(1, orang.getNama());
            q.setString(2, orang.getKota());
            q.setString(3, orang.getTelp());
            q.executeUpdate();
        }
    }

    public static void Edit(DataBaseManager orang, String id) throws SQLException {
        Connection db = KoneksiDB.getKoneksi();
        String sqledit = "UPDATE cobaajadulu SET nama = ?, kota = ?, telp = ? WHERE id = ?";
        try (PreparedStatement q = db.prepareStatement(sqledit)) {
            q.setString(1, orang.getNama());
            q.setString(2, orang.getKota());
            q.setString(3, orang.getTelp());
            q.setString(4, Global.id); // Menggunakan ID dari Global
            q.executeUpdate();
        }
    }

    public static boolean Del(String id) throws SQLException {
        Connection db = KoneksiDB.getKoneksi();
        String sql = "DELETE FROM cobaajadulu WHERE id = ?";
        try (PreparedStatement q = db.prepareStatement(sql)) {
            q.setString(1, id);
            int efek = q.executeUpdate();
            return efek > 0;
        }
    }

    public static boolean DelAll() throws SQLException {
        Connection db = KoneksiDB.getKoneksi();
        String sql = "DELETE FROM cobaajadulu";
        try (PreparedStatement q = db.prepareStatement(sql)) {
            int efek = q.executeUpdate();
            return efek > 0;
        }
    }

    public static boolean DelRandom() throws SQLException {
        Connection db = KoneksiDB.getKoneksi();
        String sql = "DELETE FROM cobaajadulu WHERE id IN (SELECT id FROM cobaajadulu ORDER BY RAND() LIMIT 1)";
        try (PreparedStatement q = db.prepareStatement(sql)) {
            int efek = q.executeUpdate();
            return efek > 0;
        }
    }
}
