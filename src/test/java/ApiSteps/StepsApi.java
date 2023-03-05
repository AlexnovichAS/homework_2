package ApiSteps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.preemptive;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specification.Specification.*;
import static utils.PropConf.getProperty;

public class StepsApi {
    public static int lastEpisodeNumber;
    public static String speciesCharacter;
    public static String locationCharacter;
    public static int lastCharacterNumber;
    public static String speciesLastCharacter;
    public static String locationLastCharacter;
    public static Map<String, String> allCookies;

    @Step("Найти информацию по персонажу: {character}")
    public static void getInformationCharacter(String character) {
        Response getCharacter = given()
                .spec(requestSpecGet(getProperty("rick.url")))
                .param("name", character)
                .when()
                .get("/character/")
                .then()
                .extract()
                .response();
        String infoNameCharacter = new JSONObject(getCharacter.getBody().asString()).getJSONArray("results")
                .getJSONObject(0).get("name").toString();
        Assertions.assertEquals(character, infoNameCharacter, "Ошибка, найден персонаж: " + infoNameCharacter);
        int episode = (new JSONObject(getCharacter.getBody().asString()).getJSONArray("results")
                .getJSONObject(0).getJSONArray("episode")).length() - 1;
        speciesCharacter = new JSONObject(getCharacter.getBody().asString()).getJSONArray("results")
                .getJSONObject(0).get("species").toString();
        locationCharacter = new JSONObject(getCharacter.getBody().asString()).getJSONArray("results")
                .getJSONObject(0).getJSONObject("location").get("name").toString();
        lastEpisodeNumber = Integer.parseInt(new JSONObject(getCharacter.getBody().asString())
                .getJSONArray("results")
                .getJSONObject(0)
                .getJSONArray("episode")
                .get(episode).toString().replaceAll("[^0-9]", ""));
    }

    @Step("Получить из списка последнего эпизода последнего персонажа")
    public static void getLastCharacter() {
        Response getLastCharacter = given()
                .spec(requestSpecGet(getProperty("rick.url")))
                .when()
                .get("/episode/" + lastEpisodeNumber)
                .then()
                .extract()
                .response();
        int character = (new JSONObject(getLastCharacter.getBody().asString()).getJSONArray("characters")).length() - 1;
        lastCharacterNumber = Integer.parseInt(new JSONObject(getLastCharacter.getBody().asString())
                .getJSONArray("characters")
                .get(character).toString().replaceAll("[^0-9]", ""));
    }

    @Step("Получить данные последнего персонажа и сравнить с персонажем: {character}")
    public static void getInformationLastCharacter(String character) {
        Response getCharacterInfo = given()
                .spec(requestSpecGet(getProperty("rick.url")))
                .when()
                .get("/character/" + lastCharacterNumber)
                .then()
                .extract()
                .response();
        String infoNameLastCharacter = new JSONObject(getCharacterInfo.getBody().asString()).get("name").toString();
        speciesLastCharacter = new JSONObject(getCharacterInfo.getBody().asString()).get("species").toString();
        locationLastCharacter = new JSONObject(getCharacterInfo.getBody().asString()).getJSONObject("location").get("name").toString();
        Assertions.assertAll("Проверка совпадения информации по персонажам: " + character + " и " + infoNameLastCharacter,
                () -> assertEquals(speciesCharacter, speciesLastCharacter, "Ошибка, не совпадает"),
                () -> assertEquals(locationCharacter, locationLastCharacter, "Ошибка, не совпадает")
        );
    }

