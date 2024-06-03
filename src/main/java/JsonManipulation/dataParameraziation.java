package JsonManipulation;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class dataParameraziation
{
	@Test(dataProvider = "bookingData")
	public void createBooking(String firstname , String lastname , int TotalProce)
	{
		 // JSON payload for creating a booking
        String jsonPayload = "{\"firstname\": \"" + firstname + "\", " +
                             "\"lastname\": \"" + lastname + "\", " +
                             "\"totalprice\": " + TotalProce + ", " +
                             "\"depositpaid\": true, " +
                             "\"bookingdates\": {\"checkin\": \"2024-06-01\", \"checkout\": \"2024-06-05\"}, " +
                             "\"additionalneeds\": \"Breakfast\"}";

		
		 // Define base URI and path
        String baseURI= "https://restful-booker.herokuapp.com";
        String resourcePath = "/booking/2"; // Assuming you are updating booking with ID 1
        
        // Send PUT request and validate response
        given()
            .contentType(ContentType.JSON)
            .header("Accept", "application/json")
            .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
            .body(jsonPayload)
        .when()
            .put(baseURI+resourcePath)
        .then()
            .statusCode(200)
            .body("firstname", equalTo("James"))
            .body("lastname", equalTo("Brown"))
            .body("totalprice", equalTo(111));
        
	}
	
	@DataProvider(name = "bookingData")
	public Object[][] bookingData()
	{
		return new Object[][]
				{
					{"James","Brown",111}
				};
	}
}
