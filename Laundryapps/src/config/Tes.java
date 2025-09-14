package config;


import java.sql.Connection;

import javax.swing.JOptionPane;

public class Tes {
	public static void main(String[] args) {
       
        Connection conn = Database.koneksi();
        
        if (conn != null) {
        	JOptionPane.showMessageDialog(null, "Database Berhasil terhubung");
        } else {
        	JOptionPane.showMessageDialog(null, "Database Tidak Terhubung");
        }
	} 
}
