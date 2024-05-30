package restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

public class CreateAuthToken 
{
	RequestSpecification _requestSpecification;
	Response _response;
	ValidatableResponse  _validatableResponse;
	
	@Test
	public void postCreateToken()
	{
		
		String jsonString ="{\"username\":\"admin\" , \"password\" : \"password123\"}";
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com/auth";
		
		//create a _requestSpecification
		_requestSpecification = RestAssured.given();
		
		_requestSpecification.contentType(ContentType.JSON);
		_requestSpecification.body(jsonString);
		_response = _requestSpecification.post();
		
		//String responsestring = _response.prettyPrint();
		
		_validatableResponse = _response.then();
		_validatableResponse.statusCode(200);
		_validatableResponse.statusLine("HTTP/1.1 200 OK");
		
	}
}
