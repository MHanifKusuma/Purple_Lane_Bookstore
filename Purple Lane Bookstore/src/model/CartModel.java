package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import core.model.Model;

public class CartModel extends Model{

	private Integer userId;
	private Integer productId;
	private Integer quantity;
	
	public CartModel() {
		// TODO Auto-generated constructor stub
		this.tablename = "carts";
	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		String query = String.format("insert into %s values(?, ?, ?)", tablename);
		java.sql.PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setInt(1, userId);
			ps.setInt(2, productId);
			ps.setInt(3, quantity);
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
		Vector<Model> data = new Vector<>();
		String query = String.format("select * from %s", tablename);
		ResultSet rs = con.executeQuery(query);
		
		try {
			
			while(rs.next()){
				Integer userId = rs.getInt("UserId");
				Integer productId = rs.getInt("ProductId");
				Integer productQty = rs.getInt("ProductQuantity");
				
				CartModel cart = new CartModel();
				cart.setProductId(userId);
				cart.setProductId(productId);
				cart.setQuantity(productQty);
				
				data.add(cart);
				
			}
			
			return data;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
}
