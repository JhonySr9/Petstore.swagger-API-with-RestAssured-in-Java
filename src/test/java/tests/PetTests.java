package tests;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class PetTests extends BaseAPITest {
    File newMascotJson = new File("src/test/java/utils/NewMascot.json");
    public final String GET_ALL_MASCOTS = "/v2/pet/findByStatus";
    public final String GET_INDIVIDUAL_MASCOT = "/v2/pet";

    @Test (groups = "smoke")
    public void ObtainAllMascotsAsAvailable(){
        /* As a user,
        I want to verify that all mascots in the "Available" category
        have a status value of "available". */

        given()
                .spec(requestSpecification)
                .log().all()
        .when()
                .get(GET_ALL_MASCOTS + "?status=available")
        .then()
                .log().body()
                .spec(responseSpecification)
                .assertThat().body("status", everyItem(equalTo("available")));
    }

    @Test (groups = "smoke")
    public void ObtainAllMascotsAsPending(){
        /* As a user,
        I want to verify that all mascots in the "Pending" category
        have a status value of "pending". */

        given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get(GET_ALL_MASCOTS + "?status=pending")
                .then()
                .log().body()
                .spec(responseSpecification)
                .assertThat().body("status", everyItem(equalTo("pending")));
    }

    @Test (groups = "smoke")
    public void ObtainAllMascotsAsSold(){
        /* As a user,
        I want to verify that all mascots in the "Sold" category
        have a status value of "sold". */

        //Test
        given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get(GET_ALL_MASCOTS + "?status=sold")
                .then()
                .log().body()
                .spec(responseSpecification)
                .assertThat().body("status", everyItem(equalTo("sold")));
    }

    @Test (groups = "sanity")
    public void addANewMascot(){
        /* As a User I want to add a mascot */

        given()
                .spec(requestSpecification)
                .log().all()
                .basePath("/v2/pet")
                .body(newMascotJson)
        .when()
                .post()
        .then()
                .log().body()
                .spec(responseSpecification)
                .assertThat()
                    .body("name", equalTo("Catbug"))
                    .body("status", equalTo("available"))
                    .body("photoUrls[0]", equalTo("Nothing"));
    }

    @Test (groups = "sanity")
    public void ObtainASpecificMascot(){
        /* As a User I want to obtain all the mascots */

        given()
                .spec(requestSpecification)
                .log().all()
        .when()
                .get(GET_INDIVIDUAL_MASCOT + "/5")
        .then()
                .log().body()
                .spec(responseSpecification)
                .assertThat()
                    .body("name", equalTo("doggie"))
                    .body("status", equalTo("string"))
                    .body("photoUrls[0]", equalTo("string"));
    }
}
