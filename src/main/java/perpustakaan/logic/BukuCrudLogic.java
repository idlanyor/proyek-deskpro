package perpustakaan.logic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class BukuCrudLogic {
    private Connection koneksi;

    public BukuCrudLogic() {
        koneksi();
    }

    private void koneksi() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost/perpustakaan", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void tambahBuku(String judul, String idKategori, String pengarang, String penerbit, String tahunTerbit,
            String isbn, String stok) {
        String query = "INSERT INTO perpus (judul_buku, id_kategori, pengarang, penerbit, tahun_terbit, isbn, stok) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = koneksi.prepareStatement(query)) {
            pstmt.setString(1, judul);
            pstmt.setString(2, idKategori);
            pstmt.setString(3, pengarang);
            pstmt.setString(4, penerbit);
            pstmt.setString(5, tahunTerbit);
            pstmt.setString(6, isbn);
            pstmt.setString(7, stok);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
    }

    public DefaultTableModel getTableModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Judul Buku");
        model.addColumn("Pengarang");
        model.addColumn("Penerbit");
        model.addColumn("Tahun Terbit");

        try (Statement stmt = koneksi.createStatement();
                ResultSet res = stmt.executeQuery("SELECT * FROM buku")) {

            while (res.next()) {
                model.addRow(new Object[] {
                        res.getString("judul_buku"),
                        res.getString("pengarang"),
                        res.getString("penerbit"),
                        res.getString("tahun_terbit") });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return model;
    }

    public void isiComboBoxKategori(JComboBox<String> comboBox) {
        try (Statement stmt = koneksi.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT kategori FROM kategori")) {

            while (rs.next()) {
                comboBox.addItem(rs.getString("kategori"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
