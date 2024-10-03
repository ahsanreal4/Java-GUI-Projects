package mainPackage;

import java.awt.Color;
import javax.swing.JLabel;

public class IntersectionThread extends Thread {
    private Thread t;
    private int status = 1;
    private JLabel label;
    private int stoppingTime;
    private int simulationStatus = 3;
    
    public IntersectionThread(JLabel label, int stoppingTime) {
        this.label = label;
        t = new Thread();
        this.stoppingTime = stoppingTime;
    }
    
    public void setGreen() {
        this.label.setForeground(Color.green);
        this.label.setText(("Green"));
        this.status = 1;
    }
    
    public void setRed() {
        this.label.setForeground(Color.red);
        this.label.setText(("Red"));
        this.status = 3;
    }
    
    public void setYellow() {
        this.label.setForeground(Color.orange);
        this.label.setText(("Orange"));
        this.status = 2;
    }
    
    public void setSimulationStatus(int s) {
        this.simulationStatus = s;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public void run() {
        while(true) {
            try {
                if(this.simulationStatus == 1){
                    if (status == 1){
                        setGreen();
                        Thread.sleep(stoppingTime);
                        setYellow();
                        Thread.sleep(500);
                        setRed();
                    }
                    else if(status == 3){
                        Thread.sleep(3000);
                        setYellow();
                        Thread.sleep(500);
                        setGreen();
                    }
                }
                else if(this.simulationStatus == 3){
                    setGreen();
                }

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
