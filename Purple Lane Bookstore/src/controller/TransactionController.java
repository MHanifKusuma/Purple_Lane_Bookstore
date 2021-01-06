package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import core.controller.Controller;
import core.model.Model;
import core.view.View;
import model.AdminModel;
import model.CartModel;
import model.DetailTransactionModel;
import model.PromoModel;
import model.TransactionModel;
import model.UserModel;

public class TransactionController extends Controller {
	
	TransactionModel transactions;
	PromoModel promos;
	AdminModel products;
	DetailTransactionModel details;
	CartModel carts;
	private static TransactionController controller;

	public TransactionController() {
		transactions = new TransactionModel();
		promos = new PromoModel();
		products = new AdminModel();
		details = new DetailTransactionModel();
		carts = new CartModel();
	}
	
	public static TransactionController getInstance() {
		return controller = (controller == null) ? new TransactionController() : controller;
	}
	
	public static boolean isNumeric(String strNum) {
		//method untuk mengecek apakah inputan String hanya berisi angka atau tidak
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public Integer calculatePrice(Vector<CartModel> items) {
		//method untuk mengkalkulasi total harga yang dibeli
		int total = 0;
		
		for (CartModel countItems : items) {
			ResultSet rs = products.checkProduct(countItems.getProductId());
			
			try {
				if(rs.next()) {
					String productName = rs.getString("ProductName");
					Integer productPrice = rs.getInt("ProductPrice");
					
					System.out.println(productName + "  " + productPrice);
					total += rs.getInt("ProductPrice") * countItems.getQuantity();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return total;
	}
	
	public Integer promoDiscount(Integer total, String code) {
		//method untuk menghitung diskon
		int newTotal = 0;
		
		ResultSet checkPromo = promos.checkPromoCode(code);
		
		if(code.equals("")) {
			return newTotal = 0;
		}
		else {
			try {
				if(!checkPromo.next()) {
					return newTotal = -1; // error jika promo code tidak ditemukan
				}
				else {
					int discount = checkPromo.getInt("PromoDiscount");
					
					return newTotal = total - discount;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return newTotal;
	}
	
	public Integer attemptPayment(String code, String card, String type) {
		// Ini merupakan method untuk validasi saat user ingin melakukan pembayaran
		Integer error = 0;
		
		if(card.equals("") || type.equals("")) {
			return error = 1; // error jika ada field yang kosong
		}
		
		if (!isNumeric(card)) {
			return error = 2; // error jika card number tidak numeric
		}
		
		if(card.length() != 16) {
			return error = 3; // error jika card number tidak 16 angka
		}
		
		if(!type.equals("debit") && !type.equals("credit")) {
			return error = 5; // error jika promo code selain 'debit' dan 'credit' (case sensitive)
		}
		
		return error;
	}
	
	public void postInsert(UserModel user, Vector<CartModel> cartItems) {
		//Method ini akan mengerjakan pekerjaan post transaction, yakni membuat detail transaction, mengurangi stock barang
		//dan menghapus cart user yang melakukan transaksi.
		
		//Mengurangi stock barang
		Integer productId = 0, productStock = 0, productPrice = 0, transactionId = 0;
		String productName = "", productAuthor = ""; 
		
		for (CartModel items : cartItems) {
			productId = items.getProductId();
			
			ResultSet checkProduct = products.checkProduct(productId);
			
			try {
				if(checkProduct.next()) {
					productName = checkProduct.getString("ProductName");
					productAuthor = checkProduct.getString("ProductAuthor");
					productPrice = checkProduct.getInt("ProductPrice");
					productStock = checkProduct.getInt("ProductStock");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			productStock -= items.getQuantity();
			
			AdminModel pModel = new AdminModel();
			
			pModel.setProductId(productId);
			pModel.setProductName(productName);
			pModel.setProductAuthor(productAuthor);
			pModel.setProductPrice(productPrice);
			pModel.setStock(productStock);
			
			
			pModel.update();
			
			//Membuat detail transactions
			ResultSet InsertedTransaction = transactions.getInsertedTransaction();
			
			try {
				if(InsertedTransaction.next()) {
					transactionId = InsertedTransaction.getInt("TransactionId");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			DetailTransactionModel dtModel = new DetailTransactionModel();
			
			dtModel.setTransactionId(transactionId);
			dtModel.setProductId(productId);
			dtModel.setProductQty(items.getQuantity());
			
			dtModel.insert();
			
			//menghapus cart user
			CartModel deleteCart = new CartModel();
			
			deleteCart.setProductId(productId);
			deleteCart.setUserId(user.getUserId());
			deleteCart.delete();
		}
	}
	
	public void insert(UserModel user, Vector<CartModel> cartItems, String type, String card, String code) {
		ResultSet checkPromo = promos.checkPromoCode(code);
		Integer promoId = null;
		try {
			if(checkPromo.next()) {
				promoId = checkPromo.getInt("PromoId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TransactionModel tModel = new TransactionModel();
		
		tModel.setPaymentType(type);
		tModel.setCardNumber(card);
		tModel.setPromoId(promoId);
		tModel.setUserId(user.getUserId());
		tModel.insert();
		
		postInsert(user, cartItems);
	}

	@Override
	public View view() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
