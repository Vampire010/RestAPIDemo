package JsonManipulation;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import java.nio.file.Files;
import java.nio.file.Paths;

public class readreqBodyFromJSONFile 
{
	@Test
    public void testCreateBooking() {
        // Read the JSON file and extract its content as a String
        String jsonFilePath = "./src/main/resources/reqBody.json";
        
        String requestBody = readJsonFile(jsonFilePath);

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        // Make API call with JSON request body from file
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/booking")
                .then()
                .statusCode(200);
    }

    // Method to read JSON file and return its content as String
    private String readJsonFile(String filePath) {
        try 
        {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }

}
