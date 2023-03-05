package StepDefinition;

import io.cucumber.java.ru.И;
import io.qameta.allure.Attachment;

public class Steps {

    private static double x;
    private static double y;

    @И("^Получить первое число : (.*)$")
    public static void получить_первое_число(Double numberX) {
        x = numberX;
    }

    @И("^Получить второе число : (.*)$")
    public static void получить_второе_число(Double numberY) {
        y = numberY;
    }

    @Attachment(value = "Ответ", type = "text/html")
    @И("^Получить (сумму|разность|остаток|произведение)$")
    public static String получить(String name) {
        switch (name) {
            case "сумму":
                System.out.println(x + y);
                return String.valueOf(x + y);
            case "разность":
                System.out.println(x - y);
                return String.valueOf(x - y);
            case "остаток":
                System.out.println(x / y);
                return String.valueOf(x / y);
            case "произведение":
                System.out.println(x * y);
                return String.valueOf(x * y);
        }
        return null;
    }
}
