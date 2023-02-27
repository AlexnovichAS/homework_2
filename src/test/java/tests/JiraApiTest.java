package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ApiSteps.StepsApi.authorizationPreemptiveBasic;
import static ApiSteps.StepsApi.deleteJiraPreemptiveBasic;

public class JiraApiTest {

    @DisplayName("Авторизация в Jira preemptive basic")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @CsvSource({"200, aalehnovich, 204"})
    public void testJiraApiPreemptiveBasic(String status, String checkValue, String checkDeleteStatus) {
        authorizationPreemptiveBasic(status, checkValue);
        deleteJiraPreemptiveBasic(checkDeleteStatus);
    }
}
