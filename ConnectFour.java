   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;


   public class ConnectFour extends JPanel
   {
   //Declare Subcomponents
   
   
      public JLabel scoreLabel;
      public JPanel buttonPanel;
      private JButton[][] buttonArray; 
      public boolean win;
      public static int[][] board;
      public int row;
      public int col;
      public int rowSelected;
      public int colSelected;
      public int playerTurn;
      public JButton honorCode;
      public JButton ruleButton;
      final int rowTiles = 6;
      final int colTiles = 5;
      final ImageIcon i0 = new ImageIcon("p0.png");
      final ImageIcon i1 = new ImageIcon("p1.png");
      final ImageIcon i2 = new ImageIcon("p2.png");
   
   
      public ConnectFour()
      
      {
         GridLayout connectGrid = new GridLayout(7,7);
         setLayout(new BorderLayout());
      
         win = false;
         board = new int[6][7];
         row = 0;
         col = 0;
         rowSelected = 0;
         colSelected = 0;
         playerTurn = 0;
      
      
      
      //North
         scoreLabel = new JLabel("Connect Four");
         scoreLabel.setForeground(new Color(0, 89, 220));
         scoreLabel.setFont(new Font("Ariel Black", Font.BOLD, 24));
         scoreLabel.setHorizontalAlignment(JLabel.CENTER);
         add(scoreLabel, BorderLayout.NORTH); 
      
      //Center
         buttonPanel = new JPanel();
         buttonPanel.setLayout(connectGrid);
         buttonArray = new JButton[rowTiles][colTiles];
      
         for (int i = rowTiles - 2; i >= 0; i--) {
            for (int j = colTiles - 1; j >= 0; j--) {
               board[i][j] = -1;
            
            }
         }
      // Fills the board with buttons
         for (row = 0; row <= rowTiles - 1; row++) {
            for (col = 0; col <= colTiles - 1; col++) {
               buttonArray[row][col] = new JButton("");
               buttonArray[row][col].addActionListener(new ButtonPressed());
               buttonPanel.add(buttonArray[row][col]);
            
            }
         }
         add(buttonPanel, BorderLayout.CENTER);
      
      //SOUTH
         honorCode = new JButton("Honor Code");
         honorCode.setHorizontalAlignment(JButton.CENTER);
         honorCode.setForeground(Color.black);
         honorCode.setFont(new Font("Ariel Black", Font.BOLD, 24));
         honorCode.addActionListener(new HonorPressed());
         add(honorCode, BorderLayout.SOUTH);
        
        //EAST
         ruleButton = new JButton("Rules");
         ruleButton.setHorizontalAlignment(JButton.CENTER);
         ruleButton.setForeground(Color.red);
         ruleButton.setFont(new Font("Ariel Black", Font.BOLD, 18));
         ruleButton.addActionListener(new rulePressed());
         add(ruleButton, BorderLayout.EAST);
      
      
      
      }
      private class HonorPressed implements ActionListener {
         public void actionPerformed(ActionEvent e){
         
            JOptionPane.showMessageDialog( new JOptionPane(),
               "On my honor as a South Lakes High School Student,\n"
               + "I have neither given nor received\n"
               + "unauthorized aid on this assignment.", "Honor Code",
               JOptionPane.INFORMATION_MESSAGE); 
         }
      }
      private class rulePressed implements ActionListener {
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog( new JOptionPane(),
               "1 )To win Connect Four you must be the first player to get four of your colored checkers in a row either horizontally, vertically or diagonally.,\n"
               + "2) Player 1 is the player using the Red piece and Player 2 is the Yellow piece, \n"
               
               + "3) In order to put a piece down, you must start at the bottom, or the block underneath you must be filled by another piece", "Rules",
               JOptionPane.INFORMATION_MESSAGE); 
         
         
         }
      }
   			
      private class ButtonPressed implements ActionListener {
      
         public void actionPerformed(ActionEvent f) {
         
            for (row = rowTiles-1; row >= 0; row--) {
               for (col = colTiles-1; col >= 0; col--) {
                  if (buttonArray[row][col] == f.getSource()) {
                     if (playerTurn % 2 == 0 && board[row][col] == 0) {
                        buttonArray[row][col].setIcon(i1);
                        board[row][col] = 1;
                        try {
                           board[row-1][col] = 0;
                        }
                           catch (ArrayIndexOutOfBoundsException e) {
                              JOptionPane.showMessageDialog( new JOptionPane(), "Reached Top of Column!", " Reached top of column! ",JOptionPane.INFORMATION_MESSAGE); 
                           
                           
                           
                           }
                        if (checkWin()) {
                        
                           scoreLabel.setText("Player 1 wins!");
                        
                          
                           for (int i = rowTiles - 1; i>=0; i--) {
                              for (int j = colTiles - 1; j >= 0; j--) {
                                 board[i][j] = -1;
                              }
                           }
                        }
                        playerTurn = playerTurn + 1;
                        break;
                     }
                     if (playerTurn % 2 == 1 && board[row][col] == 0) {
                        buttonArray[row][col].setIcon(i2);
                        board[row][col] = 2;
                        try {
                           board[row-1][col] = 0;
                        }
                           catch (ArrayIndexOutOfBoundsException e) {
                              JOptionPane.showMessageDialog( new JOptionPane(), "Reached Top of Column!", " Reached top of column! ",JOptionPane.INFORMATION_MESSAGE);
                           }
                        if (checkWin()) {
                        
                           scoreLabel.setText("Player 2 wins!");
                          
                           for (int i = rowTiles - 1; i >=0; i--) {
                              for (int j = colTiles - 1; j >= 0; j--) {
                                 board[i][j] = -1;
                              }
                           }
                        }
                        playerTurn = playerTurn + 1;
                        break;
                     }
                     else {
                        System.out.println("");
                     }
                  }
               }
            }
         }
      }
   
      public boolean checkWin() {
      
         for (int i=0; i<6; i++) {
            for (int j=0; j<4; j++) {
               if (board[i][j] != 0 && board[i][j] != -1 &&
               board[i][j] == board[i][j+1] &&
               board[i][j] == board[i][j+2] &&
               board[i][j] == board[i][j+3]) {
                  win = true;
               }
            }
         }
      
         for (int i=0; i<3; i++) {
            for (int j=0; j<7; j++) {
               if (board[i][j] != 0 && board[i][j] != -1 &&
               board[i][j] == board[i+1][j] &&
               board[i][j] == board[i+2][j] &&
               board[i][j] == board[i+3][j]) {
                  win = true;
               }
            }
         }
      
         for (int i=0; i<3; i++) {
            for (int j=0; j<4; j++) {
               if (board[i][j] != 0 && board[i][j] != -1 &&
               board[i][j] == board[i+1][j+1] &&
               board[i][j] == board[i+2][j+2] &&
               board[i][j] == board[i+3][j+3]) {
                  win = true;
               }
            }
         }
      
         for (int i=3; i<6; i++) {
            for (int j=0; j<4; j++) {
               if (board[i][j] != 0 && board[i][j] != -1 &&
               board[i][j] == board[i-1][j+1] &&
               board[i][j] == board[i-2][j+2] &&
               board[i][j] == board[i-3][j+3]) {
                  win = true;
               }
            }
         }
         return win;
      }
     
   
   
      public static void main(String[] args)
      {         
         JFrame frame = new JFrame("Connect Four");
         frame.setSize(800,600);
         frame.setLocation(270, 70);
         frame.setContentPane(new ConnectFour());
         frame.setVisible(true);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }
   
   }