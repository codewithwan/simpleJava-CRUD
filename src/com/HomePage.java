package com;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.MouseEvent;
import java.util.List;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.table.TableRowSorter;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;

public final class HomePage extends javax.swing.JFrame {

    private JPopupMenu popupMenu;
    private JMenuItem editMenuItem;
    private JMenuItem deleteMenuItem;
    private String filterCriteria = "Terbaru";
    private int currentPage = 1;
    private int totalRows;
    private int totalPages;
    private boolean isDarkMode = false;

    public HomePage() {
        try {
            if(isDarkMode) {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } else {
                UIManager.setLookAndFeel(new FlatLightLaf());
            }
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Tidak dapat menerapkan tema FlatLaf: " + ex.getMessage());
        }
        initComponents();
        loadDataToTable();
        createPopupMenu();
        centerTableCells();
        setResizable(true);
    }

    public void loadDataToTable() {
        DefaultTableModel model = (DefaultTableModel) Tabel.getModel();
        model.setRowCount(0);
        List<DataBaseManager> data = DataBaseManager.loadDataToTable();

        switch (filterCriteria) {
            case "Terbaru" -> {
            }
            case "Terlama" ->
                Collections.reverse(data);
            case "A-Z" ->
                Collections.sort(data, Comparator.comparing(DataBaseManager::getNama));
            default -> {
            }
        }
        totalRows = data.size();
//        int rowHeight = Tabel.getRowHeight();
        int visibleRows = (Tabel.getHeight() - 20) / 20; 
        int pageSize = Math.max(20, visibleRows); 
//        System.out.println(visibleRows);

        totalPages = (int) Math.ceil((double) totalRows / pageSize);

        int start = (currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, totalRows);

        for (int i = start; i < end; i++) {
            DataBaseManager record = data.get(i);
            Object[] row = {String.valueOf(record.getId()), record.getNama(), record.getTelp(), record.getKota()};
            model.addRow(row);
        }

        jTextField1.setText(String.valueOf(currentPage));
    }

    public void createPopupMenu() {
        popupMenu = new JPopupMenu();
        editMenuItem = new JMenuItem("Edit");
        deleteMenuItem = new JMenuItem("Delete");

        editMenuItem.addActionListener(e -> {
            int selectedRow = Tabel.getSelectedRow();
            if (selectedRow != -1) {
                editData(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diperbarui terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });

        deleteMenuItem.addActionListener(e -> {
            int selectedRow = Tabel.getSelectedRow();
            if (selectedRow != -1) {
                deleteData(selectedRow);
            }
        });

        popupMenu.add(editMenuItem);
        popupMenu.add(deleteMenuItem);
    }

    private void editData(int selectedRow) {
        String idString = (String) Tabel.getValueAt(selectedRow, 0);
        int id = Integer.parseInt(idString);
        String nama = (String) Tabel.getValueAt(selectedRow, 1);
        String telp = (String) Tabel.getValueAt(selectedRow, 2);
        String kota = (String) Tabel.getValueAt(selectedRow, 3);

        EditData editData = new EditData(id, nama, telp, kota, this);

        JDialog dialog = new JDialog(this, "Perbarui Data", true);
        dialog.getContentPane().add(editData);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        loadDataToTable();
    }

    private void deleteData(int selectedRow) {
        int option = JOptionPane.showConfirmDialog(HomePage.this, "Apakah Anda yakin ingin menghapus item ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) Tabel.getModel();
            int modelRow = Tabel.convertRowIndexToModel(selectedRow);
            String id = model.getValueAt(modelRow, 0).toString();
            try {
                boolean deleted = DataBaseManager.Del(id);
                if (deleted) {
                    model.removeRow(modelRow);
                    loadDataToTable();
                    JOptionPane.showMessageDialog(this, "Item berhasil dihapus.");
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus item.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus item: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void performSearch(String query) {
        DefaultTableModel model = (DefaultTableModel) Tabel.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        Tabel.setRowSorter(sorter);

        if (query.isEmpty()) {
            sorter.setRowFilter(null);
            return;
        }

        String[] parts = query.split(":");
        if (parts.length == 2) {
            String column = parts[0].trim().toLowerCase();
            String value = parts[1].trim();

            switch (column) {
                case "id" ->
                    sorter.setRowFilter(RowFilter.regexFilter(value, 0));
                case "nama" ->
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + value, 1));
                case "telp" ->
                    sorter.setRowFilter(RowFilter.regexFilter(value, 2));
                case "kota" ->
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + value, 3));
                default ->
                    JOptionPane.showMessageDialog(this, "Syntax pencarian tidak valid.\nid, nama, telp, dan kota", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        }
    }

    private void centerTableCells() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < Tabel.getColumnCount(); i++) {
            Tabel.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        Title = new javax.swing.JLabel();
        Search = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabel = new javax.swing.JTable();
        Filter = new javax.swing.JComboBox<>();
        back = new javax.swing.JButton();
        next = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        Add = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        Delete = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.SystemColor.controlLtHighlight);
        setMinimumSize(new java.awt.Dimension(866, 588));

        Title.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        Title.setText("Data Customer");

        Search.setMinimumSize(new java.awt.Dimension(68, 22));
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        Tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nama", "No. Hp", "Kota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabel.setFocusable(false);
        Tabel.setGridColor(new java.awt.Color(51, 51, 51));
        Tabel.setShowHorizontalLines(true);
        Tabel.setShowVerticalLines(true);
        Tabel.getTableHeader().setReorderingAllowed(false);
        Tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tabel);
        if (Tabel.getColumnModel().getColumnCount() > 0) {
            Tabel.getColumnModel().getColumn(0).setPreferredWidth(15);
            Tabel.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        Filter.setBackground(new java.awt.Color(255, 255, 255));
        Filter.setForeground(new java.awt.Color(51, 51, 51));
        Filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Terbaru", "Terlama", "A-Z" }));
        Filter.setFocusable(false);
        Filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterActionPerformed(evt);
            }
        });

