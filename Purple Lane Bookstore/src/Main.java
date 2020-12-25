import connect.Connect;
import core.view.View;
import view.LoginView;

public class Main {
	
	
	public Main() {
		new Connect();
		
		new LoginView().showForm();
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
