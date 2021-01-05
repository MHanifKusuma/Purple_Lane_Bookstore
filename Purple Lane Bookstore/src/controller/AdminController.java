package controller;

import java.util.Vector;

import core.controller.Controller;
import core.model.Model;
import core.view.View;
import model.AdminModel;
import view.admin.AdminPage;

public class AdminController extends Controller {

	private AdminModel ProductModel;
	private static AdminController controller;
	
	
	
	public AdminController() {
		ProductModel = new AdminModel();
	}

	public static AdminController getInstance() {
		return controller = (controller == null) ? new AdminController() : controller;
	}
	
	
	@Override
	public View view() {
		// TODO Auto-generated method stub
		return new AdminPage();
	}

	
	public void insert(String name, String author, Integer price, Integer stock) {
		// TODO Auto-generated method stub
		AdminModel product = new AdminModel();
		product.setProductName(name);
		product.setProductAuthor(author);
		product.setProductPrice(price);
		product.setStock(stock);
		product.insert();
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
	
	public Integer insertvalidator(String name, String author, String price, String stock) {
		int error = 0;
		
		if (name.equals("") || author.equals("") || price.equals("") || stock.equals("")) {
			return error = 1;
		}else if ((!isNumeric(price)) || (!isNumeric(stock))) {
			return error = 2;
		}
		return error;
	}
	
	
	public void update(String name, String author, Integer price, Integer stock, Integer Id) {
		// TODO Auto-generated method stub
		AdminModel product = new AdminModel();
		product.setProductName(name);
		product.setProductAuthor(author);
		product.setProductPrice(price);
		product.setStock(stock);
		product.setProductId(Id);
		product.update();
	}

	
	public void delete(Integer Id) {
		// TODO Auto-generated method stub
		AdminModel product = new AdminModel();
		product.setProductId(Id);
		product.delete();
	}

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return ProductModel.getAll();
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
