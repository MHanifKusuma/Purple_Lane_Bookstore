package controller;

import java.nio.channels.ShutdownChannelGroupException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.plaf.basic.BasicTreeUI.CellEditorHandler;

import core.controller.Controller;
import core.model.Model;
import core.view.View;
import model.UserModel;

public class UserController extends Controller{
	
	private UserModel user;
	private static UserController controller;
	

	public UserController() {
		user = new UserModel();
	}

	public static UserController getInstance() {
		return controller = (controller == null) ? new UserController() : controller;
	}
	
	public int AssignRole(String role) {
		int roleId = 0;
		
		if(role.equals("admin")) {
			roleId = 2;
		}
		else if (role.equalsIgnoreCase("promotion")) {
			roleId = 3;
		}
		else if (role.equalsIgnoreCase("manager")) {
			roleId = 4;
		}
		else {
			roleId = -1;
		}
		
		
		return roleId;
	}
	
	public void insert(int roleId, String username, String password) {
		UserModel uModel = new UserModel();
		
		uModel.setRoleId(roleId);
		uModel.setUsername(username);
		uModel.setPassword(password);
		
		uModel.insert();
	}
	
	public Integer registerValidator (int roleId, String username, String password) {
		int error = 0;
		
		if(username.equals("") || password.equals("")) {
			return error = 1;
		}
		
		ResultSet checkUsers = user.checkUser(username);
		
		try {
			if(checkUsers.next()) {
				return error = 2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return error;
	}
	
	public Integer AuthenticateUser(String username, String password) {
		//Dalam method autentikasi ini, akun user akan dicari ke dalam database dan me-return error message jika terjadi masalah
		//method ini juga akan memberikan otorisasi user yang berhasil login dengan mengecek role id nya dalam database.
		
		int error = 400; // me-return error jika validasi gagal
		int role = 200; // me-return role jika validasi sukses
		
		ResultSet checkUser = user.checkUser(username);
		
		try {
			if(checkUser.next()) {
				if(!checkUser.getString("Password").equals(password)) {
					return error = 401; //error jika password yang dimasukkan salah
				}
				
				if(checkUser.getInt("RoleId") == 1) {
					return role = 201; //role untuk member
				}
				else if (checkUser.getInt("RoleId") == 2) {
					return role = 202; //role untuk admin
				}
				else if (checkUser.getInt("RoleId") == 3) {
					return role = 203; //role untuk promotion
				}
				else if (checkUser.getInt("RoleId") == 4) {
					return role = 204; // role untuk manager
				}
			}
			else
			{
				return error = 402; //error jika akun tidak ditemukan
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return error;
	}

	@Override
	public View view() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		
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
