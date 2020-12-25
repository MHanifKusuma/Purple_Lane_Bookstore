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

public class ProductPage extends View {
	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel idLbl, idValue, nameLbl, priceLbl;
	JTextField nameTxt, priceTxt;
	JButton insert, update, delete;
	Vector<Vector<String>> data;
	Vector<String> detail, header;

	public ProductPage() {
		super();
		this.height = 700;
		this.width = 600;
	}

	@Override
	public void initialize() {
		top = new JPanel();
		mid = new JPanel(new GridLayout(3,2));
		bot = new JPanel();
		table = new JTable();
		sp = new JScrollPane(table);
		idLbl = new JLabel("Product ID: ");
		nameLbl = new JLabel("Product Name: ");
		priceLbl = new JLabel("Product Price: ");
		idValue = new JLabel("-");
		nameTxt = new JTextField();
		priceTxt = new JTextField();
		insert = new JButton("Insert");
		update = new JButton("Update");
		delete = new JButton("Delete");	

	}

	@Override
	public void addComponent() {
		top.add(sp);
		
		mid.add(idLbl);
		mid.add(idValue);
		mid.add(nameLbl);
		mid.add(nameTxt);
		mid.add(priceLbl);
		mid.add(priceTxt);
		
		bot.add(insert);
		bot.add(update);
		bot.add(delete);
	
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);

	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub

	}

}
