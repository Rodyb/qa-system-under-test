import base.RequestBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import payload.BasketPayload;
import payload.LoginPayload;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginBasketTest {

    private static final String BASE_URL = System.getProperty(
            "BASE_URL",
            System.getenv().getOrDefault("BASE_URL", "http://localhost:4173/"));
    private static final String OVERRIDE_BASE = System.getProperty(
            "BASE_URL",
            System.getenv("BASE_URL"));

    private static String sessionId;
    private static RequestBuilder request;

    @BeforeAll
    static void setup() {
        request = new RequestBuilder(BASE_URL, Map.of("Accept", "application/json"));
    }

    @Test
    @Order(1)
    @DisplayName("Login succeeds and returns session cookie")
    void loginShouldSucceed() {

        Response resp = request.buildRequest(LoginPayload.validUser())
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("success", equalTo(true))
                .body("token", notNullValue())
                .extract()
                .response();

        sessionId = resp.getCookie("sessionId");
        assertThat("sessionId cookie must be set", sessionId, notNullValue());

    }

    @Test
    @Order(2)
    @DisplayName("Add item to basket using session cookie from login")
    void addBasketWithSession() {
        request.buildRequest(BasketPayload.addProduct(1))
                .cookie("sessionId", sessionId)
                .when()
                .post("/api/basket")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("success", equalTo(true));
    }
}

