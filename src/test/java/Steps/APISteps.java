package Steps;

import io.cucumber.java.en.*;

public class APISteps {
    @Given("Envio un request tipo Get a un endpoint")
    public void sendGetRequest(){
        System.out.println("Prueba Step 1");
    }

    @Then("Obtengo una lista de 10 usuarios")
    public void getResponse(){
        System.out.println("Prueba Step 2");
    }
}
