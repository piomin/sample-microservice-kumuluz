package pl.piomin.services.kumuluz.account;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.kumuluz.ee.discovery.annotations.RegisterService;

@RegisterService("account-service")
@ApplicationPath("v1")
public class AccountApplication extends Application {

}