        back.setBackground(new java.awt.Color(102, 102, 102));
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setText("<");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        next.setBackground(new java.awt.Color(102, 102, 102));
        next.setForeground(new java.awt.Color(255, 255, 255));
        next.setText(">");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setText("1");
        jTextField1.setFocusable(false);

        Add.setBackground(new java.awt.Color(102, 255, 102));
        Add.setForeground(new java.awt.Color(51, 51, 51));
        Add.setText("Tambah Data");
        Add.setFocusable(false);
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        Update.setBackground(new java.awt.Color(102, 102, 255));
        Update.setForeground(new java.awt.Color(51, 51, 51));
        Update.setText("Perbarui Data");
        Update.setFocusable(false);
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        Delete.setBackground(new java.awt.Color(255, 102, 102));
        Delete.setForeground(new java.awt.Color(51, 51, 51));
        Delete.setText("Hapus Data");
        Delete.setFocusable(false);
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(next))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Search, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addComponent(Add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addGap(28, 28, 28))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Add, Delete, Update});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {back, jTextField1, next});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(next)
                    .addComponent(back)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Add, Delete, Filter, Search, Update});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        AddData tambahDataPanel = new AddData(this);

        JDialog dialog = new JDialog(this, "Tambah Data", true);
        dialog.getContentPane().add(tambahDataPanel);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }//GEN-LAST:event_AddActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        String query = Search.getText();
        performSearch(query);
    }//GEN-LAST:event_SearchActionPerformed

    private void TabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelMouseClicked
        int row = Tabel.rowAtPoint(evt.getPoint());
        if (evt.getButton() == MouseEvent.BUTTON3 && row != -1) {
            Tabel.setRowSelectionInterval(row, row);
            popupMenu.show(Tabel, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_TabelMouseClicked

    private void FilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterActionPerformed
        filterCriteria = (String) Filter.getSelectedItem();
        loadDataToTable();
    }//GEN-LAST:event_FilterActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        int selectedRow = Tabel.getSelectedRow();
        if (selectedRow != -1) {
            editData(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diperbarui terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_UpdateActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        int selectedRow = Tabel.getSelectedRow();
        if (selectedRow != -1) {
            deleteData(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_DeleteActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        if (currentPage > 1) {
            currentPage--;
            loadDataToTable();
        }
    }//GEN-LAST:event_backActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        if (currentPage < totalPages) {
            currentPage++;
            loadDataToTable();
        }
    }//GEN-LAST:event_nextActionPerformed

    public static void main(String args[]) {
        FlatLaf.registerCustomDefaultsSource("com");
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HomePage window = new HomePage();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton Delete;
    private javax.swing.JComboBox<String> Filter;
    private javax.swing.JTextField Search;
    private javax.swing.JTable Tabel;
    private javax.swing.JLabel Title;
    private javax.swing.JButton Update;
    private javax.swing.JButton back;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton next;
    // End of variables declaration//GEN-END:variables
}
