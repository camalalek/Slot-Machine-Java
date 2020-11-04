package slotmachine;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SlotMachine extends JFrame {
    private JButton btnOne;
    private JButton btnTwo;
    private JButton btnThree;
    private JButton btnFour;
    private JButton btnFive;
    private JButton btnSix;
    
    private JLabel imageLabel1;
    private JLabel imageLabel2; 
    private JLabel imageLabel3;
    
    private ImageIcon imageIcon1;
    private ImageIcon imageIcon2;
    private ImageIcon imageIcon3;
    
    queueReel thread1;
    queueReel thread2;
    queueReel thread3;
    
    Reel reel;
    Reel reel1;
    Reel reel2;
    Reel reel3;
        
    private JLabel header;
    private JLabel header2;
    private TextArea console;
    private JTextField coinAmount;
    private JLabel currentWage;
    private String currentWageStart = "0";
    private JLabel currentWage2;
    private JLabel gameScore;
    private String gameScoreStart = "10";
    private JLabel gameScore2;
    private JLabel coinSign;
    private JButton saveData;
   
    
    private int gameWins = 0;
    private ArrayList avgList = new ArrayList();;
    private int gameLoss = 0;
    private boolean isSpinning = false;
    
    
    public SlotMachine(){
        imageLabel1 = new JLabel("",JLabel.CENTER); 
        imageLabel2 = new JLabel("",JLabel.CENTER); 
        imageLabel3 = new JLabel("",JLabel.CENTER);
        
        reel1 = new Reel();
        reel2 = new Reel();
        reel3 = new Reel();

        Reel reel = new Reel();
        Symbols reel1img = reel.randomImg();
        Symbols reel2img = reel.randomImg();
        Symbols reel3img = reel.randomImg();
        
        
        imageLabel1.setIcon(reel1img.getImgDisplayed());
        imageLabel2.setIcon(reel2img.getImgDisplayed());
        imageLabel3.setIcon(reel3img.getImgDisplayed());

        imageLabel1.setPreferredSize(new Dimension(250,250));
        imageLabel2.setPreferredSize(new Dimension(250,250));
        imageLabel3.setPreferredSize(new Dimension(250,250));
        
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,2));
        p1.setPreferredSize(new Dimension(50, 50));
        btnOne = new JButton("Add Credits");
        btnOne.addActionListener(new ButtonListener());
        btnOne.setFont(new Font("Stencil", Font.BOLD, 20));
        p1.add(btnOne);

        btnTwo = new JButton("Bet One");
        btnTwo.addActionListener(new ButtonListener());
        btnTwo.setFont(new Font("Stencil", Font.BOLD, 20));
        p1.add(btnTwo);
        
        btnThree = new JButton("Bet Max");
        btnThree.addActionListener(new ButtonListener());
        btnThree.setFont(new Font("Stencil", Font.BOLD, 30));
        p1.add(btnThree);
        
        btnFour = new JButton("SPIN");
        btnFour.addActionListener(new ButtonListener());
        btnFour.setFont(new Font("Stencil", Font.BOLD, 30));
        p1.add(btnFour);
        
        btnFive = new JButton("Statictics");
        btnFive.addActionListener(new ButtonListener());
        btnFive.setFont(new Font("Stencil", Font.BOLD, 20));
        p1.add(btnFive);
        
        btnSix = new JButton("Reset");
        btnSix.addActionListener(new ButtonListener());
        btnSix.setFont(new Font("Stencil", Font.BOLD, 20));
        p1.add(btnSix);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(1,2));
        
        p2.add(imageLabel1); 
        
        p2.add(imageLabel2);
        
        p2.add(imageLabel3);
        
        console = new TextArea(" Welcome to SLOT MACHINE.\n Press \"SPIN\" and play with 10 free credits\n or\n ADD some more credits");
        console.setForeground(Color.white);
        console.setFont(new Font("Arial", Font.PLAIN, 15));
        console.setEnabled(false);
        p2.add(console);

        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(1,2));
        p3.setPreferredSize(new Dimension(50, 50));
        p3.setBackground(Color.white);
        
        header = new JLabel("Slot Machine ", JLabel.LEFT);
        header.setFont(new Font("Stencil", Font.PLAIN, 20));
        p3.add(header);
        
        coinAmount = new JTextField("", JTextField.LEFT);
        coinAmount.setFont(new Font("Stencil", Font.PLAIN, 25));
        p3.add(coinAmount);
        
        coinSign = new JLabel("Current Wage:", JLabel.RIGHT);
        coinSign.setFont(new Font("Arial", Font.PLAIN, 25));
        p3.add(coinSign);
        
        currentWage = new JLabel(currentWageStart, JLabel.RIGHT);
        currentWage.setFont(new Font("Stencil", Font.PLAIN, 35));
        p3.add(currentWage);
        
        coinSign = new JLabel("Funds Available:", JLabel.RIGHT);
        coinSign.setFont(new Font("Arial", Font.PLAIN, 25));
        p3.add(coinSign);
        
        gameScore = new JLabel(gameScoreStart, JLabel.RIGHT);
        gameScore.setFont(new Font("Stencil", Font.PLAIN, 35));
        p3.add(gameScore);
        
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.setBackground(Color.GRAY);
        
        cp.add(p3, BorderLayout.NORTH);
        
        cp.add(p2, BorderLayout.CENTER);

        cp.add(p1, BorderLayout.SOUTH);
    }
    
    public int gameNo(int gameWins, int gameLoss){
        return gameWins+gameLoss;
    }
    
    public Double gameAvg(ArrayList avgList, int gameWins){
        if(gameWins != 0 && gameLoss != 0){
            int semiCalc = 0;
            Double completedCalc = 0.0;
            for(int i = 0; i < avgList.size(); i++){
                int tmp = Integer.parseInt(avgList.get(i).toString());
                semiCalc += tmp;
            }
            completedCalc += (semiCalc/gameNo(gameWins, gameLoss));
            return completedCalc;
        }else{
            return 0.0;
        }
    }
    
    public void addCoins(){
        console.append("\n\n[ * ] Add Credits Activated\n\n");
        int creditInt = Integer.parseInt(gameScore.getText());
        int coinsInt = Integer.parseInt(coinAmount.getText());
        int coinsCalc = 0;
        if(coinsInt > 0){
            coinsCalc = coinsInt+creditInt;
            gameScore.setText(""+coinsCalc);
            console.append("\n "+coinAmount.getText()+" Credits added sucessfully...\n");
            coinAmount.setText("");
        }else{
            console.append("\n NO Negative numbers!\n");
        }
    }
    
    public void betMax(){
        console.append("\n\n[ * ] Bet Max Activated\n\n");
        int coinsInt = 3;
        int balanceInt = Integer.parseInt(gameScore.getText());
        int wageCalc = 0;
        wageCalc = balanceInt-coinsInt;
        if(wageCalc >= 0){
            gameScore.setText(""+wageCalc);
            console.append("\n 3 credits added on bet\n");
            int coinsInt1 = 3;
            int currWageInt = Integer.parseInt(currentWage.getText());
            int wageCalc2 = 0;
            wageCalc2 = coinsInt1+currWageInt;
            currentWage.setText(""+wageCalc2);
        }else{
            console.append("\n Not enough credits!\n");
        }
    }
    
    public void betOne(){
        console.append("\n\n[ * ] Bet One Activated\n\n");
        int coinsInt = 1;
        int balanceInt = Integer.parseInt(gameScore.getText());
        int wageCalc = 0;
        wageCalc = balanceInt-coinsInt;
        if(wageCalc >= 0){
            gameScore.setText(""+wageCalc);
            console.append("\n 1 credits added on bet\n");
            int coinsInt1 = 1;
            int currWageInt = Integer.parseInt(currentWage.getText());
            int wageCalc2 = 0;
            wageCalc2 = coinsInt1+currWageInt;
            currentWage.setText(""+wageCalc2);
        }else{
            console.append("\n Not enough credits!\n");
        }
    }
    
    public void spinLogic(){
        reel = new Reel();
        String imgName1;
        String imgName2;
        String imgName3;

        Symbols reel1img = reel.randomImg();
        Symbols reel2img = reel.randomImg();
        Symbols reel3img = reel.randomImg();

        imageLabel1.setIcon(reel1img.getImgDisplayed());
        imgName1 = reel1img.getName();
        imageLabel2.setIcon(reel2img.getImgDisplayed());
        imgName2 = reel2img.getName();
        imageLabel3.setIcon(reel3img.getImgDisplayed());
        imgName3 = reel3img.getName();

        if(imgName1 == imgName2 || imgName1 == imgName3 || imgName2 == imgName3){
            
            int Wager = Integer.parseInt(currentWage.getText());
            int Balance = Integer.parseInt(gameScore.getText());
            
            if(reel1img.getName().equals(reel2img.getName())){
                int totalCredits = ((Wager * reel1img.getMultiplier()) - Wager);
                avgList.add(totalCredits);
                int totalBalance = (totalCredits+Balance);
                gameScore.setText(""+totalBalance);
                console.append("\n You Won... \n "+totalCredits+" Credits added to your funds. \n");
            }else if(reel2img.getName().equals(reel3img.getName())){
                System.out.println(reel2img.getName()+" "+reel2img.getMultiplier());
                int totalCredits = ((Wager * reel1img.getMultiplier()) - Wager);
                avgList.add(totalCredits);
                int totalBalance = (totalCredits+Balance);
                gameScore.setText(""+totalBalance);
                console.append("\n You Won... \n "+totalCredits+" Credits added to your funds. \n");
            }else if(reel1img.getName().equals(reel3img.getName())){
                int totalCredits = ((Wager * reel1img.getMultiplier()) - Wager);
                avgList.add(totalCredits);
                int totalBalance = (totalCredits+Balance);
                gameScore.setText(""+totalBalance);
                console.append("\n You Won... \n "+totalCredits+" Credits added to your funds. \n");
            }
            gameWins++;
        }else{
            currentWage.setText("0");
            console.append("\n Sorry, Better luck next time... \n");
            gameLoss++;
        }
        isSpinning = false;
    }
    
    public void spin(){
        console.append("\n\n[ * ] Spin Activated\n\n");
        
        int currWage = Integer.parseInt(currentWage.getText());
        if(currWage > 0){
            isSpinning = true;
            
            queueReel thread1 = new queueReel(reel1,imageLabel1);
            queueReel thread2 = new queueReel(reel2,imageLabel2);
            queueReel thread3 = new queueReel(reel3,imageLabel3);

            thread1.start();
            thread2.start();
            thread3.start();
            
            imageLabel1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    //while(!isSpinning){    
                        thread1.stop();
                        thread2.stop();
                        thread3.stop();
                    //}
                    if(isSpinning){
                        spinLogic();
                    }
                }
            });
            imageLabel2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e2) {
                    //while(!isSpinning){    
                        thread1.stop();
                        thread2.stop();
                        thread3.stop();
                    //}
                    if(isSpinning){
                        spinLogic();
                    }
                }
            });
            imageLabel3.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e3) {
                    //while(!isSpinning){    
                        thread1.stop();
                        thread2.stop();
                        thread3.stop();
                    //}
                    if(isSpinning){
                        spinLogic();
                    }
                }
            });
        }else{
            console.append("\n Whats your wage!\n");
        }
    }
    
    private void saveFile(String path) throws IOException {
       try {
            PrintWriter out = new PrintWriter(new FileWriter(path));
            out.println("Wins: "+gameWins);
            out.println("Loss: "+gameLoss);
            out.println("Spins Played: "+gameNo(gameWins,gameLoss));
            out.println("Average: "+gameAvg(avgList, gameWins));
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("[*] File Saved");
    }
    
    public void statictics(){
        console.append("\n\n[ * ] Statictics Activated\n\n");
        console.append("\n Wins: "+gameWins+" Losses: "+gameLoss+" Slots Played: "+gameNo(gameWins,gameLoss)+"\n");
        JFrame j2 = new JFrame();
        
        JLabel winStats;
        JLabel lossStats;
        JLabel avgStats;
        JLabel maxStats;
        JPanel p2 = new JPanel();
        
        p2.setLayout(new BorderLayout(1,2));
        saveData = new JButton("Save Data");
        saveData.addActionListener(new ButtonListener());
        saveData.setFont(new Font("Stencil", Font.BOLD, 20));
        saveData.setPreferredSize(new Dimension(50, 50));
        
        winStats = new JLabel("Wins: "+gameWins, JLabel.CENTER);
        winStats.setFont(new Font("Arial", Font.PLAIN, 30));
        
        lossStats = new JLabel("Losses: "+gameLoss, JLabel.CENTER);
        lossStats.setFont(new Font("Arial", Font.PLAIN, 30));
        
        avgStats = new JLabel("Average Percentage: "+gameAvg(avgList, gameWins)+"%", JLabel.CENTER);
        avgStats.setFont(new Font("Arial", Font.PLAIN, 30));
        
        maxStats = new JLabel("Slots Played: "+gameNo(gameWins,gameLoss), JLabel.CENTER);
        maxStats.setFont(new Font("Arial", Font.PLAIN, 30));
        
        p2.add(winStats, BorderLayout.WEST);
        p2.add(lossStats, BorderLayout.CENTER);
        p2.add(avgStats, BorderLayout.BEFORE_FIRST_LINE);
        p2.add(maxStats, BorderLayout.EAST);
        
        p2.add(saveData, BorderLayout.SOUTH);
                
        j2.add(p2);
        
        j2.setSize(700, 600);
        j2.setTitle("Slot Machine Stats");
        j2.setVisible(true);                  
        j2.setLocationRelativeTo(null);
    }
    
    public void reset(){
        console.append("\n\n[ * ] Reset Activated\n\n");
        int currentWager = Integer.parseInt(currentWage.getText());
        int balanceInt = Integer.parseInt(gameScore.getText());
        int wageCalc = 0;
        wageCalc = balanceInt+currentWager;
        gameScore.setText(""+wageCalc);
        currentWage.setText("0");
        
        console.append("\n Game has been Restarted... \n");
    }
    
    private class ButtonListener implements ActionListener{
        public void actionPerformed(java.awt.event.ActionEvent ae) {
            if(ae.getSource() == btnOne){
                if(!isSpinning){
                    addCoins();
                }
            }
            if(ae.getSource() == btnTwo){
                if(!isSpinning){
                    betOne();
                }
            }
            if(ae.getSource() == btnThree){
                if(!isSpinning){
                    betMax();
                }
            }
            if(ae.getSource() == btnFour){
                if(!isSpinning){
                    spin(); 
                }
            }
            if(ae.getSource() == btnFive){
                if(!isSpinning){
                    statictics();
                }
            }
            if(ae.getSource() == btnSix){
                if(!isSpinning){
                    reset();
                }
            }
            if(ae.getSource() == saveData){
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
                String path = timeStamp+".txt/";
                try {
                    saveFile(path);
                } catch (IOException ex) {
                    Logger.getLogger(SlotMachine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        SlotMachine myFrame = new SlotMachine();
         
        myFrame.setSize(1200, 600);
        myFrame.setTitle("Slot Machine");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);                  
        myFrame.setLocationRelativeTo(null);
    }
}
