package Steps;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Base64;

import static io.restassured.RestAssured.given;
public class EjemploAPI {


    public void GETRequest(){
        given().baseUri("https://api.github.com")
                .when().get("/users/bloymir/repos")
                .getBody().toString();
    }

    public void POSTRequest(){
        given().baseUri("")
                .when()
                .post("", "");
        //Cuerpo o set de datos que se envian junto al path
    }

    public void PUTRequest(){
        given().baseUri("")
                .when()
                .put("", "");
    }

    public void DELETERequest(){
        given().baseUri("")
                .when()
                .delete();
    }


    //Autentificacion Basica
    public void basicAuth(String userName, String password){
        given()
                .auth().basic(userName, password)
                .when()
                .get("")
                .then()
                .assertThat().statusCode(200);

    }

    //Autentificacion Formulario
    public void formularioAuth(String userName, String pass){
        given()
                .auth().form(userName, pass)
                .when()
                .get("")
                .then()
                .assertThat().statusCode(200);

    }

    //Autentificacion servicios
    //1.- Obtener el codigo del servicio original para obtener el token
    //2.- Obtener el token, intercambiando el codigo que obtuvimos
    //3.- Acceder al recurso protegido, con el Token

    public static String clienId = "";
    public static String redirectUri = "";
    public static String scope = "";
    public static String userName = "";
    public static String password = "";
    public static String grantType = "";
    public static String accessToken = "";

    public static String encode(String str1, String str2){
        return new String (Base64.getEncoder().encode((str1+":"+str2).getBytes()));
    }

    public static Response getCode(){
        String authorization = encode(userName, password);

        return given()
                .header("authorizacion", "Basic " + authorization)
                .contentType(ContentType.URLENC)
                .formParam("response_type", "code")
                .queryParam("client_id", clienId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("scope", scope)
                .post("/oauth/authorize")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
    public static String parseForOAuthCode(Response response){
        return response.jsonPath().getString("code");
    }

    public static Response getToken(String authCode){
        String authorization = encode(userName, password);

        return given()
                .header("authorization", "Basic" + authorization)
                .contentType(ContentType.URLENC)
                .formParam("response_type", authCode)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("grant_type", grantType)
                .post("/oauth/token")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public static String parseForToken(Response loginResponse){
        return loginResponse.jsonPath().getString("access_token");
    }

    public static void getFinalService(){
        given().auth()
                .oauth2(accessToken)
                .when()
                .get("/service")
                .then()
                .statusCode(200);
    }

}
