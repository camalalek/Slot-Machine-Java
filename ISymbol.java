package slotmachine;

import javax.swing.ImageIcon;

public interface ISymbol {
    //Defining methods for an interface
    public void setImgDisplayed(ImageIcon imgDisplayed);
    public ImageIcon getImgDisplayed();
    public int getMultiplier();
    public String getName();
}
