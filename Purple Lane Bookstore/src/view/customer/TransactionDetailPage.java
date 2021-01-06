package view.customer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.AdminController;
import controller.TransactionController;
import core.model.Model;
import core.view.View;
import model.AdminModel;
import model.DetailTransactionModel;
import model.TransactionModel;
import model.UserModel;

public class TransactionDetailPage extends View{

	JPanel top, bot;
	JTable table;
	JScrollPane sp;
	JButton viewProduct, viewCart, viewTransactionHistory;
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	UserModel user;
	DetailTransactionModel dtm;
	Vector<Model> transactionDetail;
	
	public TransactionDetailPage(Vector<Model> transactionDetail, DetailTransactionModel dtm) {
		super();
		this.transactionDetail = transactionDetail;
		this.dtm = dtm;
		this.height = 600;
		this.width = 600;
		addComponent(transactionDetail);
		
		System.out.println(user.getUserId() + dtm.getProductId());
	}

	@Override
	public void initialize() {
		top = new JPanel();
		bot = new JPanel();
		table = new JTable();
		sp = new JScrollPane(table);
		viewProduct = new JButton("View Products");
		viewCart = new JButton("View cart");
		viewTransactionHistory = new JButton("View Transaction History");
		sp.setPreferredSize(new Dimension(550, 300));
	}

	@Override
	public void addComponent() {
		// TODO Auto-generated method stub

		top.add(viewProduct);
		top.add(viewCart);
		top.add(sp);
		
		bot.add(viewTransactionHistory);
		
		add(top, BorderLayout.NORTH);
		add(bot, BorderLayout.SOUTH);
	}
	
	public void addComponent(Vector<Model> transactionDetail) {
		loadData();
	}
	
	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		viewCart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ViewCart(user).showForm();
				
			}
		});
		
		viewProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ProductPage(user).showForm();
			}
		});
		
		viewTransactionHistory.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new TransactionHistoryPage(user).showForm();
			}
		});
	}

	
	private void loadData() {
		header = new Vector<>();
		data = new Vector<>();
		
		header.add("Transaction ID");
		header.add("Product ID");
		header.add("Product Name");
		header.add("Product Quantity");
		
		for (Model model : transactionDetail) {
			DetailTransactionModel dtm = (DetailTransactionModel) model;
			detail = new Vector<>();
			detail.add(dtm.getTransactionId().toString());
			detail.add(dtm.getProductId().toString());
			detail.add(dtm.getProductName());
			detail.add(dtm.getProductQty().toString());
			
			
			data.add(detail);
		}
		
		
//		try {
//			while(rs.next()) {
//				Integer id = rs.getInt("ProductId");
//				String name = rs.getString("ProductName");
//				String author = rs.getString("ProductAuthor");
//				Integer price = rs.getInt("ProductPrice");
//				Integer stock = rs.getInt("ProductStock");
//				
//				detail = new Vector<>();
//				
//				
//				detail.add(id+"");
//				detail.add(name);
//				detail.add(author);
//				detail.add(price+"");
//				detail.add(stock+"");
//				
//				data.add(detail);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		DefaultTableModel dtm2 = new DefaultTableModel(data, header) {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			
		};
		
		
		table.setModel(dtm2);
	}

}
