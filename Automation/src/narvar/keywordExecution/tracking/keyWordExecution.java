package narvar.keywordExecution.tracking;

import java.io.IOException;
import java.sql.SQLException;
import narvar.performActions.tracking.*;
import narvar.readExcelFiles.tracking.ReadExl;
import jxl.Sheet;
import jxl.read.biff.BiffException;

public class keyWordExecution extends ReadExl{
	
	PerformActions actions=new PerformActions();

	public void executeTest(String testCaseName)	throws BiffException, IOException, ClassNotFoundException, SQLException, InterruptedException {
		Sheet testSheet = new ReadExl().getSheet(testCaseName);
	
		System.out.println("EXECUTE TESTS");

		int maxtestSteps = testSheet.getRows();
	
		for (int step = 1; step < maxtestSteps; step++) {
		int c = 0;
		String testStepId = testSheet.getCell(c, step).getContents();
		String testStep = testSheet.getCell(c + 1, step).getContents();
		String objectName = testSheet.getCell(c + 2, step).getContents();
		String inputdata = testSheet.getCell(c + 3, step).getContents();
		String assertionValue = testSheet.getCell(c + 4, step).getContents();
		
		String value = "";
		if (!inputdata.isEmpty()) {
			int colNumber = testSheet.findCell(inputdata)
					.getColumn();
			value = testSheet.getCell(colNumber, step).getContents();
		}
		
		if (testStep.equals("type")) {
			actions.type(testCaseName, testStepId, objectName, value);
		}

		if (testStep.equals("click")) {
			actions.click(testCaseName, testStepId, objectName);
		}
		
		if (testStep.equals("logoClick")) {
			actions.testLogoLink(objectName,assertionValue);
		}

		if (testStep.equals("verifyTextPresent")) {
			actions.verifyTextPresent(objectName,assertionValue);
		}
		
		if (testStep.equals("customerCareLink")) {
			actions.customerCareLink(objectName, assertionValue);
		}

		if(testStep.equals("enterURL")){
			actions.enterURL(objectName);
		}
				
		if(testStep.equals("verifyElementPresent")) {
			actions.verifyElementDisplay(objectName);
		}

		if (testStep.equals("verifyImageDisplay")) {
			actions.verifyImageDisplay(objectName);
		}
		
		if (testStep.equals("closeBrowser")) {
			actions.closeBrowser();
		}
		
		if (testStep.equals("SMS")) {
			actions.dbDecryption(assertionValue,inputdata);
		}
		
		if (testStep.equals("overallScore")) {
			actions.overallScore(assertionValue, inputdata);
		}
		
		if (testStep.equals("itemcondScore")) {
			actions.itemcondScore(assertionValue, inputdata);
		}
		
		if (testStep.equals("packagingScore")) {
			actions.packagingScore(assertionValue, inputdata);
		}
		
		if (testStep.equals("uidesignScore")) {
			actions.uidesignScore(assertionValue, inputdata);
		}
		
		if (testStep.equals("shortComment")) {
			actions.shortComment(assertionValue, inputdata);
		}
		
		if (testStep.equals("longComment")) {
			actions.longComment(assertionValue, inputdata);
		}
		
		if (testStep.equals("clickCounts")) {
			actions.clickCounts(assertionValue, inputdata);
		}
		
		if (testStep.equals("trackingNumberClick"))
		{
			actions.trackingNumberFlip(objectName,assertionValue);
		}
		
		if (testStep.equals("recentHistoryFlip"))
		{
			actions.trackingNumberFlip(objectName,assertionValue);
		}
		
		if (testStep.equals("null"))
		{
			System.out.println("Please enter valid Keyword in Test Step");
		}

		if (testStep.equals("verifyCalenderDate"))
		{
			actions.calenderDatePicker(objectName, assertionValue);
		}

	}
		

		
	}
	

}