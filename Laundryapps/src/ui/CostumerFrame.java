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

import DAO.CostumerRepo;
import model.Costumer;
import table.TableCostumer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CostumerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableCostumer;
	private JTextField txtNama;
	private JTextField txtNomorHp;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnCancel;
	private JTextField txtAlamat;
	
	CostumerRepo csr = new CostumerRepo();
	List<Costumer> ls;
	public String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CostumerFrame frame = new CostumerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				CostumerFrame frame = new CostumerFrame();
				frame.setVisible(true);
				frame.loadTable();
			}
		});
	}
	
	public void reset() {
		txtNama.setText("");
		txtAlamat.setText("");
		txtNomorHp.setText("");
	}
	
	public void loadTable() {
		ls = csr.Show();
		TableCostumer tu = new TableCostumer(ls);
		tableCostumer.setModel(tu);
		tableCostumer.getTableHeader().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public CostumerFrame() {
		setTitle("Costumer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 666);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tableCostumer = new JTable();
		tableCostumer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = tableCostumer.getValueAt(tableCostumer.getSelectedRow(), 0).toString();
				txtNama.setText(tableCostumer.getValueAt(tableCostumer.getSelectedRow(),  1).toString());
				txtAlamat.setText(tableCostumer.getValueAt(tableCostumer.getSelectedRow(),  2).toString());
				txtNomorHp.setText(tableCostumer.getValueAt(tableCostumer.getSelectedRow(),  3).toString());
			}
			
		});
		tableCostumer.setBounds(10, 295, 575, 324);
		contentPane.add(tableCostumer);
		
		JLabel lblNewLabel = new JLabel("Nama");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(43, 56, 79, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblCostumername = new JLabel("Alamat");
		lblCostumername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCostumername.setBounds(43, 100, 79, 34);
		contentPane.add(lblCostumername);
		
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
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Costumer costumer = new Costumer();
				costumer.setNama(txtNama.getText());
				costumer.setAlamat(txtAlamat.getText());
				costumer.setNomorHp(txtNomorHp.getText());
				csr.save(costumer);
				reset();
			}
		});
		btnSave.setBackground(new Color(50, 205, 50));
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(132, 210, 85, 34);
		contentPane.add(btnSave);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Costumer costumer = new Costumer();
				costumer.setNama(txtNama.getText());
				costumer.setAlamat(txtAlamat.getText());
				costumer.setNomorHp(txtNomorHp.getText());
				costumer.setId(id);
				csr.update(costumer);
				reset();
				loadTable();
			}
		});
		
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdate.setBackground(new Color(65, 105, 225));
		btnUpdate.setBounds(227, 210, 99, 34);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id != null) {
					csr.delete(id);
					reset();
					loadTable();
				}else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBackground(Color.RED);
		btnDelete.setBounds(336, 210, 99, 34);
		contentPane.add(btnDelete);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBackground(Color.YELLOW);
		btnCancel.setBounds(445, 210, 99, 34);
		contentPane.add(btnCancel);
		
	}
}
