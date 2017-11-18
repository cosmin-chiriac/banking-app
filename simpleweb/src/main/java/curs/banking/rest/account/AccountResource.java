package curs.banking.rest.account;

import java.sql.SQLException;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import curs.banking.business.AccountService;
import curs.banking.db.utils.DataSourceConnectionFactory;
import curs.banking.model.Account;

@Path("/accounts")
public class AccountResource {

	@GET
	@Produces("application/json")
	public Collection<Account> getAccounts() throws Exception {
		return new AccountService(DataSourceConnectionFactory.factory()).loadAllAccounts();
	}

}
