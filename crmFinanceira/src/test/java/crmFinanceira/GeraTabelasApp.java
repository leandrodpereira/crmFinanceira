package crmFinanceira;


import javax.persistence.Persistence;

public class GeraTabelasApp {
		
	public static void main(String[] args) {
		
		Persistence.createEntityManagerFactory("CrmFinanceiraPU");
		
	
	}

}
