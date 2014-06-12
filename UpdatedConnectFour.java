import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class UpdatedConnectFour extends MouseInputAdapter {
  
   public static final int SIZE_OF_GRID = 7;
   
   private static JFrame frame;
   private static char[][] grid;
   private static Graphics g;



   public static void main (String[] args){
      
      super.paintComponent(g);      
      frame = new JFrame();
      grid = new char[SIZE_OF_GRID][SIZE_OF_GRID];
             
      boolean victory = false;
      int player = 1;
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(430, 475);
      frame.setTitle("Connect Four");
      frame.setLayout(new BorderLayout()); 
      
      //g = frame.getGraphics();
      
      setUpModel();
      display(player);
      grid[1][1] = 'x';
      display(player);
      
      /*
      // call controller
      while(victory == false){
         getInput(player);
         victory = checkVictory();
         
         // keep track of stopping program
         if(victory == true){
            
            //change the println to a graphic
            System.out.println("Player " + player + " has won!");
         }
            
         if(player == 1){
            player = 2;
         }else {
            player = 1;   
         }  
       
      }
      */   
   }
   
   public static void setUpModel(){
      for(int a = grid.length -1; a >= 0; a--){
         for(int b = grid[a].length - 1; b >= 0; b--){
            grid[a][b] = '-';
         }
      } 
   }
   
   public static void display(int player){         
      JPanel north = new JPanel();
      
      //Graphics g = north.getGraphics();
      
      north.add(new JLabel("Player " + player + "'s Turn"));
      frame.add(north, BorderLayout.NORTH);
     
      DrawGrid panel = new DrawGrid();
      panel.setBackground(Color.WHITE);
      frame.add(panel, BorderLayout.CENTER);
      printGrid();
      
      frame.setVisible(true);
      
   }
   
   public static void printGrid(){
      for(int a = grid.length -1; a >= 0; a--){
         for(int b = grid[a].length - 1; b >= 0; b--){
            if(grid[a][b] == '-'){
               g.drawRect(10,10,10,10);
            }
            else if(grid [a][b] == 'x'){
               g.setColor(Color.BLUE);
               //g.drawOval(a * 60, b * 60, 30, 10);
               g.drawOval(10,10,10,10);
            }
            else if(grid[a][b] == 'o'){
               g.setColor(Color.RED);
               g.drawOval(50,50,10,10);
               //g.drawOval(a * 60, b * 60, 30, 10);
            }
         }
      }
   }
   
   public static void getInput (int player){
      
      //change this to get rid of system.out.print
      
      if(player == 1){
         System.out.print("Player 1's move: ");
      }
      else{
         System.out.print("player 2's move: ");
      }
      
      //get mouse click
      getUserInput();
           
      //This method will update the model
      //based on the new information
      //makeMove(player, mouseclick);
      
      //Redraw the grid
      display(player);
      
   }
   
   public static void changeModel(int player, int column){
      
      //this might become unneccesary       
      //if it does turn out to be necessary fix the println to graphics
      /*
      if (column > SIZE_OF_GRID){
         System.out.println("You cannot play in that column");
      } */
      
      column = SIZE_OF_GRID - column;
      int rowCounter = 0;
      while(grid[rowCounter][column] != '-'){
         rowCounter++;
      }
      
      if (player == 1)  
         grid[rowCounter][column] = 'x';
      else 
         grid[rowCounter][column] = 'o';       
   }      
   
   public static int getUserInput(){
      return 1;
   }
   
   /*
   public static void createGUI(){     
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(430, 475);
      frame.setTitle("Connect Four");
      frame.setLayout(new BorderLayout()); 
   }
   */
   
   public static boolean checkVictory(){
      for(int a = 0; a < SIZE_OF_GRID; a++){
         for(int b = 0; b <= 3; b++){  
            if ((grid[a][b] != '-')
               && (grid[a][b+1] != '-')
               && (grid[a][b+2] != '-')
               && (grid[a][b+3] != '-')
               && ((grid[a][b] == grid[a][b+1])
               && (grid[a][b+1] == grid[a][b+2])
               && (grid[a][b+2] == grid[a][b+3]))) {
               return true;
            }         
         }
      }
      
      for(int a = 0; a < 3; a++){
         for(int b = 0; b < SIZE_OF_GRID; b++){    
            if ((grid[a][b] != '-')
               && (grid[a+1][b] != '-')
               && (grid[a+2][b] != '-')
               && (grid[a+3][b] != '-')
               && ((grid[a][b] == grid[a+1][b])
               && (grid[a+1][b] == grid[a+2][b])
               && (grid[a+2][b] == grid[a+3][b]))) {
               return true;
            }
         }
      }
      
      for(int a = 0; a < 4; a++){
         for(int b = 0; b < 4; b++){    
            if ((grid[a][b] != '-')
               && (grid[a+1][b+1] != '-')
               && (grid[a+2][b+2] != '-')
               && (grid[a+3][b+3] != '-')
               && ((grid[a][b] == grid[a+1][b+1])
               && (grid[a+1][b+1] == grid[a+2][b+2])
               && (grid[a+2][b+2] == grid[a+3][b+3]))) {
               return true;
            }
         }
      }
      
      for(int a = 3; a < 7; a++){
         for(int b = 0; b <= 3; b++){
            if ((grid[a][b] != '-')
               && (grid[a-1][b+1] != '-')
               && (grid[a-2][b+2] != '-')
               && (grid[a-3][b+3] != '-')
               && ((grid[a][b] == grid[a-1][b+1])
               && (grid[a-1][b+1] == grid[a-2][b+2])
               && (grid[a-2][b+2] == grid[a-3][b+3]))) {
               return true;
            }
         }
      }     
      return false;
   }
}

class DrawGrid extends JPanel {
   public void paintComponent(Graphics g){
      super.paintComponent(g);      
      for(int i = 0; i <= 420; i += 60){
         g.drawLine(0,i,420,i);
         g.drawLine(i,0,i,420);
      }
   }
}