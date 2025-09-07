package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Laundry Apps");
		lblNewLabel.setForeground(new Color(255, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(111, 91, 256, 85);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("PESANAN");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(111, 173, 189, 75);
		contentPane.add(btnNewButton);
		
		JButton btnLayanan = new JButton("LAYANAN");
		btnLayanan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLayanan.setBounds(333, 173, 189, 75);
		contentPane.add(btnLayanan);
		
		JButton btnPengguna = new JButton("PENGGUNA");
		btnPengguna.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPengguna.setBounds(111, 263, 189, 75);
		contentPane.add(btnPengguna);
		
		JButton btnPelanggan = new JButton("PELANGGAN");
		btnPelanggan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPelanggan.setBounds(551, 173, 189, 75);
		contentPane.add(btnPelanggan);
		
		JButton btnLaporan = new JButton("LAPORAN");
		btnLaporan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLaporan.setBounds(333, 263, 189, 75);
		contentPane.add(btnLaporan);
		
		JButton btnFile = new JButton("FILE");
		btnFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFile.setBounds(551, 263, 189, 75);
		contentPane.add(btnFile);
		
		JButton btnKeluar = new JButton("KELUAR");
		btnKeluar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnKeluar.setBounds(111, 351, 629, 75);
		contentPane.add(btnKeluar);
	}

}
