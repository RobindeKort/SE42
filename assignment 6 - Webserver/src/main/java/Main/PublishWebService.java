package Main;

import javax.xml.ws.Endpoint;
import web.Auction;
import web.Registration;

public class PublishWebService {

    private static final String urlAuction = "http://localhost:8080/Auction";
    private static final String urlRegistration = "http://localhost:8080/Registration";

    public static void main(String[] args) {
        Endpoint.publish(urlAuction, new Auction());
        Endpoint.publish(urlRegistration, new Registration());
    }
}
