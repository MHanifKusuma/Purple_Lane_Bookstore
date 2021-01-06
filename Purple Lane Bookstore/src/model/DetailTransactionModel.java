package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import core.model.Model;

public class DetailTransactionModel extends Model {
	private String tablename;
	private Integer transactionId, productId, productQty;
	
	public DetailTransactionModel() {
		this.tablename = "detail_transactions";
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

}
