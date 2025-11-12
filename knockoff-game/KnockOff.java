
/* Muaaz Wahid & Mohana Nukala
 * 4/17/17
 * Period 6
 * KnockOff.java
 * This program/game prompts users to knock each other off the board great way
 * to socialize since it is local multiplayer
 * -------------------------------------------------------------------------
 * All comments with "//" is for classes, methods, if else statements, and loops
 * All comments with "///" is for everything else
 * */
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.Math;
import java.lang.Object;                // Necessary for ellipses
import java.awt.geom.RectangularShape;  // Necessary for ellipses
import java.awt.geom.Ellipse2D;         // Necessary for ellipses
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.lang.Object;
import java.lang.Number;
import java.lang.Integer;
public class KnockOff extends JFrame // This class creates the frame
{
    public KnockOff() // Initializes the random coordinates for each puck
    {
        super("!KnockOff!");                        /// Sets name of Frame
        setSize(1000, 1000);                            /// Sets frame size
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); /// Closes frame when you close the window
        setResizable(false);                        /// Prohibits users from resizing frame
        Panel pan = new Panel();
        setContentPane(pan);
        setVisible(true);                            /// Sets frame to be visible
    }
 
    public static void main(String [] args) // Calls constructor
    {
        KnockOff panel = new KnockOff();
    }
}
 
class Panel extends JPanel implements MouseListener, MouseMotionListener, ActionListener // This is responsible for drawing all images
{
   Image startScreen = new ImageIcon("StartScreen.png").getImage();  /// Start Screen
    Image gameBoard  = new ImageIcon("GameBoard.jpg").getImage();    /// Board for pucks to play on
    Image launchButton = new ImageIcon("button.jpg").getImage();     /// Button for launching
    Image switchButton = new ImageIcon("EndTurn.png").getImage();    /// Button for switching turns
    Image player1 = new ImageIcon("p1Victory.png").getImage();
    Image player2 = new ImageIcon("p2Victory.png").getImage();
    Image submit1 = new ImageIcon("player1Submit.png").getImage();
    Image submit2 = new ImageIcon("player2Submit.png").getImage();
    Color greyUK = new Color(0, 0, 0);                               /// Sets background color
    int begin;                                                        /// Checks if user clicked on start screen
    boolean drag1, drag2, drag3, drag4, drag5, drag6;                /// Checks if mouse is being dragged
    boolean playerTurn;                                              /// Determine which players turn it is
    boolean buttonPressed;                                           /// True when button is pressed
    int x1,x2,x3,x4,x5,x6,y1,y2,y3,y4,y5,y6;                         /// Random x & y coordinates for each puck
    int launchCounter;                                               /// Used to determine which pucks to launch also increments by 1 each time button is pressed
    double end1x, end2x, end3x, end4x, end5x, end6x;                 /// End x coords for each line
    double end1y, end2y, end3y, end4y, end5y, end6y;                 /// End y coords for each line
    double rand, rand2;                                              /// Stores values for exchanging of pucks
    double timer;                                                    /// Used to adjust coordinates of pucks when launching
    double distance1, distance2;
    boolean intersects;
    double x1Time, x2Time;
    Ellipse2D puck1, puck2, puck3, puck4, puck5, puck6;              /// Pucks for both players
    double startTime1, endTime1;
    double startTime2, endTime2;
    double speedX1, speedX2;
    int xStart1, xStart2, yStart1, yStart2, xStart3, yStart3, xStart4, yStart4,xStart5 ,yStart5, xStart6,yStart6;
    boolean out1, out2, out3, out4, out5, out6,oneandtwo, oneandthree,twoandthree;
    JTextField textBox1 = new JTextField("                                     "); /// blank text field
    JTextField textBox2 = new JTextField("                                     "); /// blank text field
    String name1;	/// player 1 name from input
    String name2;	/// player 2 name from input
    JLabel blank = new JLabel("Player 1: Please enter your name here."); /// Jlabel to prompt user
    JLabel blank2 = new JLabel("Player 2: Please enter your name here.");/// Jlabel to prompt user
    Font font = textBox1.getFont().deriveFont(Font.PLAIN, 55f);
    JFrame frame = new JFrame();
    
