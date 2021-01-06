package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import core.model.Model;

public class DetailReportModel extends Model{

	private String TransactionId;
	private String ProductId;
	private String ProductName;
	private String ProductQuantity;
	
	

	public String getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}

	public String getProductId() {
		return ProductId;
	}

	public void setProductId(String productId) {
		ProductId = productId;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getProductQuantity() {
		return ProductQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		ProductQuantity = productQuantity;
	}

	public DetailReportModel() {
		// TODO Auto-generated constructor stub
		this.tablename = "detail_transactions";
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
		String query = String.format("select "
                + "%s.ProductId, products.ProductName, %s.ProductQuantity"
                + " from %s join products on %s.ProductId = products.ProductId"
                + " where %s.TransactionId = %s", tablename, tablename, tablename, tablename, tablename, TransactionId);
		


		ResultSet rs = con.executeQuery(query);
		
		try {
			
			while(rs.next()){
				
				String productId = rs.getString("ProductId");
				String name = rs.getString("ProductName");
				String qty = rs.getString("ProductQuantity");
		
				
				DetailReportModel tran = new DetailReportModel();
				tran.setProductId(productId);
				tran.setProductName(name);
				tran.setProductQuantity(qty);
				
				data.add(tran);
				
			}
			
			return data;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
