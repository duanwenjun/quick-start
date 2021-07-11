package com.duan.selenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author duanwj
 */
public class SeleniumElementTakeScreenshot {

  public static void main (String[] args) throws IOException {
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("http://localhost:8081");
    WebElement element = driver.findElement(By.id("headlessBrowserRenderSign"));
    File scrFile = element.getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile, new File("./image1.png"));
    driver.quit();
  }
}

