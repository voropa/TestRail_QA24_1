package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Dropdown {
    private final MyElement element;

    public Dropdown(WebDriver driver, By by) {
        this.element = new MyElement(driver, by);
    }

    public Dropdown(WebElement element) {
        this.element = new MyElement(element);
    }

    public Dropdown(WebDriver driver, String id) {
        this.element = new MyElement(driver, By.id(id));
    }

    public String getSelectedOption() {
        for(MyElement element : getAllOptions()) {
            if(element.getAttribute("class").contains("result-selected")) {
                return element.getText();
            }
        }
        return null;
    }

    public void selectOptionByText(String text, boolean shouldSearch) {
        expand();
        if(shouldSearch) {
            element.findMyElement(By.cssSelector("input[type=text]")).sendKeys(text);
        }
        for(MyElement element : getAllOptions()) {
            if(element.getText().equals(text)) {
                element.click();
                return;
            }
        }
    }

    // indices starts from 1
    public void selectOptionByIndex(int index) {
        expand();
        getAllOptions().get(index - 1).click();
    }

    private List<MyElement> getAllOptions() {
        return element.findMyElements(By.cssSelector("ul.chzn-results li"));
    }

    private void expand() {
        element.findMyElement(By.tagName("a")).click();
    }
}
