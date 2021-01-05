// Page URL: https://formy-project.herokuapp.com/form
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebForm extends PageObject{

    @FindBy(name = "login")
    private WebElement first_name;

    @FindBy(name = "password")
    private WebElement last_name;

    @FindBy(xpath = "/html/body/main/div/section/div/div/form/button")
    private WebElement submit_button;

    @FindBy(xpath = "/html/body/div[1]/div")
    private WebElement suc_message;

    @FindBy(xpath = "/html/body/main/div/section/div/div/form/div[1]")
    private WebElement fail_message;


    public WebForm(WebDriver driver) {
        super(driver);
    }

    public void enterName(int strLen){
        this.first_name.sendKeys(Utils.getTestLoginInfo(strLen));
    }

    public void enterPass(int strLen){
        this.last_name.sendKeys(Utils.getTestLoginInfo(strLen));
    }

    public void pressSubmitButton(){
        this.submit_button.click();
    }

    public int resState(){
        try{
            if(suc_message.isDisplayed()){
                return 0;
            }
        }
        catch (NoSuchElementException e){
        }

        try{
            if(fail_message.isDisplayed()){
                return 1;
            }
        }
        catch (NoSuchElementException e){
        }

        return 10;
    }

    public void delCookie(){
        this.driver.manage().deleteAllCookies();
    }
}


