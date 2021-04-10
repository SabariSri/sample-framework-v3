package com.test.tests;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.test.base.TestActions;
import com.test.pages.GoogleHomePage;

@Test(dataProvider = "sampleTest", dataProviderClass = com.test.utils.dataprovider.TestDataProviders.class)
public class GoogleTest extends TestActions {
	public void sampleTest(HashMap<String,String> data) {

		report.startTest("Test-Trial001");
		launchUrl("https://www.google.co.in");

		GoogleHomePage homePage = PageFactory.initElements(driver, GoogleHomePage.class);

		homePage.searchAndVerify(data.get("SearchKey"));
	}
}
