package com.soapexample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {

	public final static String NAMESPACE = "http://tempuri.org/";
	public final static String URL = "http://localhost/WebService/RequestAgenLogin.php";
	public final static String SOAP_ACTION = "http://tempuri.org/#SimpleWebServiceTest";
	public final static String METHOD_NAME = "SimpleWebServiceTest";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SoapObject request = new SoapObject(MainActivity.NAMESPACE,
				MainActivity.METHOD_NAME);
		request.addProperty("name", "Kartik Bhatt");
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = false;
		// request.addProperty("AgentId", agentId + ""); // sending data to
		// webservice
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		try {
			androidHttpTransport.call(SOAP_ACTION, envelope); // this will make
																// actual web
																// request
			// 1. for vector

			String name = (String) envelope
					.getResponse();
			Toast.makeText(this, name, Toast.LENGTH_LONG).show();
			// 2. for simple response soapObject
			
			// use 1 or 2 as per requirement.
			
			Pattern EMAIL_ADDRESS_PATTERN =
		            Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
		                            "\\@" +
		                            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
		                            "(" +
		                            "\\." +
		                            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
		                            ")+");
		        Matcher matcher = EMAIL_ADDRESS_PATTERN.matcher("kd.kartik@gmail.com");
		        if (! matcher.matches()) {
		            Log.d("Test", "Please enter valid email id");
					
		        }
		        else
		        	Log.d("Test", "your email is valid");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
