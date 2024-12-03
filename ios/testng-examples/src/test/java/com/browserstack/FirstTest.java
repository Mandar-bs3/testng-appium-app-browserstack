package com.browserstack;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class FirstTest extends AppiumTest {

  @Test
  public void test() throws Exception {

    new WebDriverWait(driver, Duration.ofSeconds(30)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.className("XCUIElementTypeStaticText")));

    WebElement newButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("New")));
    newButton.click();
    Thread.sleep(50);

    WebElement bestButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Best")));
    bestButton.click();
    Thread.sleep(50);

    WebElement favButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Fav")));
    favButton.click();
    Thread.sleep(50);

    WebElement settingsButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Setting")));
    settingsButton.click();
    Thread.sleep(50);
    driver.shake();
  }
}
