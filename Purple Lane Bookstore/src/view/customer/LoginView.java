package view.customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.UserController;
import core.view.View;
import model.UserModel;
import view.manager.HireStaffPage;

public class LoginView extends View{
	
	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel passLbl, passValue, nameLbl, nameValue, pageHeader;
	JTextField nameTxt ;
	JPasswordField passTxt;
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
		passTxt = new JPasswordField();
		
		pageHeader = new JLabel("Login");
		pageHeader.setFont(new Font("Serif", Font.PLAIN, 18));
		
		login = new JButton("Login");
		register = new JButton("Register");
	}

	@Override
	public void addComponent() {
		top.add(pageHeader);
		
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
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = nameTxt.getText();
				String pass = passTxt.getText();
				
				int AttemptLogin = UserController.getInstance().AuthenticateUser(name, pass);
				
				if(AttemptLogin == 401) {
					JOptionPane.showMessageDialog(null, "Wrong password!");
				}
				else if (AttemptLogin == 402) {
					JOptionPane.showMessageDialog(null, "User not found!");
				}
				else if (AttemptLogin == 201) {
					UserModel user = UserController.getInstance().setLoggedInUser(name);
					
					new ProductPage(user).showForm();
				}
				else if (AttemptLogin == 202) {
					new view.admin.AdminPage().showForm();
				}
				else if (AttemptLogin == 203) {
					System.out.println("Welcome Promotion");
				}
				else if (AttemptLogin == 204) {
					new HireStaffPage().showForm();
				}
			}
		});
		
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new RegisterView().showForm();
				
			}
		});
		
	}

}
