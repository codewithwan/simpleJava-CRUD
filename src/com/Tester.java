package com;

import java.sql.SQLException;
import java.util.Random;
import javax.swing.JOptionPane;

public class Tester extends javax.swing.JFrame {

    public Tester() {
        initComponents();
    }

    private void addData() {
        // Tambah Data
        try {
            int numData = Integer.parseInt(jTextField1.getText());
            for (int i = 0; i < numData; i++) {
                String nama = getRandomName();
                String telp = "08" + getRandomTenDigitNumber();
                String kota = getRandomCity();
                DataBaseManager.Add(new DataBaseManager(nama, kota, telp));
                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan: " + nama + ", " + telp + ", " + kota);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid untuk jumlah data!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menambahkan data: " + e.getMessage());
        }
    }

    private void deleteRandomData() {
        // Hapus Data Acak
        try {
            int numToDelete = Integer.parseInt(jTextField1.getText());
            for (int i = 0; i < numToDelete; i++) {
                boolean berhasil = DataBaseManager.DelRandom();
                if (berhasil) {
                    JOptionPane.showMessageDialog(this, "Data acak berhasil dihapus!");
                } else {
                    JOptionPane.showMessageDialog(this, "Tidak ada data yang dihapus.");
                    break; // Hentikan penghapusan jika tidak ada data yang tersisa
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid untuk jumlah data yang ingin dihapus!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus data: " + e.getMessage());
        }
    }

    private void deleteAllData() {
        // Hapus Semua Data
        int confirm = JOptionPane.showConfirmDialog(this, "Anda yakin ingin menghapus semua data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                boolean berhasil = DataBaseManager.DelAll();
                if (berhasil) {
                    JOptionPane.showMessageDialog(this, "Semua data berhasil dihapus!");
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus data.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus data: " + e.getMessage());
            }
        }
    }

    private static String getRandomName() {
        String[] NAMES = {"Michael", "Christopher", "Jessica", "Matthew", "Ashley", "Jennifer", "Joshua", "Amanda", "Daniel", "David",
            "James", "Robert", "John", "Joseph", "Andrew", "Ryan", "Brandon", "Jason", "Justin", "Sarah",
            "William", "Jonathan", "Stephanie", "Brian", "Nicole", "Nicholas", "Anthony", "Heather", "Eric", "Elizabeth",
            "Adam", "Megan", "Kevin", "Steven", "Thomas", "Timothy", "Christina", "Kyle", "Rachel", "Laura",
            "Lauren", "Amber", "Brittany", "Danielle", "Emily", "Melissa", "Rebecca", "Kimberly", "Mark", "Jeffrey",
            "Amy", "Crystal", "Michelle", "Jeremy", "Benjamin", "Samantha", "Aaron", "Kelly", "Jamie", "Patrick",
            "Sean", "Nathan", "Sara", "Brandi", "Zachary", "Katherine", "Andrea", "Gregory", "Scott", "Erica",
            "Mary", "Travis", "Lisa", "Kenneth", "Lindsay", "Kristen", "Jose", "Kristin", "Erin", "Jesse",
            "Paul", "Angela", "Shannon", "Tiffany", "Cody", "Stephen", "Heidi", "Chelsea", "Allison", "Crystal",
            "Cassandra", "Tara", "Kristina", "Anna", "Vanessa", "Jared", "Courtney", "Bryan", "Katie", "Lindsey",
            "Jordan", "Alicia", "Peter", "Catherine", "Caitlin", "Hannah", "Julie", "April", "Derek", "Christine"
        };

        Random random = new Random();
        int index = random.nextInt(NAMES.length);
        return NAMES[index];
    }

    private static String getRandomTenDigitNumber() {
        Random random = new Random();
        long number = (long) (random.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(number);
    }

    private static String getRandomCity() {
        String[] CITIES = {"Jakarta", "Surabaya", "Bandung", "Medan", "Semarang", "Makassar", "Palembang", "Tangerang", "Depok", "Batam",
            "Bekasi", "Padang", "Bogor", "Malang", "Pekanbaru", "Bandar Lampung", "Denpasar", "Surakarta", "Banjarmasin", "Jambi",
            "Yogyakarta", "Pontianak", "Manado", "Balikpapan", "Ambon", "Samarinda", "Palu", "Cirebon", "Purwokerto", "Tasikmalaya",
            "Serang", "Banda Aceh", "Tegal", "Kupang", "Binjai", "Pematangsiantar", "Palangkaraya", "Probolinggo", "Pasuruan", "Salatiga",
            "Batam", "Lubuklinggau", "Cimahi", "Mojokerto", "Magelang", "Batu", "Tarakan", "Singkawang", "Prabumulih", "Bangka",
            "Padangsidimpuan", "Metro", "Tanjungpinang", "Bontang", "Kendari", "Parepare", "Lhokseumawe", "Ternate", "Bitung", "Palopo",
            "Bukittinggi", "Pagar Alam", "Tomohon", "Ambarawa", "Tebing Tinggi", "Tidore", "Palopo", "Pangkal Pinang", "Sukabumi", "Banda Aceh",
            "Dumai", "Madiun", "Bukittinggi", "Kendari", "Tual", "Sorong", "Merauke", "Cilacap", "Bengkulu", "Sawahlunto",
            "Pare-pare", "Pematangsiantar", "Sijunjung", "Tarakan", "Waingapu", "Blitar", "Kepahiang", "Padangpanjang", "Padang Sidempuan", "Pangkalpinang",
            "Parepare", "Pemalang", "Pematang Siantar", "Sungailiat", "Tabanan", "Tangerang Selatan", "Tanjungbalai", "Tanjungpinang", "Tapaktuan", "Tarakan",
            "Tasikmalaya", "Tebing Tinggi", "Tegal", "Tidore Kepulauan", "Tomohon", "Tual", "Banyuwangi", "Barru", "Takalar", "Lamongan",
            "Blitar", "Lhokseumawe", "Sukabumi", "Sungai Penuh", "Sungailiat", "Palopo", "Pangkajene", "Pasuruan", "Lamongan", "Langsa",
            "Biak", "Muna", "Buton", "Kefamenanu", "Rote Ndao", "Larantuka", "Ende", "Bima", "Sumbawa Besar", "Dompu"
        };

        Random random = new Random();
        int index = random.nextInt(CITIES.length);
        return CITIES[index];
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("DataBase Tester");

        jLabel2.setText("Value :");

        jButton1.setText("Add Dummy Data");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Delete Random Data");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Delete All Data");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(33, 33, 33)
                        .addComponent(jButton2)
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(145, 145, 145))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(28, 28, 28)
                .addComponent(jButton3)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jTextField1});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        deleteRandomData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        deleteAllData();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tester().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
