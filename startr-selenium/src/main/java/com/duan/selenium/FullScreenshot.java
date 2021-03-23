package com.duan.selenium;

import java.io.File;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @author duanwenjun@gokuaidian.com
 */
public class FullScreenshot {

  @SneakyThrows
  public static void main (String[] args) {
    ChromeOptions options = new ChromeOptions();
    options.setExperimentalOption("useAutomationExtension", false);
    options.addArguments("disable-infobars");

    ChromeDriverEx driver = new ChromeDriverEx(options);

    driver.get("https://www.thinbug.com/q/45199076");
    File file = driver.getFullScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("./full.png"));
  }

}
