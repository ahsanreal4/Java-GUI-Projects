package mainPackage;

public class SimulationThread extends Thread {
        private final Thread t;
        private int status = 3;
        private Car c1;
        private Car c2;
        private Car c3;
	   
        public SimulationThread(Car c1, Car c2, Car c3) {
            t = new Thread (this);
            this.c1 = c1;
            this.c2 = c2;
            this.c3 = c3;
        }
        
        public void updatePositions() {
            int a = c1.updatePosition();
            int b = c2.updatePosition();
            int c = c3.updatePosition();
            
            if ( a == 0 && b == 0 && c == 0) { 
                c1.setIntersectionStatus(3); 
                this.status = 3;
                System.out.println("Simulation Over!");
            }
        }
        
        public void resetPositions() {
            c1.resetPositions();
            c2.resetPositions();
            c3.resetPositions();
        }
	   
        public void run() {
            while(true) {
                try {
                    if(status == 1) {
                        this.updatePositions();
                    }
                    else if (status == 3){
                        this.resetPositions();
                    }
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        public void setStatus(int status) {
            this.status = status;
        }
}

