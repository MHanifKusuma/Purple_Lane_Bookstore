package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import core.model.Model;

public class PromoModel extends Model{
	private Integer promoId, promoDisc;
	private String promoCode, promoNote;

	

	public Integer getPromoId() {
		return promoId;
	}

	public void setPromoId(Integer promoId) {
		this.promoId = promoId;
	}

	public Integer getPromoDisc() {
		return promoDisc;
	}

	public void setPromoDisc(Integer promoDisc) {
		this.promoDisc = promoDisc;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getPromoNote() {
		return promoNote;
	}

	public void setPromoNote(String promoNote) {
		this.promoNote = promoNote;
	}

	public PromoModel() {
		// TODO Auto-generated constructor stub
		this.tablename = "promos";
	}
	
	public ResultSet checkPromoCode(String code) {
		ResultSet rs = null;
		String query = "SELECT * FROM promos WHERE PromoCode LIKE ?";
		
		PreparedStatement pQuery = con.prepareStatement(query);
		
		try {
			pQuery.setString(1, code);
			rs = pQuery.executeQuery();
			
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		String query = String.format("insert into %s values(null, ?, ?, ?)", tablename);
		java.sql.PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setString(1, promoCode);
			ps.setInt(2, promoDisc);
			ps.setString(3, promoNote);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update() {
		String query = String.format("update %s set PromoCode = ?, PromoDiscount = ?, PromoNote = ? where PromoId = ? ", tablename);
		java.sql.PreparedStatement ps = con.prepareStatement(query);
	
		try {
			ps.setString(1, promoCode);
			ps.setInt(2, promoDisc);
			ps.setString(3, promoNote);
			ps.setInt(4, promoId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete() {
		String query = String.format("Delete from %s where PromoId = ? ", tablename);
		java.sql.PreparedStatement ps = con.prepareStatement(query);
	
		try {
			ps.setInt(1, promoId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Vector<Model> getAll() {
		Vector<Model> data = new Vector<>();
		String query = String.format("select * from %s", tablename);
		ResultSet rs = con.executeQuery(query);
		
		try {
			
			while(rs.next()){
				Integer id = rs.getInt("PromoId");
				String code = rs.getString("PromoCode");
				Integer discount = rs.getInt("PromoDiscount");
				String note = rs.getString("PromoNote");
				
				PromoModel promo = new PromoModel();
				promo.setPromoId(id);
				promo.setPromoCode(code);
				promo.setPromoDisc(discount);
				promo.setPromoNote(note);
				
				data.add(promo);
				
			}
			
			return data;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
