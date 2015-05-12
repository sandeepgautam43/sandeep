package narvar.performActions.tracking;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class GetObjectDetails {

	public String[] getObjectDetails(String objectName) throws BiffException,
			IOException {

		File file = new File("test_data/OR.xls");

		Workbook pageFactoryWorkbook = Workbook.getWorkbook(file);
		Sheet pageFactorySheet = pageFactoryWorkbook.getSheet("pagefactory");
		int maxRows = pageFactorySheet.getRows();

		String[] rs = new String[2];
		for (int i = 1; i < maxRows; i++) {
			if (objectName.equals(pageFactorySheet.getCell(1, i).getContents())) {
				rs[0] = pageFactorySheet.getCell(2, i).getContents();
				rs[1] = pageFactorySheet.getCell(3, i).getContents();

			}
		}

		return rs;

	}

}
