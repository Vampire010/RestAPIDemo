package restfulbooker;
import io.restassured.RestAssured;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class UpdateBooking {
	
	RequestSpecification _requestSpecification;
	Response _response;
	ValidatableResponse  _validatableResponse;
	
	@Test
	public void updateBooking()
	{
		String baseURI="https://restful-booker.herokuapp.com/booking/1";
		
		String jsonString = "{\r\n"
				+ "    \"firstname\" : \"Bagwan\",\r\n"
				+ "    \"lastname\" : \"Brown\",\r\n"
				+ "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}";
		
		_validatableResponse = given()
				.baseUri(baseURI)
				.contentType(ContentType.JSON)
				.header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
				.body(jsonString)
				.when()
				.put()
				.then()
				.assertThat().statusCode(200)
				.body("firstname", equalTo("Bagwan"));
		System.out.println(_validatableResponse.extract().asPrettyString());
	}

}
