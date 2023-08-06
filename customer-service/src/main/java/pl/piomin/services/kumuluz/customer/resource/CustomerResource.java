package pl.piomin.services.kumuluz.customer.resource;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.logs.cdi.LogParams;

import pl.piomin.services.kumuluz.customer.model.Account;
import pl.piomin.services.kumuluz.customer.model.Customer;
import pl.piomin.services.kumuluz.customer.model.CustomerType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("customers")
@RequestScoped
public class CustomerResource {

    private List<Customer> customers;

    @Inject
    @DiscoverService(value = "account-service", version = "1.0.x", environment = "dev")
    private WebTarget target;

    public CustomerResource() {
        customers = new ArrayList<>();
        customers.add(new Customer(1, "12345", "Adam Kowalski", CustomerType.INDIVIDUAL));
        customers.add(new Customer(2, "12346", "Anna Malinowska", CustomerType.INDIVIDUAL));
        customers.add(new Customer(3, "12347", "Paweł Michalski", CustomerType.INDIVIDUAL));
        customers.add(new Customer(4, "12348", "Karolina Lewandowska", CustomerType.INDIVIDUAL));
    }

    @GET
    @Path("pesel/{pesel}")
    @Log(value = LogParams.METRICS, methodCall = true)
    public Customer findByPesel(@PathParam("pesel") String pesel) {
        return customers.stream().filter(it -> it.getPesel().equals(pesel)).findFirst().get();
    }

    @GET
    @Log(value = LogParams.METRICS, methodCall = true)
    public List<Customer> findAll() {
        return customers;
    }

    @GET
    @Path("{id}")
    @Log(value = LogParams.METRICS, methodCall = true)
    public Customer findById(@PathParam("id") Integer id) {
        Customer customer = customers.stream().filter(it -> it.getId().intValue() == id.intValue()).findFirst().get();
        WebTarget t = target.path("v1/accounts/customer/" + customer.getId());
        List<Account> accounts = t.request().buildGet().invoke(List.class);
        customer.setAccounts(accounts);
        return customer;
    }

}
