package enh.db.pages;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

public class JodaDateDifferentExample {
	 public static void main(String[] args) throws ParseException {

			String dateStart = "2020-01-08 04:55:24.0";
			String dateStop = "2020-01-08 04:57:17.0";

			SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");


		
			Date	d1 = format.parse(dateStart);
			Date	d2 = format.parse(dateStop);

				DateTime dt1 = new DateTime(d1);
				DateTime dt2 = new DateTime(d2);
				
				 long difference =  (d1.getTime()-d2.getTime());
				 System.out.println(difference + " milli seconds.");	
				 long difference1 =  (d2.getTime()-d1.getTime());
				 System.out.println(difference1 + " milli seconds.");	
				
		 		Seconds.secondsBetween(dt1, dt2).getSeconds();
				
				System.out.println(Seconds.secondsBetween(dt1, dt2).getSeconds() + " daysBetween, ");
				System.out.println(Days.daysBetween(dt1, dt2).getDays() + " days, ");
				System.out.println(Hours.hoursBetween(dt1, dt2).getHours() /24 + " hours, ");
				System.out.println(Minutes.minutesBetween(dt1, dt2).getMinutes() /60 + " minutes, ");
				System.out.println(Seconds.secondsBetween(dt1, dt2).getSeconds()/60  + " seconds.");				
				

		  }

}
