package auctionclient;

import web.User;

public class Registration {

    public User getUser(java.lang.String arg0) {
        web.RegistrationService service = new web.RegistrationService();
        web.Registration port = service.getRegistrationPort();
        return port.getUser(arg0);
    }

    public User registerUser(java.lang.String arg0) {
        web.RegistrationService service = new web.RegistrationService();
        web.Registration port = service.getRegistrationPort();
        return port.registerUser(arg0);
    }
    
}
