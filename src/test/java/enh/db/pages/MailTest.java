package enh.db.pages;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.mysql.jdbc.PreparedStatement;

public class MailTest {
	
     
    String sql;
    String sql1;
    String sql2;
    String society_name=null;
    String resi_name=null;
    String flat_no=null;
    String tower_no=null;
    String contact=null;
    String email_id=null;
    String cat=null;
    String subcat=null;
    String quan=null;
    String unit=null;
    String rate=null;
    String total=null;
    ArrayList ar=new ArrayList();
   // String user=null;
    //String cate=null;
 
        public static String  MailToVendor() throws FileNotFoundException
        {
       // HttpServletRequest request = null;
      // HttpSession ss =request.getSession(true);
       // String user=(String)ss.getAttribute("user");
       // String cate=(String)ss.getAttribute("cate");
         
        //System.out.println("Username in the vendor mailing servlet..... " + user);
        //System.out.println("Category in the vendor mailing servlet..... " + cate);
        PreparedStatement pst = null;
        String status = null;
        Exception ex = null;
        String url=null;
        String username = "automationteam.enhops@gmail.com";
        String from = "automationteam.enhops@gmail.com";
        String host = "smtp.gmail.com";
 
        String subject = "ORDER FOR ONLINE BOOKING ORDERS";
        String pass = "Enhops@123";
        String port = "465";
        File attachment = null;
         
       // System.out.println("Username in the vendor mailing servlet..... " + user);
        try {
            //String user=request.("username");
        // con=jdbc.getConnection();
          
        /*stmt2=con.createStatement();
         sql2="select username,category from history_grocery";
         rs2=stmt2.executeQuery(sql2);
         System.out.println("Grocery History query is " + sql2);
         while(rs2.next())
             {
                user=rs2.getString(1);
                cate=rs2.getString(2);
             }*/
       //  System.out.println("*** user**** "+user+"\n"+"&&& category&&&&&&& " + cate);
        // stmt=con.createStatement();
       //  sql="select society_name,name_of_owner,appartment_no,tower_no,mobileno_ofmainperson,emailid_ofmainperson from appartment where emailid_ofmainperson='"+user+"'";
        // rs=stmt.executeQuery(sql);
        // System.out.println("Appartment query is " + sql);
         /*//while(rs.next())
             {
                society_name=rs.getString(1);
                resi_name=rs.getString(2);
                flat_no=rs.getString(3);
                tower_no=rs.getString(4);
                contact=rs.getString(5);
                email_id=rs.getString(6);
             }*/
        /* ArrayList arr=null;
         if(ar!=null)
         {
             Iterator it=ar.iterator();
             while(it.hasNext())
             {
                 arr=(ArrayList)it.next();
                 System.out.println("ARRAYLIST IN MAILING...... "+arr);
                  
             }
         }*/
              
              
          
              
           // url="http://localhost:8084/FacilitySupervisor/show_uploadedimg_details.jsp";
            // create some properties and get the default Session
            Properties props = new Properties();
            props.put("mail.smtp.user", username);
            props.put("mail.smtp.host", host);
            if (!"".equals(port)) 
            {
                props.put("mail.smtp.port", port);
            }
             
            if (!"".equals("true")) 
            {
                props.put("mail.smtp.starttls.enable", "true");
            }
             
             props.put("mail.smtp.auth", "true");
              
            if (true) 
            {
                props.put("mail.smtp.debug", "true");
            }
            else
            {
                props.put("mail.smtp.debug", "false");
            }
             
            if (!"".equals(port)) 
            {
                props.put("mail.smtp.socketFactory.port", port);
            }
             
            if (!"".equals("javax.net.ssl.SSLSocketFactory")) 
            {
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            }
             
            if (!"".equals("false")) {
                props.put("mail.smtp.socketFactory.fallback", "false");
            }
            // Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, null);
            session.setDebug(true);
            MimeMessage msg = new MimeMessage(session); // create a message
            msg.setFrom(new InternetAddress(from));
            msg.setSubject(subject);
            MimeBodyPart mbp1 = new MimeBodyPart();// create and fill the first message part
          //  mbp1.setText(msgText1);
            MimeBodyPart mbp2 = new MimeBodyPart();// create the second message part
            Multipart mp = new MimeMultipart(); // create the Multipart and add its parts to it
            mp.addBodyPart(mbp1);
          
            	 String filename1 =  "D:\\ZestIOT\\ExecutionReports\\HtmlReport\\TestReport.html";
                FileDataSource fds = new FileDataSource(filename1);
                mbp2.setDataHandler(new DataHandler(fds));
                mbp2.setFileName(fds.getName());
                 mbp2.setFileName(filename1);
                mp.addBodyPart(mbp2);
            
            msg.setContent(mp);// add the Multipart to the message
            msg.setSentDate(new Date()); // set the Date: header
        // stmt1=con.createStatement();
         //sql1="select category,subcategory,quantity,units,price_tqty,total_amount from history_grocery where username='"+user+"'and category='"+cate+"'";
        // rs1=stmt1.executeQuery(sql1);
        // System.out.println("History grocery qry is=====  " + sql1);
         //int index=0;
        /* while(rs1.next())
         {
                cat=rs1.getString(1);
                subcat=rs1.getString(2);
                quan=rs1.getString(3);
                unit=rs1.getString(4);
                rate=rs1.getString(5);
                total=rs1.getString(6);
               // ar.add(1,subcat);
               // ar.add(2,quan);
               // ar.add(3,unit);
               // ar.add(4,rate);
               // ar.add(5,total);
               // index++;
                 
         }*/
           String html="<html><head>"
                    + "<title>"+msg.getSubject()+"</title>"
                    + "</head>"+"<LINK REL='stylesheet' HREF='stylesheet/fac_css.css' TYPE='text/css'>"
                    + "<body>"
                    +"<table width='900' cellpadding='0' cellspacing='0' border='0'>"
                    +"<tr><td class ='text12' width='100%'><br>I ordered all the following items.Please deliver all the same items on the given below address:</td></tr><tr>"
                    +"<td height='5'></td></tr>"
                    +"<tr><td></td></tr>"
                    +"<tr><td height='5'></td></tr>"
                    +"<tr><td><table border='1' width='800' cellpadding='2' cellspacing='1' bgColor='#B6AFA9' style='border-collapse: collapse' bordercolor='#EBDA2A' align='left'>"
                    +"<tr bgColor=#CD919E class='centerheading' align='left'>"
                            +"<td width='30' style='color: #FFFFFF;'><b>Category</b></td>"
                            +"<td width='35' style='color: #FFFFFF;'><b>Subcategory</b></td>"
                            +"<td width='30' style='color: #FFFFFF;'><b>Quantity</b></td>"
                            +"<td width='30' style='color: #FFFFFF;'><b>Units</b></td>"
                            +"<td width='30' style='color: #FFFFFF;'><b>Rate</b></td>"
                            +"<td width='30' style='color: #FFFFFF;'><b>Total</b></td>"
                   + "</tr>"
                   +"<tr>"
                        +"<td width='30' style='color: #EEE9E9;'>"+""+"</b></td>"
                            +"<td width='35' style='color: #EEE9E9;'>"+""+"</td>"
                            +"<td width='30' style='color: #EEE9E9;'>"+""+"</td>"
                            +"<td width='30' style='color: #EEE9E9;'>"+""+"</td>"
                            +"<td width='30' style='color: #EEE9E9;'>"+""+"</td>"
                            +"<td width='30' style='color: #EEE9E9;'>"+""+"</td>"
                   +"</tr>"
                    +"</table>"
                +"</td>"
        +"</tr>"
        +"<tr>"
             +"<td height='6'></td>"
        +"</tr>"
        +"<tr>"
            +"<td class ='text12' width='100%'><a href="+url+" style='font-weight: bolder;'>Give Order Online Here---->>>>></a>."+"</td>"
        +"</tr>"
        +"<tr>"
             +"<td height='15'></td>"
        +"</tr>"
       
        +"</table>"
        +"</body></html>";
        //HTMLDataSource is an inner class
      //  msg.setDataHandler(new DataHandler(new HTMLDataSource(html)));
          
            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, pass);
            try {
                 String tos = "stiyyagura@enhops.com";
              //  InternetAddress[] address ;//= {new InternetAddress(to)};
              // for (int i = 0; i < tos.length; i++) {
                   // if (tos[i].trim().equalsIgnoreCase("")) {
                   // } else {//
                       InternetAddress[] address= {new InternetAddress(tos)};
                        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(tos));
                        msg.setRecipients(Message.RecipientType.TO, address);
               msg.setRecipients(Message.RecipientType.CC, "stiyyagura@enhops.com");
              
               msg.saveChanges(); // this is must
               transport.sendMessage(msg, msg.getAllRecipients());
               status="OK";
           // }
             //  }
            } finally {
                transport.close();
            }
        } catch (MessagingException mex) {
            mex.printStackTrace();
            if ((ex = mex.getNextException()) != null) {
                ex.printStackTrace();
            }
            status=null;
        }finally{
            System.out.println("The status in mail sending file is=="+status);
            return status;
        }
    }
 
 
        static class HTMLDataSource implements DataSource {
        private String html;
 
        public HTMLDataSource(String htmlString) {
            html = htmlString;
        }
 
        // Return html string in an InputStream.
        // A new stream must be returned each time.
        public InputStream getInputStream() throws IOException {
            if (html == null) throw new IOException("Null HTML");
            return new ByteArrayInputStream(html.getBytes());
        }
 
        public OutputStream getOutputStream() throws IOException {
            throw new IOException("This DataHandler cannot write HTML");
        }
 
        public String getContentType() {
            return "text/html";
        }
 
        public String getName() {
            return "JAF text/html dataSource to send e-mail only";
        }
    }
 
 public static void main(String args[]) throws FileNotFoundException{
	 MailToVendor();
 }

}
