package com.omer.facades;

import com.omer.exceptions.AuthenticationsExeption;

public class LoginManager {

	private static LoginManager instance = null;

	private LoginManager() {

	}

	public static LoginManager getInstance() {
		if (instance == null)
			synchronized (LoginManager.class) {
				if (instance == null)
					instance = new LoginManager();
			}

		return instance;
	}

	public ClientsFacade login(String email, String password, ClientType clientType) throws AuthenticationsExeption {
		AdminFacade adminFacade = new AdminFacade();
		CustomerFacade customerFacade = new CustomerFacade();
		CompanyFacade companyFacade = new CompanyFacade();
		if (clientType == ClientType.Administrator && adminFacade.login(email, password))
			return adminFacade;
		else if (clientType == ClientType.Customer && customerFacade.login(email, password))
			return customerFacade;
		else if (clientType == ClientType.Company && companyFacade.login(email, password))
			return companyFacade;
		else {
			System.out.println("Incorrect Email and/or password entered. Please try again.");
			return null;
		}
	}
}
