package enh.db.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utilities.ConfigReader;
import utilities.HtmlReportUtil;

public class Bsspl {
	
	public static ArrayList<String> testData = new ArrayList<String>();
	public static ArrayList<String> list_logid = new ArrayList<String>();
	public static ArrayList<String> list_logid2 = new ArrayList<String>();
	public static ArrayList<String> list_logid3 = new ArrayList<String>();
	public static ArrayList<String> list_logid4 = new ArrayList<String>();
	public static HashSet<String> set=new HashSet<String>(); 
	public static HashSet<String> set2=new HashSet<String>();  
	public static HashSet<String> set3=new HashSet<String>();  
	public static String logId=null;
	static XSSFWorkbook workbook;
	static FileInputStream file;
	static XSSFSheet sheet;   
	static XSSFCell cell;
	public static String flightnumber_departure=null;
	
	public static void bsspl(String Execlfilepath, String sheetName,String environment) throws SQLException, IOException {
		 HtmlReportUtil.stepInfo(sheetName);
			System.out.println(sheetName);
		 file = new FileInputStream(new File(Execlfilepath));
		 workbook = new XSSFWorkbook(file);
		 sheet =workbook.getSheet(sheetName);
		 String[][] data=new String[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int i=0;i<sheet.getLastRowNum();i++){			
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++){
					//data[i][j]=sheet.getRow(i+1).getCell(j).toString();
					DataFormatter formatter = new DataFormatter();
					data[i][j] = formatter.formatCellValue(sheet.getRow(i+1).getCell(j));
					String FlightNum=data[i][j].replace("-", " ");
					System.out.println(FlightNum);
					testData.add(FlightNum);
					String SQL_Querry1="SELECT * FROM `DailyFlightSchedule_Merged` "
							+ "where date(IFNULL(atd, sensor_atd))= '2019-12-25' and operationunit = 22 "
							+ "and flightnumber_departure like '%"+FlightNum+"%' ";
					
					ResultSet result = DBWrapper.Connect(SQL_Querry1,environment);
					while(result.next()) {
						 logId=  result.getString("LogId");
						 flightnumber_departure=  result.getString("flightnumber_departure");
						 //HtmlReportUtil.stepInfo("Logid-"+logId);
						set3.add(logId);
						list_logid3.add(flightnumber_departure);
						//System.out.println("logid "+list_logid);
						System.out.println("list_logid.size() - "+set3.size());
						
					}
					
					
					String SQL_Querry2= "SELECT * FROM `EquipActivityLogs` where flight_pk='"+logId+"' ";
					ResultSet result2 = DBWrapper.Connect(SQL_Querry2,environment);
					while(result2.next()) {				
						String flight_pk=  result2.getString("flight_pk");	
						String operationName=result2.getString("operationname");
						String flightno=  result2.getString("flightno");
						String devid=  result2.getString("devid");
						String flogdate=  result2.getString("flogdate");
						String tlogdate=  result2.getString("tlogdate");
						if(operationName.equalsIgnoreCase("FLE")) {
						set.add(flight_pk);
						System.out.println("yes fle data count -"+set.size());						
						System.out.println(flight_pk+"|"+flightno+"|"+devid+"|"+operationName+"|"+flogdate+"|"+tlogdate);
						//HtmlReportUtil.stepInfo(" Yes"+"devid-"+devid+" flight_pk-"+flight_pk+" flightno-"+flightno+" flogdate- "+flogdate+" tlogdate"+tlogdate +"flightnumber_departure"+flightnumber_departure);
				
						}else {
						set2.add(flight_pk);
						
						//System.out.println("yes fle "+list_logid2);	
						System.out.println("no fle data count -"+set2.size());
						
						System.out.println(flight_pk+"|"+flightno+"|"+devid+"|"+operationName+"|"+flogdate+"|"+tlogdate);
						//HtmlReportUtil.stepInfo(" no"+"devid-"+devid+" flight_pk-"+flight_pk+" flightno-"+flightno+" flogdate- "+flogdate+" tlogdate"+tlogdate +"flightnumber_departure"+flightnumber_departure);
						}	
					}
					
				}}
			 HtmlReportUtil.stepInfo("test data count -"+testData.size());
			 HtmlReportUtil.stepInfo("test data-"+testData);
			HtmlReportUtil.stepInfo("logid count-"+set3.size());
				HtmlReportUtil.stepInfo("logid-"+set3);
				 HtmlReportUtil.stepInfo("fle data count -"+set.size());
				 HtmlReportUtil.stepInfo("fle data-"+set);	
				 set3.removeAll(set);
				 HtmlReportUtil.stepInfo("no fle data size-"+set3.size());
				 HtmlReportUtil.stepInfo("no fle data-"+set3);
				DBWrapper.dbConnectionClose();
					 
		 
				/* HtmlReportUtil.stepInfo(sheetName);
				 file = new FileInputStream(new File(Execlfilepath));
				 workbook = new XSSFWorkbook(file);
				 sheet =workbook.getSheet(sheetName);
				 String[][] data=new String[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
					for(int i=0;i<sheet.getLastRowNum();i++){			
						for(int j=0;j<sheet.getRow(0).getLastCellNum();j++){
							//data[i][j]=sheet.getRow(i+1).getCell(j).toString();
							DataFormatter formatter = new DataFormatter();
							data[i][j] = formatter.formatCellValue(sheet.getRow(i+1).getCell(j));
							String FlightNum=data[i][j].replace("-", " ");
							System.out.println(FlightNum);
							testData.add(FlightNum);
						}}
					for(int i= 0; i<testData.size() ; i++){
					String SQL_Querry1="SELECT * FROM `DailyFlightSchedule_Merged` "
							+ "where date(IFNULL(atd, sensor_atd))= '2019-12-10' and operationunit = 22 and flightnumber_departure like '%"+testData.get(i)+"%' ";
					
					ResultSet result = DBWrapper.Connect(SQL_Querry1);
					while(result.next()) {
						 logId=  result.getString("LogId");
						// HtmlReportUtil.stepInfo("Logid-"+logId);
						String flightnumber_departure=  result.getString("flightnumber_departure");
						list_logid.add(logId);	
						System.out.println("logid "+list_logid);
					}}
					for(int i= 0; i<list_logid.size() ; i++){
					String SQL_Querry2= "SELECT * FROM `EquipActivityLogs` where flight_pk='"+list_logid.get(i)+"' and operationname='fle' ";
					ResultSet result2 = DBWrapper.Connect(SQL_Querry2);
					while(result2.next()) {					
						String flight_pk=  result2.getString("flight_pk");
						if(list_logid2.contains(flight_pk)) {
						list_logid2.add(flight_pk);
						System.out.println("fle "+list_logid2);
						}
						String flightno=  result2.getString("flightno");
						String devid=  result2.getString("devid");
						String operationname=  result2.getString("operationname");
						String flogdate=  result2.getString("flogdate");
						String tlogdate=  result2.getString("tlogdate");
						System.out.println(flight_pk+"|"+flightno+"|"+devid+"|"+operationname+"|"+flogdate+"|"+tlogdate);
						HtmlReportUtil.stepInfo("Logid-"+logId+" flight_pk-"+flight_pk+" flightno-"+flightno+" flogdate- "+flogdate+" tlogdate"+tlogdate);
						
					}}

					HtmlReportUtil.stepInfo("Test data count -"+testData.size());
					HtmlReportUtil.stepInfo("Test data  -"+testData);
					HtmlReportUtil.stepInfo("list_logid data count -"+list_logid.size());
					HtmlReportUtil.stepInfo("list_logid data  -"+list_logid);
					HtmlReportUtil.stepInfo("fle data count -"+list_logid2.size());
					HtmlReportUtil.stepInfo("fle data  -"+list_logid2);
					testData.removeAll(list_logid);
					HtmlReportUtil.stepInfo("missing  -"+testData);
					DBWrapper.dbConnectionClose();
*/
				
	}
	
	
}
