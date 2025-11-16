package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DAO.CustomerRepo;
import model.Customer;
import model.CustomerBuilder;
import table.TableCustomer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableCustomer;
	private JTextField txtNama;
	private JTextField txtNomorHp;
	private JButton btnCancel;
	private JTextField txtAlamat;
	private JTextField txtEmail;
	
	CustomerRepo customerRepo = new CustomerRepo();
	List<Customer> ls;
	public String id;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerFrame frame = new CustomerFrame();
					frame.setVisible(true);
					frame.loadTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void reset() {
		txtNama.setText("");
		txtAlamat.setText("");
		txtNomorHp.setText("");
		txtEmail.setText("");
	}
	
	public void loadTable() {
		ls = customerRepo.Show();
		TableCustomer tu = new TableCustomer(ls);
		tableCustomer.setModel(tu);
		tableCustomer.getTableHeader().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public CustomerFrame() {
		setTitle("Customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 666);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tableCustomer = new JTable();
		tableCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = tableCustomer.getValueAt(tableCustomer.getSelectedRow(), 0).toString();
				txtNama.setText(tableCustomer.getValueAt(tableCustomer.getSelectedRow(),  1).toString());
				txtEmail.setText(tableCustomer.getValueAt(tableCustomer.getSelectedRow(),  2).toString());
				txtAlamat.setText(tableCustomer.getValueAt(tableCustomer.getSelectedRow(),  3).toString());
				txtNomorHp.setText(tableCustomer.getValueAt(tableCustomer.getSelectedRow(),  4).toString());
			}
			
		});
		tableCustomer.setBounds(10, 295, 575, 324);
		contentPane.add(tableCustomer);
		
		JLabel lblNewLabel = new JLabel("Nama");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(43, 56, 79, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblCustomername = new JLabel("Alamat");
		lblCustomername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCustomername.setBounds(43, 100, 79, 34);
		contentPane.add(lblCustomername);
		
		JLabel lblPassword = new JLabel("Nomor HP");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(43, 144, 79, 34);
		contentPane.add(lblPassword);
		
		txtNama = new JTextField();
		txtNama.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNama.setBounds(132, 56, 412, 34);
		contentPane.add(txtNama);
		txtNama.setColumns(10);
		
		txtAlamat = new JTextField();
		txtAlamat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAlamat.setColumns(10);
		txtAlamat.setBounds(132, 100, 412, 34);
		contentPane.add(txtAlamat);
		
		txtNomorHp = new JTextField();
		txtNomorHp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNomorHp.setColumns(10);
		txtNomorHp.setBounds(132, 144, 412, 34);
		contentPane.add(txtNomorHp);
		
		JButton btnSave = new JButton("Simpan");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer customer = new CustomerBuilder()
						.setNama(txtNama.getText())
						.setAlamat(txtAlamat.getText())
						.setNomorHp(txtNomorHp.getText())
						.setEmail(txtEmail.getText())
						.build();
				customerRepo.save(customer);
				reset();
				loadTable();
			}
		});
		btnSave.setBackground(new Color(50, 205, 50));
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(175, 238, 85, 34);
		contentPane.add(btnSave);
		
		btnCancel = new JButton("Batal");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBackground(Color.YELLOW);
		btnCancel.setBounds(460, 238, 86, 34);
		contentPane.add(btnCancel);
		
		JLabel lblEmai = new JLabel("Email");
		lblEmai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmai.setBounds(43, 188, 79, 34);
		contentPane.add(lblEmai);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBounds(132, 188, 412, 34);
		contentPane.add(txtEmail);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id != null) {
					customerRepo.delete(id);
					reset();
					loadTable();
					} else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");
					}
			}
		});
		btnHapus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnHapus.setBackground(new Color(255, 0, 0));
		btnHapus.setBounds(270, 238, 85, 34);
		contentPane.add(btnHapus);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer customer = new CustomerBuilder()
						.setNama(txtNama.getText())
						.setEmail(txtEmail.getText())
						.setAlamat(txtAlamat.getText())
						.setNomorHp(txtNomorHp.getText())
						.setId(id)
						.build();
				customerRepo.update(customer);
				reset();
				loadTable();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdate.setBackground(new Color(0, 128, 255));
		btnUpdate.setBounds(365, 238, 85, 34);
		contentPane.add(btnUpdate);
		
	}
}
