package robotFramwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AppiumCapabilities {

   public static AppiumDriver driver;

   //Getting Start time for Program Execution
   final long startTime = System.currentTimeMillis();

   @BeforeMethod
   public static void start() {
     System.out.println("********************");
     System.out.println("Starting Appium Server ");
       // set up appium
       File appDir = new File(System.getProperty("user.dir"), "/src/apps/");
       File app = new File(appDir, "app_debug.apk");
       DesiredCapabilities capabilities = new DesiredCapabilities();
       //capabilities.setCapability("UDID","40 letter sequence"); Only for Running on Real Device
       capabilities.setCapability("platformName","Android");
       capabilities.setCapability("deviceName","android_native_sim");
       capabilities.setCapability("platformVersion", "4.4");
       //capabilities.setCapability("browserName", "Chrome");
       //capabilities.setCapability(CapabilityType.PLATFORM, "WINDOW");
       capabilities.setCapability("app", app.getAbsolutePath());
       capabilities.setCapability("app-package", "com.company.android");
       capabilities.setCapability("app-activity", "com.company.android.authenticator.LaunchActivity");
       System.out.println("Starting Application ");
       try {
         driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

      } catch (MalformedURLException e) {
        // TODO Auto-generated catch block
      }
       Assert.assertNotNull(driver, "Driver Failed in AppiumCapabilities" );
       System.out.println("Finished Installing Application");
       System.out.println("********************");
       System.out.println("********************");
       System.out.println("Start Driver Instance");
       System.out.println("********************");
   }

   public AppiumDriver getDriver(){
     Assert.assertNotNull(driver, "Driver Failed in getDriver()");
     return driver;
   }

   @Test
   public void robotTest() throws InterruptedException, FileNotFoundException, IOException, ParseException  {

     JSONParser parser = new JSONParser();
     JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(System.getProperty("user.dir") + "/src/users/website_login_users.json"));
     JSONArray loginCredentialsArray = (JSONArray) jsonObject.get("credentials");
     Iterator<?> nextLogin = loginCredentialsArray.iterator();
     while (nextLogin.hasNext()) {
       System.out.println("Next User ");
       new RuleEngine(System.getProperty("user.dir") + "/src/rule_sets/rule_spec_set_1_for_prototype.json",(JSONObject) nextLogin.next());
     }
     //Getting End time for Program Execution
     final long endTime = System.currentTimeMillis();
     System.out.println("Calculating time Run for the Robot ");
     int hrs = (int) TimeUnit.MILLISECONDS.toHours(endTime - startTime) % 24;
     int min = (int) TimeUnit.MILLISECONDS.toMinutes(endTime - startTime) % 60;
     int sec = (int) TimeUnit.MILLISECONDS.toSeconds(endTime - startTime) % 60;
     System.out.println("Total Execution time of the Robot in HH:mm:ss => " + String.format("%02d:%02d:%02d", hrs, min, sec));

   }

}