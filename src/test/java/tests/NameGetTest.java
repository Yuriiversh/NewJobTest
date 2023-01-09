package tests;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class NameGetTest {
    ResponseSpecification responseSpecification = null;

    @BeforeEach
    void beforeTest() {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectHeader("X-API-Quota-Request", "1.0")
                .build();
    }

    // Get recipe request
// авторизация в примере опущена, для повторения теста необходимо добавить параметр apiKey
    @Test
    void getRecipePositiveTest() {
        given()
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                //.queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                .then()
                .spec(responseSpecification);
        /*RestAssured.responseSpecification =
                responseSpecification
                        .expect()
                        .body(containsString("84578389"));*/
    }


    private Object apiKey = "09550fa5227b48faa64520bf998ce557";
    private Object username = "your-users-name604";

    @Test
    void getRecipeWithBodyChecksInGivenPositiveTest() {
        given()
                .queryParam("hash", "91cfc1cde36287369b55d715e2c8104f3f8a53cb")
                .queryParam("apiKey", apiKey)
                .queryParam("includeNutrition", "false")
                .expect()
                .body("vegetarian", is(false))
                .body("vegan", is(false))
                .body("license", equalTo("CC BY-SA 3.0"))
                .body("pricePerServing", equalTo(163.15F))
                .body("extendedIngredients[0].aisle", equalTo("Milk, Eggs, Other Dairy"))
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information");
    }

    @DisplayName("dsf")
    void testPost1() {
        given()
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"Yurii\"\n" +
                        "  },\n" +
                        "  \"name\": \"Artur\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"Baysic\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 1,\n" +
                        "      \"name\": \"Musya\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(200);

    }

    //AccountInfoResponse response = null;
    @Test
    void getAccountInfoWithExternalEndpointTest() {
        JsonPath response = given()
                .queryParam("cuisine", "African")
                .queryParam("number", 5)
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .body()

                .jsonPath();
        assertThat(response.get("totalResults"), CoreMatchers.equalTo(4));





        /**/
    }

    @Test
    void getAccountInfoWithExternalEndpointTest1() {
        List<Result> response = given()
                .queryParam("cuisine", "African")
                .queryParam("number", 5)
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .when()
                .contentType(ContentType.JSON)
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then().log().all()
                .extract()
                .body().jsonPath().getList("results", Result.class);
        int i = 0;

        // response.forEach();
       /* List<AddMealResponse> response =given()
                .pathParam("username", username)
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .extract()
                .body().jsonPath().getList(AddMealResponse)
                .as(AddMealResponse.class);
        assertThat(response.getStatus(), equalTo(200));
        assertThat(response.getData().getBio(), containsString("system Electronics Handcrafted migration Dakota"));
        assertThat(response.getData().getUrl(), containsString(username));*/
    }
}
