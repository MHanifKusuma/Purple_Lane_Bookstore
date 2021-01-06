package view.manager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.ManagerController;
import core.model.Model;
import core.view.View;
import model.DetailReportModel;
import model.ManagerModel;

public class FinancialReportPage extends View {


	private JPanel top, mid, bot;
	private JButton register, filter, viewAll;
	private JLabel filterTxt;
	private JTable reportList;
	private JScrollPane sp;
	private JComboBox<String> Year;
	private JComboBox<String> Month;
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	
	

	public FinancialReportPage() {
		super();
		this.height = 450;
		this.width = 700;
		
	}


	@Override
	public void initialize() {
		String[] YearSet = new String[] {
				"2020",
				"2021",
				"2022"
		};
		
		 String[] MonthSet = new String[] {
				"January","February",
				"March","April",
				"May","June",
				"July","August",
				"September","October",
				"November","December"
				
		};
		
		
		// TODO Auto-generated method stub
		reportList = new JTable();
		sp = new JScrollPane(reportList);
		sp.setPreferredSize(new Dimension(600, 300));
		 
		Year = new JComboBox<String>(YearSet);
		Month = new JComboBox<String>(MonthSet);
		
		filterTxt = new JLabel("Filter the report list by date:");
		
		top = new JPanel();
		mid = new JPanel();
		bot = new JPanel();
		
		filter = new JButton("Filter Report");
		register = new JButton("Hire Staff");
		viewAll = new JButton("View All Report");
		
	}

	@Override
	public void addComponent() {
		// TODO Auto-generated method stub
		top.add(sp);
		
		mid.add(filterTxt);
		mid.add(Year);
		mid.add(Month);
		
		bot.add(viewAll);
		bot.add(filter);
		bot.add(register);
	
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
		
		loadData();
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
		
		
		filter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String YearValue = (String) Year.getSelectedItem(); 
				String MonthValue = (String) Month.getSelectedItem();
				
				if(MonthValue.equals("January")) {
				    MonthValue = "1";
				}
				else if (MonthValue.equals("February")) {
				    MonthValue = "2";
				}
				else if (MonthValue.equals("March")) {
				    MonthValue = "3";
				}
				else if (MonthValue.equals("April")) {
				    MonthValue = "4";
				}
				else if (MonthValue.equals("May")) {
				    MonthValue = "5";
				}
				else if (MonthValue.equals("June")) {
				    MonthValue = "6";
				}
				else if (MonthValue.equals("July")) {
				    MonthValue = "7";
				}
				else if (MonthValue.equals("August")) {
				    MonthValue = "8";
				}
				else if (MonthValue.equals("September")) {
				    MonthValue = "9";
				}
				else if (MonthValue.equals("October")) {
				    MonthValue = "10";
				}
				else if (MonthValue.equals("November")) {
				    MonthValue = "11";
				}
				else if (MonthValue.equals("December")) {
				    MonthValue = "12";
				}
				
				showFilteredData(YearValue, MonthValue);
			
				
			
				
			}
		});
		
		viewAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loadData();
			}
		});
		
		reportList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Vector<Model> itemDetail = new Vector<>();
				DetailReportModel model = null;
				
				// TODO Auto-generated method stub
				int row = reportList.getSelectedRow();
				String transactionId = (String) reportList.getValueAt(row, 0);
				
				itemDetail = ManagerController.getInstance().getDetailTransaction(transactionId);
				
				new ReportDetailPage(itemDetail, model).showForm();
				dispose();
				
				System.out.println(transactionId);
				
			}
		});
		
		
	}
	
	public void showFilteredData(String Year, String Month) {
		header = new Vector<>();
		data = new Vector<>();
		
		Vector<Model>  FilteredData = ManagerController.getInstance().filterTheReport(Year, Month);
		
		header.add("Transaction ID");
		header.add("Transaction Date");
		header.add("Payment Type");
		header.add("Card Number");
		header.add("Promo ID");
		header.add("User ID");
		
		//Vector<Model> transactionList = ManagerController.getInstance().getDataFiltered();
		
		for (Model model : FilteredData) {
			ManagerModel man = (ManagerModel) model;
			detail = new Vector<>();
			detail.add(man.getTransactionId().toString());
			detail.add(man.getTransactionDate());
			detail.add(man.getPaymentType());
			detail.add(man.getCardNumber().toString());
			detail.add(man.getPromoId().toString());
			detail.add(man.getUserId().toString());
			
			data.add(detail);
			
		}
		
		
		DefaultTableModel dtm = new DefaultTableModel(data, header) {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
		
		reportList.setModel(dtm);
	}
	
	public void loadData() {
		header = new Vector<>();
		data = new Vector<>();
		
		header.add("Transaction ID");
		header.add("Transaction Date");
		header.add("Payment Type");
		header.add("Card Number");
		header.add("Promo ID");
		header.add("User ID");
		
		Vector<Model> transactionList = ManagerController.getInstance().getAll();
		
		for (Model model : transactionList) {
			ManagerModel man = (ManagerModel) model;
			detail = new Vector<>();
			detail.add(man.getTransactionId().toString());
			detail.add(man.getTransactionDate());
			detail.add(man.getPaymentType());
			detail.add(man.getCardNumber().toString());
			detail.add(man.getPromoId().toString());
			detail.add(man.getUserId().toString());
			
			data.add(detail);
			
		}
		
		
		DefaultTableModel dtm = new DefaultTableModel(data, header) {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
		
		reportList.setModel(dtm);
	}

}
