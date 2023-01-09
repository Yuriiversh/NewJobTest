package tests;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasSize;


public class RecipeTests {
    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    /*@BeforeAll
    static void beforeAll() {
//for logging request and responses in Allure reporting
        RestAssured.filters(new AllureRestAssured());
    }*/
    @Test
    void getRecipePositiveTest() {
       JsonPath response = given()
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")

               .body()

               .jsonPath()

               ;

        assertThat(response.get("license"), equalTo("CC BY-SA 3.0"));
        assertThat(response.get("vegetarian"), equalTo(false));



    }

    @Test
    void getRecipeWithBodyChecksInGivenPositiveTest() {
        given()
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .queryParam("includeNutrition", "false")
                .expect()
                .body("vegetarian", equalTo(false))
                .body("vegan", equalTo(false))
                .body("license", equalTo("CC BY-SA 3.0"))
                .body("pricePerServing", equalTo(163.15F))
                .body("extendedIngredients[0].aisle", equalTo("Milk, Eggs, Other Dairy"))
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                .then()
                //.log().all()
                .statusCode(200);
    }
    @Test
    void getRecipePositiveTest1() {
         given()
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .queryParam("includeNutrition", "false")
                //.when()
                //.get("https://api.spoonacular.com/recipes/716429/information")
                 .get("https://api.spoonacular.com/recipes/716429/information")

                .then()
                .body("vegan", equalTo(false))
                 .body("extendedIngredients.id", hasItems(1001,10011135,1041009,1034053,11215)) ;
}
    @Test
    void checkSchema() {
        given()
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .queryParam("includeNutrition", "false")
                //.when()
                //.get("https://api.spoonacular.com/recipes/716429/information")
                .get("https://api.spoonacular.com/recipes/716429/information")

                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schema.json"));
}

    @Test
    void checkAmount() {
        given()
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .queryParam("includeNutrition", "false")
                //.when()
                //.get("https://api.spoonacular.com/recipes/716429/information")
                .get("https://api.spoonacular.com/recipes/716429/information")

                .then()

                .body("extendedIngredients.findAll{it.amount < 2.0}.unit", hasItems("tbsp", "tbsp", "cup"));
    }

    @Test
    void checkAmount1() {
        /*given()
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .queryParam("includeNutrition", "false")*/
                //.when()
                //.get("https://api.spoonacular.com/recipes/716429/information")
                /*List<Map<String, Object>> products = get("https://api.spoonacular.com/recipes/716429/information")
                .as(new TypeRef<List<Map<String, Object>>>() {});*/
        Collection<Map<String, Object>> products = given()
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .queryParam("includeNutrition", "false").get("https://api.spoonacular.com/recipes/716429/information")
                .as(new TypeRef<List<Map<String, Object>>>() {});
        //assertThat(products, hasSize(20));
    }

    /*@Test
    void find() {
        given()
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .queryParam("includeNutrition", "false")
                //.when()
                //.get("https://api.spoonacular.com/recipes/716429/information")
                .get("https://api.spoonacular.com/recipes/716429/information")

                .then()

                .body("extendedIngredients.findAll{it.amount < 2.0}.unit", hasItems("tbsp", "tbsp", "cup"));
    }*/
}
