package narvar.readExcelFiles.tracking;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExl {

	public Sheet getSheet(String sheet) throws BiffException, IOException {
		File file = new File("test_data/TestSuite.xls");
		Workbook testsWorkbook = Workbook.getWorkbook(file);
		Sheet testSuiteSheet = testsWorkbook.getSheet(sheet);
		return testSuiteSheet;
	}

}
