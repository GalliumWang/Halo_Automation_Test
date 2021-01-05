import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

public class ContentDir extends PageObject{

    @FindBy(id = "navbarSupportedContent")
    private WebElement nav_bar;

    public ContentDir(WebDriver driver) {
        super(driver);
    }

    public List<String> getContentHrefs(){
        return new ArrayList<String>();
    }

    public int resState(){
        try{
            if(nav_bar.isDisplayed()){
                return 0;
            }
        }
        catch (NoSuchElementException e){
        }

        return 10;
    }
}