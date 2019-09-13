package tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import io.restassured.specification.RequestSpecification;

public class BaseTestConfig {

    protected RequestSpecification request;

    @BeforeSuite
    protected void buildRequestSpec() {
        RestAssured.baseURI = "http://dummy.restapiexample.com";
        RestAssured.basePath = "/api/v1";
        request = RestAssured.given();
    }
}
