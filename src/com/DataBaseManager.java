package com;

//import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager extends Data {

    private String id;
    private String telp;
    private String belanja;
    private String total;

    public DataBaseManager(String nama, String kota, String poin, String member, String telp, String total) {
        super(nama, kota, poin, member);
        this.telp = telp;
        this.total = total;
    }

    public DataBaseManager(String nama, String kota, String poin, String member, String telp, String belanja, String total) {
        super(nama, kota, poin, member);
        this.telp = telp;
        this.belanja = belanja;
        this.total = total;
    }

    public String getTelp() {
        return telp;
    }

    public String getBelanja() {
        return belanja;
    }

    public String getTotal() {
        return total;
    }

    public String getId() {
        return id;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public void setBelanja(String belanja) {
        this.belanja = belanja;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static List<DataBaseManager> loadDataToTable() {
        List<DataBaseManager> data = new ArrayList<>();
        String sql = "SELECT id, nama, telp, kota, poin, member, total FROM customer ORDER BY id DESC";
        try (Connection conn = KoneksiDB.getKoneksi(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String nama = rs.getString("nama");
                String telp = rs.getString("telp");
                String kota = rs.getString("kota");
                String poin = rs.getString("poin");
                String member = rs.getString("member");
                String total = rs.getString("total");
                DataBaseManager record = new DataBaseManager(nama, kota, poin, member, telp, total);
                record.setId(id);
                data.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static DataBaseManager getDataById(String id) {
        String sql = "SELECT id, nama, telp, kota, poin, member, total FROM customer WHERE id = ?";
        try (Connection conn = KoneksiDB.getKoneksi(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nama = rs.getString("nama");
                    String telp = rs.getString("telp");
                    String kota = rs.getString("kota");
                    String poin = rs.getString("poin");
                    String member = rs.getString("member");
                    String total = rs.getString("total");
                    DataBaseManager record = new DataBaseManager(nama, kota, poin, member, telp, total);
                    record.setId(id);
                    return record;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] getNamaKotaByTelp(String telp) throws SQLException {
        String[] result = new String[2];
        String sql = "SELECT nama, kota FROM customer WHERE telp = ?";
        try (Connection db = KoneksiDB.getKoneksi(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, telp);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result[0] = rs.getString("nama");
                result[1] = rs.getString("kota");
            }
        }
        return result;
    }

    public static String getBelanjaById(String id) {
        String belanja = null;
        String sql = "SELECT belanja FROM customer WHERE id = ?";
        try (Connection conn = KoneksiDB.getKoneksi(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                belanja = rs.getString("belanja");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return belanja;
    }

    public static void Add(DataBaseManager orang) throws SQLException {
        Connection db = KoneksiDB.getKoneksi();
        String checkDuplicateTelpSql = "SELECT id FROM customer WHERE telp = ? AND (nama != ? OR kota != ?)";
        String checkIfExistsSql = "SELECT id, belanja, total FROM customer WHERE nama = ? AND telp = ? AND kota = ?";
        String insertSql = "INSERT INTO customer (nama, kota, poin, member, telp, belanja, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String updateSql = "UPDATE customer SET belanja = ?, poin = ?, member = ?, total = ? WHERE id = ?";

        try (PreparedStatement checkDuplicateTelpStmt = db.prepareStatement(checkDuplicateTelpSql)) {
            checkDuplicateTelpStmt.setString(1, orang.getTelp());
            checkDuplicateTelpStmt.setString(2, orang.getNama());
            checkDuplicateTelpStmt.setString(3, orang.getKota());

            ResultSet rs = checkDuplicateTelpStmt.executeQuery();
            if (rs.next()) {
                throw new SQLException("Nomor telepon sudah digunakan.");
            }
        }

        try (PreparedStatement checkIfExistsStmt = db.prepareStatement(checkIfExistsSql)) {
            checkIfExistsStmt.setString(1, orang.getNama());
            checkIfExistsStmt.setString(2, orang.getTelp());
            checkIfExistsStmt.setString(3, orang.getKota());

            ResultSet rs = checkIfExistsStmt.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                double existingBelanja = rs.getDouble("belanja");
                double newBelanja = existingBelanja + Double.parseDouble(orang.getBelanja());
                int totalData = rs.getInt("total") + 1;

                double newPoin = calculatePoin(newBelanja, getMemberTitle(totalData));

                try (PreparedStatement updateStmt = db.prepareStatement(updateSql)) {
                    updateStmt.setDouble(1, newBelanja);
                    updateStmt.setInt(2, (int) newPoin);
                    updateStmt.setString(3, getMemberTitle(totalData));
                    updateStmt.setInt(4, totalData);
                    updateStmt.setString(5, id);
                    updateStmt.executeUpdate();
                }
            } else {
                int poin = (int) calculatePoin(Double.parseDouble(orang.getBelanja()), getMemberTitle(1));

                try (PreparedStatement insertStmt = db.prepareStatement(insertSql)) {
                    insertStmt.setString(1, orang.getNama());
                    insertStmt.setString(2, orang.getKota());
                    insertStmt.setInt(3, poin);
                    insertStmt.setString(4, getMemberTitle(1));
                    insertStmt.setString(5, orang.getTelp());
                    insertStmt.setDouble(6, Double.parseDouble(orang.getBelanja()));
                    insertStmt.setInt(7, 1);
                    insertStmt.executeUpdate();
                }
            }
        }
    }

    private static double calculatePoin(double belanja, String member) {
        return switch (member) {
            case "Diamond" ->
                belanja / 800;
            case "Gold" ->
                belanja / 900;
            case "Silver" ->
                belanja / 950;
            default ->
                belanja / 1000; // Bronze
        };
    }

    private static String getMemberTitle(int total) {
        if (total >= 15) {
            return "Diamond";
        } else if (total >= 10) {
            return "Gold";
        } else if (total >= 5) {
            return "Silver";
        } else {
            return "Bronze";
        }
    }

    public static void Edit(DataBaseManager orang, String id) throws SQLException {
        Connection db = KoneksiDB.getKoneksi();
        String checkDuplicateTelpSql = "SELECT id FROM customer WHERE telp = ? AND (nama != ? OR kota != ?)";
        String sqledit = "UPDATE customer SET nama = ?, kota = ?, telp = ? WHERE id = ?";

        try (PreparedStatement checkDuplicateTelpStmt = db.prepareStatement(checkDuplicateTelpSql)) {
            checkDuplicateTelpStmt.setString(1, orang.getTelp());
            checkDuplicateTelpStmt.setString(2, orang.getNama());
            checkDuplicateTelpStmt.setString(3, orang.getKota());

            ResultSet rs = checkDuplicateTelpStmt.executeQuery();
            if (rs.next()) {
                throw new SQLException("Nomor telepon sudah digunakan.");
            }
        }

        try (PreparedStatement q = db.prepareStatement(sqledit)) {
            q.setString(1, orang.getNama());
            q.setString(2, orang.getKota());
            q.setString(3, orang.getTelp());
            q.setString(4, id);
            q.executeUpdate();
        }
    }

    public static boolean Del(String id) throws SQLException {
        Connection db = KoneksiDB.getKoneksi();
        String sql = "DELETE FROM customer WHERE id = ?";
        try (PreparedStatement q = db.prepareStatement(sql)) {
            q.setString(1, id);
            int efek = q.executeUpdate();
            return efek > 0;
        }
    }

    public static boolean DelAll() throws SQLException {
        Connection db = KoneksiDB.getKoneksi();
        String sql = "DELETE FROM customer";
        try (PreparedStatement q = db.prepareStatement(sql)) {
            int efek = q.executeUpdate();
            return efek > 0;
        }
    }
}
