package com.browserstack;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.html5.Location;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ScreenOrientation;
import io.appium.java_client.TouchAction;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.PointOption;
import java.time.Duration;
import java.util.Map;

public class FirstTest extends AppiumTest {

  @Test
  public void test() throws Exception {
    driver.launchApp();
    driver.setLocation(new Location(49, 123, 10));


    new WebDriverWait(driver, Duration.ofSeconds(30)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.className("XCUIElementTypeStaticText")));

    WebElement newButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("New")));
    newButton.click();
    Thread.sleep(50);

    driver.performTouchAction(new TouchAction(driver).tap(PointOption.point(500, 500)));
    TouchAction action1 = new TouchAction(driver).press(PointOption.point(500, 500));
    TouchAction action2 = new TouchAction(driver).longPress(PointOption.point(700, 500));
    TouchAction action3 = new TouchAction(driver).moveTo(PointOption.point(800, 800));

    MultiTouchAction multiTouchAction = new MultiTouchAction(driver);
    driver.performMultiTouchAction(multiTouchAction.add(action1).add(action2).add(action3));

    WebElement bestButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Best")));
    bestButton.click();
    Thread.sleep(50);
    driver.rotate(ScreenOrientation.LANDSCAPE);

    WebElement favButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Fav")));
    favButton.click();
    Thread.sleep(50);

    WebElement settingsButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Setting")));
    settingsButton.click();
    Thread.sleep(50);
    driver.shake();
    driver.resetApp();
    driver.activateApp("japan.tokyo.chiyoda.sogarage.ios.HackerNewsScrapBookFree");
    driver.rotate(ScreenOrientation.PORTRAIT);
    Map<String, Object> deviceInfo = (Map<String, Object>) driver.executeScript("mobile: deviceInfo");
    System.out.println("Device Model: " + deviceInfo.get("model"));
  }
}
