package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import core.model.Model;

public class ManagerModel extends Model{

	private Integer TransactionId;
	private String TransactionDate;
	private String PaymentType;
	private String CardNumber;
	private Integer PromoId;
	private Integer UserId;
	
	private String Year ; 
	private String Month ;
	
	
	public Integer getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(Integer transactionId) {
		TransactionId = transactionId;
	}

	public String getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		TransactionDate = transactionDate;
	}



	public String getPaymentType() {
		return PaymentType;
	}

	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}

	public String getCardNumber() {
		return CardNumber;
	}

	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}

	public Integer getPromoId() {
		return PromoId;
	}

	public void setPromoId(Integer promoId) {
		PromoId = promoId;
	}

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public String getMonth() {
		return Month;
	}

	public void setMonth(String month) {
		Month = month;
	}

	
	
	public ManagerModel (){
		this.tablename = "transactions";
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
		
		Vector<Model> data = new Vector<>();
		String query = String.format("select * from %s", tablename);
		ResultSet rs = con.executeQuery(query);
		
		try {
			
			while(rs.next()){
				Integer id = rs.getInt("TransactionId");
				String date = rs.getString("TransactionDate");
				String payment = rs.getString("PaymentType");
				String card = rs.getString("CardNumber");
				Integer promo = rs.getInt("PromoId");
				Integer userid = rs.getInt("UserId");
				
				
				ManagerModel tran = new ManagerModel();
				tran.setTransactionId(id);
				tran.setTransactionDate(date);
				tran.setPaymentType(payment);
				tran.setCardNumber(card);
				tran.setPromoId(promo);
				tran.setUserId(userid);
				
				data.add(tran);
				
			}
			
			return data;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public Vector<Model> getFilteredData() {
		
	
		Vector<Model> data = new Vector<>();
		String query = String.format("SELECT * FROM %s WHERE YEAR(TransactionDate) = %s AND MONTH(TransactionDate) = %s", tablename, Year, Month);    
		ResultSet rs = con.executeQuery(query);
		
		
			try {
			
			while(rs.next()){
				Integer id = rs.getInt("TransactionId");
				String date = rs.getString("TransactionDate");
				String payment = rs.getString("PaymentType");
				String card = rs.getString("CardNumber");
				Integer promo = rs.getInt("PromoId");
				Integer userid = rs.getInt("UserId");
				
				
				ManagerModel tran = new ManagerModel();
				tran.setTransactionId(id);
				tran.setTransactionDate(date);
				tran.setPaymentType(payment);
				tran.setCardNumber(card);
				tran.setPromoId(promo);
				tran.setUserId(userid);
				
				data.add(tran);
				
			}
			
			return data;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
