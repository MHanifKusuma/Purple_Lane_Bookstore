package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import core.model.Model;

public class UserModel extends Model{
	
	private int roleId;
	private String username, password;
	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public UserModel() {
		// TODO Auto-generated constructor stub
		this.tablename = "users";
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public void insert() {
		String query = String.format("INSERT INTO %s VALUES(null, ?, ?, ?)", tablename);
		
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setInt(1, roleId);
			ps.setString(2, username);
			ps.setString(3, password);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet checkUser(String username) {
		ResultSet rs = null;
		String query = "SELECT * FROM users WHERE Username LIKE ?";
		
		PreparedStatement pQuery = con.prepareStatement(query);
		
		try {
			pQuery.setString(1, username);
			rs = pQuery.executeQuery();
			
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
