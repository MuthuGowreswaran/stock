package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import base.BaseClass;

public class NseHomePage extends BaseClass {

	double purchasePrice = Double.parseDouble(prop.getProperty("purchase.price"));

	public NseHomePage searchStock(String stockSymbol) throws IOException {
		try {
			WebElement searchBox = getDriver().findElement(By.xpath("//div[@class='rbt']//input"));
			searchBox.sendKeys(stockSymbol);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='autocompleteList']")))
					.click();
			reportStep("Searched for stock: " + stockSymbol, "pass");
		} catch (Exception e) {
			reportStep("Failed to Searched for stock: " + stockSymbol, "fail");
		}
		return this;

	}

	public NseHomePage getCurrentPrice() throws IOException {
		try {
			String currentPrice = getWait()
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='quoteLtp']"))).getText();
			double price = Double.parseDouble(currentPrice.replaceAll(",", ""));
			Assert.assertTrue(price > 0, "Current price is valid");
			double profitLoss = price - purchasePrice;
			Assert.assertTrue(profitLoss != 0, "Profit/Loss is not zero");
			reportStep("Fetched current price: " + currentPrice, "pass");
		} catch (Exception e) {
			reportStep("Failed to fetch current price", "fail");
		}
		return this;
	}

	public NseHomePage getWeekHigh() throws IOException {
		try {
			String weekHigh = getDriver().findElement(By.xpath("//span[@id='week52highVal']")).getText();
			reportStep("Fetched 52-week high: " + weekHigh, "pass");
		} catch (Exception e) {
			reportStep("Failed to fetch 52-week high", "fail");

		}
		return this;
	}

	public NseHomePage getWeekLow() throws IOException {
		try {
			String weekLow = getDriver().findElement(By.xpath("//span[@id='week52lowVal']")).getText();
			reportStep("Fetched 52-week low: " + weekLow, "pass");

		} catch (Exception e) {
			reportStep("Failed to fetch 52-week low", "fail");

		}
		return this;
	}

}