    @Step("Создать пользователя")
    public static void createUserInReqres() {
        JSONObject body = new JSONObject();
        try {
            body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/json/Potato.json"))));
            body.put("name", "Tomato");
            body.put("job", "Eat maket");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Response postCreateUser = given()
                .spec(requestSpecPost(getProperty("reqres.url"), body.toString()))
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .response();
        Assertions.assertEquals(body.get("name"), new JSONObject(postCreateUser.getBody().asString()).get("name"), "Ошибка, не совпадает");
        Assertions.assertEquals(body.get("job"), new JSONObject(postCreateUser.getBody().asString()).get("job"), "Ошибка, не совпадает");
    }

    @Step("Создать новый сеанс для пользователя: '{name}' в Jira")
    public static void authorizationInJira(String name, String pass, String status, String checkValue) {
        JSONObject body = new JSONObject();
        body.put("username", name);
        body.put("password", pass);
        Response getJiraLogin = given()
                .spec(requestSpecPost(getProperty("jira.auth.url"), body.toString()))
                .when()
                .post("/session")
                .then()
                .spec(responseSpec(status))
                .extract()
                .response();
        String infoName = new JSONObject(getJiraLogin.getBody().asString()).getJSONObject("session").get("name").toString();
        Assertions.assertEquals(checkValue, infoName, "Ошибка, пользователь не авторизирован");
        allCookies = getJiraLogin.getCookies();
    }

    @Step("Запросить текущего пользователя с именем: '{name}' в jira")
    public static void getCurrentUserInJira(String status, String name) {
        Response getJiraUser = given()
                .cookies(allCookies)
                .baseUri(getProperty("jira.auth.url"))
                .when()
                .get("/session")
                .then()
                .spec(responseSpec(status))
                .extract()
                .response();
        String nameValue = new JSONObject(getJiraUser.getBody().asString())
                .get("name")
                .toString();
        Assertions.assertEquals(name, nameValue, "Ошибка, пользователь не авторизирован");
    }

    @Step("Выйти из Jira")
    public static void deleteJira(String checkDeleteStatus) {
        given()
                .cookies(allCookies)
                .baseUri(getProperty("jira.auth.url"))
                .when()
                .delete("/session")
                .then()
                .spec(responseSpec(checkDeleteStatus));
    }

    @Step("Создать новый сеанс для пользователя в Jira: basic authentication")
    public static void authorizationBasicInJira(String param, String status, String checkValue) {
        String encodedPass = Base64.getEncoder().encodeToString(param.getBytes(StandardCharsets.UTF_8));
        Response getJiraLogin = given()
                .header("Authorization", "Basic " + encodedPass)
                .spec(requestSpecGet(getProperty("jira.auth.url")))
                .when()
                .get("/session")
                .then()
                .spec(responseSpec(status))
                .extract()
                .response();
        String infoName = new JSONObject(getJiraLogin.getBody().asString()).get("name").toString();
        Assertions.assertEquals(checkValue, infoName, "Ошибка, пользователь не авторизирован");
    }

    @Step("Возвратить текущего зарегистрированного пользователя в Jira: basic authentication")
    public static void getCurrentUserBasic(String param, String status, String checkValue) {
        String encodedPass = Base64.getEncoder().encodeToString(param.getBytes(StandardCharsets.UTF_8));
        Response getJiraLogin = given()
                .header("Authorization", "Basic " + encodedPass)
                .baseUri("https://edujira.ifellow.ru/rest/api/2")
                .when()
                .get("/myself")
                .then()
                .spec(responseSpec(status))
                .extract()
                .response();
        String infoName = new JSONObject(getJiraLogin.getBody().asString()).get("name").toString();
        Assertions.assertEquals(checkValue, infoName, "Ошибка, пользователь не авторизирован");
    }

    @Step("Выйти из Jira: basic authentication")
    public static void deleteJiraBasic(String param, String checkDeleteStatus) {
        String encodedPass = Base64.getEncoder().encodeToString(param.getBytes(StandardCharsets.UTF_8));
        given()
                .header("Authorization", "Basic " + encodedPass)
                .baseUri(getProperty("jira.auth.url"))
                .when()
                .delete("/session")
                .then()
                .spec(responseSpec(checkDeleteStatus));
    }

    @Step("Создать новый сеанс для пользователя в Jira: preemptive basic")
    public static void authorizationPreemptiveBasic(String status, String checkValue) {
        RestAssured.authentication = preemptive().basic("aalehnovich", "Qwerty123");
        Response getJiraLogin = given()
                .spec(requestSpecGet(getProperty("jira.auth.url")))
                .when()
                .get("/session")
                .then()
                .spec(responseSpec(status))
                .extract()
                .response();
        String infoName = new JSONObject(getJiraLogin.getBody().asString()).get("name").toString();
        Assertions.assertEquals(checkValue, infoName, "Ошибка, пользователь не авторизирован");
    }

    @Step("Выйти из Jira: preemptive basic")
    public static void deleteJiraPreemptiveBasic(String checkDeleteStatus) {
        given()
                .baseUri(getProperty("jira.auth.url"))
                .when()
                .delete("/session")
                .then()
                .spec(responseSpec(checkDeleteStatus));
    }
}
