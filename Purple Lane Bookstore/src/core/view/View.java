package core.view;

import javax.swing.JFrame;

public abstract class View extends JFrame{
	
	protected int width;
	protected int height;

	public View() {
		// TODO Auto-generated constructor stub
		initialize();
		addComponent();
		addListener();
	}
	
	public void showForm(){
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	
	public abstract void initialize();
	public abstract void addComponent();
	public abstract void addListener();

}
