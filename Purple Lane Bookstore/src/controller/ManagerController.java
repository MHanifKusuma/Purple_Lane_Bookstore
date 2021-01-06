package controller;

import java.util.Vector;

import core.controller.Controller;
import core.model.Model;
import core.view.View;
import model.AdminModel;
import model.DetailReportModel;
import model.ManagerModel;
import view.manager.FinancialReportPage;

public class ManagerController extends Controller {

	private ManagerModel TransactionModel;
	private static ManagerController controller;
	
	public ManagerController() {
		TransactionModel = new ManagerModel();
	}
	
	public static ManagerController getInstance() {
		return controller = (controller == null) ? new ManagerController() : controller;
	}
	
	public Vector<Model> getDetailTransaction(String id) {
		DetailReportModel detail = new DetailReportModel();
		detail.setTransactionId(id);
		return detail.getAll();
	}
	
	
	public Vector<Model> filterTheReport(String Year, String Month) {
		ManagerModel Date = new ManagerModel();
		Date.setYear(Year);
		Date.setMonth(Month);
		return Date.getFilteredData();
	}
	
	
	
	@Override
	public View view() {
		// TODO Auto-generated method stub
		return new FinancialReportPage();
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

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return TransactionModel.getAll();
	}

	public Vector<Model> getDataFiltered() {
		// TODO Auto-generated method stub
		return TransactionModel.getFilteredData();
	}
}
