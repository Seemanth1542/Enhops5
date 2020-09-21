package enh.db.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utilities.HtmlReportUtil;

public class Bsspl_Bay {
	public static ArrayList<String> testData = new ArrayList<String>();
	public static ArrayList<String> list_flightNumber = new ArrayList<String>();
	public static ArrayList<String> list_bayNumberFromDB = new ArrayList<String>();
	
	
	static XSSFWorkbook workbook;
	static FileInputStream file;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell cell;
	
	
	public static int bayNotEqual =0;
	public static int bayEqual =0;
	public static int bayIsNull=0;
	
	public static void bsspl(String Execlfilepath, String sheetName,String environment) throws SQLException, IOException {
			HtmlReportUtil.stepInfo(sheetName);
			 file = new FileInputStream(new File(Execlfilepath));
			 workbook = new XSSFWorkbook(file);
			 sheet = workbook.getSheet(sheetName);
			
			 String[][] data=new String[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
				
			 for(int i=0;i<sheet.getLastRowNum();i++){			
						DataFormatter formatter = new DataFormatter();
						data[i][0] = formatter.formatCellValue(sheet.getRow(i+1).getCell(0));
						String FlightNum=data[i][0].replace("-", " ");
						System.out.println("FlightNumber: " +FlightNum);
						
						data[i][1] = formatter.formatCellValue(sheet.getRow(i+1).getCell(1));
						String BayNumberFromExcel=data[i][1].replace("-", "");
						System.out.println("BayNumber: " +BayNumberFromExcel);
						
						ResultSet result = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged`\r\n" + 
						"where date(IFNULL(atd, sensor_atd))= '2019-12-22' and operationunit = 22 and \r\n"
						+ "flightnumber_departure like '%"+FlightNum+"%' and bay is not null",environment);
						
						while(result.next()) {
						String logId=  result.getString("LogId");
						String flightnumber_departure = result.getString("flightnumber_departure");
						String bayNumberFromDB = result.getString("bay");
						
						if (bayNumberFromDB.equals(BayNumberFromExcel)) {
							System.out.println("bay number not equal for logid =" +logId);
							bayNotEqual = bayNotEqual +1;
							HtmlReportUtil.stepInfo("<b style=\"color:red;\">Logid: " +logId+"  FlightNo: " +flightnumber_departure+  "  bay No. in DB: "+bayNumberFromDB+"  Bay no. in Excel: "+BayNumberFromExcel+"</b>");
						}
						else {
							bayEqual = bayEqual +1;
							System.out.println("bay number equal for logid =" +logId);
							HtmlReportUtil.stepInfo("<b style=\"color:green;\">Logid: " +logId+  "  FlightNo: " +flightnumber_departure+  "  bay No. in DB: "+bayNumberFromDB+"  Bay no. in Excel: "+BayNumberFromExcel+"</b>");
						}
						}
						
						ResultSet result2 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged`\r\n" + 
								"where date(IFNULL(atd, sensor_atd))= '2019-12-09' and operationunit = 22 and \r\n"
								+ "flightnumber_departure like '%"+FlightNum+"%' and bay is null",environment);
						while(result2.next()) {
							String logId=  result2.getString("LogId");
							String flightnumber_departure = result2.getString("flightnumber_departure");
							String bayNumberFromDB = result2.getString("bay");
							bayIsNull = bayIsNull+1;
							HtmlReportUtil.stepInfo("<b style=\"color:brown;\">Logid: " +logId+"  FlightNo: " +flightnumber_departure+  "  bay No. in DB: "+bayNumberFromDB+"  Bay no. in Excel: "+BayNumberFromExcel+"</b>");
						
				}
			 }
			 HtmlReportUtil.stepInfo("<b style=\"color:red;\"> Total not equal : "+bayNotEqual+"</b>");
			 HtmlReportUtil.stepInfo("<b style=\"color:red;\"> Total bay is null in DB : "+bayIsNull+"</b>");
			 HtmlReportUtil.stepInfo("<b style=\"color:green;\"> Total equal : "+bayEqual+"</b>");
				
			 DBWrapper.dbConnectionClose();
			 																																																																																																																																																																	
	}
					
}			
				
				
		
	

						
				
						
						
		

	

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
					/*String FlightNum=Flight_No.replace("-", " ");
					String BayNum=Bay_No.replace("-", "");
					String BayNumFromExcel = BayNum.trim();
					System.out.println(FlightNum);
					System.out.println(BayNumFromExcel);
					testData.add(FlightNum);
					String SQL_Query1="SELECT * FROM `DailyFlightSchedule_Merged` \r\n"
							+ "where date(IFNULL(atd, sensor_atd))= '2019-12-09' and operationunit = 22 and flightnumber_departure like '%"+FlightNum+"%' and bay is not null";
					
					ResultSet result = DBWrapper.Connect(SQL_Query1);
					while(result.next()) {
						 logId=  result.getString("LogId");
						 flightnumber_departure=  result.getString("flightnumber_departure");
						 bayNumber = result.getString("bay");
						list_logid.add(logId);
						list_departureFlightNumber.add(flightnumber_departure);
					}
						if (!bayNumber.equals(BayNumFromExcel)) {
							//System.out.println("logid : "+list_logid+ "FlightNo : "+FlightNum+ "Bay no from excel :" +BayNum+ "Bay no from DB: " +bayNumber);
							//list_BayNotMatching.add(logId);
							HtmlReportUtil.stepInfo("logid : "+list_logid+ "FlightNo : "+FlightNum+ "Bay no from excel :" +BayNumFromExcel+ "Bay no from DB: " +bayNumber);
						}
						else {
							System.out.println("No data to display");
							HtmlReportUtil.stepInfo("No data to display");
						}
						System.out.println("logid "+list_logid);
						System.out.println("flightNo "+list_departureFlightNumber);
						
					
					System.out.println("logid count - "+list_logid.size());
					System.out.println("flight count - "+list_departureFlightNumber.size());
								
					HtmlReportUtil.stepInfo("logid count-"+list_logid.size());
					
					//HtmlReportUtil.stepInfo("Bay number not matching logid-"+list_BayNotMatching.size());
				 			 
				 //HtmlReportUtil.stepInfo("missing-"+testData);
*/				
					 
		 
				


