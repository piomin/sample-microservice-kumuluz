package pl.piomin.services.kumuluz.account.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.logs.cdi.LogParams;

import pl.piomin.services.kumuluz.account.model.Account;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("accounts")
public class AccountResource {

	private List<Account> accounts;
	
//	protected Logger logger = Logger.getLogger(Api.class.getName());
	
	public AccountResource() {
		accounts = new ArrayList<>();
		accounts.add(new Account(1, 1, "111111"));
		accounts.add(new Account(2, 2, "222222"));
		accounts.add(new Account(3, 3, "333333"));
		accounts.add(new Account(4, 4, "444444"));
		accounts.add(new Account(5, 1, "555555"));
		accounts.add(new Account(6, 2, "666666"));
		accounts.add(new Account(7, 2, "777777"));
	}
	
	@GET
	@Path("{number}")
	@Log(value = LogParams.METRICS, methodCall = true)
	public Account findByNumber(@PathParam("number") String number) {
//		logger.info(String.format("Account.findByNumber(%s)", number));
		return accounts.stream().filter(it -> it.getNumber().equals(number)).findFirst().get();
	}
	
	@GET
	@Path("customer/{customer}")
	@Log(value = LogParams.METRICS, methodCall = true)
	public List<Account> findByCustomer(@PathParam("customer") Integer customerId) {
//		logger.info(String.format("Account.findByCustomer(%s)", customerId));
		return accounts.stream().filter(it -> it.getCustomerId().intValue()==customerId.intValue()).collect(Collectors.toList());
	}
	
	@GET
	@Log(value = LogParams.METRICS, methodCall = true)
	public List<Account> findAll() {
//		logger.info("Account.findAll()");
		return accounts;
	}
	
}
