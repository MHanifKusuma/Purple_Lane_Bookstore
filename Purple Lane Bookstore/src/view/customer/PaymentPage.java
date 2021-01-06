package view.customer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controller.AddToCartController;
import controller.AdminController;
import controller.TransactionController;
import core.model.Model;
import core.view.View;
import model.AdminModel;
import model.CartModel;
import model.UserModel;

public class PaymentPage extends View{
	JPanel top, mid, bot;
	JTable table;
	JScrollPane sp;
	JLabel searchLbl, totalLbl, totalVal, cardLbl, promoLbl, typeLbl;
	JTextField cardTxt, promoTxt, typeTxt;
	JButton viewCarts, payBtn, promoBtn;
	Vector<Vector<Object>> data;
	Vector<Object> header;
	Vector<Object> detail;
	Vector<CartModel> cartItems;
	UserModel user;

	public PaymentPage(Vector<CartModel> cartItems, UserModel user) {
		super();
		this.cartItems = cartItems;
		this.height = 200;
		this.width = 350;
		this.user = user;
		addComponent(cartItems);
		addListener(user, cartItems);
		
		for (CartModel cartModel : cartItems) {
			System.out.println(cartModel.getProductId() + " " + cartModel.getQuantity());
		}
	}

	@Override
	public void initialize() {
		top = new JPanel();
		mid = new JPanel(new GridLayout(4,2));
		bot = new JPanel();
		
		totalLbl = new JLabel("Total Price: ");
		promoLbl = new JLabel("PromoCode ");
		cardLbl = new JLabel("Card Number: ");
		typeLbl = new JLabel("Card Type");
		
		totalVal = new JLabel("");
		promoTxt = new JTextField();
		cardTxt = new JTextField();
		typeTxt = new JTextField();

		payBtn = new JButton("Pay");
		viewCarts = new JButton("View Carts");
		promoBtn = new JButton("Apply Promo");
	}

	@Override
	public void addComponent() {
		
	}
	
	public void addComponent(Vector<CartModel> cartItems) {
		
		loadData(cartItems);
		
		mid.add(totalLbl);
		mid.add(totalVal);
		mid.add(promoLbl);
		mid.add(promoTxt);
		mid.add(typeLbl);
		mid.add(typeTxt);
		mid.add(cardLbl);
		mid.add(cardTxt);
		
		bot.add(viewCarts);
		bot.add(payBtn);
		bot.add(promoBtn);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);

		add(top, BorderLayout.NORTH);
		add(bot, BorderLayout.SOUTH);
		
	}

	public void addListener(UserModel user, Vector<CartModel> cartItems) {
		payBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				String promoCode = "";
				String card = "";
				String type = "";
				
				promoCode = promoTxt.getText();
				card = cardTxt.getText();
				type = typeTxt.getText();
				
				int attemptPayment = TransactionController.getInstance().attemptPayment(promoCode, card, type);
				
				if (attemptPayment == 1) {
					JOptionPane.showMessageDialog(null, "Payment Type and Card Number must be filled");
				}
				else {
					if(attemptPayment == 2) {
						JOptionPane.showMessageDialog(null, "Card number must be numeric");
					}
					else if(attemptPayment == 3) {
						JOptionPane.showMessageDialog(null, "Card number must be 16 characters long");
					}
					else if (attemptPayment == 5) {
						JOptionPane.showMessageDialog(null, "Payment type must be between 'debit' and 'credit'\n(Case Sensitive!!)");
					}
					else {
						TransactionController.getInstance().insert(user, cartItems, type, card, promoCode);
						JOptionPane.showMessageDialog(null, "Items bought Successfully");
						new ViewCart(user).showForm();;
					}
				}
						
			}
		});
		
		
		viewCarts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ViewCart(user);
				
			}
		});
		
		promoBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				Integer total = Integer.parseInt(totalVal.getText());
				String code = "";
				code = promoTxt.getText();
				
				Integer newTotal = TransactionController.getInstance().promoDiscount(total, code);
				
				if(newTotal == -1) {
					JOptionPane.showMessageDialog(null, "Promo code does not exists");
				}
				else {
					JOptionPane.showMessageDialog(null, "Promo applied successfully");
					totalVal.setText(newTotal.toString());
					promoBtn.setEnabled(false);
				}				
			}
		});
		
	}
	
	private void loadData(Vector<CartModel> cartItems) {
		String total = TransactionController.getInstance().calculatePrice(cartItems).toString();
		totalVal.setText(total);
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
	}
}
