package tests;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.NseHomePage;

public class StockInfoTest extends BaseClass {

	@BeforeTest
	public void setValue() {
		testName = "Verify Stock Information";
		testDescription = "Verify the stock information for the given stock symbol";
		testCategory = "smoke";
		testAuthor = "Hari";
	}

	@Test
	public void testStockInformation() throws IOException {
		String stockSymbol = prop.getProperty("stock.symbol");
		 new NseHomePage()
		.searchStock(stockSymbol)
		.getCurrentPrice()
		.getWeekHigh()
		.getWeekLow();
		

	}

}
