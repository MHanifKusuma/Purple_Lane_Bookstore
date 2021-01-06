package view.customer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
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

public class TransactionHistoryPage extends View{

	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel idLbl, idValue, transactionDateLbl, transactionDateValue;
	JButton viewProduct, viewCart, viewTransactionDetail;
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	UserModel user;
	DetailTransactionModel dtm;
	
	public TransactionHistoryPage(UserModel user) {
		super();
		this.height = 600;
		this.width = 600;
		this.user = user;
		addComponent(user);
		
		System.out.println(user.getUserId());
	}

	@Override
	public void initialize() {
		top = new JPanel();
		mid = new JPanel(new GridLayout(2,2));
		bot = new JPanel();
		table = new JTable();
		sp = new JScrollPane(table);
		idLbl = new JLabel("Product ID: ");
		idValue = new JLabel("-");
		transactionDateLbl = new JLabel("Transaction Date: ");
		transactionDateValue = new JLabel("-");
		viewProduct = new JButton("View Products");
		viewCart = new JButton("View cart");
		viewTransactionDetail = new JButton("View Transaction Detail");
		sp.setPreferredSize(new Dimension(550, 300));
	}

	@Override
	public void addComponent() {
		// TODO Auto-generated method stub
	}
	
	public void addComponent(UserModel user) {
		loadData(user);
		
		top.add(viewProduct);
		top.add(viewCart);
		top.add(sp);
		
		mid.add(idLbl);
		mid.add(idValue);
		mid.add(transactionDateLbl);
		mid.add(transactionDateValue);
	
		bot.add(viewTransactionDetail);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Vector<Model> transactionDetail = new Vector<>();
				DetailTransactionModel dtm = null;
				
				int row = table.getSelectedRow();
				String transactionId = (String) table.getValueAt(row, 0);
				
				Integer transId = Integer.parseInt(transactionId);
				
				transactionDetail = TransactionController.getInstance().getDetailTransaction(transId, user);
				
				new TransactionDetailPage(transactionDetail, dtm);
			}
		});
		
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
	}

	
	private void loadData(UserModel user) {
		header = new Vector<>();
		data = new Vector<>();
		
		header.add("Transaction ID");
		header.add("Transaction Date");
		header.add("Payment Type");
		header.add("Card Number");
		header.add("Promo ID");
		
		Vector<Model> transactionList = TransactionController.getInstance().getAll(user);
		
		for (Model model : transactionList) {
			TransactionModel tm = (TransactionModel) model;
			detail = new Vector<>();
			detail.add(tm.getTransactionId().toString());
			detail.add(tm.getTransactionDate().toString());
			detail.add(tm.getPaymentType());
			detail.add(tm.getCardNumber());
			detail.add(tm.getPromoId().toString());
			
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
		
		
		DefaultTableModel dtm = new DefaultTableModel(data, header) {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			
		};
		
		
		table.setModel(dtm);
	}

}
