package view.promotion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement.ParseInfo;

import connect.Connect;
import controller.AdminController;
import controller.PromoController;
import core.model.Model;
import core.view.View;
import model.AdminModel;
import model.PromoModel;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class PromoPage extends View {

	JPanel top, top2, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel idLbl, idValue, codeLbl, discLbl, noteLbl;
	JTextField codeTxt, discTxt, noteTxt;
	JButton insert, update, delete;
	Vector<Vector<String>> data;
	Vector<String> detail, header;

	public PromoPage() {
		super();
		this.height = 800;
		this.width = 700;
	}

	@Override
	public void initialize() {
		top = new JPanel();
		top2 = new JPanel();
		mid = new JPanel(new GridLayout(4,2));
		bot = new JPanel();
		table = new JTable();
		sp = new JScrollPane(table);
		idLbl = new JLabel("Promo ID: ");
		codeLbl = new JLabel("Promo Code: ");
		discLbl = new JLabel("Promo Discount: ");
		noteLbl = new JLabel("Promo Note: ");

		idValue = new JLabel("-");
		codeTxt = new JTextField();
		discTxt = new JTextField();
		noteTxt = new JTextField();
		
		insert = new JButton("Insert") ;
		update = new JButton("Update");
		delete = new JButton("Delete");	
		
		sp.setPreferredSize(new Dimension(550, 300));
		
	}

	@Override
	public void addComponent() {
		Connect con = new Connect();
			top.add(sp);
			
			mid.add(idLbl);
			mid.add(idValue);
			mid.add(codeLbl);
			mid.add(codeTxt);
			mid.add(discLbl);
			mid.add(discTxt);
			mid.add(noteLbl);
			mid.add(noteTxt);
			
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
						String promoCode = "";
						String promoDiscount = "";
						String promoNote = "";
					
						promoCode = codeTxt.getText();
						promoDiscount = discTxt.getText();
						promoNote = noteTxt.getText();
						
						int attemptInsert = PromoController.getInstance().attemptInsert(promoCode, promoDiscount, promoNote, 0);
						
						if (attemptInsert == -1) {
							JOptionPane.showMessageDialog(null, "All fields must be filled");
						}
						else {
							if(attemptInsert == 1) {
								JOptionPane.showMessageDialog(null, "Promo code already exists");
							}
							else if(attemptInsert == 2) {
								JOptionPane.showMessageDialog(null, "Promo discount must be numeric");
							}
							else if(attemptInsert == 3) {
								JOptionPane.showMessageDialog(null, "Promo discount must be more than 15000");
							}
							else {
								Integer disc = Integer.parseInt(promoDiscount);
								PromoController.getInstance().insert(promoCode, disc, promoNote);
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
						codeTxt.setText(table.getValueAt(row, 1).toString());
						discTxt.setText(table.getValueAt(row, 2).toString());
						noteTxt.setText(table.getValueAt(row, 3).toString());
					}
				});
				
				update.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						String promoCode = "";
						String promoDiscount = "";
						String promoNote = "";
						
						Integer PromoId = Integer.parseInt(idValue.getText());
						promoCode = codeTxt.getText();
						promoDiscount = discTxt.getText();
						promoNote = noteTxt.getText();
						
						int attemptInsert = PromoController.getInstance().attemptInsert(promoCode, promoDiscount, promoNote, PromoId);
						
						if (attemptInsert == -1) {
							JOptionPane.showMessageDialog(null, "All fields must be filled");
						}
						else {
							if(attemptInsert == 2) {
								JOptionPane.showMessageDialog(null, "Promo code already exists");
							}
							else if(attemptInsert == 3) {
								JOptionPane.showMessageDialog(null, "Promo discount must be numeric");
							}
							else if(attemptInsert == 4) {
								JOptionPane.showMessageDialog(null, "Promo discount must be more than 15000");
							}
							else {
								Integer disc = Integer.parseInt(promoDiscount);
								PromoController.getInstance().update(promoCode, disc, promoNote, PromoId);
							}
						}
						loadData();
					}
				});
				
				delete.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Integer promoId = Integer.parseInt(idValue.getText());
						
						PromoController.getInstance().delete(promoId);
						loadData();
					}
				});
		
	}
	
	private void loadData() {
		header = new Vector<>();
		data = new Vector<>();
		
		header.add("Promo ID");
		header.add("Promo Code");
		header.add("Promo Discount");
		header.add("Promo Note");
		
		
		Vector<Model> promoList = PromoController.getInstance().getAll();
		
		for (Model model : promoList) {
			PromoModel p = (PromoModel) model;
			
			detail = new Vector<>();
			detail.add(p.getPromoId().toString());
			detail.add(p.getPromoCode());
			detail.add(p.getPromoDisc().toString());
			detail.add(p.getPromoNote());
			
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
