package restfulbooker;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetBookingIds
{
	RequestSpecification _requestSpecification;
	Response _response;
	ValidatableResponse  _validatableResponse;
	
	@Test
	public void getBookingIds()
	{
		System.out.println("******************getBookingIds*************************");
		RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking";

		//create a _requestSpecification
		_requestSpecification = RestAssured.given();
		
		//Calling GET Method
		_response = _requestSpecification.get();
		
		String reststring = _response.prettyPrint();
		//System.out.println(reststring);
		
		_validatableResponse = _response.then();
		_validatableResponse.statusCode(200);
		_validatableResponse.statusLine("HTTP/1.1 200 OK");
		System.out.println("*******************************************");

	}
	
	@Test
	public void getBookingIdDetail()
	{
		System.out.println("******************getBookingIdDetail*************************");
		RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking/1";

		//create a _requestSpecification
		_requestSpecification = RestAssured.given();
		
		//Calling GET Method
		_response = _requestSpecification.get();
		
		String reststring = _response.prettyPrint();
		//System.out.println(reststring);
		
		_validatableResponse = _response.then();
		_validatableResponse.statusCode(200);
		_validatableResponse.statusLine("HTTP/1.1 200 OK");
		System.out.println("*******************************************");


	}

}
