package restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteBooking {

	RequestSpecification _requestSpecification;
	Response _response;
	ValidatableResponse  _validatableResponse;
	String baseURI="https://restful-booker.herokuapp.com/booking/1";

	@Test
	public void deleteBooking()
	{
		_validatableResponse = given()
				.baseUri(baseURI)
				.contentType(ContentType.JSON)
				.header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
				.when()
				.delete()
				.then()
				.assertThat().statusCode(201);
	
	}
}
