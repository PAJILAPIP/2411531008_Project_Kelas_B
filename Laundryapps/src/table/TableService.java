package table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Service;

public class TableService extends AbstractTableModel {
	List<Service> ls;
    private String[] columnNames = {"ID", "Jenis", "Harga", "Status"};

    public TableService(List<Service> ls) {
        this.ls = ls;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return ls.size();
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        switch (columnIndex) {
            case 0:
                return ls.get(rowIndex).getId();
            case 1:
                return ls.get(rowIndex).getJenis();
            case 2:
                return ls.get(rowIndex).getHarga();
            case 3:
                return ls.get(rowIndex).getStatus();
            default:
                return null;
        }
    }
}
