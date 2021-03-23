package com.duan.selenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author duanwj
 */
public class SeleniumTakeScreenshot {

  public static void main (String[] args) throws IOException {
   /* ChromeOptions options = new ChromeOptions();
    // 无头浏览
    options.addArguments("headless");
    // 以最高权限运行
    options.addArguments("no-sandbox");*/
    WebDriver driver = new ChromeDriver();
    //最大化窗口
    driver.manage().window().maximize();
    //driver.get("http://172.20.25.193:5000");
    driver.get("http://www.baidu.com");
    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile, new File("./image.png"));
    driver.quit();
  }
}

