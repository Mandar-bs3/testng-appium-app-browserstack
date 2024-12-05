package com.browserstack;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.html5.Location;
import io.appium.java_client.*;
import org.openqa.selenium.By;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import org.openqa.selenium.ScreenOrientation;
import io.appium.java_client.TouchAction;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.PointOption;
//import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
//import io.appium.java_client.flutter.commands.DragAndDropParameter;


public class FirstTest extends AppiumTest {

    @Test
    public void test() throws Exception {
      driver.launchApp();
//      driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, Duration.ofSeconds(5));

      driver.setLocation(new Location(49, 123, 10));
      WebElement continueButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")));
      continueButton.click();
      driver.performTouchAction(new TouchAction(driver).tap(PointOption.point(500, 500)));
      TouchAction action1 = new TouchAction(driver).press(PointOption.point(500, 500));
      TouchAction action2 = new TouchAction(driver).longPress(PointOption.point(700, 500));
      TouchAction action3 = new TouchAction(driver).moveTo(PointOption.point(800, 800));

      MultiTouchAction multiTouchAction = new MultiTouchAction(driver);
      driver.performMultiTouchAction(multiTouchAction.add(action1).add(action2).add(action3));

      WebElement skipButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
             ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")));
      skipButton.click();
  
      WebElement searchElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
          ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Search Wikipedia")));

      searchElement.click();
      WebElement insertTextElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
          ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")));
      insertTextElement.click();
//      Actions action = new Actions(driver);
//      action.moveToElement(insertTextElement);
//      action.doubleClick();
//      action.perform();
      driver.hideKeyboard();
      insertTextElement.sendKeys("test");
//      driver.pressKeyCode(AndroidKeyCode.SPACE);
      insertTextElement.clear();
      insertTextElement.sendKeys("BrowserStack");

      Thread.sleep(5000);

      List<WebElement> allProductsName = driver.findElements(AppiumBy.className("android.widget.TextView"));
      Assert.assertTrue(allProductsName.size() > 0);

      WebElement searchResultItem= (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
              ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")));
      searchResultItem.click();
      Thread.sleep(5000);
      driver.rotate(ScreenOrientation.LANDSCAPE);
//      driver.longPressKeyCode(AndroidKeyCode.HOME);
      driver.resetApp();
      String relativePath = "EspressoTestingUrl-debug.apk";  // Relative path to APK file
      String projectDir = System.getProperty("user.dir");  // Get the current project directory
      String bundleId = "com.bsstag.espressotesting";
      String appActivity = "org.wikipedia.main.MainActivity";
//      driver.startActivity("appPackage",bundleId, null, null);
      driver.installApp("https://qa-live-server.bsstag.com/download/EspressoTestingUrl-debug.apk");
      Set<String> contextNames = driver.getContextHandles();
      System.out.println("Available Contexts:");
      for (String contextName : contextNames) {
        System.out.println(contextName);
      }
      driver.activateApp(bundleId);
      driver.rotate(ScreenOrientation.PORTRAIT);
      try {
        WebElement allowButton = driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_one_time_button"));
        allowButton.click();
        System.out.println("Location permission granted.");
      } catch (Exception e) {
        System.out.println("No permission dialog appeared.");
      }
      WebElement alertButton= (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
              ExpectedConditions.elementToBeClickable(AppiumBy.id("com.bsstag.espressotesting:id/alert_button")));
      alertButton.click();
      Thread.sleep(2000);
      driver.switchTo().alert().accept();
      Map<String, Object> deviceInfo = (Map<String, Object>) driver.executeScript("mobile: deviceInfo");
      System.out.println("Device Model: " + deviceInfo.get("model"));
      driver.context(contextNames.toArray(new String[contextNames.size()])[0]);

//      driver.installApp("https://qa-live-server.bsstag.com/download/google-chrome-122-0-6261-43.apk");
      driver.activateApp("com.android.chrome");
      Thread.sleep(2000);

      Set<String> contextHandles = driver.getContextHandles();
      System.out.println("Available Contexts:");
      for (String contextName : contextHandles) {
        System.out.println(contextName);
      }
      for (String context : contextHandles) {
        if (context.contains("WEBVIEW_chrome")) {
          driver.context(context); // Switch to WebView context
          break;
        }
      }

      driver.get("https://www.example.com/ajax_test");

      // Asynchronous JavaScript to wait for AJAX request to finish
      String script = "var callback = arguments[arguments.length - 1];"
              + "var interval = setInterval(function() {"
              + "    if (document.readyState === 'complete') {"
              + "        clearInterval(interval);"
              + "        callback(true);"
              + "    }"
              + "}, 100);";

      // Execute the async script and wait for the AJAX request to finish
      Boolean ajaxCompleted = (Boolean) driver.executeAsyncScript(script);

      if (ajaxCompleted) {
        System.out.println("AJAX request completed.");
      } else {
        System.out.println("AJAX request not completed.");
      }

      // switch to frame and parent frame
      driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_iframe");
      driver.switchTo().frame("iframeResult");
//      WebElement text = driver.findElement(By.cssSelector("h1"));
//      System.out.println(text.getText());
      driver.switchTo().parentFrame();
//      WebElement result = driver.findElement(By.id("result"));
//      System.out.println("Result text: " + result.getText());

      driver.get("https://the-internet.herokuapp.com/javascript_alerts");
      WebElement confirmPopupBtn = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
              ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='jsConfirm()']")));
      confirmPopupBtn.click();
      Thread.sleep(2000);
      driver.switchTo().alert().dismiss();
      String title = (String) driver.executeScript("return document.title;");
      System.out.println("Page Title: " + title);
      WebElement elementalWeb = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
              ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='http://elementalselenium.com/']")));
      elementalWeb.click();
      driver.navigate().refresh();
      driver.navigate().back();
      driver.navigate().forward();
      driver.terminateApp(bundleId);

//      driver.setWindowRect(100, 200, 800, 600);

//      driver.performDragAndDrop(new DragAndDropParameter(
//              driver.findElement(AppiumBy.flutterKey("enabled")),
//              driver.findElement(AppiumBy.flutterKey("Fixed Size Text"))));
    }
}
