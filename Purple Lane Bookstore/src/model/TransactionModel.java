package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import core.model.Model;

public class TransactionModel extends Model {
	private String tablename, paymentType, cardNumber;
	private Integer transactionId, promoId, userId;
	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	private Timestamp transactionDate;

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getPromoId() {
		return promoId;
	}

	public void setPromoId(Integer promoId) {
		this.promoId = promoId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	public TransactionModel() {
		this.tablename = "transactions";
		
		this.transactionDate = new Timestamp(new Date().getTime());
	}
	
	public ResultSet getInsertedTransaction() {
		ResultSet rs = null;
		String query = "SELECT * FROM transactions ORDER BY TransactionId DESC LIMIT 1";
		
		PreparedStatement pQuery = con.prepareStatement(query);
		
		try {
			rs = pQuery.executeQuery();
			
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void makeDetail() {
		
	}

	@Override
	public void insert() {
		
		if(promoId == null) {
			String query = String.format("INSERT INTO %s VALUES(null, ?, ?, ?, null, ?)", tablename);
			
			PreparedStatement ps = con.prepareStatement(query);
			
			try {
				ps.setTimestamp(1, transactionDate);
				ps.setString(2, paymentType);
				ps.setString(3, cardNumber);
				ps.setInt(4, userId);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			String query = String.format("INSERT INTO %s VALUES(null, ?, ?, ?, ?, ?)", tablename);
		
			PreparedStatement ps = con.prepareStatement(query);
			
			try {
				ps.setTimestamp(1, transactionDate);
				ps.setString(2, paymentType);
				ps.setString(3, cardNumber);
				ps.setInt(4, promoId);
				ps.setInt(5, userId);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
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

	public Vector<Model> getAllUserTransaction(UserModel user) {
		Vector<Model> data = new Vector<>();
		String query = "SELECT * from transactions WHERE UserId = ?";
		ResultSet rs = null;
		
		PreparedStatement pQuery = con.prepareStatement(query);
		
		try {
			pQuery.setInt(1, user.getUserId());
			rs = pQuery.executeQuery();
			
			while(rs.next()){
				Integer transactionId = rs.getInt("TransactionId");
				Timestamp transactionDate = rs.getTimestamp("TransactionDate");
				String paymentType = rs.getString("PaymentType");
				String cardNumber = rs.getString("CardNumber");
				Integer promoId = rs.getInt("PromoId");
				
				TransactionModel transHistory = new TransactionModel();
				transHistory.setTransactionId(transactionId);
				transHistory.setTransactionDate(transactionDate);
				transHistory.setPaymentType(paymentType);
				transHistory.setCardNumber(cardNumber);
				transHistory.setPromoId(promoId);
				
				data.add(transHistory);
				
			}
			return data;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
