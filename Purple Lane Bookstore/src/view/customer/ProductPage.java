package view.customer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connect.Connect;
import core.view.View;

public class ProductPage extends View {
	
	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel searchLbl;
	JTextField searchTxt;
	JButton search, addToCart, viewCart;
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
		
		searchLbl = new JLabel("Search Product ID: ");
		searchTxt = new JTextField();
		
		search = new JButton("Search");
		addToCart = new JButton("Add to cart");
		
		viewCart = new JButton("View cart");
		
		searchTxt.setPreferredSize(new Dimension(100, 30));
		
		sp.setPreferredSize(new Dimension(550, 600));
	}

	@Override
	public void addComponent() {
		Connect con = new Connect();
		
		loadData(con.executeQuery("SELECT * FROM products"));
		
		top.add(searchLbl);
		top.add(searchTxt);
		top.add(search);
		top.add(addToCart);
		top.add(viewCart);
		top.add(sp);
		
		add(top, BorderLayout.CENTER);
	}

	@Override
	public void addListener() {
		
		addToCart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddToCartPage().showForm();
				
			}
		});
	}
	
	private void loadData(ResultSet rs) {
		header = new Vector<>();
		data = new Vector<>();
		
		header.add("Product ID");
		header.add("Product Name");
		header.add("Product Author");
		header.add("Product Price");
		header.add("Product Stock");
		
		try {
			while(rs.next()) {
				Integer id = rs.getInt("ProductId");
				String name = rs.getString("ProductName");
				String author = rs.getString("ProductAuthor");
				Integer price = rs.getInt("ProductPrice");
				Integer stock = rs.getInt("ProductStock");
				
				detail = new Vector<>();
				
				detail.add(id+"");
				detail.add(name);
				detail.add(author);
				detail.add(price+"");
				detail.add(stock+"");
				
				data.add(detail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DefaultTableModel dtm = new DefaultTableModel(data, header);
		table.setModel(dtm);
	}

}
