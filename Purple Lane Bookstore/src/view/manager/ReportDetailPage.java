package view.manager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.AdminController;
import core.model.Model;
import core.view.View;
import model.AdminModel;
import model.DetailReportModel;

public class ReportDetailPage extends View{

	private JPanel top, mid, bot;
	private JLabel filterTxt;
	private JTable reportDetailList;
	private JScrollPane sp;
	private JButton back;
	private JComboBox<String> Year;
	private JComboBox<String> Month;
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	Vector<Model> ItemDetail;
	DetailReportModel model;
	
	public ReportDetailPage(Vector<Model> ItemDetail, DetailReportModel model) {
		// TODO Auto-generated constructor stub
		super();
		this.ItemDetail = ItemDetail;
		this.model = model;
		this.height = 500;
		this.width = 700;
		addComponent(ItemDetail);
		
	}
	
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		back = new JButton("Back");
		
		top = new JPanel();
		mid = new JPanel();
		bot = new JPanel();
		
		reportDetailList = new JTable();
		sp = new JScrollPane(reportDetailList);
		
		
	}

	@Override
	public void addComponent() {
		// TODO Auto-generated method stub
		
		top.add(sp);
		bot.add(back);
		
		add(top, BorderLayout.NORTH);
		add(bot, BorderLayout.SOUTH);
	}

	public void addComponent(Vector<Model> ItemDetail) {
		loadData();
	}
	
	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new FinancialReportPage().showForm();
				dispose();
			}
		});
		
	}

	
	public void loadData() {
		header = new Vector<>();
		data = new Vector<>();
		
		header.add("Product ID");
		header.add("Product Name");
		header.add("Product Quantity");

	
		for (Model model : ItemDetail) {
			DetailReportModel p = (DetailReportModel) model;
			detail = new Vector<>();
			detail.add(p.getProductId().toString());
			detail.add(p.getProductName());
			detail.add(p.getProductQuantity());
			
			
			data.add(detail);
		}
		
		
		DefaultTableModel dtm = new DefaultTableModel(data, header) {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			
		};
		
		
		reportDetailList.setModel(dtm);
		
		
	}
	
}
