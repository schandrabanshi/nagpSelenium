/**
 * 
 */
package in.amazon.nagpselenium.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author sarojchandrabanshi
 *FileUtility class for all generic methods for accessing file.
 */
public class FileUtility extends Base{	
	/**
	 * Getting value from config.properties file.
	 * @param Key name to get it's value
	 * @return Value based on passed key.
	 * @throws IOException
	 */
	public synchronized static String getConfigProperties(String Key) throws IOException {		
			
			String absPath=System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties";
			Properties prop=new Properties();
			prop.load(new FileInputStream(absPath));
			return (String) prop.get(Key);
		}
	/**
	 * Getting value from testData.properties file.
	 * @param Key name to get it's value
	 * @return Value based on passed key.
	 */
	public synchronized static String getTestDataProperties(String Key){		
		
		String absPath=System.getProperty("user.dir")+"\\src\\main\\resources\\testData.properties";
		Properties prop=new Properties();
		try {
			prop.load(new FileInputStream(absPath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return (String) prop.get(Key);
	}
	/**
	 * Getting value from Log4j.properties file.
	 * @param Key name to get it's value
	 * @return Value based on passed key.
	 */
	public synchronized static String getLog4jProperties(String Key)  {		
		
		String absPath=System.getProperty("user.dir")+"\\src\\main\\resources\\log4j.properties";
		Properties prop=new Properties();
		try {
			prop.load(new FileInputStream(absPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (String) prop.get(Key);
	}
	/**
	 * Getting value from testCase.properties file.
	 * @param Key name to get it's value
	 * @return Value based on passed key.
	 */
	public synchronized static String getTestCaseProperties(String Key) {		
		
		String absPath=System.getProperty("user.dir")+"\\src\\main\\resources\\testCase.properties";
		Properties prop=new Properties();
		try {
			prop.load(new FileInputStream(absPath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return (String) prop.get(Key);
	}
	/**
	 * Archiving or moving report folder to archived folder.
	 */
	public synchronized static void archiveReport() {
		File source = new File(System.getProperty("user.dir")+"/current_test_results");
		File dest = new File(System.getProperty("user.dir")+"/archived_test_results");
		try {
		    FileUtils.copyDirectory(source, dest);
		    FileUtils.cleanDirectory(source);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	/**
	 * Reading data from excel file.
	 * @param filePath Excel file complete location/path
	 * @param shtName Excel sheet name.
	 * @return returning excel data in row-column value.
	 */
	public synchronized static Object[][] getExcelData(String filePath, String shtName){
		Object[][] obj=null;		
		XSSFWorkbook wb=null;
		try {
			
			File file=new File(filePath);
			
			InputStream inputStream=new FileInputStream(file);	
			
			
			wb=new XSSFWorkbook(inputStream);
			XSSFSheet sheet=wb.getSheet(shtName);
			
			
			
			int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
			 int cellCount=sheet.getRow(1).getLastCellNum();
			 
			 obj=new Object[rowCount][cellCount];
			 
			 for(int i=1; i<=rowCount; i++) {
				 for (int j=0; j<cellCount; j++) {
					 obj[i-1][j]=sheet.getRow(i).getCell(j).getStringCellValue();
				 }
			 }
			 
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		wb=null;
		return obj;
		
	}
	
}
