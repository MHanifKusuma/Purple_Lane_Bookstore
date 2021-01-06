package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import core.model.Model;

public class DetailTransactionModel extends Model {
	private String tablename, productName;
	private Integer transactionId, productId, productQty;
	
	public DetailTransactionModel() {
		this.tablename = "detail_transactions";
	}
	
	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductQty() {
		return productQty;
	}

	public void setProductQty(Integer productQty) {
		this.productQty = productQty;
	}

	@Override
	public void insert() {
		String query = String.format("INSERT INTO %s VALUES(?, ?, ?)", tablename);
		
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setInt(1, transactionId);
			ps.setInt(2, productId);
			ps.setInt(3, productQty);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public Vector<Model> getAll(Integer transactionId, UserModel user) {
		Vector<Model> data = new Vector<>();
		String query = String.format("SELECT detail_transactions.TransactionId, detail_transactions.ProductId, products.ProductName, detail_transactions.ProductQuantity" +
				"FROM transactions JOIN detail_transactions ON transactions.TransactionId = detail_transactions.TransactionId JOIN products ON detail_transactions.ProductId = products.ProductId" + 
				"WHERE transactions.UserId = %d AND detail_transactions.TransactionId = %d", user.getUserId(), transactionId);

		ResultSet rs = con.executeQuery(query);
		
		try {
			
			while(rs.next()){
				
				Integer transactionnId = rs.getInt("transactionId");
				Integer productId = rs.getInt("ProductId");
				String productName = rs.getString("ProductName");
				Integer qty = rs.getInt("ProductQuantity");
		
				
				DetailTransactionModel dtm  = new DetailTransactionModel();
				dtm.setTransactionId(transactionnId);
				dtm.setProductId(productId);
				dtm.setProductName(productName);
				dtm.setProductQty(qty);
				
				data.add(dtm);
				
			}
			
			return data;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
}
