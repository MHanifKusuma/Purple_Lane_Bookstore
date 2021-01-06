package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import core.model.Model;

public class CartModel extends Model{

	private Integer userId;
	private Integer productId;
	private Integer quantity;
	private String productName;
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

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
		String query = String.format("Delete from %s where ProductId = ? AND UserId = ? ", tablename);
		java.sql.PreparedStatement ps = con.prepareStatement(query);
	
		try {
			ps.setInt(1, productId);
			ps.setInt(2, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public Vector<Model> getAll(UserModel user) {
		// TODO Auto-generated method stub
		Vector<Model> data = new Vector<>();
		String query = String.format("select "
										+ "%s.ProductId, products.ProductName, %s.ProductQuantity"
										+ " from %s join `products` on `%s`.`ProductId` = `products`.`ProductId`"
										+ " where %s.UserId = ?", tablename, tablename, tablename, tablename, tablename);
		
		PreparedStatement pQuery = con.prepareStatement(query);
		ResultSet rs = null;
		
		try {
			pQuery.setInt(1, user.getUserId());
			
			rs = pQuery.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			
			while(rs.next()){
				Integer productId = rs.getInt("ProductId");
				String productName = rs.getString("ProductName");
				Integer productQty = rs.getInt("ProductQuantity");
				
				CartModel cart = new CartModel();
				cart.setProductId(productId);
				cart.setProductName(productName);
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

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
