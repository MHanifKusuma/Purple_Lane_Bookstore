package view.customer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import core.view.View;

public class RegisterView extends View{
	
	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel passLbl, passValue, nameLbl, nameValue;
	JTextField nameTxt, passTxt;
	JButton register;
	Vector<Vector<String>> data;
	Vector<String> detail, header;

	public RegisterView() {
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
		
		
		register = new JButton("Register");
		
	}

	@Override
	public void addComponent() {
		mid.add(nameLbl);
		mid.add(nameTxt);
		mid.add(passLbl);
		mid.add(passTxt);
		
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
