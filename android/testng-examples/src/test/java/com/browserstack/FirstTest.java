package com.browserstack;

import java.time.Duration;
import java.util.List;

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

public class FirstTest extends AppiumTest {

    @Test
    public void test() throws Exception {
      driver.launchApp();
      driver.setLocation(new Location(49, 123, 10));
      WebElement continueButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")));
      continueButton.click();

      WebElement skipButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
             ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")));
      skipButton.click();
  
      WebElement searchElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
          ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Search Wikipedia")));

      searchElement.click();
      WebElement insertTextElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
          ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")));
      insertTextElement.sendKeys("test");
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
      driver.resetApp();
      String relativePath = "WikipediaSample.apk";  // Relative path to APK file
      String projectDir = System.getProperty("user.dir");  // Get the current project directory
      File appFile = Paths.get(projectDir, relativePath).toFile();  // Resolve the absolute path
      String bundleId = "org.wikipedia.alpha";
      driver.terminateApp(bundleId);
      driver.activateApp(bundleId);
      String appActivity = "org.wikipedia.main.MainActivity";
//      driver.startActivity("appPackage",bundleId, null, null);
      if (appFile.exists()) {
        System.out.println("Found APK: " + appFile.getAbsolutePath());
//        driver.installApp(appFile.getAbsolutePath());
      }
    }
}
