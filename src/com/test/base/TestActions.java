package com.test.base;

import org.openqa.selenium.WebElement;

public class TestActions extends TestBase {

	public void launchUrl(String url) {
		try {
			driver.get(url);
			report.stepPass("Url launched :: " + url);
		} catch (Exception e) {
			report.stepFail("Cant able to launch url :: " + url + "\n\n" + " ERROR MESSAGE :: " + e.toString());
		}
	}

	public void typeIn(WebElement element, String value, String refKey) {
		try {
			element.sendKeys(value);
			report.stepPass("Typed " + value + " in " + refKey);
		} catch (Exception e) {
			report.stepFail("Cant able to type " + value + " in " + refKey + "\n\n" + "ERROR MESSAGE " + e.toString());
		}
	}

	public void clickOn(WebElement element, String refKey) {
		try {
			element.click();
			report.stepPass("Clicked on " + refKey);
		} catch (Exception e) {
			report.stepFail("Cant able to click on " + refKey + "\n\n" + "ERROR MESSAGE :: " + e.toString());
		}
	}

	public String getText(WebElement element, String refKey) {
		try {
			String text = element.getText();
			report.stepInfo("Text of " + refKey + " is " + text);
			return text;
		} catch (Exception e) {
			report.stepFail("Cant able to getText of " + refKey + "\n\n" + "ERROR MESSAGE :: " + e.toString());
			return null;
		}
	}
}
