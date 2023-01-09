package tests.mealPlan;

import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import tests.Specifications;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;


public class MealPlanTest {
    private final static String URL = "https://api.spoonacular.com/";


@Test
    void mealPlanTemplates(){

    tests.mealPlan.Specifications.installSpecification(tests.mealPlan.Specifications.requestSpecification(URL), tests.mealPlan.Specifications.response200());
    JsonPath jsonPath = given()
            //.queryParam("hash","91cfc1cde36287369b55d715e2c8104f3f8a53cb")
            //.queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
            .pathParams("username","your-users-name604")
            //.pathParams("id","7335")
            .when().get("mealplanner/{username}/templates/").then().log().all().extract().jsonPath();
        Assert.assertNotNull(jsonPath.get("templates[0].id"));
    int i = 0;
}

    @Test
    void addMealPlanTemplate() throws IOException {
        File reqFile = new File("src/main/resources/addMealPlan.json");

        JsonPath jsonPath = given()
                .queryParam("hash","91cfc1cde36287369b55d715e2c8104f3f8a53cb")
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .pathParams("username","your-users-name604")
                .when().body(GetDoc.summing(reqFile)).post("https://api.spoonacular.com/mealplanner/{username}/templates")
                .then().log().all().extract().jsonPath();
    }


    public Integer mealPlanTemplates1(){
        List<Template> templateList = given()
                .queryParam("hash","91cfc1cde36287369b55d715e2c8104f3f8a53cb")
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .pathParams("username","your-users-name604")
                //.pathParams("id","7335")
                .when().get("https://api.spoonacular.com/mealplanner/{username}/templates/").then()//.log().body()
                .extract().jsonPath().getList("templates", Template.class);
        int result = 0;
        for(int i =0; i< templateList.size(); i++){
                if(templateList.get(i).id > result){
                    result = templateList.get(i).id;
                }
                }
        return result;
        //int i = 0;
    }
    @Test
        public void deleteDataTest(){
    JsonPath respDell = given().queryParam("hash","91cfc1cde36287369b55d715e2c8104f3f8a53cb")
            .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
            .pathParams("username","your-users-name604")
        .pathParams("id",mealPlanTemplates1())
            .when().delete("https://api.spoonacular.com/mealplanner/{username}/templates/{id}").then().log().all().extract().jsonPath();
        Assert.assertEquals(respDell.get("status"),"success");
        int i = 0;
    }
}
