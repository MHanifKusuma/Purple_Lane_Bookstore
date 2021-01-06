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
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicTreeUI.CellEditorHandler;
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
	Vector<Vector<Object>> data;
	Vector<Object> header;
	Vector<Object> detail;
	UserModel user;
	
	public ViewCart(UserModel user) {
		super();
		this.user = user;
		addComponent(user);
		addListener(user);
		this.height = 600;
		this.width = 600;
		
	}

	@Override
	public void initialize() {
		top = new JPanel();
		bot = new JPanel();
		table = new JTable();
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
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
				
				top.add(viewProduct);
				top.add(sp);

				bot.add(checkout);	
				
				add(top, BorderLayout.NORTH);
				add(bot, BorderLayout.SOUTH);
				
			}

	public void addListener(UserModel user) {
		Vector<CartModel> checkoutItems = new Vector<>();
		
		checkout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				for(int i=0; i<table.getRowCount(); i++) {
					Boolean checked = Boolean.valueOf(table.getValueAt(i, 0).toString());
					
					if(checked) {
						Integer productId = Integer.parseInt(table.getValueAt(i, 1).toString());
						String productName = table.getValueAt(i, 2).toString();
						Integer productQty = Integer.parseInt(table.getValueAt(i, 3).toString());
						
						CartModel items = new CartModel();
						items.setProductId(productId);
						items.setQuantity(productQty);
						items.setUserId(user.getUserId());
						items.setProductName(productName);
						
						checkoutItems.add(items);
					}
					
					
				}
				new PaymentPage(checkoutItems, user).showForm();
				
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
		
		DefaultTableModel dtm = new DefaultTableModel() {
			
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;

				default:
					return String.class;
				}
			}
			
		};
		
		
		table.setModel(dtm);
		
		dtm.addColumn("Action");
		dtm.addColumn("Product ID");
		dtm.addColumn("Product Name");
		dtm.addColumn("Quantity");	
		
		Vector<Model> cartList = AddToCartController.getInstance().getAll(user);
		
		for(int i=0;i < cartList.size();i++)
	    {
			CartModel items = (CartModel) cartList.get(i);
			
			dtm.addRow(new Object[0]);
			dtm.setValueAt(false,i,0);
			dtm.setValueAt(items.getProductId(), i, 1);
			dtm.setValueAt(items.getProductName(), i, 2);
			dtm.setValueAt(items.getQuantity(), i, 3);
	    }
												
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
	}

}
