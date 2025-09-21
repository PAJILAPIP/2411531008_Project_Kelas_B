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

import DAO.ServiceRepo;
import model.Service;
import table.TableService;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServiceFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableService;
	private JTextField txtJenis;
	private JTextField txtStatus;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnCancel;
	private JTextField txtHarga;
	
	ServiceRepo srv = new ServiceRepo();
	List<Service> ls;
	public String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiceFrame frame = new ServiceFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ServiceFrame frame = new ServiceFrame();
				frame.setVisible(true);
				frame.loadTable();
			}
		});
	}
	
	public void reset() {
		txtJenis.setText("");
		txtHarga.setText("");
		txtStatus.setText("");
	}
	
	public void loadTable() {
		ls = srv.Show();
		TableService tu = new TableService(ls);
		tableService.setModel(tu);
		tableService.getTableHeader().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ServiceFrame() {
		setTitle("Service");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 666);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tableService = new JTable();
		tableService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = tableService.getValueAt(tableService.getSelectedRow(), 0).toString();
				txtJenis.setText(tableService.getValueAt(tableService.getSelectedRow(),  1).toString());
				txtHarga.setText(tableService.getValueAt(tableService.getSelectedRow(),  2).toString());
				txtStatus.setText(tableService.getValueAt(tableService.getSelectedRow(),  3).toString());
			}
			
		});
		tableService.setBounds(10, 295, 575, 324);
		contentPane.add(tableService);
		
		JLabel lblNewLabel = new JLabel("Jenis");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(43, 56, 79, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblServicename = new JLabel("Harga");
		lblServicename.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblServicename.setBounds(43, 100, 79, 34);
		contentPane.add(lblServicename);
		
		JLabel lblPassword = new JLabel("Status");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(43, 144, 79, 34);
		contentPane.add(lblPassword);
		
		txtJenis = new JTextField();
		txtJenis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtJenis.setBounds(132, 56, 412, 34);
		contentPane.add(txtJenis);
		txtJenis.setColumns(10);
		
		txtHarga = new JTextField();
		txtHarga.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtHarga.setColumns(10);
		txtHarga.setBounds(132, 100, 412, 34);
		contentPane.add(txtHarga);
		
		txtStatus = new JTextField();
		txtStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStatus.setColumns(10);
		txtStatus.setBounds(132, 144, 412, 34);
		contentPane.add(txtStatus);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Service service = new Service();
			    service.setJenis(txtJenis.getText());
			    service.setHarga(txtHarga.getText());
			    service.setStatus(txtStatus.getText());
			    srv.save(service);
			    reset();
			    loadTable();}
		});
		btnSave.setBackground(new Color(50, 205, 50));
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(132, 210, 85, 34);
		contentPane.add(btnSave);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Service service = new Service();
			    service.setJenis(txtJenis.getText());
			    service.setHarga(txtHarga.getText());
			    service.setStatus(txtStatus.getText());
			    service.setId(id);
			    srv.update(service);
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
					srv.delete(id);
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
