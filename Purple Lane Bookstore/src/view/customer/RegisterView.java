package view.customer;

import java.awt.BorderLayout;
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

public class RegisterView extends View{
	
	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel passLbl, passValue, nameLbl, nameValue, pageHeader;
	JTextField nameTxt;
	JPasswordField passTxt;
	JButton register, login;
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
		passTxt = new JPasswordField();
		
		pageHeader = new JLabel("Register");
		pageHeader.setFont(new Font("Serif", Font.PLAIN, 18));
		
		
		register = new JButton("Register");
		login = new JButton("Login");
		
	}

	@Override
	public void addComponent() {
		top.add(pageHeader);
		
		mid.add(nameLbl);
		mid.add(nameTxt);
		mid.add(passLbl);
		mid.add(passTxt);
		
		bot.add(register);
		bot.add(login);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
		
	}

	@Override
	public void addListener() {
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				String name = "";
				String pass = "";
				
				int roleId = 1;
				name = nameTxt.getText();
				pass = passTxt.getText();
				
				int AttemptRegis = UserController.getInstance().registerValidator(roleId, name, pass);
				
				if (AttemptRegis == 1){
					JOptionPane.showMessageDialog(null, "All fields must be filled");
				}
				else if (AttemptRegis == 2) {
					JOptionPane.showMessageDialog(null, "Username already taken");
				}
				else {
					UserController.getInstance().insert(roleId, name, pass);
					new LoginView().showForm();
				}
				
				
				
			}
		});
		
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new LoginView().showForm();
				
			}
		});
		
	}
	
	

}
