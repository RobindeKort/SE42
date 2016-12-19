package web;

import auction.domain.User;
import auction.service.RegistrationMgr;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Registration {

    private final RegistrationMgr registrationMgr = new RegistrationMgr();

    @WebMethod
    public User registerUser(String email) {
        return registrationMgr.registerUser(email);
    }

    @WebMethod
    public User getUser(String email) {
        return registrationMgr.getUser(email);
    }
}
