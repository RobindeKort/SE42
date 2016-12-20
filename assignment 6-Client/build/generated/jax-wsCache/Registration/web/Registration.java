
package web;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Registration", targetNamespace = "http://web/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Registration {


    /**
     * 
     * @param arg0
     * @return
     *     returns web.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUser", targetNamespace = "http://web/", className = "web.GetUser")
    @ResponseWrapper(localName = "getUserResponse", targetNamespace = "http://web/", className = "web.GetUserResponse")
    @Action(input = "http://web/Registration/getUserRequest", output = "http://web/Registration/getUserResponse")
    public User getUser(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns web.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "registerUser", targetNamespace = "http://web/", className = "web.RegisterUser")
    @ResponseWrapper(localName = "registerUserResponse", targetNamespace = "http://web/", className = "web.RegisterUserResponse")
    @Action(input = "http://web/Registration/registerUserRequest", output = "http://web/Registration/registerUserResponse")
    public User registerUser(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
