package view.customer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connect.Connect;
import controller.AddToCartController;
import controller.AdminController;
import core.model.Model;
import core.view.View;
import model.AdminModel;
import model.CartModel;
import model.UserModel;

public class ViewCart extends View{

	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel searchLbl, idLbl, nameLbl, quantityLbl, idValue, nameValue;
	JTextField searchTxt, quantityTxt;
	JButton viewProduct, checkout;
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	Integer userId = 0;
	UserModel user;
	
	public ViewCart(UserModel user) {
		super();
		this.userId = user.getUserId();
		this.user = user;
		addComponent(user);
		this.height = 600;
		this.width = 600;
		
	}

	@Override
	public void initialize() {
		top = new JPanel();
		bot = new JPanel();
		table = new JTable();
		sp = new JScrollPane(table);

		checkout = new JButton("Chekout");
		viewProduct = new JButton("View Products");
		sp.setPreferredSize(new Dimension(550, 300));
	}

	@Override
	public void addComponent() {
		
	}
	
	public void addComponent(UserModel user) {
				loadData(user);
				System.out.println("passed user id: " + userId);
				
				top.add(viewProduct);
				top.add(sp);

				bot.add(checkout);	
				
				add(top, BorderLayout.NORTH);
				add(bot, BorderLayout.SOUTH);
				
			}

	@Override
	public void addListener() {
//		checkout.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				UserModel um = new UserModel();
//				AddToCartController cont = new AddToCartController();
//				Integer ProductId = Integer.parseInt(idValue.getText());
//				Integer ProductQty = Integer.parseInt(quantityTxt.getText());
//				AddToCartController.getInstance().insert(user, ProductId, ProductQty);
//				loadData();
//			}
//		});
		
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
				int row = table.getSelectedRow();
				idValue.setText(table.getValueAt(row, 0).toString());
				nameValue.setText(table.getValueAt(row, 1).toString());
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
		System.out.println(user.getUserId());
		System.out.println(user.getUsername());
		System.out.println(userId);
		header = new Vector<>();
		data = new Vector<>();
		
		header.add("Product ID");
		header.add("Product Name");
		header.add("Quantity");
		
		Vector<Model> cartList = AddToCartController.getInstance().getAll(user);
		
		for (Model model : cartList) {
			CartModel p = (CartModel) model;
			
			detail = new Vector<>();
			
			detail.add(p.getProductId().toString());
			detail.add(p.getProductName());
			detail.add(p.getQuantity().toString());
			
			data.add(detail);
		}
		
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
