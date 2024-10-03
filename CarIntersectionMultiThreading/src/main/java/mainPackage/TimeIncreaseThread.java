package mainPackage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JLabel;

public class TimeIncreaseThread extends Thread {
	   private final Thread t;
           private final JLabel label;
	   
        public TimeIncreaseThread(JLabel timeLabel) {
            t = new Thread (this);
            label = timeLabel;
        }
	   
        public void run() {
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
            String time;
            while(true) {
                time = timeFormat.format(Calendar.getInstance().getTime());
                this.label.setText("Time: " + time);
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) { 
                    e.printStackTrace();
                }
                }
            }
}

