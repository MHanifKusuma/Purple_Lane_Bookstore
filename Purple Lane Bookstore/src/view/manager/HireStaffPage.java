package view.manager;

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
import view.customer.ProductPage;

public class HireStaffPage extends View{
	
	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel passLbl, passValue, nameLbl, nameValue, roleLbl, pageHeader;
	JTextField nameTxt, roleTxt;
	JPasswordField passTxt;
	JButton register;
	Vector<Vector<String>> data;
	Vector<String> detail, header;

	public HireStaffPage() {
		super();
		this.height = 200;
		this.width = 350;
		
	}

	@Override
	public void initialize() {
		top = new JPanel();
		mid = new JPanel(new GridLayout(3,2));
		bot = new JPanel();
		
		pageHeader = new JLabel("Hire New Staff ");
		pageHeader.setFont(new Font("Serif", Font.PLAIN, 18));
	
		nameLbl = new JLabel("Username: ");
		passLbl = new JLabel("password: ");
		roleLbl = new JLabel("Staff Role: ");
		
		nameTxt = new JTextField();
		passTxt = new JPasswordField();
		roleTxt = new JTextField();
		
		register = new JButton("Register");
		
	}

	@Override
	public void addComponent() {
		top.add(pageHeader);
		
		mid.add(nameLbl);
		mid.add(nameTxt);
		mid.add(passLbl);
		mid.add(passTxt);
		mid.add(roleLbl);
		mid.add(roleTxt);
		
		bot.add(register);
	
		
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
				
				
				String role = roleTxt.getText();
				String name = nameTxt.getText();
				@SuppressWarnings("deprecation")
				String pass = passTxt.getText();
				
				int roleId = UserController.getInstance().AssignRole(role);
				
				int AttemptRegis = UserController.getInstance().registerValidator(roleId, name, pass);
				
				if (AttemptRegis == 1){
					JOptionPane.showMessageDialog(null, "All fields must be filled");
				}
				else {
					if (AttemptRegis == 2) {
						JOptionPane.showMessageDialog(null, "Username already taken");
					}
					else if(roleId == 4) {
						JOptionPane.showMessageDialog(null, "Cannot hire new manager");
					}
					else if (roleId == -1) {
						JOptionPane.showMessageDialog(null, "Role do not exist!\nMust be between 'admin' or 'promotion'\n(Case Sensitive!!)");
					}
					else {
						String msg = String.format("New Staff Hired!\n"
														+ "%-11s: %s\n"
														+ "%-12s: %s\n"
														+ "%-17s: %s","Username", name, "Password", pass, "Role", role);
						UserController.getInstance().insert(roleId, name, pass);
						JOptionPane.showMessageDialog(null, msg);
					}
				}
								
			}});
			
	}

}
