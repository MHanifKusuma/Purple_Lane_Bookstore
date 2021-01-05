package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import core.controller.Controller;
import core.model.Model;
import core.view.View;
import model.AdminModel;
import model.PromoModel;
import view.admin.AdminPage;

public class PromoController extends Controller {

	PromoModel promos;
	private static PromoController controller;
	
	public PromoController() {
		promos = new PromoModel();
	}
	
	public static PromoController getInstance() {
		return controller = (controller == null) ? new PromoController() : controller;
	}

	@Override
	public View view() {
		// TODO Auto-generated method stub
		return new AdminPage();
	}
	
	public static boolean isNumeric(String strNum) {
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
	
	public Integer attemptInsert(String code, String discount, String note, Integer PromoId) {
		// method ini merupakan method untuk memvalidasi input saat melakukan insert dan update promo
		
		ResultSet checkPromoCode = promos.checkPromoCode(code);
		int error = 0;
		
		if(code.equals("") || discount.equals("") || note.equals("")) {
			return error = -1; // error jika ada field yang kosong
		}
		
		try {
			if(checkPromoCode.next()) {
				return error = 1; // error jika saat melakukan insert sudah ada promo code yang tersimpan di database
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Vector<Model> promoList = getAll();
		
		for (Model model : promoList) {
			PromoModel p = (PromoModel) model;
			
			if(!p.getPromoId().equals(PromoId)) {
				if(p.getPromoCode().equals(code)) {
					return error = 2; // error jika saat melakukan update, promo code yang diubah sudah ada di database.
				}					  // namun, jika promo code tidak diubah (misal hanya mengubah diskon/note)
			}						  // tidak menimbulkan error.
		}
		
		if(!isNumeric(discount)) {
			return error = 3; // error jika diskon tidak numeric
		}
		else {
			Integer disc = Integer.parseInt(discount);
			if(disc < 15000) {
				return error = 4; // error jika diskon kurang dari 15000
			}
		}
		
		return error;
	}

	
	public void insert(String code, Integer discount, String note) {
		// TODO Auto-generated method stub
		PromoModel newPromo = new PromoModel();
		newPromo.setPromoCode(code);
		newPromo.setPromoDisc(discount);
		newPromo.setPromoNote(note);
		newPromo.insert();
	}

	public void update(String code, Integer discount, String note, Integer Id) {
		// TODO Auto-generated method stub
		PromoModel upPromo = new PromoModel();
		upPromo.setPromoCode(code);
		upPromo.setPromoDisc(discount);
		upPromo.setPromoNote(note);
		upPromo.setPromoId(Id);
		upPromo.update();
	}

	public void delete(Integer Id) {
		
		PromoModel delPromo = new PromoModel();
		delPromo.setPromoId(Id);
		delPromo.delete();
	}

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return promos.getAll();
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

}
