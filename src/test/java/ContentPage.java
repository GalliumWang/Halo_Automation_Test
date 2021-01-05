import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContentPage extends PageObject{

    @FindBy(id = "content-title")
    private WebElement content_title;

    public ContentPage(WebDriver driver) {
        super(driver);
    }

    public int resState(){
        try{
            if(content_title.isDisplayed()){
                return 0;
            }
        }
        catch (NoSuchElementException e){
        }

        return 10;
    }
}