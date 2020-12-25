package view.customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import core.view.View;

public class LoginView extends View{
	
	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel passLbl, passValue, nameLbl, nameValue;
	JTextField nameTxt, passTxt;
	JButton login, register;
	Vector<Vector<String>> data;
	Vector<String> detail, header;

	public LoginView() {
		super();
		this.height = 150;
		this.width = 350;
	}

	@Override
	public void initialize() {
		
		top = new JPanel();
		mid = new JPanel(new GridLayout(2,2));
		bot = new JPanel();
	
		nameLbl = new JLabel("Username: ");
		passLbl = new JLabel("password: ");
		nameTxt = new JTextField();
		passTxt = new JTextField();
		
		
		login = new JButton("Login");
		register = new JButton("Register");
	}

	@Override
	public void addComponent() {
		
		mid.add(nameLbl);
		mid.add(nameTxt);
		mid.add(passLbl);
		mid.add(passTxt);
		
		bot.add(login);
		bot.add(register);
	
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
	}

}
