package pl.piomin.services.kumuluz.customer;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.kumuluz.ee.discovery.annotations.RegisterService;

@RegisterService("customer-service")
@ApplicationPath("v1")
public class CustomerApplication extends Application {

}
