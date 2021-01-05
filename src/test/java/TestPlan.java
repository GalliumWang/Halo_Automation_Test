import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;
import java.util.Random;


public class TestPlan {
    private static WebDriver driver = null;
    public static Random rand = new Random();

    @BeforeSuite
    public static void main(String[] args) {
        // ChromeDriver location set up in Utils class
        //System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
        //FIXME
    }

    @Test(testName = "loginTest")
    public static void loginTest() {
        StackWalker walker = StackWalker.getInstance();
        Optional<String> methodName = walker.walk(frames -> frames
                .findFirst()
                .map(StackWalker.StackFrame::getMethodName));
        String currentMethod=methodName.get();

        driver = new ChromeDriver();
        int iterNum=20;
        for (var tmpcnt = 0; tmpcnt < iterNum; ++tmpcnt) {
            driver.get(Utils.LOGIN_URL);
            WebForm webForm = new WebForm(driver);
            webForm.enterName(rand.nextInt(20));
            webForm.enterPass(rand.nextInt(20));
            //Utils.sleep(1);
            webForm.pressSubmitButton();
            //Utils.sleep(1);
            switch (webForm.resState()) {
                case 0:
                    System.out.println(String.format("%s %d/%d:",currentMethod,tmpcnt+1,iterNum)+"success");
                    break;
                case 1:
                    System.out.println(String.format("%s %d/%d:",currentMethod,tmpcnt+1,iterNum)+"login info wrong");
                    break;
                default:
                    System.out.println(String.format("%s %d/%d:",currentMethod,tmpcnt+1,iterNum)+"Unknown error");
            }
            webForm.delCookie();
        }
    }


    @Test(testName = "contentCheck")
    public static void contentCheck() throws Exception{
        StackWalker walker = StackWalker.getInstance();
        Optional<String> methodName = walker.walk(frames -> frames
                .findFirst()
                .map(StackWalker.StackFrame::getMethodName));
        String currentMethod=methodName.get();

        String raw_para_str="?page=%d";
        int start_page=Utils.START_PAGE,end_page=Utils.END_PAGE;
        int page_num;

        driver = new ChromeDriver();//for content dir iter
        WebDriver content_driver = new ChromeDriver();//for content validate

        ContentDir contentDir=new ContentDir(driver);
        ContentPage contentPage=new ContentPage(content_driver);


        for(page_num=start_page;page_num<=end_page;page_num++){
            System.out.println(String.format("%s page %d:",currentMethod,page_num));
            String page_url=Utils.CONTENT_DIR_URL+String.format(raw_para_str,page_num);

            driver.get(page_url);
            if(contentDir.resState()>0){
                throw new Exception("Page Fault");
            }
            List<String> content_list=contentDir.getContentHrefs();

            int content_index=1;

            for(String content_url:content_list){
                content_driver.get(content_url);
                if(contentPage.resState()>0){
                    throw new Exception("Content Fault");
                }
                System.out.println(String.format("content:%d-%d validated",page_num,content_index));
                content_index++;
            }
        }


    }





    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}