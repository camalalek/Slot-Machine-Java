package slotmachine;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Reel {
 
    
    private ArrayList<Symbols> reel;

    public Reel(){
        Symbols bell = null;
        Symbols cherry = null;
        Symbols lemon = null;
        Symbols plum = null;
        Symbols redseven = null;
        Symbols watermelon = null;
        
        // instanciate the array list
        reel = new ArrayList<Symbols>(); //creates arraylist called reel using Symbols objects
        
        try{
        bell = new Symbols("bell", new ImageIcon(getClass().getResource("res/bell.png/")), 6);
        }catch(NullPointerException e){
            System.out.println("Bell image is not found");
        }
        try{
        cherry = new Symbols("cherry", new ImageIcon(getClass().getResource("res/cherry.png/")), 2);
        }catch(NullPointerException e){
            System.out.println("Cherry image is not found");
        }
        try{
        lemon = new Symbols("lemon", new ImageIcon(getClass().getResource("res/lemon.png/")), 3);
        }catch(NullPointerException e){
            System.out.println("Lemon image is not found");
        }
        try{
        plum = new Symbols("plum", new ImageIcon(getClass().getResource("res/plum.png/")), 4);
        }catch(NullPointerException e){
            System.out.println("Plum image is not found");
        }
        try{
        redseven = new Symbols("redseven", new ImageIcon(getClass().getResource("res/redseven.png/")), 7);
        }catch(NullPointerException e){
            System.out.println("Redseven image is not found");
        }
        try{
        watermelon = new Symbols("watermelon", new ImageIcon(getClass().getResource("res/watermelon.png/")), 7);
        }catch(NullPointerException e){
            System.out.println("Watermelon image is not found");
        }
        
        // add the dice face to the array
        reel.add(bell);
        reel.add(cherry);
        reel.add(lemon);
        reel.add(plum);
        reel.add(redseven);
        reel.add(watermelon);
    }
    
    public int arraySize(){
        return reel.size();
    }
    
    public Symbols randomImg(){
        // retrieve a random number
        Random rand = new Random();
        int randomNum; 
        randomNum = rand.nextInt(reel.size());
        
        return reel.get(randomNum);
    }
    
    
}