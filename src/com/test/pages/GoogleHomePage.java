package com.test.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.test.base.TestActions;

public class GoogleHomePage extends TestActions {

	@FindBy(how = How.ID, using = "lst-ib")
	private WebElement searchBar;

	@FindBy(how = How.XPATH, using = "//input[@value='Google Search']")
	private WebElement searchBtn;

	@FindBy(how = How.XPATH, using = "//h3[child::a]")
	private WebElement verifySearch;

	public void searchAndVerify(String searchKey) {
		typeIn(searchBar, searchKey, "Search");
		clickOn(searchBtn, "Search button");

		String actualText = getText(verifySearch, "Search");

		if (searchKey.contains(actualText)) {

		} else {

		}
	}

}
