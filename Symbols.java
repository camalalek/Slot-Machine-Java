package slotmachine;

import javax.swing.ImageIcon;

public class Symbols implements ISymbol{
    private String name;
    private ImageIcon imgDisplayed;
    private int multiplier;
    
    // first constructor
    public Symbols(){
        name = "";
        imgDisplayed = null;
        multiplier = 0;
    }
    // second constructor
    public Symbols(String name, ImageIcon imgDisplayed, int multiplier){
        this.name = name;
        this.imgDisplayed = imgDisplayed;
        this.multiplier = multiplier;
    }
    
    // setter and getter method
    public void setImgDisplayed(ImageIcon imgDisplayed){
        this.imgDisplayed = imgDisplayed;
    }
    
    public String getName(){
        return name;
    }
    
    public ImageIcon getImgDisplayed(){
        return imgDisplayed;
    }
    
    public int getMultiplier(){
        return multiplier;
    }
    
    public int compareTo(Symbols b){
        int returnValue = 0;
        
        if(this.getName().equals(b.name)){
            returnValue = 1;
        }
        return returnValue;
    }
    
}