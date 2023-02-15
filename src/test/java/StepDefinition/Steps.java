package StepDefinition;

import io.cucumber.java.ru.И;

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

    @И("^Получить (сумму|разность|остаток|произведение)$")
    public static void получить(String name) {
        switch (name) {
            case "сумму":
                System.out.println(x + y);
                break;
            case "разность":
                System.out.println(x - y);
                break;
            case "остаток":
                System.out.println(x / y);
                break;
            case "произведение":
                System.out.println(x * y);
                break;
        }
    }
}
