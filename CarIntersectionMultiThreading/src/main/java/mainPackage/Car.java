package mainPackage;

import java.awt.Color;
import javax.swing.JLabel;

public class Car {
    private JLabel label;
    private int x;
    private int speed;
    private IntersectionThread t1;
    private IntersectionThread t2;
    private IntersectionThread t3;
    private static int carId = 1;
    private int id;
    
    public Car(JLabel label, int speed, IntersectionThread t1, IntersectionThread t2, IntersectionThread t3) {
        this.label = label;
        this.x = 0;
        this.speed = speed;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        id = carId;
        carId += 1;
    } 
    
    public void setRed() {
        label.setForeground(Color.red);
    }
    
    public void setBlack() {
       label.setForeground(Color.black); 
    }
    
    public int updatePosition() {
        if(this.x >= 6000) { 
            setBlack();
            System.out.println("Car " + id + " reached the destination");
            return 0; 
        }
        int xAfterSpeed = x + speed;
        if(x < 2000 && xAfterSpeed > 2000) {
            if(t1.getStatus() == 3) { 
                System.out.println("Car " + id + " stopped At Red light 1");
                setRed();
                return 1;
            }
        }
        else if (x < 4000 && xAfterSpeed > 4000) {
            if (t2.getStatus() == 3) { 
                System.out.println("Car " + id + " stopped At Red light 2");
                setRed();
                return 1;
            }
        }
        else if(x < 5000 && xAfterSpeed > 5000) {
            if (t3.getStatus() == 3) { 
                System.out.println("Car " + id + " stopped At Red light 3");
                setRed();
                return 1; 
            }
        }
        setBlack();
        x = xAfterSpeed;
        label.setText("" + x);
        return 1;
    }
    
    public void setIntersectionStatus(int status){
        t1.setSimulationStatus(status);
        t2.setSimulationStatus(status);
        t3.setSimulationStatus(status);
    }
    
    public void resetPositions() {
        x = 0;
        label.setText("" + x);
    }
    
}
