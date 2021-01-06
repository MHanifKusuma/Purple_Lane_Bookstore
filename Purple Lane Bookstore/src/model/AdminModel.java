package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import core.model.Model;

public class AdminModel extends Model{

	private Integer ProductId;
	private String ProductName;
	private String ProductAuthor;
	private Integer ProductPrice;
	private Integer Stock;
	
	
	

	public Integer getProductId() {
		return ProductId;
	}

	public void setProductId(Integer productId) {
		ProductId = productId;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getProductAuthor() {
		return ProductAuthor;
	}

	public void setProductAuthor(String productAuthor) {
		ProductAuthor = productAuthor;
	}

	public Integer getProductPrice() {
		return ProductPrice;
	}

	public void setProductPrice(Integer productPrice) {
		ProductPrice = productPrice;
	}

	public Integer getStock() {
		return Stock;
	}

	public void setStock(Integer stock) {
		Stock = stock;
	}

	
	public AdminModel () {
		this.tablename = "products";
	}
	
	
	
	@Override
	public void insert() {
		// TODO Auto-generated method stub
		
		String query = String.format("insert into %s values(null, ?, ?, ?, ?)", tablename);
		java.sql.PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setString(1, ProductName);
			ps.setString(2, ProductAuthor);
			ps.setInt(3, ProductPrice);
			ps.setInt(4, Stock);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		String query = String.format("update %s set ProductName = ?, ProductAuthor = ?, ProductPrice = ?, ProductStock = ? where ProductId = ? ", tablename);
		java.sql.PreparedStatement ps = con.prepareStatement(query);
	
		try {
			ps.setString(1, ProductName);
			ps.setString(2, ProductAuthor);
			ps.setInt(3, ProductPrice);
			ps.setInt(4, Stock);
			ps.setInt(5, ProductId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		String query = String.format("Delete from %s where ProductId = ? ", tablename);
		java.sql.PreparedStatement ps = con.prepareStatement(query);
	
		try {
			ps.setInt(1, ProductId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		
		Vector<Model> data = new Vector<>();
		String query = String.format("select * from %s", tablename);
		ResultSet rs = con.executeQuery(query);
		
		try {
			
			while(rs.next()){
				Integer id = rs.getInt("ProductId");
				String name = rs.getString("ProductName");
				String author = rs.getString("ProductAuthor");
				Integer price = rs.getInt("ProductPrice");
				Integer stock = rs.getInt("ProductStock");
				
				AdminModel prod = new AdminModel();
				prod.setProductId(id);
				prod.setProductName(name);
				prod.setProductAuthor(author);
				prod.setProductPrice(price);
				prod.setStock(stock);
				
				data.add(prod);
				
			}
			
			return data;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public ResultSet checkProduct(Integer productId) {
		ResultSet rs = null;
		String query = "SELECT * FROM products WHERE ProductId LIKE ?";
		
		PreparedStatement pQuery = con.prepareStatement(query);
		
		try {
			pQuery.setInt(1, productId);
			rs = pQuery.executeQuery();
			
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
