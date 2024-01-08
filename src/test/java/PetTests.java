import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PetTests extends BaseAPITest {

    @Test
    public void ObtainAllMascots(){
        /* As a User I want to obtain all the mascots */
        given()
                .spec(requestSpecification)
                .log().all()
        .when()
                .get("/v2/pet/findByStatus?status=available")
        .then()
                .log().body()
                .spec(responseSpecification)
                .statusCode(200);
    }

    @Test
    public void addANewMascot(){
        /* As a User I want to add a mascot */
        given()
                .spec(requestSpecification)
                .log().all()
                .basePath("/v2/pet")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"id\": 123,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                    .post()
                .then()
                    .log().body()
                    .spec(responseSpecification)
                    .statusCode(200);
    }

    @Test
    public void ObtainASpecificMascot(){
        /* As a User I want to obtain all the mascots */
        given()
                .spec(requestSpecification)
                .log().all()
        .when()
                .get("/v2/pet/2")
        .then()
                .log().body()
                .spec(responseSpecification)
                .statusCode(200);
    }
}
