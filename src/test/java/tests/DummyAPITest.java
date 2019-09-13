package tests;

import data.Employee;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import org.testng.Assert;

public class DummyAPITest extends BaseTestConfig {

    private static final String EMPLOYEE_NAME = "cstest1000";
    private static final String EMPLOYEE_SALARY = "123";
    private static final String EMPLOYEE_AGE = "23";
    private static final String PROFILE_IMAGE = "http://dummyurl.com";
    private Employee employee = new Employee(EMPLOYEE_NAME,
            EMPLOYEE_SALARY, EMPLOYEE_AGE,
            PROFILE_IMAGE);

    private static final String ID_JSON_PROPERTY = "id";
    private static final String NAME_JSON_PROPERTY = "name";
    private static final String SALARY_JSON_PROPERTY = "salary";
    private static final String AGE_JSON_PROPERTY = "age";
    private static final String PROFILE_IMAGE_JSON_PROPERTY = "profile_image";
    private static final String EMPLOYEE_NAME_JSON_PROPERTY = "employee_name";
    private static final String EMPLOYEE_SALARY_JSON_PROPERTY = "employee_salary";
    private static final String EMPLOYEE_AGE_JSON_PROPERTY = "employee_age";

    private String id;

    @Test
    public void createEmployee() {
        Response response = request
                .contentType("application/json")
                .body(employee)
                .when()
                .post("/create");

        Assert.assertEquals(response.getStatusCode(), 200);

        // response data is returned as html so
        // have to call below method in order to index into the json with
        // jsonPath()
        String html = response.asString();
        System.out.println(html);

        id = response.jsonPath().getString(ID_JSON_PROPERTY);

        Assert.assertEquals(employee.getName(), response.jsonPath().getString(NAME_JSON_PROPERTY));
        Assert.assertEquals(employee.getSalary(), response.jsonPath().getString(SALARY_JSON_PROPERTY));
        Assert.assertEquals(employee.getAge(), response.jsonPath().getString(AGE_JSON_PROPERTY));
        Assert.assertEquals(employee.getProfileImage(), response.jsonPath().getString(PROFILE_IMAGE_JSON_PROPERTY));
    }

    @Test(dependsOnMethods = {"createEmployee"})
    public void retrieveEmployee() {
        Response response = request
                .when()
                .get("/employee/" + id);

        Assert.assertEquals(response.getStatusCode(), 200);

        // response data is returned as html so
        // have to call below method in order to index into the json with
        // jsonPath()
        String html = response.asString();

        Assert.assertEquals(id, response.jsonPath().getString(ID_JSON_PROPERTY));
        Assert.assertEquals(employee.getName(), response.jsonPath().getString(EMPLOYEE_NAME_JSON_PROPERTY));
        Assert.assertEquals(employee.getSalary(), response.jsonPath().getString(EMPLOYEE_SALARY_JSON_PROPERTY));
        Assert.assertEquals(employee.getAge(), response.jsonPath().getString(EMPLOYEE_AGE_JSON_PROPERTY));

    }

    @Test(dependsOnMethods = {"retrieveEmployee"})
    public void updateEmployee() {
        employee = new Employee(EMPLOYEE_NAME, "1123", EMPLOYEE_AGE,
                PROFILE_IMAGE);

        Response response = request
                .contentType("application/json")
                .body(employee)
                .when()
                .put("/update/" + id);

        Assert.assertEquals(response.getStatusCode(), 200);

        // response data is returned as html so
        // have to call below method in order to index into the json with
        // jsonPath()
        String html = response.asString();

        Assert.assertEquals(employee.getName(), response.jsonPath().getString(NAME_JSON_PROPERTY));
        Assert.assertEquals(employee.getSalary(), response.jsonPath().getString(SALARY_JSON_PROPERTY));
        Assert.assertEquals(employee.getAge(), response.jsonPath().getString(AGE_JSON_PROPERTY));
        Assert.assertEquals(employee.getProfileImage(), response.jsonPath().getString(PROFILE_IMAGE_JSON_PROPERTY));
    }

    @Test(dependsOnMethods = {"updateEmployee"})
    public void deleteEmployee() {
        Response response = request
                .when()
                .delete("/delete/" + id);

        Assert.assertEquals(response.getStatusCode(), 200);

        // response data is returned as html so
        // have to call below method in order to index into the json with
        // jsonPath()
        String html = response.asString();

        Assert.assertEquals(response.jsonPath().getString("success.text"), "successfully! deleted Records");

        // verify employee is now deleted
        response = request
                .when()
                .get("/employee/" + id);

        Assert.assertEquals(response.getStatusCode(), 200);

        // response data is returned as html so
        // have to call below method in order to index into the json with
        // jsonPath()
        html = response.asString();

        // asssert response body is equal to false
        Assert.assertEquals(html, "false");
    }
}
