package listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import enh.db.pages.SQL_Queries;
import utilities.ConfigReader;
import utilities.GlobalUtil;
import utilities.Utility;










/**
 * @author EN
 */
@SuppressWarnings("unused")
public class TestCasesSendMail extends Utility {
	private static String mailPropertiesFile = System.getProperty("user.dir")
			+ "/src/main/resources/ConfigFiles/mail.properties";
	private static Properties PROP = loadPropertyFile(mailPropertiesFile);
	
	// PROP.load(new FileInputStream(mailPropertiesFile));
	public static final String USERNAME = PROP.getProperty("userName");
	public static final String PASSWORD = PROP.getProperty("passWord");
	public static final String EMAILTO1 = PROP.getProperty("emailTo");
	public static final String EMAILTO = GlobalUtil.getCommonSettings().getEmailIds();
	public static final String EMAILTOCC = PROP.getProperty("emailToCC");
	public static final Boolean STARTTLS = true;
	public static final String HOST = PROP.getProperty("host");
	public static final String PORT = PROP.getProperty("port");
	public static final String EMAILAUTH = PROP.getProperty("mail_authentication");
	public static final String SOCKETFACTORYCLASS = PROP.getProperty("socketFactoryClass");
	public static final String FALLBACK = PROP.getProperty("fallback");
	public static final String PATH = null;
	public static final String MODULENAME = null;
	public static final int INDEXOFCOMMA = 0;
	public static final String USERFULLNAME = null;
	public static final String EMAIL_REGEX = "[a-z0-9\\_\\-\\.]+@[a-z0-9\\_\\-\\.]+\\.[a-z]+";
	public static final String REPORT_PATH = "/ExecutionReports/ExecutionReports";
	public static final String DIR_PATH = "user.dir";
	public static final String BLANK_VARIABLE = "";
	   public static StringBuilder testCase_Summary_Report = new StringBuilder();	
	    public static StringBuilder testCase_consolidated_Summary_Report = new StringBuilder();
	    public static Calendar cal;
		public static DateFormat dateFormat;
		public static XSSFWorkbook workbook;
		public static FileInputStream file;
		public static XSSFSheet sheet;   
		public static XSSFCell cell;
		public static int sno=0;
	/**
	 * @throws IOException
	 * @throws MessagingException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	@Test
	public static void sendDSREmailToClient() throws IOException, MessagingException {
		String subject1 = "Automation Test Cases Status Report ";
		//String subject2 = SQL_Queries.todayDayDateTime();
		Properties PROPS = System.getProperties();
		PROPS.put("mail.smtp.host", HOST);
		PROPS.put("mail.smtp.user", USERNAME);
		PROPS.put("mail.smtp.password", PASSWORD);
		PROPS.put("mail.smtp.auth", EMAILAUTH);

		if (!"".equals(PORT)) {
			PROPS.put("mail.smtp.port", PORT);
			PROPS.put("mail.smtp.socketFactory.port", PORT);
		}
		if (!"".equals(STARTTLS))
			PROPS.put("mail.smtp.starttls.enable", STARTTLS);

		if (!"".equals(SOCKETFACTORYCLASS))
			PROPS.put("mail.smtp.socketFactory.class", SOCKETFACTORYCLASS);

		if (!"".equals(FALLBACK))
			PROPS.put("mail.smtp.socketFactory.fallback", FALLBACK);

		Session session = Session.getDefaultInstance(PROPS);
		session.setDebug(false);

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(USERNAME, PROP.getProperty("userFullName")));
		Date date = new Date();
		 SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");  
		 String strDate = formatter.format(date);
		msg.setSubject(subject1 +"-"+strDate);

		cal = Calendar.getInstance();
		  dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		  // System.out.println("Today's date is "+dateFormat.format(cal.getTime()));
	   String todayDate= dateFormat.format(cal.getTime());

		if (!"".equals(EMAILTO)) {
			if (EMAILTO.contains(",")) {
				String[] multipleEmailTo = EMAILTO.split(",");
				for (int j = 0; j < multipleEmailTo.length; j++) {
					if (j == 0)
						msg.addRecipient(Message.RecipientType.TO, new InternetAddress(multipleEmailTo[j]));
					else
						msg.addRecipient(Message.RecipientType.CC, new InternetAddress(multipleEmailTo[j]));
				}
			} else {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(EMAILTO));
			}
		} else if (EMAILTOCC.equals(BLANK_VARIABLE) || EMAILTOCC == null) {
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(EMAILTO));
		}

		System.out.println("Sending Automated Execution Report To -  : " + EMAILTO);
		/*
		 * if (!"".equals(EMAILTOCC)) {
		 * 
		 * msg.addRecipient(Message.RecipientType.CC, new InternetAddress(EMAILTOCC)); }
		 */

