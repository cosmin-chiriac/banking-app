package curs.banking.rest.transaction;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import curs.banking.business.TransactionService;
import curs.banking.db.utils.DataSourceConnectionFactory;
import curs.banking.model.TransactionPair;

@Path("/transactions")
public class TransactionResource {
	@GET
	@Produces("application/json")
	public Collection<TransactionPair> getTransactions() throws Exception {
		return new TransactionService(DataSourceConnectionFactory.factory()).loadAllTransactionPairs();
	}

}
