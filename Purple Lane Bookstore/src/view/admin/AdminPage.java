package view.admin;

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
import controller.AdminController;
import core.model.Model;
import core.view.View;
import model.AdminModel;

public class AdminPage extends View {
	
	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel idLbl, idValue, nameLbl, authorLbl, priceLbl, stockLbl, searchLbl;
	JTextField nameTxt, authorTxt, priceTxt, stockTxt, searchTxt;
	JButton insert, update, delete, search;
	Vector<Vector<String>> data;
	Vector<String> detail, header;

	public AdminPage() {
		super();
		this.height = 600;
		this.width = 600;
	}

	@Override
	public void initialize() {
		top = new JPanel();
		mid = new JPanel(new GridLayout(5,2));
		bot = new JPanel();
		table = new JTable();
		sp = new JScrollPane(table);
		idLbl = new JLabel("Product ID: ");
		nameLbl = new JLabel("Product Name: ");
		authorLbl = new JLabel("Product Author:");
		priceLbl = new JLabel("Product Price: ");
		stockLbl = new JLabel("Product Stock:");
		idValue = new JLabel("-");
		nameTxt = new JTextField();
		authorTxt = new JTextField();
		priceTxt = new JTextField();
		stockTxt = new JTextField();
		
		searchLbl = new JLabel("Search Product ID: ");
		searchTxt = new JTextField();
		
		insert = new JButton("Insert") ;
		update = new JButton("Update");
		delete = new JButton("Delete");	
		search = new JButton("Search");
		
		sp.setPreferredSize(new Dimension(550, 300));
		searchTxt.setPreferredSize(new Dimension(200, 30));
		
	}

	@Override
	public void addComponent() {
		Connect con = new Connect();
		
	//	loadData(con.executeQuery("SELECT * FROM products"));
		
		top.add(searchLbl);
		top.add(searchTxt);
		top.add(search);
		top.add(sp);
		
		mid.add(idLbl);
		mid.add(idValue);
		mid.add(nameLbl);
		mid.add(nameTxt);
		mid.add(authorLbl);
		mid.add(authorTxt);
		mid.add(priceLbl);
		mid.add(priceTxt);
		mid.add(stockLbl);
		mid.add(stockTxt);
		
		bot.add(insert);
		bot.add(update);
		bot.add(delete);
	
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
		
		loadData();
		
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		insert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ProductName = nameTxt.getText();
				String ProductAuthor = authorTxt.getText();
				String ProductPrice = priceTxt.getText();
				String ProductStock = stockTxt.getText();
				
				
				
				
				int validate = AdminController.getInstance().insertvalidator(ProductName, ProductAuthor, ProductPrice, ProductStock);			
				
				if(validate == 1) {
					JOptionPane.showMessageDialog(null, "All fields must be filled");
				}else if(validate == 2) {
					JOptionPane.showMessageDialog(null, "Price/Stock must be numeric");
					
				}
				
				Integer ProdPrice = Integer.parseInt(ProductPrice);
				Integer ProdStock = Integer.parseInt(ProductStock);
				
				if(ProdPrice == 0 || ProdStock == 0) {
					JOptionPane.showMessageDialog(null, "Price/Stock must be more than 0");
				}else {
					
					AdminController.getInstance().insert(ProductName, ProductAuthor, ProdPrice, ProdStock);
					loadData();
				}
				
				
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
				nameTxt.setText(table.getValueAt(row, 1).toString());
				authorTxt.setText(table.getValueAt(row, 2).toString());
				priceTxt.setText(table.getValueAt(row, 3).toString());
				stockTxt.setText(table.getValueAt(row, 4).toString());
			}
		});
		
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Integer ProductId = Integer.parseInt(idValue.getText());
				String ProductName = nameTxt.getText();
				String ProductAuthor = authorTxt.getText();
				Integer ProductPrice = Integer.parseInt(priceTxt.getText());
				Integer ProductStock = Integer.parseInt(stockTxt.getText());
				AdminController.getInstance().update(ProductName, ProductAuthor, ProductPrice, ProductStock, ProductId);
				loadData();
			}
		});
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Integer ProductId = Integer.parseInt(idValue.getText());
				
				AdminController.getInstance().delete(ProductId);
				loadData();
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
