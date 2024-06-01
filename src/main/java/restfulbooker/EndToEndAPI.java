package restfulbooker;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class EndToEndAPI
{
	RequestSpecification _requestSpecification;
	Response _response;
	ValidatableResponse  _validatableResponse;
	
	String TestbaseURI="https://restful-booker.herokuapp.com";
	int bookingId;
	
		@BeforeClass
		public void setUp()
		{		
			RestAssured.baseURI = TestbaseURI;
		}
		
		@Test(priority = 0)
		public void createBooking()
		{
			String jsonreqBody="{\r\n"
					+ "    \"firstname\" : \"Jack\",\r\n"
					+ "    \"lastname\" : \"Brown\",\r\n"
					+ "    \"totalprice\" : 111,\r\n"
					+ "    \"depositpaid\" : true,\r\n"
					+ "    \"bookingdates\" : {\r\n"
					+ "        \"checkin\" : \"2018-01-01\",\r\n"
					+ "        \"checkout\" : \"2019-01-01\"\r\n"
					+ "    },\r\n"
					+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
					+ "}";
			//create a _requestSpecification
					_requestSpecification = RestAssured.given();
					
					_requestSpecification.contentType(ContentType.JSON);
					_requestSpecification.body(jsonreqBody);
					_response = _requestSpecification.post("/booking");
					
					//validate ststus code
					int statusCode = _response.getStatusCode();
					Assert.assertEquals(statusCode,200,"Expected Status Code : 200");
					
					//validate response attributres
					String responseBody = _response.getBody().asString();
					Assert.assertTrue(responseBody.contains("firstname"));	
					
					//extarct bookingId from Json response
					JsonObject respBody = new Gson().fromJson(_response.getBody().asString(),JsonObject.class);
					bookingId = respBody.get("bookingid").getAsInt();
					System.out.println("bookingId: " + bookingId);					
		}
		@Test(priority = 1)
		public void getBookingIdDetail()
		{
			System.out.println("******************getBookingIdDetail*************************");
			

			//create a _requestSpecification
			_requestSpecification = RestAssured.given();
			
			//Calling GET Method
			_response = _requestSpecification.get("/booking/"+bookingId);
			
			String reststring = _response.prettyPrint();
			//System.out.println(reststring);
			
			_validatableResponse = _response.then();
			_validatableResponse.statusCode(200);
			_validatableResponse.statusLine("HTTP/1.1 200 OK");			
		}

}

/*
 Step1:  CreateBooking - extarct only "bookingid": 1
 Step2: Get Details bookingid generated Step1
 Step3: Update daetals for  bookingid generated Step1
 Step4: Delete Booking for bookingid generated Step1
*/