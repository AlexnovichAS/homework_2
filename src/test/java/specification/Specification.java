package specification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {
    public static RequestSpecification requestSpecGet(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .build();

    }

    public static RequestSpecification requestSpecPost(String url, String body) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(url)
                .setBody(body)
                .build();
    }

    public static ResponseSpecification responseSpec(String status) {
        return new ResponseSpecBuilder()
                .expectStatusCode(Integer.parseInt(status))
                .build();
    }
}
