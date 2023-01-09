package tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;


public class RecipesTests {
    private final static String URL = "https://api.spoonacular.com/";


    @Test
    void getAccountInfoWithExternalEndpointTest1() {
        Map<RootRecipes, String> list = given()
                .queryParam("cuisine", "African")
                .queryParam("number", 5)
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .when()
                .contentType(ContentType.JSON)
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()//.log().all()
                .extract()
                .body().jsonPath().getMap("");//.getList("rootPath", RootRecipes.class);
      //ArrayList<String> few= list.get("results", );
        int i = 0;
    }
    @Test
    void getAccountInfoWithExternalEndpointTest2() {
        Map<RootRecipes, String> response = given()
                .queryParam("cuisine", "African")
                .queryParam("number", 5)
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .when()
                .contentType(ContentType.JSON)
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then().log().all()
                .extract()
                .body().jsonPath().getMap("");

        Assert.assertEquals(response.get("number"),5);
        Map<ArrayList, String> dsg = new HashMap<>();
        //dsg = response.get("result").lines().
        //String resul = response.get("results").toString();

        //List<String> dsg = new ArrayList<>();
       // response.forEach(x->dsg.put(x.getRecipes(),"dfsg"));
        int i = 0;
      //  assertThat(response.get("results"), assertArrayEquals(););
    }


    public Map<RootRecipes, String> responseMet(){
        return given()
                .queryParam("cuisine", "African")
                .queryParam("number", 5)
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .when()
                .contentType(ContentType.JSON)
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then().log().all()
                .extract()
                .body().jsonPath().getMap("");
    }
    @Test //ID contains in image
    public void checkIDInImage(){
        List<Result> results = given()
                .queryParam("cuisine", "African")
                .queryParam("number", 5)
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .when()
                .contentType(ContentType.JSON)
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()//.log().all()
                .extract()
                .body().jsonPath().getList("results", Result.class);
        List<String> ids = results.stream().map(x->x.getId().toString()).collect(Collectors.toList());
        List<String> images = results.stream().map(Result::getImage).collect(Collectors.toList());
        for(int i =0; i<images.size(); i++){
            Assert.assertTrue(images.get(i).contains(ids.get(i)));
        }
        int i = 0;
    }
    @Test
    public void imageEndImageType(){
        List<Result> results = given()
                .queryParam("cuisine", "African")
                .queryParam("number", 5)
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .when()
                .contentType(ContentType.JSON)
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()//.log().all()
                .extract()
                .body().jsonPath().getList("results", Result.class);
    List<String> image = results.stream().map(x->x.getImage()).collect(Collectors.toList());
    List<String> typeImage = results.stream().map(x->x.getImageType()).collect(Collectors.toList());
    for(int i =0; i<image.size(); i++) {
        Assert.assertTrue(image.get(i).endsWith(typeImage.get(i)));
    }
        int i = 0;

    }
    @Test
    public void totalResult(){
        List<Result> results = given()
                .queryParam("cuisine", "African")
                .queryParam("number", 5)
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .when()
                .contentType(ContentType.JSON)
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()//.log().all()
                .extract()
                .body().jsonPath().getList("results", Result.class);
        List<String> ids = results.stream().map(x->x.getId().toString()).collect(Collectors.toList());


        Map<RootRecipes, String> list = given()
                .queryParam("cuisine", "African")
                .queryParam("number", 5)
                .queryParam("apiKey", "09550fa5227b48faa64520bf998ce557")
                .when()
                .contentType(ContentType.JSON)
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then().log().all()
                .extract()
                .body().jsonPath().getMap("");
        Assert.assertEquals(list.get("totalResults"), results.size());
        int i = 0;

    }
    @Test
    public void root(){
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.response200());
      FullResponse fullResponse = given()
                .queryParam("cuisine", "African")
                .queryParam("number", 5)

                .when()
                //.contentType(ContentType.JSON)
                .get("recipes/complexSearch")
                .then()//.log().all()
                .extract()
                .body().as(FullResponse.class);
        Assert.assertEquals(fullResponse.totalResults, fullResponse.results.size());
        int i = 0;
    }
}