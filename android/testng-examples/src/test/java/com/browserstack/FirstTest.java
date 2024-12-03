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

public class FirstTest extends AppiumTest {

    @Test
    public void test() throws Exception {
      driver.launchApp();
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

      driver.setLocation(new Location(49, 123, 10));
      WebElement continueButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")));
      continueButton.click();
      driver.performTouchAction(new TouchAction(driver).tap(PointOption.point(500, 500)));
      TouchAction action1 = new TouchAction(driver).press(PointOption.point(500, 500));
      TouchAction action2 = new TouchAction(driver).press(PointOption.point(700, 500));

      MultiTouchAction multiTouchAction = new MultiTouchAction(driver);
      driver.performMultiTouchAction(multiTouchAction.add(action1).add(action2));

      WebElement skipButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
             ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")));
      skipButton.click();
  
      WebElement searchElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
          ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Search Wikipedia")));

      searchElement.click();
      WebElement insertTextElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
          ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")));
      insertTextElement.click();
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
      File appFile = Paths.get(projectDir, relativePath).toFile();  // Resolve the absolute path
      String bundleId = "com.bsstag.espressotesting";
      String appActivity = "org.wikipedia.main.MainActivity";
//      driver.startActivity("appPackage",bundleId, null, null);
      if (appFile.exists()) {
        driver.installApp("https://qa-live-server.bsstag.com/download/EspressoTestingUrl-debug.apk");
      }
      Set<String> contextNames = driver.getContextHandles();
      System.out.println("Available Contexts:");
      for (String contextName : contextNames) {
        System.out.println(contextName);
      }
      driver.activateApp(bundleId);
      driver.context(contextNames.toArray(new String[contextNames.size()])[0]);
      driver.terminateApp(bundleId);
//      JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//      jsExecutor.executeScript("document.body.style.backgroundColor = 'lightblue';");
    }
}
