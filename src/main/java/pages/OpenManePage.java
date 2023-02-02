package pages;

import org.junit.Assert;

import static com.codeborne.selenide.Selenide.title;


public class OpenManePage {

    public void checkPage(String element) {
        String title = title();
        Assert.assertEquals("Ошибка, открылась страница: " + title, element, title);
    }
}
