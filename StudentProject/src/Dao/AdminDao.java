package Dao;

import java.util.List;

import entity.Admin;

public interface AdminDao {
	public List<Admin> AdminList();
	public boolean insert(Admin admin);

}
