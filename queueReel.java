package slotmachine;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author user
 */
public class queueReel extends Thread{ 
    
    //initialising 
    private Reel reel; 
    private JLabel area; 
//    boolean threadBoolean = true;
    //label

	public queueReel(Reel reel, JLabel area){
		this.reel = reel; 
                this.area = area;
        }
        
            public void run(){
		while(true){
                        Symbols i = reel.randomImg(); // assigning a random symbol to i
                        area.setIcon(i.getImgDisplayed()); //gets image of the symbol i and sets it to  the JLabel area
                    try {
                        Thread.sleep(100); //sleep speed 
                    } catch (InterruptedException ex) {
                        Logger.getLogger(queueReel.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
            }
    
    
    
    
    
}
