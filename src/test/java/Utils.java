// Configuration class for Form Automation project
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class Utils {
    final static String BASE_URL = "http://localhost:8000/";
    final static String LOGIN_URL = "http://localhost:8000/accounts/login/";
    final static String CONTENT_DIR_URL = "http://localhost:8000/";
    final static int START_PAGE = 1;
    final static int END_PAGE = 10;

    final static String CHROME_DRIVER_LOCATION = "chromedriver.exe";
    public static void sleep(int sleep_dur){
        try {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (Exception e){
            System.out.println("Sleep failed");
        }
    }

    public static String getTestLoginInfo(int length){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
