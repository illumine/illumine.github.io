package gr.illumine.wsclient.alc;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;

import gr.illumine.wsclient.stub.ZALCINTERFACE;
import gr.illumine.wsclient.stub.ZALCINTERFACE_Service;
import gr.illumine.wsclient.stub.ZAlcAlertinterfaceRfcException;
import gr.illumine.wsclient.stub.ZalcTtIntfAlertparameter;

/**
 * 
 * @author Michael Mountrakis mountrakis.AT.illumine.gr
 * 
 * Simple Client to ALC Web Service
 * This is an example how we can actually call the ALC and create an alert.
 *
 */

public class AlcClient {
	
	private static final Logger log= Logger.getLogger( AlcClient.class.getName() );
	
	/* 
	 * Use this static initializer to provide Basic Authentication for the Web Service Consumption
	 */
	static {
	    java.net.Authenticator.setDefault(new java.net.Authenticator() {

	        @Override
	        protected java.net.PasswordAuthentication getPasswordAuthentication() {
	            return new java.net.PasswordAuthentication("myuser", "mypassword".toCharArray());
	        }
	    });
	}
	
	
	
	public static void main(String [] argv) throws MalformedURLException, ZAlcAlertinterfaceRfcException{
        /*
         * Use the following settings to specify how this client will utilize the X509 trust store
         * called mykeystore.jks. In this trustore, it is stored the server�s public certificate
         * Also the trustore/keystores are password protected with a password "password"
         */
		System.setProperty("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol");
        System.setProperty("javax.net.ssl.keyStore","mykeystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword","password");
        System.setProperty("javax.net.ssl.keyStoreType", "JKS");
        System.setProperty("javax.net.ssl.trustStore","mykeystore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword","password");
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        System.out.println("Ok passed.");
		
        /* Following options enable logging of all communication to the console
         * We are most interested in the request response SOAP Messages   
        */
        System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
		
        /*
         * Get a reference to the web service using the given Qualified Name
         */
        ZALCINTERFACE_Service service = new ZALCINTERFACE_Service( new URL("myserver-name.com/wsd/alc_interface?wsdl"),
       new QName("urn:com:myserver-name:document:sap:soap:functions:mc-style", 
                      "ZALC_INTERFACE"));
		
		/*
		 * From this service get the proper port
		 */
		ZALCINTERFACE port = service.getZALCINTERFACE();
		
		//ZALCINTERFACE port = service.getZALCINTERFACESoap12();		
/*		
		//
		// Prepare the call parameters and output holders
		//
		String alertMessage = "This is a test";
		String alertSender = "SCP Jira";
		String alertSubject = "Test alert - ignore";
		Date now = new Date();
		BigDecimal alertTimestamp = new BigDecimal(now.getTime());
		Holder<String> alertId = new Holder<String>();
		Holder<String> errorMessage = new Holder<String>();
		
		//
		// Call the remote web method
		//
		debug("Arguments for zalcPostAlertRfc() call: ");
		debug("alertMessage = " + alertMessage );
		debug("alertSender = " + alertSender );
		debug("alertSubject = " + alertSubject);
		debug("alertTimestamp = " + now );
		debug("Just before call zalcPostAlertRfc() ");
		port.zalcPostAlertRfc(alertMessage, alertSender, alertSubject, alertTimestamp, alertId, errorMessage);
*/		
		String alertSeverity = "LOW";
		String alertSource = "SCP Jira";
		String alertText = "This is a text";
		ZalcTtIntfAlertparameter alertparameterTab  = new ZalcTtIntfAlertparameter ();
		String impact = "test";
		String inboundChecks = "none";
		String monitor = "monitor";
		String sender = "SCP Jira";
		// out params
        Holder<BigDecimal> alertFirsttimestamp = new Holder<BigDecimal>();
        Holder<String> alertId = new Holder<String>();
        Holder<String> alertProcessor = new Holder<String>();
        Holder<String> alertStatus= new Holder<String>();
        Holder<String> alertUrl= new Holder<String>();
        Holder<String> objectKey= new Holder<String>();
        Holder<String> objectRepository= new Holder<String>();
        Holder<String> objectStatus= new Holder<String>();
        Holder<String> performedAction= new Holder<String>();
        Holder<Integer> returncode= new Holder<Integer>();
        Holder<String> returnmessage= new Holder<String>();
		port.zAlcAlertinterfaceRfc(alertSeverity, alertSource, alertText, alertparameterTab, impact, inboundChecks, monitor, sender, 
				alertFirsttimestamp, alertId, alertProcessor, alertStatus, alertUrl, 
				objectKey, objectRepository, objectStatus, performedAction, returncode, returnmessage);

		debug("Service Returned!");
		debug("alertId reference : " + alertId + " alertId Value : " + alertId.value);
		debug("returnmessage Reference : " + returnmessage.toString() + " returnmessage value " + returnmessage.value );
	}
	
	
	private static void debug(String s){
		log.info(s);
	}
	
}
