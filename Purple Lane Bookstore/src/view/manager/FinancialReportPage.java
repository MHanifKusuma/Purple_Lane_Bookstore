package view.manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import core.view.View;

public class FinancialReportPage extends View {


	private JPanel top, mid, bot;
	private JButton register;

	


	public FinancialReportPage() {
		super();
		this.height = 200;
		this.width = 350;
		
	}


	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		top = new JPanel();
		mid = new JPanel();
		bot = new JPanel();
		
		register = new JButton("Register");

		
	}

	@Override
	public void addComponent() {
		// TODO Auto-generated method stub
		bot.add(register);
	
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
		
		
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new HireStaffPage().showForm();
				dispose();
			}
		});
		
		
	}

}
