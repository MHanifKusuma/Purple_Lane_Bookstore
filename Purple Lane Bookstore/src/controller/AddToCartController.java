package controller;

import java.util.Vector;

import core.controller.Controller;
import core.model.Model;
import core.view.View;
import model.CartModel;
import model.UserModel;

public class AddToCartController extends Controller{
	
	private CartModel CartModel;
	private static AddToCartController controller;
	
	public AddToCartController() {
		CartModel = new CartModel();
	}

	public static AddToCartController getInstance() {
		return controller = (controller == null) ? new AddToCartController() : controller;
	}
	
	@Override
	public View view() {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(UserModel user, int productId, int quantity) {
		// TODO Auto-generated method stub
		CartModel cart = new CartModel();
		cart.setUserId(user.getUserId());
		cart.setProductId(productId);
		cart.setQuantity(quantity);
		cart.insert();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	
	public Vector<Model> getAll(UserModel userId) {
		// TODO Auto-generated method stub
		return CartModel.getAll(userId);
	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		
	}
	
	public void getUserId (UserModel user) {
		user.getUserId();
	}

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
