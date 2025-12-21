package ui;

import dao.TransaksiRepo;
import error.ValidationException;
import model.Pemasukan;
import model.Transaksi;
import model.TransaksiBuilder;
import util.ValidationUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainGui extends JFrame {

    private JTextField txtTanggal, txtNominal, txtDeskripsi;
    private JComboBox<String> cbJenis, cbKategori;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblTotal;

    private TransaksiRepo repo;
    private int selectedId = -1;
    private int userId = 1;

    public MainGui() {
        repo = new TransaksiRepo();
        initUI();
        refreshTable();
    }

    private void initUI() {
        setTitle("Personal Finance Manager");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        add(headerPanel(), BorderLayout.NORTH);
        add(formPanel(), BorderLayout.WEST);
        add(tablePanel(), BorderLayout.CENTER);
    }

    private JPanel headerPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(15, 20, 15, 20));
        panel.setBackground(new Color(33, 150, 243));

        JLabel title = new JLabel("Personal Finance Manager");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        
        lblTotal = new JLabel("Saldo: Rp 0");
        lblTotal.setForeground(Color.WHITE);
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));

        panel.add(lblTotal, BorderLayout.EAST);

        panel.add(title, BorderLayout.WEST);
        return panel;
    }

    private JPanel formPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setPreferredSize(new Dimension(280, 0));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);

        txtTanggal = new JTextField();
        txtNominal = new JTextField();
        txtDeskripsi = new JTextField();

        cbJenis = new JComboBox<>(new String[]{"PEMASUKAN", "PENGELUARAN"});
        cbKategori = new JComboBox<>(new String[]{"Makan", "Transport", "Gaji", "Lainnya"});

        panel.add(label("Tanggal (YYYY-MM-DD)"));
        panel.add(txtTanggal);
        panel.add(label("Nominal"));
        panel.add(txtNominal);
        panel.add(label("Jenis"));
        panel.add(cbJenis);
        panel.add(label("Kategori"));
        panel.add(cbKategori);
        panel.add(label("Deskripsi"));
        panel.add(txtDeskripsi);
        panel.add(buttonPanel());

        return panel;
    }

    private JLabel label(String text) {
        JLabel lb = new JLabel(text);
        lb.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return lb;
    }

    private JPanel buttonPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 8, 8));
        panel.setBackground(Color.WHITE);

        JButton btnAdd = createButton("Tambah", new Color(76, 175, 80));
        JButton btnUpdate = createButton("Update", new Color(255, 193, 7));
        JButton btnDelete = createButton("Hapus", new Color(244, 67, 54));
        JButton btnReset = createButton("Reset", new Color(96, 125, 139));

        btnAdd.addActionListener(e -> {
            try {
                Transaksi tr = buildTransaksi();
                ValidationUtil.validate(tr);
                repo.save(tr);
                refreshTable();
                resetForm();
                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan");
            } catch (Exception ex) {
                showError(ex);
            }
        });
        
        btnUpdate.addActionListener(e -> {
            if (selectedId == -1) {
                JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu");
                return;
            }
            try {
                Transaksi tr = buildTransaksi();
                tr.setId(selectedId);
                ValidationUtil.validate(tr);
                repo.update(tr);
                refreshTable();
                resetForm();
                JOptionPane.showMessageDialog(this, "Data berhasil diperbarui");
            } catch (Exception ex) {
                showError(ex);
            }
        });

        btnDelete.addActionListener(e -> {
            if (selectedId == -1) {
                JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(
                    this, "Yakin ingin menghapus data?",
                    "Konfirmasi", JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                repo.delete(selectedId);
                refreshTable();
                resetForm();
            }
        });

        btnReset.addActionListener(e -> resetForm());

        panel.add(btnAdd);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnReset);

        return panel;
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return btn;
    }
    
    private JScrollPane tablePanel() {
        String[] col = {"ID", "Tanggal", "Nominal", "Jenis", "Kategori", "Deskripsi"};
        tableModel = new DefaultTableModel(col, 0);
        table = new JTable(tableModel);

        table.setRowHeight(24);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                selectedId = Integer.parseInt(table.getValueAt(row, 0).toString());
                txtTanggal.setText(table.getValueAt(row, 1).toString());
                txtNominal.setText(table.getValueAt(row, 2).toString());
                cbJenis.setSelectedItem(table.getValueAt(row, 3).toString());
                cbKategori.setSelectedItem(table.getValueAt(row, 4).toString());
                txtDeskripsi.setText(table.getValueAt(row, 5).toString());
                System.out.println("Selected ID: " + selectedId);
            }
        });

        return new JScrollPane(table);
    }

    private Transaksi buildTransaksi() {
        return new TransaksiBuilder()
                .setTanggal(txtTanggal.getText())
                .setNominal(Double.parseDouble(txtNominal.getText()))
                .setKategori(cbKategori.getSelectedItem().toString())
                .setDeskripsi(txtDeskripsi.getText())
                .setUserId(userId)
                .setIsIncome(cbJenis.getSelectedItem().toString().equals("PEMASUKAN"))
                .build();
    }

    private void refreshTable() {
    	  tableModel.setRowCount(0);
    	    double total = 0;

    	    for (Transaksi tr : repo.show(userId)) {
    	        boolean isIncome = tr instanceof Pemasukan;

    	        if (isIncome) {
    	            total += tr.getNominal();
    	        } else {
    	            total -= tr.getNominal();
    	        }

    	        Object[] row = {
    	                tr.getId(),
    	                tr.getTanggal(),
    	                tr.getNominal(),
    	                isIncome ? "PEMASUKAN" : "PENGELUARAN",
    	                tr.getKategori(),
    	                tr.getDeskripsi()
    	        };
    	        tableModel.addRow(row);
    	    }

    	    lblTotal.setText("Saldo: Rp " + String.format("%,.0f", total));
    }

    private void resetForm() {
        txtTanggal.setText("");
        txtNominal.setText("");
        txtDeskripsi.setText("");
        cbJenis.setSelectedIndex(0);
        cbKategori.setSelectedIndex(0);
        selectedId = -1;
    }

    private void showError(Exception ex) {
        String msg = ex instanceof ValidationException ? ex.getMessage() : "Terjadi kesalahan";
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // ================= MAIN =================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGui().setVisible(true));
    }
}
