package tests;

import hooks.ApiHooks;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ApiSteps.StepsApi.*;

public class ApiTest extends ApiHooks {

    @Epic(value = "rickandmortyapi.com")
    @DisplayName("Сравнить информацию по персонажу")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @CsvSource({"Morty Smith", "Rick Sanchez", "Adjudicator Rick", "Alan Rails"})
    public void testRickAndMortyApi(String search) {
        getInformationCharacter(search);
        getLastCharacter();
        getInformationLastCharacter(search);
    }

    @Epic(value = "reqres.in")
    @DisplayName("Создание пользователя в Reqres")
    @Test
    public void testReqresApi() {
        createUserInReqres();
    }

    @Epic(value = "edujira.ifellow.ru")
    @DisplayName("Авторизация в Jira Cookies")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @CsvSource({"aalehnovich, Qwerty123, 200, JSESSIONID, 204"})
    public void testJiraApi(String name, String pass, String status, String checkValue, String checkDeleteStatus) {
        authorizationInJira(name, pass, status, checkValue);
        getCurrentUserInJira(status, name);
        deleteJira(checkDeleteStatus);
    }

    @Epic(value = "edujira.ifellow.ru")
    @DisplayName("Авторизация в Jira basic authentication")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @CsvSource({"aalehnovich:Qwerty123, 200, aalehnovich, 204"})
    public void testJiraApiBasic(String param, String status, String checkValue, String checkDeleteStatus) {
        authorizationBasicInJira(param, status, checkValue);
        getCurrentUserBasic(param, status, checkValue);
        deleteJiraBasic(param, checkDeleteStatus);
    }
}
