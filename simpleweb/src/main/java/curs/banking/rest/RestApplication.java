package curs.banking.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import curs.banking.model.Transaction;
import curs.banking.rest.account.AccountResource;
import curs.banking.rest.customer.CustomerResource;
import curs.banking.rest.transaction.TransactionResource;

@ApplicationPath("/rest1")
public class RestApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
		Set<Class<?>> clzz = new HashSet<>();
		clzz.add(CustomerResource.class);
		clzz.add(AccountResource.class);
		clzz.add(TransactionResource.class);
    	return clzz;
    }
}
