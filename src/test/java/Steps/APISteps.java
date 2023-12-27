package Steps;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class APISteps {

    private static RequestSpecification request;
    private static Response response;
    private ValidatableResponse json;

    @Given("^Envio un request tipo Get a un endpoint (.+) URI$")
    public void sendGetRequest(String URI){
        request = given()
                .baseUri(URI)
                .contentType(ContentType.JSON);
    }

    @Then("^Obtengo una lista de 10 usuarios$")
    public void getResponse(){
        System.out.println("Prueba Step 2");
    }

    @Then("Obtengo un status code {int}")
    public void getStatusCode(int expectedStatusCode){
        response = request
                .when()
                .get("/users/bloymir/repos");

        json = response.then().statusCode(expectedStatusCode);
    }
}
