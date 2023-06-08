package tests;

import elements.Button;
import elements.Dropdown;
import elements.Input;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void loginTest() {
        new Input(driver, "title").setValue("My titile");
        new Input(driver, "estimate").setValue("10 sp");
        new Input(driver, "custom_preconds").setValue("Precohfsdhjfdshfsdhf");
        new Button(driver, "accept").click();
        new Button(driver, By.xpath("//button[@id='accept']//following-sibling::a[contains(@class, 'button-cancel')]")).click();

        new Dropdown(driver, "id").selectOptionByIndex(1);
        new Dropdown(driver, "id2").selectOptionByText("Functional", false);
        new Dropdown(driver, "id2").getSelectedOption();

    }
}
