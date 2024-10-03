package mainPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Layout extends JFrame{
    private Font font = new Font("Courier", Font.BOLD,14);
    private JButton startButton;
    private JButton pauseButton;
    private JButton stopButton;
    
    public Layout() {
        this.createComponents();
    }
    
    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);  
        button.setBounds(x,y,width,height);  
        button.setFont(font);
        
        return button;
    }
    
    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);  
        label.setBounds(x,y,width,height);  
        label.setFont(font);
        
        return label;
    }
    
    private void setStatus(int status, SimulationThread simThread) {
        if (status == 1) { System.out.println("Simulation Started!"); }
        simThread.setStatus(status);
        if (status == 3) {
            simThread.resetPositions();
        }
    }
    
    private void setIntersectionThreadStatus(int status, IntersectionThread t1, IntersectionThread t2, IntersectionThread t3) {
        t1.setSimulationStatus(status);
        t2.setSimulationStatus(status);
        t3.setSimulationStatus(status);
    }
   
    private void createComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Traffic Simulator");
        this.setSize(700, 550);
        this.setResizable(false);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        int addX = 40;
        
        // Creating Car Position Labels
        JLabel pos1 = createLabel("0", 300 + addX, 330, 250, 25);
        panel.add(pos1);
        
        
        JLabel pos2 = createLabel("0", 300 + addX, 380, 250, 25);
        panel.add(pos2);
        
        JLabel pos3 = createLabel("0", 300 + addX, 430, 250, 25);
        panel.add(pos3);
                
        // ===================
        
        // Creating Intersection Labels
        JLabel labelA = createLabel("Intersection A(2000): ", 220, 160, 250, 25);
        JLabel statusA = createLabel("Green", 400, 160, 250, 25);
        statusA.setForeground(Color.GREEN);
        panel.add(statusA);
        panel.add(labelA);
        
        JLabel labelB = createLabel("Intersection B(4000): ", 220, 200, 250, 25);
        JLabel statusB = createLabel("Green", 400, 200, 250, 25);
        statusB.setForeground(Color.GREEN);
        panel.add(statusB);
        panel.add(labelB);
        
        JLabel labelC = createLabel("Intersection C(5000): ", 220, 240, 250, 25);
        JLabel statusC = createLabel("Green", 400, 240, 250, 25);
        statusC.setForeground(Color.GREEN);
        panel.add(statusC);
        panel.add(labelC);
        
        // Creating Intersection Threads
        
        IntersectionThread it1 = new IntersectionThread(statusA, 2000);
        IntersectionThread it2 = new IntersectionThread(statusB, 3000);
        IntersectionThread it3 = new IntersectionThread(statusB, 1000);
        it1.start();
        it2.start();
        it3.start();
        
        // ===================
        
        // Creating Car threads
        
        Car c1 = new Car(pos1, 100, it1, it2, it3);
        Car c2 = new Car(pos2, 180, it1, it2, it3);
        Car c3 = new Car(pos3, 150, it1, it2, it3);
        
        // ===================
        
        // Creating Simulation Threads
        
        SimulationThread simThread = new SimulationThread(c1, c2 ,c3);
        simThread.start();
        
        // ==================
        // Creating Labels
        
        JLabel startLabel = createLabel("To run simulation click Start", 240, 20, 250, 25);
        panel.add(startLabel);
        
        JLabel timeLabel = createLabel("", 290, 50, 250, 25);
        panel.add(timeLabel);
        
        // ==================
        
        // Creating Time update thread per second
        TimeIncreaseThread timeThread = new TimeIncreaseThread(timeLabel);
        timeThread.start();
        
        // ==================
        // Creating Buttons
        startButton = createButton("Start", 215, 100, 95, 35);  
        panel.add(startButton);
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setStatus(1, simThread);
                setIntersectionThreadStatus(1, it1, it2, it3);
            }
        });
        
        pauseButton = createButton("Pause", 315, 100, 95, 35);  
        panel.add(pauseButton);
        pauseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setStatus(2, simThread);
                setIntersectionThreadStatus(2, it1, it2, it3);
            }
        });
        
        stopButton =createButton("Stop", 415, 100, 95, 35);  
        panel.add(stopButton);
        stopButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setStatus(3, simThread);
                setIntersectionThreadStatus(3, it1, it2, it3);
            }
        });
             
        // ===================        
        
        // Creating Car Position Labels
        JLabel car1Label = createLabel("Car 1", 180 + addX, 330, 250, 25);
        panel.add(car1Label);
        
        JLabel car2Label = createLabel("Car 2", 180 + addX, 380, 250, 25);
        panel.add(car2Label);
        
        JLabel car3Label = createLabel("Car 3", 181 + addX, 430, 250, 25);
        panel.add(car3Label);        
        
        // Creating Position Header Labels
        JLabel positionHeaderLabel = createLabel("Position", 280 + addX, 295, 250, 25);
        panel.add(positionHeaderLabel);
        
        JLabel speedHeaderLabel = createLabel("Speed(km/h)", 390 + addX, 295, 250, 25);
        panel.add(speedHeaderLabel);
        
        // Creating Car Speed Labels
        JLabel speed1 = createLabel("" + 100 + " km/h", 400 + addX, 330, 250, 25);
        panel.add(speed1);
        
        JLabel speed2 = createLabel("" + 180 + " km/h", 400 + addX, 380, 250, 25);
        panel.add(speed2);
        
        JLabel speed3 = createLabel("" + 150 + " km/h", 400 + addX, 430, 250, 25);
        panel.add(speed3);
        
        // ==============================
        
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
