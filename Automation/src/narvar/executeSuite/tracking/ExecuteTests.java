package narvar.executeSuite.tracking;

import java.io.IOException;
import java.sql.SQLException;

import jxl.Sheet;
import jxl.read.biff.BiffException;
import narvar.keywordExecution.tracking.keyWordExecution;
import narvar.readExcelFiles.tracking.ReadExl;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class ExecuteTests {

	keyWordExecution test = new keyWordExecution();

	@Test(priority = 3)
	public void Forever21() throws BiffException, IOException,
			ClassNotFoundException, SQLException, InterruptedException {

		Sheet testSuiteSheet = new ReadExl().getSheet("Suite");

		int maxRows = testSuiteSheet.getRows();

		for (int row = 1; row < maxRows; row++) {
			int col = 0;

			String testCaseName = testSuiteSheet.getCell(col + 1, row)
					.getContents();
			String runMode = testSuiteSheet.getCell(col + 3, row).getContents();

			if (testCaseName.equals("Forever21") && runMode.equals("Y")) {
				test.executeTest(testCaseName);
				System.out.println("Forever21 Test Cases Executed");
			} else if (testCaseName.equals("Forever21") && runMode.equals("N")) {

				throw new SkipException("Skip this");
			}
		}

	}

	@Test(priority = 2)
	public void Birchbox() throws BiffException, IOException,
			ClassNotFoundException, SQLException, InterruptedException {

		Sheet testSuiteSheet = new ReadExl().getSheet("Suite");
		int maxRows = testSuiteSheet.getRows();
		for (int row = 1; row < maxRows; row++) {
			int col = 0;
			String testCaseName = testSuiteSheet.getCell(col + 1, row)
					.getContents();
			String runMode = testSuiteSheet.getCell(col + 3, row).getContents();
			if (testCaseName.equals("Birchbox") && runMode.equals("Y")) {
				test.executeTest(testCaseName);
				System.out.println("Birchbox Test Cases Executed");
			} else if (testCaseName.equals("Birchbox") && runMode.equals("N")) {
				throw new SkipException("Skip this Test Case");
			}
		}
	}

	@Test(priority = 4)
	public void Freepeople() throws BiffException, IOException,
			ClassNotFoundException, SQLException, InterruptedException {

		Sheet testSuiteSheet = new ReadExl().getSheet("Suite");

		int maxRows = testSuiteSheet.getRows();

		for (int row = 1; row < maxRows; row++) {
			int col = 0;

			String testCaseName = testSuiteSheet.getCell(col + 1, row)
					.getContents();
			String runMode = testSuiteSheet.getCell(col + 3, row).getContents();

			if (testCaseName.equals("Freepeople") && runMode.equals("Y")) {
				test.executeTest(testCaseName);
				System.out.println("Freepeople Test Cases Executed");
			} else if (testCaseName.equals("Freepeople") && runMode.equals("N")) {
				throw new SkipException("Skip this");
			}
		}
	}

	@Test(priority = 1)
	public void Baublebar() throws BiffException, IOException,
			ClassNotFoundException, SQLException, InterruptedException {

		Sheet testSuiteSheet = new ReadExl().getSheet("Suite");

		int maxRows = testSuiteSheet.getRows();

		for (int row = 1; row < maxRows; row++) {
			int col = 0;

			String testCaseName = testSuiteSheet.getCell(col + 1, row)
					.getContents();
			String runMode = testSuiteSheet.getCell(col + 3, row).getContents();

			if (testCaseName.equals("Baublebar") && runMode.equals("Y")) {
				test.executeTest(testCaseName);
				System.out.println("Baublebar Test Cases Executed");
			} else if (testCaseName.equals("Baublebar") && runMode.equals("N")) {
				throw new SkipException("Skip this");
			}
		}
	}
}