    public Panel() // initializes random coordinates for each puck
    {
        setBackground(Color.BLACK);
        x1 = (int)(Math.random()*200+50);    /// Assigns random x coordinate for 1st puck
        y1 = (int)(Math.random()*351+150);   /// Assigns random y coordinate for 1st puck
        x2 = (int)(Math.random()*200+351);   /// Assigns random x coordinate for 2nd puck
        y2 = (int)(Math.random()*351+150);   /// Assigns random y coordinate for 2nd puck
        x3 = (int)(Math.random()*201+650);   /// Assigns random x coordinate for 3rd puck
        y3 = (int)(Math.random()*351+150);   /// Assigns random y coordinate for 3rd puck
        x4 = (int)(Math.random()*201+50);    /// Assigns random x coordinate for 4th puck
        y4 = (int)(Math.random()*400+550);   /// Assigns random y coordinate for 4th puck
        x5 = (int)(Math.random()*201+351);   /// Assigns random x coordinate for 5th puck
        y5 = (int)(Math.random()*400+550);   /// Assigns random y coordinate for 5th puck
        x6 = (int)(Math.random()*180+651);   /// Assigns random x coordinate for 6th puck
        y6 = (int)(Math.random()*400+550);   /// Assigns random y coordinate for 6th puck
        //xStart1 = x1;
        //yStart1 = y1;
        end1x = x1;                          /// The end x and y coords for first line
        end1y = y1;                          /// The end x and y coords for first line
        end2x = x2;                          /// The end x and y coords for first line
        end2y = y2;                          /// The end x and y coords for first line
        end3x = x3;                          /// The end x and y coords for first line
        end3y = y3;                          /// The end x and y coords for first line
        end4x = x4;                          /// The end x and y coords for first line
        end4y = y4;                          /// The end x and y coords for first line
        end5x = x5;                          /// The end x and y coords for first line
        end5y = y5;                          /// The end x and y coords for first line
        end6x = x6;                          /// The end x and y coords for first line
        end6y = y6;                          /// The end x and y coords for first line
        drag1 = drag2 = drag3 = false;       /// Tells program player1 isnt moving any of their pucks
        drag4 = drag5 = drag6 = false;       /// Tells program player2 isnt moving any of their pucks
        launchCounter = 0;                   /// Starts counter at 0
        playerTurn = true;                   /// At start of program it is player 1's turn
        addMouseListener(this);              /// Adds Mouse Listener
        addMouseMotionListener(this);        /// Adds Mouse Motion Listener
        out1 = out2 = out3 = out4 = out5 = out6 = false;
        // ALL JStuff  is happening here
        textBox1.setFont(font);
        textBox2.setFont(font);
        textBox1.setSize(600,500);
        textBox2.setSize(600,500);
        blank.setFont(font);
        blank2.setFont(font);
        blank.setForeground(Color.WHITE);
        blank2.setForeground(Color.WHITE);
        add(blank);
        add(textBox1, BorderLayout.NORTH);
        add(blank2);
        add(textBox2, BorderLayout.SOUTH);
        textBox1.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                textBox1.setCaretPosition(0);
            }
            public void focusLost(FocusEvent e){
                textBox1.setCaretPosition(0);
            }
        });
        textBox2.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                textBox2.setCaretPosition(0);
            }
            public void focusLost(FocusEvent e)
            {
                textBox2.setCaretPosition(0);
            }
        });
    }
      public void paintComponent(Graphics g) // Draws Home Screen, board, pucks on board, and vectors
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;                                         /// Necessary object for making ellipses with Ellipse2D
         
        if(begin == 0)
        {
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("Click the buttons, then click", 25, 600);
            g.drawString("anywhere else to see the instructions!", 25, 650);
            g.drawImage(submit1, 50, 375, 300, 90, this);
            g.drawImage(submit2, 650, 375, 300, 90, this);
        }
        else if(begin == 1)                                                        // Checks if user clicked on start screen
        {
            g.drawImage(startScreen, 0, 0, 1000, 1000, this);                     /// Draws Home Screen on start of program
            textBox1.setVisible(false);
            textBox2.setVisible(false);
            blank.setVisible(false);
            blank2.setVisible(false);
        }
 
        else // This draws the pucks on the board in their initial positions if begin is true
        {
            Font f = new Font("Calibri", Font.BOLD, 20);
            
            name1 = name1.trim();
            name2 = name2.trim();
            
            g.setFont(f);
            g.setColor(Color.WHITE);
            if(playerTurn)
                g.drawString("It is "+name1+"'s turn right now!", 400,30);
            else
                g.drawString("It is "+name2+"'s turn right now!", 400,30);
            
            if( (x1+50<0 || y1+50<0) || (x1-50>900 || y1-50>900) )
            {
                out1 = true;
            }
            
            if( (x2+50<0 || y2+50<0) || (x2-50>900 || y2-50>900) )
            {
                out2 = true;
            }
            if( (x3+50<0 || y3+50<0) || (x3-50>900 || y3-50>900) )
            {
                out3 = true;
            }
            if( (x4+50<0 || y4+50<0) || (x4-50>900 || y4-50>900) )
            {
                out4 = true;
            }
            if( (x5+50<0 || y5+50<0) || (x5-50>900 || y5-50>900) )
            {
                out5 = true;
            }
            if( (x6+50<0 || y6+50<0) || (x6-50>900 || y6-50>900) )
            {
                out6 = true;
            }
            
            g.drawImage(gameBoard, 0, 50, 1000,1000, this);                      /// Draws Game Board for pucks to play on
            g2.setPaint(Color.BLACK);                                         /// Color for player 1's pucks
            puck1 = new Ellipse2D.Double((int)x1-50, (int)y1-50, 100, 100);  /// Declares size and coords of 1st black puck
            g2.fill(puck1);                                                     /// Draws 1st puck
            puck2 = new Ellipse2D.Double((int)x2-50, (int)y2-50, 100, 100);  /// Declares size and coords of 2nd black puck
            g2.fill(puck2);                                                     /// Draws 2nd puck
            puck3 = new Ellipse2D.Double((int)x3-50, (int)y3-50, 100, 100);  /// Declares size and coords of 3rd black puck
            g2.fill(puck3);                                                     /// Draws 3rd puck
            g2.setPaint(Color.WHITE);                                         /// Color for player 2's pucks
            puck4 = new Ellipse2D.Double((int)x4-50, (int)y4-50, 100, 100);  /// Declares size and coords of 1st white puck
            g2.fill(puck4);                                                     /// Draws 4th puck
            puck5 = new Ellipse2D.Double((int)x5-50, (int)y5-50, 100, 100);  /// Declares size and coords of 2nd white puck
            g2.fill(puck5);                                                     /// Draws 5th puck
            puck6 = new Ellipse2D.Double((int)x6-50, (int)y6-50, 100, 100);     /// Declares size and coords of 3rd white puck
            g2.fill(puck6);                                                     /// Draws 6th puck
            
            g.setColor(Color.red);                                             /// Color for player1's vectors
            if( (x1!=end1x && y1!=end1y) && (playerTurn) )                      // If 1st puck line is bieng drawn and it is player 1's turn
                g.drawLine((int)(x1), (int)(y1), (int)end1x, (int)end1y);     /// Draws vector for 1st puck
                
            if( (x2!=end2x && y2!=end2y) && (playerTurn) )                     // If 2nd puck line is bieng drawn and it is player 1's turn
                g.drawLine((int)(x2), (int)(y2), (int)end2x, (int)end2y);     /// Draws vector for 2nd puck
                
            if( (x3!=end3x && y3!=end3y) && (playerTurn) )                      // If 3rd puck line is bieng drawn and it is player 1's turn
                g.drawLine((int)(x3), (int)(y3), (int)end3x, (int)end3y);     /// Draws vector for 3rd puck
            
            g.setColor(Color.blue);                                         /// Color for player2's vectors
            if( (x4!=end4x && y4!=end4y) && (!playerTurn) )                  // If 4th puck line is bieng drawn and it is player 2's turn
                g.drawLine((int)(x4), (int)(y4), (int)end4x, (int)end4y);     /// Draws vector for 4th puck
                
            if( (x5!=end5x && y5!=end5y) && (!playerTurn) )                  // If 5th puck line is bieng drawn and it is player 2's turn
                g.drawLine((int)(x5), (int)(y5), (int)end5x, (int)end5y);     /// Draws vector for 5th puck
                
            if( (x6!=end6x && y6!=end6y) && (!playerTurn) )                  // If 6th puck line is bieng drawn and it is player 2's turn
                g.drawLine((int)(x6), (int)(y6), (int)end6x, (int)end6y);     /// Draws vector for 6th puck
            
            if (out1 && out2)
            {
                if((x3!=end3x || y3!=end3y) && (timer<=0 && playerTurn))
                {
                    g.drawImage(switchButton, 350, 875, 200, 75, this); /// draws button
                    buttonPressed = true;
                }
            }
         if (out1 && out3)
            {
                if((x2!=end2x || y2!=end2y) && (timer<=0 && playerTurn))
                {
                    g.drawImage(switchButton, 350, 875, 200, 75, this); /// draws button
                    buttonPressed = true;
                }
            }
             if (out2 && out3)
            {
                if((x1!=end1x || y1!=end1y) && (timer<=0 && playerTurn))
                {
                    g.drawImage(switchButton, 350, 875, 200, 75, this); /// draws button
                    buttonPressed = true;
                }
            }
            
             if(out1)
            {
                if((x2!=end2x || y2!=end2y) && (x3!=end3x || y3!=end3y) && (timer<=0 && playerTurn))
                {
                    g.drawImage(switchButton, 350, 875, 200, 75, this); /// draws button
                    buttonPressed = true;
                }
            }
            
             if(out2)
            {
                if((x1!=end1x || y1!=end1y) && (x3!=end3x || y3!=end3y) && (timer<=0 && playerTurn))
                {
                    g.drawImage(switchButton, 350, 875, 200, 75, this); /// draws button
                    buttonPressed = true;
                }
            }
               
             if(out3)
            {
                if((x2!=end2x || y2!=end2y) && (x1!=end1x || y1!=end1y) && (timer<=0 && playerTurn))
                {
                    g.drawImage(switchButton, 350, 875, 200, 75, this); /// draws button
                    buttonPressed = true;
                }
            }
             if((x1!=end1x || y1!=end1y) && (x2!=end2x || y2!=end2y) && (x3!=end3x || y3!=end3y) && (timer<=0 && playerTurn)) // Makes button appear when player1 has drawn all their lines
            {
                g.drawImage(switchButton, 350, 875, 200, 75, this); /// draws button
                buttonPressed = true;
            }
            
            
           if(out4 && out5)
           {
               if((x6!=end6x || y6!=end6y) && (timer<=0 && !playerTurn))
               {
                    xStart1 = x1;
                    yStart1 = y1;
                    xStart2 = x2;
                    yStart2 = y2;
                    xStart3 = x3;
                    yStart3 = y3;
                    xStart4 = x4;
                    yStart4 = y4;
                    xStart5 = x5;
                    yStart5 = y5;
                    xStart6 = x6;
                    yStart6 = y6;
                    g.drawImage(launchButton, 350, 875, 200, 75, this); /// draws button
                    buttonPressed = true;
               }
           }
            if(out4 && out6)
           {
               if((x5!=end5x || y5!=end5y) && (timer<=0 && !playerTurn))
               {
                    xStart1 = x1;
                    yStart1 = y1;
                    xStart2 = x2;
                    yStart2 = y2;
                      xStart3 = x2;
                    yStart3 = y3;
                     xStart4 = x4;
                    yStart4 = y4;
                     xStart5 = x5;
                    yStart5 = y5;
                    xStart6 = x6;
                    yStart6 = y6;
                    
                    g.drawImage(launchButton, 350, 875, 200, 75, this); /// draws button
                    buttonPressed = true;
               }
           }
            if(out5 && out6)
           {
               if((x4!=end4x || y4!=end4y) && (timer<=0 && !playerTurn))
               {
                    xStart1 = x1;
                    yStart1 = y1;
                    xStart2 = x2;
                    yStart2 = y2;
                      xStart3 = x2;
                    yStart3 = y3;
                     xStart4 = x4;
                    yStart4 = y4;
                     xStart5 = x5;
                    yStart5 = y5;
                    xStart6 = x6;
                    yStart6 = y6;
                    g.drawImage(launchButton, 350, 875, 200, 75, this); /// draws button
                    buttonPressed = true;
               }
           }
            if(out4)
           {
               if((x6!=end6x || y6!=end6y) && (x5!=end5x || y5!=end5y) && (timer<=0 && !playerTurn))
               {
                    xStart1 = x1;
                    yStart1 = y1;
                    xStart2 = x2;
                    yStart2 = y2;
                      xStart3 = x2;
                    yStart3 = y3;
                     xStart4 = x4;
                    yStart4 = y4;
                     xStart5 = x5;
                    yStart5 = y5;
                    xStart6 = x6;
                    yStart6 = y6;
                    g.drawImage(launchButton, 350, 875, 200, 75, this); /// draws button
                    buttonPressed = true;
               }
           }
            if(out5)
           {
               if((x6!=end6x || y6!=end6y) && (x4!=end4x || y4!=end4y) && (timer<=0 && !playerTurn))
               {
                    xStart1 = x1;
                    yStart1 = y1;
                    xStart2 = x2;
                    yStart2 = y2;
                      xStart3 = x2;
                    yStart3 = y3;
                     xStart4 = x4;
                    yStart4 = y4;
                     xStart5 = x5;
                    yStart5 = y5;
                    xStart6 = x6;
                    yStart6 = y6;
                    g.drawImage(launchButton, 350, 975, 200, 75, this); /// draws button
                    buttonPressed = true;
               }
           }
            if(out6)
           {
               if((x5!=end5x || y5!=end5y) && (x5!=end5x || y5!=end5y) && (timer<=0 && !playerTurn))
               {
                    xStart1 = x1;
                    yStart1 = y1;
                    xStart2 = x2;
                    yStart2 = y2;
                     xStart4 = x4;
                    yStart4 = y4;
                     xStart5 = x5;
                    yStart5 = y5;
                    xStart6 = x6;
                    yStart6 = y6;
                    g.drawImage(launchButton, 350, 875, 200, 75, this); /// draws button
                    buttonPressed = true;
               }
           }
            if((x4!=end4x || y4!=end4y) && (x5!=end5x || y5!=end5y) && (x6!=end6x || y6!=end6y) && (timer<=0 && !playerTurn) ) // Makes button appear when player2 has drawn all their lines
           {
                xStart1 = x1;
                yStart1 = y1;
                xStart2 = x2;
                yStart2 = y2;
                xStart3 = x2;
                yStart3 = y3;
                xStart4 = x4;
                yStart4 = y4;
                xStart5 = x5;
                yStart5 = y5;
                xStart6 = x6;
                yStart6 = y6;
                g.drawImage(launchButton, 350, 875, 200, 75, this); /// draws button
                buttonPressed = true;
           }
                double distance = Math.sqrt(((x1 - x2) * (x1 - x2))+ ((y1 - y2) * (y1 - y2)));
                double distance3 = Math.sqrt(((x1 - x3) * (x1 - x3))+ ((y1 - y3) * (y1 - y3)));
                double distance4 = Math.sqrt(((x2 - x3) * (x2 - x3))+ ((y2 - y3) * (y2 - y3)));
                double distance5 = Math.sqrt(((x1 - x4) * (x1 - x4))+ ((y1 - y4) * (y1 - y4)));
                double distance6 = Math.sqrt(((x1 - x5) * (x1 - x5))+ ((y1 - y5) * (y1 - y5)));
                double distance7 = Math.sqrt(((x1 - x6) * (x1 - x6))+ ((y1 - y6) * (y1 - y6)));
                double distance8 = Math.sqrt(((x2 - x6) * (x2 - x6))+ ((y2 - y6) * (y2 - y6)));
                double distance9 = Math.sqrt(((x2 - x5) * (x2 - x5))+ ((y2 - y5) * (y2 - y5)));
                double distance10 = Math.sqrt(((x2 - x4) * (x2 - x4))+ ((y1 - y4) * (y1 - y4)));
                double distance11 = Math.sqrt(((x3 - x6) * (x3 - x6))+ ((y3 - y6) * (y3 - y6)));
                double distance12 = Math.sqrt(((x3 - x5) * (x3 - x5))+ ((y3 - y5) * (y3 - y5)));
                double distance13 = Math.sqrt(((x3 - x4) * (x3 - x4))+ ((y3 - y4) * (y3 - y4)));
                double distance14 = Math.sqrt(((x4 - x5) * (x4 - x5))+ ((y4 - y5) * (y4 - y5)));
                double distance15 = Math.sqrt(((x4 - x6) * (x4 - x6))+ ((y4 - y6) * (y4 - y6)));
                double distance16 = Math.sqrt(((x5 - x6) * (x6 - x5))+ ((y6 - y5) * (y6 - y5)));
            if(distance<100 || distance3<100 || distance4<100 ||distance5<100 ||distance6<100 ||distance7<100 ||distance8<100 ||distance9<100 ||distance10<100 ||distance11<100 ||distance12<100 ||distance13<100 ||distance14<100 ||distance15<100 ||distance16<100 )
            {
                timer = 20;
            }
            if(timer>0) // Starts launching of pucks when button is pressed
            {
                y1 += (end1y-y1)/timer;    /// Adjusts x coord for 1st puck according to vector
                x1 += (end1x-x1)/timer;    /// Adjusts y coord for 1st puck according to vector
                y2 += (end2y-y2)/timer;    /// Adjusts x coord for 2nd puck according to vector
                x2 += (end2x-x2)/timer;    /// Adjusts y coord for 2nd puck according to vector
                y3 += (end3y-y3)/timer;    /// Adjusts x coord for 3rd puck according to vector
                x3 += (end3x-x3)/timer; /// Adjusts y coord for 3rd puck according to vector
                y4 += (end4y-y4)/timer;    /// Adjusts x coord for 4th puck according to vector
                x4 += (end4x-x4)/timer;    /// Adjusts y coord for 4th puck according to vector
                y5 += (end5y-y5)/timer;    /// Adjusts x coord for 5th puck according to vector
                x5 += (end5x-x5)/timer;    /// Adjusts y coord for 5th puck according to vector
                y6 += (end6y-y6)/timer;    /// Adjusts x coord for 6th puck according to vector
                x6 += (end6x-x6)/timer;    /// Adjusts y coord for 6th puck according to vector
                timer-=0.2;                /// Decreases timer so that the pucks slow down to a halt like sliding on real ice
                
                if (distance4 <= 100)
                {
                    twoandthree = true;
                    end2x = xStart2+randomNumberGenerator();
                    end2y = yStart2+randomNumberGenerator();
                    end3x = xStart3+randomNumberGenerator();
                    end3y = yStart3+randomNumberGenerator();
                    timer = 75;
                }
                if (distance3 <= 100)
                {
                    oneandthree = true;
                    end1x = xStart1+randomNumberGenerator();
                    end1y = yStart1+randomNumberGenerator();
                    end3x = xStart3+randomNumberGenerator();
                    end3y = yStart3+randomNumberGenerator();
                    timer = 75;
                }
                
                if (distance <= 100)
                {
                    oneandtwo = true;
                    end1x = xStart1+randomNumberGenerator();
                    end1y = yStart1+randomNumberGenerator();
                    end2x = xStart2+randomNumberGenerator();
                    end2y = yStart2+randomNumberGenerator();
                    timer = 75;
                }
                    
                if (distance5 <= 100)//1and4
                {
                    end1x = xStart1+randomNumberGenerator();
                    end1y = yStart1+randomNumberGenerator();
                    end4x = xStart4+randomNumberGenerator();
                    end4y = yStart4+randomNumberGenerator();
                    timer = 75;
                }
                if (distance6 <= 100)//1and5
                {
                   end1x = xStart1+randomNumberGenerator();
                   end1y = yStart1+randomNumberGenerator();
                   end5x = xStart5+randomNumberGenerator();
                   end5y = yStart5+randomNumberGenerator();
                   timer = 75;
                }
               if (distance7 <= 100)
                {
                   end1x = xStart1+randomNumberGenerator();
                   end1y = yStart1+randomNumberGenerator();
                   end6x = xStart6+randomNumberGenerator();
                   end6y = yStart6+randomNumberGenerator();
                   timer = 75;
                }         
                if (distance8 <= 100)
                {
                  end2x = xStart2+randomNumberGenerator();
                  end2y = yStart2+randomNumberGenerator();
                  end6x = xStart6+randomNumberGenerator();
                  end6y = yStart6+randomNumberGenerator();
                  timer = 75;
                }  
                if (distance9 <= 100)
                {
                   end2x = xStart2+randomNumberGenerator();
                   end2y = yStart2+randomNumberGenerator();
                   end5x = xStart5+randomNumberGenerator();
                   end5y = yStart5+randomNumberGenerator();
                   timer = 75;
                }  
                if (distance10 <= 100)
                {
                   end2x = xStart2+randomNumberGenerator();
                   end2y = yStart2+randomNumberGenerator();
                   end4x = xStart4+randomNumberGenerator();
                   end4y = yStart4+randomNumberGenerator();
                   timer = 75;
                }   
                if (distance11 <= 100)
                {
                   end3x = xStart3+randomNumberGenerator();
                   end3y = yStart3+randomNumberGenerator();
                   end6x = xStart6+randomNumberGenerator();
                   end6y = yStart6+randomNumberGenerator();   
                    timer = 75;
                }   
                if (distance12 <= 100)
                {
                    end3x = xStart3+randomNumberGenerator();
                    end3y = yStart3+randomNumberGenerator();
                    end5x = xStart5+randomNumberGenerator();
                    end5y = yStart5+randomNumberGenerator();
                    timer = 75;
                }      
                if (distance13 <= 100)
                {
                    end3x = xStart3+randomNumberGenerator();
                    end3y = yStart3+randomNumberGenerator();
                    end4x = xStart4+randomNumberGenerator();
                    end4y = yStart4+randomNumberGenerator();  
                    timer = 75;
                }   
                if (distance14 <= 100)
                {
                    end5x = xStart5+randomNumberGenerator();
                    end5y = yStart5+randomNumberGenerator();
                    end4x = xStart4+randomNumberGenerator();
                    end4y = yStart4+randomNumberGenerator();
                    timer = 75;
                }  
                if (distance15 <= 100)
                {
                    end4x = xStart4+randomNumberGenerator();
                    end4y = yStart4+randomNumberGenerator();
                    end6x = xStart6+randomNumberGenerator();
                    end6y = yStart6+randomNumberGenerator();  
                    timer = 75;
                }   
                if (distance16 <= 100)
                {
                    end5x = xStart5+randomNumberGenerator();
                    end5y = yStart5+randomNumberGenerator();
                    end6x = xStart6+randomNumberGenerator();
                    end6y = yStart6+randomNumberGenerator();
                    timer = 75;
                }     
                repaint();
            }
            if(out1 && out2 && out3)
            {
                g.drawImage(player2, 0, 0, 1000, 1000, this);
            }
            
            else if(out4 && out5 && out6)
            {
                g.drawImage(player1, 0, 0, 1000, 1000, this);
            }
        }
    }// End of paintComponent
   
      public int randomNumberGenerator()
      {
            int randfactor = (int)(Math.random()*200-100);
            return randfactor;
      }
        
    public void mouseClicked(MouseEvent e) // Checks if user clicked the main screen
    {
        if( (e.getX()>=50 && e.getX()<350) && (e.getY()>=375 && e.getY()<=465 ) && begin == 0 )
        name1 = textBox1.getText();
        else if( (e.getX()>=650 && e.getX()<950) && (e.getY()>=375 && e.getY()<=465 ) && begin == 0 )
        name2 = textBox2.getText();
        else if( (!name1.equals(null) && !name2.equals(null) ) || (!name2.equals("") && !name1.equals("") ) || (!name1.equals(null) && !name2.equals("")) || (!name1.equals("") && !name2.equals(null) ) )
        begin++;
               if( (begin >= 2) && (buttonPressed) && (e.getX() >= 350 && e.getX() <= 550) && (e.getY() >= 875 && e.getY() <= 950) ) // Checks if user clicked on button
               {
                   if(!playerTurn)
                   {
                       timer = 100;    /// Sets timer at 100
                       Date d = new Date();
                       DateFormat df = new SimpleDateFormat("SS");
                       String dateStr = df.format(d);
                       startTime1 = Integer.parseInt(dateStr);
                       startTime2 = Integer.parseInt(dateStr);
                   }
                   launchCounter++;     /// Counts how many times button has been pressed
                   playerTurn = !playerTurn;
               }
        repaint();
    }
    
    public void mousePressed(MouseEvent e) // Checks if mouse was pressed on one of the pucks
    {
        if(timer < 10){
        if      ( (e.getX()>=x1-50 && e.getX()<=x1+50) && (e.getY()>=y1-50 && e.getY()<=y1+50) ) drag1 = true; /// Lets program know user is drawing line for 1st puck
        else if ( (e.getX()>=x2-50 && e.getX()<=x2+50) && (e.getY()>=y2-50 && e.getY()<=y2+50) ) drag2 = true; /// Lets program know user is drawing line for 2nd puck
        else if ( (e.getX()>=x3-50 && e.getX()<=x3+50) && (e.getY()>=y3-50 && e.getY()<=y3+50) ) drag3 = true; /// Lets program know user is drawing line for 3rd puck
        else if ( (e.getX()>=x4-50 && e.getX()<=x4+50) && (e.getY()>=y4-50 && e.getY()<=y4+50) ) drag4 = true; /// Lets program know user is drawing line for 4th puck
        else if ( (e.getX()>=x5-50 && e.getX()<=x5+50) && (e.getY()>=y5-50 && e.getY()<=y5+50) ) drag5 = true; /// Lets program know user is drawing line for 5th puck
        else if ( (e.getX()>=x6-50 && e.getX()<=x6+50) && (e.getY()>=y6-50 && e.getY()<=y6+50) ) drag6 = true; /// Lets program know user is drawing line for 6th puck
}
    }
 
    public void mouseReleased(MouseEvent e) // Finds end points for vector
    {
        System.out.println("drag1"+drag1);
                System.out.println("drag2"+drag2);
        System.out.println("drag3"+drag3);
        if(begin>=2)
        {
            if(drag1 && (playerTurn) ) // If user dragging 1st puck
            {
                end1x = e.getX();                                /// End x coord of vector
                end1y = e.getY();                                /// End y coord of vector
            }
    
            if(drag2 && (playerTurn) ) // If user dragging 2nd puck
            {
                end2x = e.getX();                                /// End x coord of vector
                end2y = e.getY();                                /// End y coord of vector
            }
    
            if(drag3 && (playerTurn) ) // If user dragging 3rd puck
            {
                end3x = e.getX();                                /// End x coord of vector
                end3y = e.getY();                                /// End y coord of vector
            }
    
            if(drag4 && (!playerTurn) ) // If user dragging 4th puck
            {
                end4x = e.getX();                                /// End x coord of vector
                end4y = e.getY();                                /// End y coord of vector
            }
    
            if(drag5 && (!playerTurn) ) // If user dragging 5th puck
            {
                end5x = e.getX();                                /// End x coord of vector
                end5y = e.getY();                                /// End y coord of vector
            }
    
            if(drag6 && (!playerTurn) ) // If user dragging 6th puck
            {
                end6x = e.getX();                                /// End x coord of vector
                end6y = e.getY();                                /// End y coord of vector
            }
            drag1 = drag2 = drag3 = drag4 = drag5 = drag6 = false; /// Terminates drawing of vector if user lets go of mouse
        }
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    
    public void mouseDragged(MouseEvent e) // Checks if mouse is dragging on
    {
        if(begin>=2)
        {
            if(drag1 && (playerTurn)) {
                ///x1 = x1 + (e.getX() - e.getX());
                ///y1 = y1 + (e.getY() - e.getY());
                end1x = e.getX();        /// Gets x coord of vector endpoint using mouse x coord
                end1y = e.getY();        /// Gets y coord of vector endpoint using mouse y coord
                repaint();                 /// repaint when dragging
            }
            
            if(drag2 && (playerTurn)) {
                ///x2 = x2 + (e.getX() - e.getX());
                ///y2 = y2 + (e.getY() - e.getY());
                end2x = e.getX();        /// Gets x coord of vector endpoint using mouse x coord
                end2y = e.getY();        /// Gets y coord of vector endpoint using mouse y coord
                repaint();                 /// repaint when dragging
            }
    
            if(drag3 && (playerTurn)) {
                ///x3 = x3 + (e.getX() - e.getX());
                ///y3 = y3 + (e.getY() - e.getY());
                end3x = e.getX();        /// Gets x coord of vector endpoint using mouse x coord
                end3y = e.getY();        /// Gets y coord of vector endpoint using mouse y coord
                repaint();                 /// repaint when dragging
            }
    
            if(drag4 && (!playerTurn)) {
                ///x4 = x4 + (e.getX() - e.getX());
                ///y4 = y4 + (e.getY() - e.getY());
                end4x = e.getX();        /// Gets x coord of vector endpoint using mouse x coord
                end4y = e.getY();        /// Gets y coord of vector endpoint using mouse y coord
                repaint();               /// repaint when dragging
            }
    
            if(drag5 && (!playerTurn)) {
                ///x5 = x5 + (e.getX() - e.getX());
                ///y5 = y5 + (e.getY() - e.getY());
                end5x = e.getX();        /// Gets x coord of vector endpoint using mouse x coord
                end5y = e.getY();        /// Gets y coord of vector endpoint using mouse y coord
                repaint();                 /// repaint when dragging
            }
    
            if(drag6 && (!playerTurn)) {
                ///x6 = x6 + (e.getX() - e.getX());
                ///y6 = y6 + (e.getY() - e.getY());
                end6x = e.getX();        /// Gets x coord of vector endpoint using mouse x coord
                end6y = e.getY();        /// Gets y coord of vector endpoint using mouse y coord
                repaint();                /// repaint when dragging
            }
        }
    }
    public void mouseMoved(MouseEvent e){}
    public void actionPerformed(ActionEvent e){}
} // end of class