package view.customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.DefaultDesktopManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import core.view.View;
import model.UserModel;

public class AddToCartPage extends View{
	
	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel idLbl, idValue, nameLbl, qtyLbl, searchLbl, msgTxt;
	JTextField nameTxt, qtyTxt, searchTxt;
	JButton search, addToCart;
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	UserModel user;

	public AddToCartPage(UserModel user) {
		super();
		this.height = 700;
		this.width = 600;
		this.user = user;
		
		System.out.println(user.getUserId());
	}

	@Override
	public void initialize() {
		top = new JPanel();
		mid = new JPanel();
		bot = new JPanel();
		table = new JTable();
		sp = new JScrollPane(table);
		
		searchLbl = new JLabel("Search Product ID: ");
		searchTxt = new JTextField();
		
		search = new JButton("Search");
		
		msgTxt = new JLabel("Search product first");
		msgTxt.setForeground(Color.BLUE.darker());
		
		qtyLbl= new JLabel("Quantity: ");
		qtyTxt = new JTextField();
		
		addToCart = new JButton("Add to cart");
		
		sp.setPreferredSize(new Dimension(550, 150));
		searchTxt.setPreferredSize(new Dimension(200, 30));
		qtyTxt.setPreferredSize(new Dimension(200, 30));
		
	}

	@Override
	public void addComponent() {
		top.add(searchLbl);
		top.add(searchTxt);
		top.add(search);
	
		mid.add(msgTxt);
		mid.add(sp);
		mid.add(qtyLbl);
		mid.add(qtyTxt);
		
		bot.add(addToCart);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
		
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
	}

}
