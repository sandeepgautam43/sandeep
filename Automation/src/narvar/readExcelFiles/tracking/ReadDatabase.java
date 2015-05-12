package narvar.readExcelFiles.tracking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jxl.Sheet;
import jxl.read.biff.BiffException;

public class ReadDatabase {

	public Connection getconnection() throws BiffException, IOException,
			ClassNotFoundException, SQLException {
		Sheet testSuiteSheet = new ReadExl().getSheet("MasterConfiguration");
		String DBUser = testSuiteSheet.getCell(1, 3).getContents();
		String DBPassword = testSuiteSheet.getCell(1, 4).getContents();
		String DBUrl = testSuiteSheet.getCell(1, 5).getContents();

		Connection c = null;

		Class.forName("org.postgresql.Driver");
		c = DriverManager.getConnection(DBUrl, DBUser, DBPassword);
		c.setAutoCommit(false);
		return c;
	}
}