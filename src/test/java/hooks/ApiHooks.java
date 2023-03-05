package hooks;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class ApiHooks {

    @BeforeAll
    public static void beforeAll() {
        RestAssured.filters(new AllureRestAssured());
    }
}
