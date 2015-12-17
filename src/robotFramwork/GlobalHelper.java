package robotFramwork;

import io.appium.java_client.AppiumDriver;

import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.testng.Assert;

public class GlobalHelper extends AppiumCapabilities{

  public AppiumDriver driver = new AppiumCapabilities().getDriver();

  public void doWait(){

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

  }

  public void verifySplashScreenElements() throws InterruptedException{
    doWait();
    System.out.println("Verifying Splash Screen Elements Started");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.splash_screen_signin_button)).isDisplayed(), "Splash Screen Signin Element Not Found");;
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.splash_screen_joinnow_button)).isDisplayed(), "Splash Screen JoinNow Element Not Found");;
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.splash_screen_company_logo)).isDisplayed(), "Splash Screen Company Logo Element Not Found");;
    System.out.println("Verifying Splash Screen Elements Completed");

  }

  public void tapSplashScreenSignIn() throws InterruptedException{
    Assert.assertNotNull(driver, "Driver Failed");
    doWait();
    driver.findElement(By.id(MobileScreenElements.splash_screen_signin_button)).click();
    doWait();
  }

  public void tapSplashScreenJoinNow() throws InterruptedException{
    Assert.assertNotNull(driver, "Driver Failed");
    doWait();
    driver.findElement(By.id(MobileScreenElements.splash_screen_joinnow_button)).click();
    doWait();
  }

  public void verifyRegisterScreenElements() throws InterruptedException{
    doWait();
    System.out.println("Verifying Register Screen Elements Started");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.register_screen_firstname_field)).isDisplayed(), "Register Screen Firstname Element Not Found");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.register_screen_lastname_field)).isDisplayed(), "Register Screen Lastname Element Not Found");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.register_screen_email_field)).isDisplayed(), "Register Screen Email Element Not Found");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.register_screen_password_field)).isDisplayed(), "Register Screen Password Element Not Found");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.register_screen_join_now_button)).isDisplayed(), "Register Screen Join Now Element Not Found");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.register_screen_already_have_an_account_button)).isDisplayed(), "Register Screen Already have an account Element Not Found");
    System.out.println("Verifying Register Screen Elements Completed");
    driver.navigate().back();

  }

  public void verifyLoginScreenElements() throws InterruptedException{
    doWait();
    System.out.println("Verifying Login Screen Elements Started");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.login_screen_email_field)).isDisplayed(), "Login Screen Email Element Not Found");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.login_screen_password_field)).isDisplayed(), "Login Screen Password Element Not Found");;
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.login_screen_signin_button)).isDisplayed(), "Login Screen Element Signin Not Found");;
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.login_screen_forgot_password_button)).isDisplayed(), "Login Screen Forgot Password Element Not Found");;
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.login_screen_join_now_button)).isDisplayed(), "Login Screen Join Now Element Not Found");;
    System.out.println("Verifying Login Screen Elements Completed");

  }

  public void setInvalidLoginCredentials(JSONObject array) throws InterruptedException{
    doWait();
    System.out.println("Set Invalid Login Credentials Started");
    verifyLoginScreenElements();
    driver.findElement(By.id(MobileScreenElements.login_screen_email_field)).clear();
    driver.findElement(By.id(MobileScreenElements.login_screen_email_field)).sendKeys(array.get("email").toString() + System.nanoTime());
    driver.findElement(By.id(MobileScreenElements.login_screen_password_field)).sendKeys(array.get("password").toString() + System.nanoTime());
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    System.out.println("Set Invalid Login Credentials Completed");

  }

  public void setValidLoginCredentials(JSONObject array) throws InterruptedException{
    doWait();
    System.out.println("Set Login Credentials Started");
    verifyLoginScreenElements();
    driver.findElement(By.id(MobileScreenElements.login_screen_email_field)).clear();
    driver.findElement(By.id(MobileScreenElements.login_screen_email_field)).sendKeys(array.get("email").toString());
    driver.findElement(By.id(MobileScreenElements.login_screen_password_field)).clear();
    driver.findElement(By.id(MobileScreenElements.login_screen_password_field)).sendKeys(array.get("password").toString());
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    System.out.println("Set Login Credentials Completed");

  }

  public void tapLoginScreenSignIn() throws InterruptedException{
    doWait();
    driver.findElement(By.id(MobileScreenElements.login_screen_signin_button)).click();
    doWait();
  }

  public void verifyFeedScreenElements() throws InterruptedException{
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    System.out.println("Verifying Feed Screen Elements Started");
    Assert.assertTrue(driver.findElement(By.xpath(MobileScreenElements.home_tab_feed_nav_icon)).isDisplayed(), "Feed Screen Feed Element Not Found");
    Assert.assertTrue(driver.findElement(By.xpath(MobileScreenElements.home_tab_profile_nav_icon)).isDisplayed(), "Feed Screen Profile Element Not Found");
    Assert.assertTrue(driver.findElement(By.xpath(MobileScreenElements.home_tab_messaging_nav_icon)).isDisplayed(), "Feed Screen Messaging Element Not Found");
    Assert.assertTrue(driver.findElement(By.xpath(MobileScreenElements.home_tab_people_nav_icon)).isDisplayed(), "Feed Screen Relationships Element Not Found");
    Assert.assertTrue(driver.findElement(By.xpath(MobileScreenElements.home_tab_search_nav_icon)).isDisplayed(), "Feed Screen Search Element Not Found");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.feed_screen_share_button)).isDisplayed(), "Feed Screen Share Button Element Not Found");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.feed_screen_camera_button)).isDisplayed(), "Feed Screen Camera Button Element Not Found");
    System.out.println("Verifying Feed Screen Elements Completed");
  }

  public void tapProfile() throws InterruptedException{
    doWait();
    System.out.println("Tap on Profile icon Started");
    driver.findElement(By.xpath(MobileScreenElements.home_tab_profile_nav_icon)).click();
    doWait();
    System.out.println("Tap on Profile icon Completed");
  }

  public void verifyProfileScreenElements() throws InterruptedException{
    doWait();
    System.out.println("Verifying Profile Screen Elements Started");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.profile_screen_profile_image)).isDisplayed(), "Profile Screen Profile Image Element Not Found");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.profile_screen_profile_name)).isDisplayed(), "Profile Screen Profile Name Element Not Found");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.profile_screen_profile_arrow)).isDisplayed(), "Profile Screen Profile Arrow Element Not Found");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.profile_screen_profile_viewers_holder)).isDisplayed(), "Profile Screen Profile Viewers Element Not Found");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.profile_screen_update_views_holder)).isDisplayed(), "Profile Screen Update Views Holder Element Not Found");
    Assert.assertTrue(driver.findElement(By.name(MobileScreenElements.profile_screen_notifications_frame)).isDisplayed(), "Profile Screen Notifications Tab Element Not Found");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.profile_screen_settings_button)).isDisplayed(), "Profile Screen Settings Button Element Not Found");
    System.out.println("Verifying Profile Screen Elements Completed");
  }

  public void tapProfileSettings() throws InterruptedException{
    doWait();
    System.out.println("Tap on Profile Settings button Started");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.profile_screen_settings_button)).isDisplayed(), "Profile Screen Settings Button Element Not Found");
    driver.findElement(By.id(MobileScreenElements.profile_screen_settings_button)).click();
    doWait();
    System.out.println("Tap on Profile Settings button Completed");
  }

  public void tapMessaging() throws InterruptedException{
    doWait();
    System.out.println("Tap on Messaging icon Started");
    driver.findElement(By.xpath(MobileScreenElements.home_tab_messaging_nav_icon)).click();
    doWait();
    System.out.println("Tap on Messaging icon Completed");
  }

  public void verifyMessagingScreenElements() throws InterruptedException{
    doWait();
    System.out.println("Verifying Messaging Screen Elements Started");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.messaging_screen_compose_message_icon)).isDisplayed(), "Messaging Screen Compose Message Icon Element Not Found");
    System.out.println("Verifying Messaging Screen Elements Completed");
  }

  public void tapPeople() throws InterruptedException{
    doWait();
    System.out.println("Tap on People icon Started");
    driver.findElement(By.xpath(MobileScreenElements.home_tab_people_nav_icon)).click();
    doWait();
    System.out.println("Tap on People icon Completed");
  }

  public void verifyPeopleScreenElements() throws InterruptedException{
    doWait();
    System.out.println("Verifying People Screen Elements Started");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.people_screen_network_button)).isDisplayed(), "People Screen Network Button Element Not Found");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.people_screen_add_contact_button)).isDisplayed(), "People Screen Add Contact Button Element Not Found");
    Assert.assertTrue(driver.findElement(By.xpath(MobileScreenElements.people_screen_my_network_text)).isDisplayed(), "People Screen My Network text Element Not Found");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.people_mynetwork_screen_date)).isDisplayed(), "People Screen Date text Element Not Found");
    System.out.println("Verifying People Screen Elements Completed");
  }

  public void tapSearch() throws InterruptedException{
    doWait();
    System.out.println("Tap on Search icon Started");
    driver.findElement(By.xpath(MobileScreenElements.home_tab_search_nav_icon)).click();
    doWait();
    System.out.println("Tap on Search icon Completed");
  }

  public void verifySearchScreenElements() throws InterruptedException{
    doWait();
    System.out.println("Verifying Search Screen Elements Started");
    Assert.assertTrue(driver.findElement(By.id(MobileScreenElements.search_screen_search_bar)).isDisplayed(), "Search Screen Search bar Element Not Found");
    Assert.assertTrue(driver.findElement(By.xpath(MobileScreenElements.search_screen_back_icon)).isDisplayed(), "Search Screen Back icon Element Not Found");
    System.out.println("Verifying Search Screen Elements Completed");
    tapBackIcon();
  }

  public void tapBackIcon() throws InterruptedException{
    doWait();
    System.out.println("Tap on Back Icon Started");
    driver.findElement(By.xpath(MobileScreenElements.search_screen_back_icon)).click();
    doWait();
    System.out.println("Tap on Back Icon Completed");
  }

  public void logout() throws InterruptedException{
    doWait();
    tapProfile();
    tapProfileSettings();
    System.out.println("Logout Started");
    driver.scrollTo(MobileScreenElements.profile_settings_screen_signout_button);
    doWait();
    driver.findElement(By.name(MobileScreenElements.profile_settings_screen_signout_button)).click();
    doWait();
    System.out.println("Logout Successfully Completed");
  }
}
