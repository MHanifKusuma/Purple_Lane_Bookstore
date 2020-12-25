import connect.Connect;
import core.view.View;
import view.customer.LoginView;
import view.customer.ProductPage;
import view.customer.RegisterView;

public class Main {
	
	
	public Main() {
		new Connect();
		
//		new LoginView().showForm();
//		new RegisterView().showForm();
		new ProductPage().showForm();
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
