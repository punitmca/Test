import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class BasicApiTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI=("https://rahulshettyacademy.com");
	    String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	    .body(payload.addPayload()).when().post("maps/api/place/add/json")
	              .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
	              .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
	    System.out.println(response);
	    
	    JsonPath js = new JsonPath(response);
	    String placeId= js.getString("place_id");
	    
	    System.out.println(placeId);
	    
	    given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	    .body("{\r\n" + 
	    		"\"place_id\":\""+placeId+"\",\r\n" + 
	    		"\"address\":\"70 winter walk, USA\",\r\n" + 
	    		"\"key\":\"qaclick123\"\r\n" + 
	    		"}").when().put("maps/api/place/update/json")
	                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
	    

	}

}
