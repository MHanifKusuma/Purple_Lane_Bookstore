package view.customer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import model.UserModel;

public class ProductPage extends View {
	
	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel searchLbl, idLbl, nameLbl, quantityLbl, idValue, nameValue;
	JTextField searchTxt, quantityTxt;
	JButton search, addToCart, viewCart;
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	UserModel user;

	public ProductPage(UserModel user) {
		super();
		this.height = 600;
		this.width = 600;
		this.user = user;
		
		System.out.println(user.getUserId());
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
		quantityLbl = new JLabel("Product Quantity: ");
		quantityTxt = new JTextField();
		searchLbl = new JLabel("Search Product ID: ");
		searchTxt = new JTextField();
		idValue = new JLabel("-");
		nameValue = new JLabel("-");
		search = new JButton("Search");
		addToCart = new JButton("Add to cart");
		viewCart = new JButton("View cart");
		sp.setPreferredSize(new Dimension(550, 300));
		searchTxt.setPreferredSize(new Dimension(200, 30));
	}

	@Override
	public void addComponent() {
		Connect con = new Connect();
		
		loadData();
		
		top.add(searchLbl);
		top.add(searchTxt);
		top.add(search);
		top.add(viewCart);
		top.add(sp);
		
		mid.add(idLbl);
		mid.add(idValue);
		mid.add(nameLbl);
		mid.add(nameValue);
		mid.add(quantityLbl);
		mid.add(quantityTxt);
		
		bot.add(addToCart);	
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	}

	@Override
	public void addListener() {
		addToCart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UserModel um = new UserModel();
				AddToCartController cont = new AddToCartController();
				
				String ProductQty = "";
				Integer ProductId = Integer.parseInt(idValue.getText());
				ProductQty = quantityTxt.getText();
				
				Integer attemptInsert = AddToCartController.getInstance().attemptInsert(ProductId, ProductQty);
				
				if(attemptInsert == 1) {
					JOptionPane.showMessageDialog(null, "All fields must be filled");
				}
				else {
					if(attemptInsert == 2) {
						JOptionPane.showMessageDialog(null, "Quantity must be numeric");
					}
					else if (attemptInsert == 3) {
						JOptionPane.showMessageDialog(null, "Book stock is not enough");
					}
					else {
						Integer qty = Integer.parseInt(ProductQty);
						AddToCartController.getInstance().insert(user, ProductId, qty);
						JOptionPane.showMessageDialog(null, "Item added to cart successfully");
					}
				}
				
				
				loadData();
				
				
			}
		});
		
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
		
		viewCart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ViewCart(user).showForm();;
				
			}
		});
	}
	
	private void loadData() {
		header = new Vector<>();
		data = new Vector<>();
		
		header.add("Product ID");
		header.add("Product Name");
		header.add("Product Author");
		header.add("Product Price");
		header.add("Product Stock");
		
		
		Vector<Model> productList = AdminController.getInstance().getAll();
		
		for (Model model : productList) {
			AdminModel p = (AdminModel) model;
			detail = new Vector<>();
			detail.add(p.getProductId().toString());
			detail.add(p.getProductName());
			detail.add(p.getProductAuthor());
			detail.add(p.getProductPrice().toString());
			detail.add(p.getStock().toString());
			
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
