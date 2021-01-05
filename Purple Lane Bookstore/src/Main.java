import connect.Connect;
import core.view.View;
import view.admin.AdminPage;
import view.customer.AddToCartPage;
import view.customer.LoginView;
import view.customer.ProductPage;
import view.customer.RegisterView;
import view.manager.HireStaffPage;
import view.promotion.PromoPage;

public class Main {
	
	
	public Main() {
		
//		new LoginView().showForm();
//		new RegisterView().showForm();
//		new ProductPage().showForm();
//		new AddToCartPage().showForm();
//		new AdminPage().showForm();
		new HireStaffPage().showForm();
//		new PromoPage().showForm();
//		new AdminPage().showForm();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
