package DAO;

import java.util.List;

import model.Costumer;

public interface CostumerDao {
	void save(Costumer costumer);
	public List<Costumer> Show();
	public void delete(String id);
	public void update(Costumer costumer);
}