		BodyPart messageBodyPart = new MimeBodyPart();
		/*messageBodyPart.setText("Hi, \nPlease find attached current sprint Automation Test Results triggred by Jenkins.  "
				+ " \n \n \nThanks & Regards,\n Automation Team");#00b8e6*/
		testCase_Summary_Report.append("<html>"
				+ "<p style=\"color:#008ae6;\">Hi All, <br>Please find below list of <b>Automation Test Cases</b> triggred by Jenkins on <b><i>'"+SQL_Queries.todayDayDateTime()+"'</b></i> ."
				+"<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>"
		+"<h3 align=\"center\" style=\"color:#008ae6;\"> Daily Status Report for Automation ("+SQL_Queries.todayDate()+")</h3>");
		testCase_Summary_Report.append("<table style=\"width:100%\" id=\"t01\"><tr>"
				+ "<th style=\"width:10%\"><b> TC.No.</b></th>"
				+ "<th style=\"width:50%\"><b>Test Case name</b></th>"
				+ "<th style=\"width:20%\"><b>Executed For</b></th>"
				+ "<th style=\"width:15%\"><b> Execution Type</b></th>"
				+ " </tr>");
			file = new FileInputStream(new File(ConfigReader.getValue("AutomationControlExcelPath")));	
			workbook = new XSSFWorkbook(file);		
		    sheet =workbook.getSheet(GlobalUtil.getCurrentSuiteName());		
			for(int i=1;i<sheet.getLastRowNum();i++){			
				DataFormatter formatterr = new DataFormatter();
				String flag = formatterr.formatCellValue(sheet.getRow(i).getCell(1));
				String dec = formatterr.formatCellValue(sheet.getRow(i).getCell(2));
				String exeFor = formatterr.formatCellValue(sheet.getRow(i).getCell(3));
				String exeType = formatterr.formatCellValue(sheet.getRow(i).getCell(4));
				if(flag.equalsIgnoreCase("y")) {					
					sno=sno+1;
				testCase_Summary_Report.append(" <tr style=\"color:#008ae6;\"> "
						+ "<td><b>"+sno+"</b> </td>"
						+ "<td><b>"+dec+"</b> </td>"
						+ "<td><b>"+exeFor+"</b> </td>"
						+ "<td><b>"+exeType+"</b> </td>"
	 					+ " </tr>");
				}
			}
	testCase_Summary_Report.append("</table><p style=\"color:#008ae6;\"><br><br><br> Thanks & Regards,<br>Automation Team</p> <html>");
	    	
		messageBodyPart.setContent(testCase_Summary_Report.toString(), "text/html; charset=ISO-8859-1");
		//messageBodyPart.setContent("Hi", "text/html");
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		if (new File(System.getProperty(DIR_PATH) + REPORT_PATH).exists()) {
			Utility.delDirectory(new File(System.getProperty(DIR_PATH) + REPORT_PATH));
		}
		if (GlobalUtil.getCommonSettings().getHtmlReport().contains("Y")) {
			System.out.println("Copying HTML report");
			copyDirectoryData("HtmlReport", "HtmlReport");
		}
		if (GlobalUtil.getCommonSettings().getXlsReport().contains("Y")) {
			copyDirectoryData("ExcelReport", "ExcelReport");
		}
		if (GlobalUtil.getCommonSettings().getTestLogs().contains("Y")) {
			copyDirectoryData("Logs", "Logs");
		}

		//Utility.createZipFile();

		messageBodyPart = new MimeBodyPart();
		String path = System.getProperty(DIR_PATH) + "/ExecutionReports/HtmlReport/TestReport.html";
		DataSource source = new FileDataSource(path);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName("TestExecutionReport.html");
		multipart.addBodyPart(messageBodyPart);

		msg.setContent(multipart);

		Transport transport = session.getTransport("smtp");
		transport.connect(HOST, USERNAME, PASSWORD);
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
		Utility.delDirectory(new File(System.getProperty(DIR_PATH) + "/ExecutionReports/ExecutionReports"));
	}

	/**
	 * @param sourceDir
	 * @param targetDir
	 * @throws IOException
	 */
	public static void copyDirectoryData(String sourceDir, String targetDir) throws IOException {
		File srcDir = new File(System.getProperty(DIR_PATH) + "/ExecutionReports/" + sourceDir);
		File destDir = new File(System.getProperty(DIR_PATH) + "/ExecutionReports/ExecutionReports/" + targetDir);
		FileUtils.copyDirectory(srcDir, destDir);
	}
}
