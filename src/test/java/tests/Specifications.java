package tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
    public static RequestSpecification requestSpecification(String url){
        return new RequestSpecBuilder().setBaseUri(url).setContentType(ContentType.JSON)
                .addQueryParam("apiKey","09550fa5227b48faa64520bf998ce557").build();
    }
   /* public static RequestSpecification queryParam(){
        return new RequestSpecBuilder().addQueryParam("cuisine","African")
                .addQueryParam("number",5)
                .addQueryParam("apiKey","09550fa5227b48faa64520bf998ce557")
                .build();
    }*/
    public static ResponseSpecification response200(){
        return new ResponseSpecBuilder().expectStatusCode(200).build();
    }
    public static ResponseSpecification responseSpecUnique(int status){
        return new ResponseSpecBuilder().expectStatusCode(status).build();
    }
    public static void installSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
        //RestAssured.requestSpecification = request1;
    }
}
