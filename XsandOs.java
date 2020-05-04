package ee219;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.border.BevelBorder;
//import javax.swing.border.*;
//import ee219.GUI.BoardListener;
//import ee219.GUI.BtnListener;
import java.util.Random; 
import java.net.URI;
public class XsandOs implements ActionListener{

	private JFrame frame;
	private BoardButton [][] buttons;
	private JButton restart,better;   ////reset,newGame,
	private JLabel score,message,human,ai,aiscore,humanscore;///scoreboard,
	private JPanel board,scoreboardp,scorep;
	private int clicks,playerscore,computerscore,randint,pattern;
	private Random rand;
	
	
	
	public XsandOs() {
		/////// Creates board
		
		frame = new JFrame("Tic-Tac-Toe");
		frame.setLayout(null);
		
		board = new JPanel();
		board.setBounds(50, 20, 199, 199);
		board.setLayout(new GridLayout(3,3,2,2));
        board.setBackground(Color.BLACK);
        
		scorep = new JPanel();
		scorep.setLayout(new GridLayout(2,2));
		scorep.setBackground(Color.WHITE);
		
		
		
		scoreboardp = new JPanel();
		scoreboardp.setBounds(315, 20, 170, 70); 
		scoreboardp.setLayout(new GridLayout(2,1));
		
		scoreboardp.setForeground(Color.decode("#E6E3E0"));
		
        message = new JLabel("You go first!");
        message.setBounds(360, 100, 170, 60); 
        message.setFont(new Font("Arial", Font.BOLD, 13));
        
		score = new JLabel("SCORE BOARD");
		score.setFont(new Font("Arial", Font.BOLD, 13));
		
		human = new JLabel("Human");
		human.setHorizontalAlignment(JLabel.CENTER);
		
		humanscore= new JLabel(Integer.toString(playerscore));
		humanscore.setHorizontalAlignment(JLabel.CENTER);
		
		ai =  new JLabel("Computer");
		ai.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		aiscore= new JLabel(Integer.toString(computerscore));
		aiscore.setHorizontalAlignment(JLabel.CENTER);
		
		score.setHorizontalAlignment(JLabel.CENTER);
		
		rand = new Random();
		
		restart = new JButton("Play Another Game");
        restart.addActionListener(this); 
        restart.setBounds(315,170,170,25);
		
        better = new JButton("Want Better?");
        better.addActionListener(this); 
        better.setBounds(315,210,170,25);
		
		 
		 
		//panel.setBounds(400,20,20,20);
		
		//scorex.setBounds(315,20,170,70);
        frame.setSize(550,295);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.add(board);
		frame.add(scoreboardp);
		frame.add(message);
		frame.add(better);
		frame.add(restart);
		restart.setVisible(false);
		
		scoreboardp.add(score);
		scoreboardp.add(scorep);
		
		scorep.add(human);
		scorep.add(ai);
		scorep.add(humanscore);
		scorep.add(aiscore);
		//scoreboardp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
       // restart.addActionListener(new ButtonListener());
		
		//frame.add(restart);
		
		
		// newGame.addActionListener(new BtnListener());
	    // reset.addActionListener(new BtnListener());

		
		 //////////////////////////////////////////Creates buttons in 3x3 grid
		buttons = new BoardButton[3][3]; 
        
		
		for (int x=0; x<3; x++) {
			for (int y=0; y<3; y++) {
				//JButton btn = new JButton();
				buttons[x][y] = new BoardButton(x,y);
				buttons[x][y].setBounds(0+(68*y), 0+(68*y),66 , 66);
				board.add(buttons[x][y]);
			    buttons[x][y].setBackground(Color.WHITE);
	            buttons[x][y].setFont(new Font("Brush Script MT", Font.ITALIC, 40));
	            buttons[x][y].addActionListener(new BoardListener());
	            buttons[x][y].setValue(0);

			}	
			
		}
			
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
}
	
	//Checks if either side has won if they have returns a 1
	
	 public int checkWin() {
	        int diagSum1 = 0;
	        int diagSum2 = 0;
	        int colSum = 0;
	        int rowSum = 0;
	        int j = 0;
	        String winner = "";
	        
	        

	        diagSum1 = buttons[0][2].getValue() + buttons[1][1].getValue() + buttons[2][0].getValue();
	        diagSum2 = buttons[0][0].getValue() + buttons[1][1].getValue() + buttons[2][2].getValue();

	        if(diagSum1 == 3 || diagSum2 == 3) {
	            winner = "computer";
	        	 message.setText("COMPUTER WINS!");
	             setPanelEnabled(board, false);
		         aiscore.setText(Integer.toString(computerscore+=2));
		         restart.setVisible(true);
		            j=1;

	        }
	        if(diagSum1 == -3 || diagSum2 == -3) {
	            winner = "you win";
	            setPanelEnabled(board, false);
	        	message.setText("YOU WIN");
	            humanscore.setText(Integer.toString(playerscore+=2));
	            restart.setVisible(true);
	            j=1;
	        }

	        for(int x = 0; x<3; x++) {
	            for(int y = 0; y<3; y++) {
	                rowSum += buttons[x][y].getValue(); 
	                colSum += buttons[y][x].getValue();
	            }
	            if(rowSum == 3 || colSum == 3 && winner.equals("")) {      
	                setPanelEnabled(board, false);
	                restart.setVisible(true);
		          //  frame.add(better);
	                winner = "Computer wins!";
	            	 message.setText("COMPUTER WINS!");
			         aiscore.setText(Integer.toString(computerscore+=2));
			         j=1;
	            }
	            else if(rowSum == -3 || colSum == -3 && winner.equals("")) {
	                setPanelEnabled(board, false);
		          // frame.add(restart);
	                winner = "You win";
	            	message.setText("YOU WIN");
		            humanscore.setText(Integer.toString(playerscore+=2));
		            restart.setVisible(true);
		          //  frame.add(better);
		            j=1;
	            }
	            rowSum = 0;
	            colSum = 0;
	        }
	        
	        if(clicks >= 9 && winner.equals("")) {
                winner = "draw";
	         setPanelEnabled(board, false);
	         restart.setVisible(true);
	           // frame.add(better);
	            message.setText("IT'S A DRAW!");
	            humanscore.setText(Integer.toString(playerscore+=1));
		         aiscore.setText(Integer.toString(computerscore+=1));
		         j=1;
	        }
	 return j;
	 }   
	 
	 //////////////////////////////////if either button clicked
	 
	  public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Play Another Game")){
        	restart.setVisible(false);
        	randint = rand.nextInt(2);
           board.removeAll();
           board.setBounds(50, 20, 199, 199); 
           board.setLayout(new GridLayout(3,3,2,2));
           board.setBackground(Color.black);
           for (int x=0; x<3; x++) {
   			for (int y=0; y<3; y++) {
   				//JButton btn = new JButton();
   				buttons[x][y] = new BoardButton(x,y);
   				buttons[x][y].setBounds(0+(68*y), 0+(68*y),66 , 66);
   				board.add(buttons[x][y]);
   			    buttons[x][y].setBackground(Color.WHITE);
   	            buttons[x][y].setFont(new Font("Brush Script MT", Font.ITALIC, 40));
   	            buttons[x][y].addActionListener(new BoardListener());
   	            buttons[x][y].setValue(0);
           
           }
           }
          board.revalidate();
          board.repaint();
        //  frame.remove(restart);
          clicks = 0;
	        //checkWin();
          message.setText("");
          if(randint == 1 ) {clicks++;AiChoice();message.setText(Integer.toString(clicks) + Integer.toString(pattern));}
        }
        
        else if (e.getActionCommand().equals("Want Better?")) {
        	
        	try {
        		   URI uri= new URI("https://www.google.com/search?safe=strict&rlz=1C1CHBF_enIE772IE772&sxsrf=ALeKk02SwFT95oiUpQTwq4tov5PfCWJeYw%3A1586973543968&ei=Z0uXXqTWOoGl1fAP7rq56Ag&q=tictactoe&oq=tictactoe&gs_lcp=CgZwc3ktYWIQAzIECCMQJzIECCMQJzIECAAQQzIECAAQCjIECAAQCjIECAAQCjIECAAQCjIECAAQCjIECAAQCjIECAAQCjoECAAQR0oNCBcSCTktNzlnMGcxMkoLCBgSBzktMWcwZzJQ_hNY_hNg5RVoAHACeACAAUOIAUOSAQExmAEAoAEBqgEHZ3dzLXdpeg&sclient=psy-ab&ved=0ahUKEwjkpeySgevoAhWBUhUIHW5dDo0Q4dUDCAw&uact=5");
        		   java.awt.Desktop.getDesktop().browse(uri);
        		   }
        		   catch (Exception j) {
        			   
        			   j.printStackTrace();
        			  }
        	
        }
        
        
        
        
        
	  }
	  
     //////// isnt used
      /*    
	public void reset() {
	        for(int x=0; x<3; x++){
	            for(int y=0; y<3; y++) {
	                //setPanelEnabled(board, true);
	               buttons[x][y].setSign("");
	               buttons[x][y].setState(false);
	               buttons[x][y].setValue(0);
	               clicks = 0;
	            }
	        }          
	    }
	 */
	  
	  
	  //////////enables board to be clicked
	  
	  public void setPanelEnabled(JPanel panel, Boolean isEnabled) {
	        panel.setEnabled(isEnabled);
	        Component[] components = panel.getComponents();

	        for(int i = 0; i < components.length; i++) {
	            if(components[i].getClass().getName() == "javax.swing.JPanel") {
	                setPanelEnabled((JPanel) components[i], isEnabled);
	            }

	            components[i].setEnabled(isEnabled);
	        }
	    } 
	
	  
	  
	  /////////////////////////if button on board is clicked this sets it
	 class BoardListener implements ActionListener {
	        public void actionPerformed (ActionEvent e) {
	            BoardButton buttonClicked = (BoardButton)e.getSource();
	            if(buttonClicked.getState()==false) {
	            	message.setText("");
	                
	                
	                
	              //  if((clicks+randint)%2==0) {
	                   
	                	//buttonClicked.setText("O");
	                   // buttonClicked.setForeground(Color.black);
	                   // buttonClicked.setValue(1);                    
	                  //  checkWin();
	                  //  clicks++;
	               // }
	               // else {
	            	clicks++;
	                    buttonClicked.setText("X");
	                    buttonClicked.setValue(-1);
	                    buttonClicked.setForeground(Color.black);
	                    buttonClicked.setState(true); 
	                int l = checkWin();
	                  //  AiChoice();
	                    //checkWin();
	               // clicks++;
	                    if(clicks <= 9 && l != 1) {
	                    clicks++;
	                	AiChoice();
	                	//message.setText(Integer.toString(clicks) + Integer.toString(pattern));
	                    checkWin();
	                    }
	            }  
	            
	           // buttonClicked.setState(true);
	        }   
	    }  


	 
	 
	 ////////////////////////////////////////// Decides what ai does. Theres a few patterns that help ai win mostly for when ai goes first. 
	 ////////////////////////////////////////// if ai cant win. it sets pattern 20 which if it can win it wins if person can win it blocks it.
	 ////////////////////////////////////////// or else it puts down a random legal move. I think its unbeatable i havent been able to beat it yet.
	 public void AiChoice(){
   		/*  
	   	int [][]box;
	   	
	   	for (int x=0; x<3; x++) {
   			for (int y=0; y<3; y++) {
   				box[x][y]=buttons[x][y].getValue();
   				
   			}
	   	}
		 */
		// message.setText(Integer.toString(clicks));
		// clicks++;
		// message.setText(Integer.toString(clicks));
		
		 
		 
		 //////////////////////////////////////patterns
		 if(clicks==1) {
			 pattern = 1;
	   	buttons[0][0].setText("O");
         buttons[0][0].setForeground(Color.black);
         buttons[0][0].setValue(1);
         buttons[0][0].setState(true);          
         
         return;
		 }
		 
         
		 if(clicks==2) {
			
			 if (buttons[1][1].getValue()==0) {
				 buttons[1][1].setText("O");
		         buttons[1][1].setForeground(Color.black);
		         buttons[1][1].setValue(1);
		         buttons[1][1].setState(true);
		         pattern = 20;  
			 	}
			 else {
				 buttons[0][0].setText("O");
		         buttons[0][0].setForeground(Color.black);
		         buttons[0][0].setValue(1);
		         buttons[0][0].setState(true); 
		         pattern = 20;
			 }
			 return;
		 }
		 
		 if(pattern == 1) {
			 if (buttons[0][1].getValue()==-1 || buttons[0][1].getValue()==-1) {
				 buttons[1][1].setText("O");
		         buttons[1][1].setForeground(Color.black);
		         buttons[1][1].setValue(1);
		         buttons[1][1].setState(true);
		         pattern = 3;  
			 	}
			 else if(buttons[2][2].getValue()==0) {
				 buttons[2][2].setText("O");
		         buttons[2][2].setForeground(Color.black);
		         buttons[2][2].setValue(1);
		         buttons[2][2].setState(true); 
		         pattern = 5;
			 }
			 else {
				 buttons[2][0].setText("O");
		         buttons[2][0].setForeground(Color.black);
		         buttons[2][0].setValue(1);
		         buttons[2][0].setState(true);  
				 pattern=4;
				 
			 }

			 return;	 
	   	 }
		 
		 if(pattern == 3) {
			 
			 if(buttons[2][2].getValue()==0) {
				 buttons[2][2].setText("O");
		         buttons[2][2].setForeground(Color.black); ////win
		         buttons[2][2].setValue(1);
		         buttons[2][2].setState(true); 
			 }
			 
			 if (buttons[0][1].getValue()==-1) {
				 buttons[2][0].setText("O");
		         buttons[2][0].setForeground(Color.black);
		         buttons[2][0].setValue(1);
		         buttons[2][0].setState(true);  
				 pattern=6; 
			 }
			 else {
				 buttons[0][2].setText("O");
		         buttons[0][2].setForeground(Color.black);
		         buttons[0][2].setValue(1);
		         buttons[0][2].setState(true); 
		         pattern = 7; 
			 }
			 return;
		 }
		 
		 if(pattern == 6) {
			 if (buttons[1][0].getValue()==0) {
				 buttons[1][0].setText("O");
		         buttons[1][0].setForeground(Color.black);       /////Win
		         buttons[1][0].setValue(1);
		         buttons[1][0].setState(true);  
				  
			 }
			 else {
				 buttons[0][2].setText("O");
		         buttons[0][2].setForeground(Color.black);       /////Win
		         buttons[0][2].setValue(1);
		         buttons[0][2].setState(true); 
				 
			 }
			 return;
		 }
		 
		 if(pattern == 7) {
			 if (buttons[2][0].getValue()==0) {
				 buttons[2][0].setText("O");
		         buttons[2][0].setForeground(Color.black);       /////Win
		         buttons[2][0].setValue(1);
		         buttons[2][0].setState(true);  
				  
			 }
			 else {
				 buttons[0][1].setText("O");
		         buttons[0][1].setForeground(Color.black);       /////Win
		         buttons[0][1].setValue(1);
		         buttons[0][1].setState(true); 
				 
			 }
			 return;
		 }
		 
		 
		 
		 if(pattern == 4) {
			 if (buttons[1][0].getValue()==0) {
				 buttons[1][0].setText("O");
		         buttons[1][0].setForeground(Color.black);       /////Win
		         buttons[1][0].setValue(1);
		         buttons[1][0].setState(true);  
				  
			 }
			 else {
				 buttons[0][2].setText("O");
		         buttons[0][2].setForeground(Color.black);      
		         buttons[0][2].setValue(1);
		         buttons[0][2].setState(true); 
				 pattern = 8;
			 }
			 return;
		 }
		 
		 if(pattern == 8) {
			 if (buttons[1][1].getValue()==0) {
				 buttons[1][1].setText("O");
		         buttons[1][1].setForeground(Color.black);       /////Win
		         buttons[1][1].setValue(1);
		         buttons[1][1].setState(true);  
				  
			 }
			 else {
				 buttons[0][1].setText("O");
		         buttons[0][1].setForeground(Color.black);       /////Win
		         buttons[0][1].setValue(1);
		         buttons[0][1].setState(true); 
				 
			 }
			 return;
		 }
		 
		 if(pattern == 5) {
			 if (buttons[1][0].getValue()==-1 || buttons[0][1].getValue()==-1 || buttons[1][2].getValue()==-1 || buttons[2][1].getValue()==-1 ) {
				 pattern=20;   
			 }
			 else if(buttons[0][2].getValue()==0) {
				 buttons[0][2].setText("O");
		         buttons[0][2].setForeground(Color.black);      
		         buttons[0][2].setValue(1);
		         buttons[0][2].setState(true); 
				 pattern = 20;
				 return;
			 }
			 else  {
				 buttons[2][0].setText("O");
		         buttons[2][0].setForeground(Color.black);      
		         buttons[2][0].setValue(1);
		         buttons[2][0].setState(true); 
				 pattern = 20;
				 return;
			 }
 
		 }
		 
		
		 
		 
		 if(pattern == 20) {
			 
			 
			 int randx=0,randy=0,j=0;
			 
			 int diagSum1 = 0;
		        int diagSum2 = 0;
		        int colSum = 0;
		        int rowSum = 0;
		   
		        //////////////////just edited version of check win method checks if ai can win if so it places down and wins	
		        
		        diagSum1 = buttons[0][2].getValue() + buttons[1][1].getValue() + buttons[2][0].getValue();
		        diagSum2 = buttons[0][0].getValue() + buttons[1][1].getValue() + buttons[2][2].getValue();

		        if(diagSum2 == 2 ) {
		        	
		        	
			            for(int y = 0; y<3; y++) {
		        	if (buttons[y][y].getValue()==0) {
						 buttons[y][y].setText("O");
				         buttons[y][y].setForeground(Color.black);      
				         buttons[y][y].setValue(1);
				         buttons[y][y].setState(true);  
				         j=1;
				         return;
					 }
			            
			            }    
		        }
		        
		        if(diagSum1 == 2) {
		        	   
				        	if (buttons[0][2].getValue()==0) {
								 buttons[0][2].setText("O");
						         buttons[0][2].setForeground(Color.black);      
						         buttons[0][2].setValue(1);
						         buttons[0][2].setState(true);  
						         j=1;
						         
							 }
				        	if (buttons[1][1].getValue()==0) {
								 buttons[1][1].setText("O");
						         buttons[1][1].setForeground(Color.black);      
						         buttons[1][1].setValue(1);
						         buttons[1][1].setState(true);  
						         j=1;
						         
							 }
				        	if (buttons[2][0].getValue()==0) {
								 buttons[2][0].setText("O");
						         buttons[2][0].setForeground(Color.black);      
						         buttons[2][0].setValue(1);
						         buttons[2][0].setState(true);  
						         j=1;
						         
							 }
				        	return;
		        }

		        
		        
		        for(int x = 0; x<3; x++) {
		            for(int y = 0; y<3; y++) {
		                rowSum += buttons[x][y].getValue(); 
		                colSum += buttons[y][x].getValue();
		            }
		            
		            if (colSum == 2 && j == 0) {    
		            	
		            
		            	  for(int y = 0; y<3; y++) {
		  		        	if (buttons[y][x].getValue()==0) {
		  						 buttons[y][x].setText("O");
		  				         buttons[y][x].setForeground(Color.black);      
		  				         buttons[y][x].setValue(1);
		  				         buttons[y][x].setState(true);  
		  				         j=1;
		  				       return;
		  					 }
		            	  }
		            }
		            else if(rowSum == 2  && j == 0) {
		            	for(int y = 0; y<3; y++) {
		  		        	if (buttons[x][y].getValue()==0) {
		  						 buttons[x][y].setText("O");
		  				         buttons[x][y].setForeground(Color.black);      
		  				         buttons[x][y].setValue(1);
		  				         buttons[x][y].setState(true);  
		  				         j=1;
		  				       return;
		  					 }
		            	  }
		            }
		            rowSum = 0;
		            colSum = 0;
		        }
		        
		        
		        
		        
		        /////////////same as above except checks if plays about to win then blocks
		        
		        for(int x = 0; x<3; x++) {
		            for(int y = 0; y<3; y++) {
		                rowSum += buttons[x][y].getValue(); 
		                colSum += buttons[y][x].getValue();
		            }
		            
		            if ( colSum == -2 && j == 0) {    
		            	
		            
		            	  for(int y = 0; y<3; y++) {
		  		        	if (buttons[y][x].getValue()==0) {
		  						 buttons[y][x].setText("O");
		  				         buttons[y][x].setForeground(Color.black);      
		  				         buttons[y][x].setValue(1);
		  				         buttons[y][x].setState(true);  
		  				         j=1;
		  				       return;
		  					 }
		            	  }
		            }
		            else if( rowSum == -2 && j == 0) {
		            	for(int y = 0; y<3; y++) {
		  		        	if (buttons[x][y].getValue()==0) {
		  						 buttons[x][y].setText("O");
		  				         buttons[x][y].setForeground(Color.black);      
		  				         buttons[x][y].setValue(1);
		  				         buttons[x][y].setState(true);  
		  				         j=1;
		  				       return;
		  					 }
		            	  }
		            }
		            rowSum = 0;
		            colSum = 0;
		        }
			 	 
		       
		        
		        
			 
		        if(diagSum1 == -2 ) {
		        	   
		        	if (buttons[0][2].getValue()==0) {
						 buttons[0][2].setText("O");
				         buttons[0][2].setForeground(Color.black);      
				         buttons[0][2].setValue(1);
				         buttons[0][2].setState(true);  
				         j=1;
				         
					 }
		        	if (buttons[1][1].getValue()==0) {
						 buttons[1][1].setText("O");
				         buttons[1][1].setForeground(Color.black);      
				         buttons[1][1].setValue(1);
				         buttons[1][1].setState(true);  
				         j=1;
				         
					 }
		        	if (buttons[2][0].getValue()==0) {
						 buttons[2][0].setText("O");
				         buttons[2][0].setForeground(Color.black);      
				         buttons[2][0].setValue(1);
				         buttons[2][0].setState(true);  
				         j=1;
				         
					 }
		        	return;
        }
			 
			 
			 
			 
			 
			 
			 
			 
			 if( diagSum2 == -2) {
		        	
		        	
		            for(int y = 0; y<3; y++) {
	        	if (buttons[y][y].getValue()==0) {
					 buttons[y][y].setText("O");
			         buttons[y][y].setForeground(Color.black);      
			         buttons[y][y].setValue(1);
			         buttons[y][y].setState(true);  
			         j=1;
			         return;
				 }
		            
		            }    
	        }
			 
			 //////////////messes up strategy that ai uses to make it unbeatable
			 
			 if (buttons[2][1].getValue()==0) {
				 buttons[2][1].setText("O");
		         buttons[2][1].setForeground(Color.black);      
		         buttons[2][1].setValue(1);
		         buttons[2][1].setState(true);  
		         j=1;
		         return;
			 }
			 
			 
			 
			 ///////////////random move only really used if play is an idiot
			 
			 while(j == 0) {
				 randx=rand.nextInt(3);
				 randy=rand.nextInt(3);
				 if (buttons[randx][randy].getValue()==0) {
					 buttons[randx][randy].setText("O");
			         buttons[randx][randy].setForeground(Color.black);      
			         buttons[randx][randy].setValue(1);
			         buttons[randx][randy].setState(true);  
			         j++;
				 }
			 }

			 return;
			 
		 }
		  
		 
	 }

	
	public static void main(String args[]) {
		XsandOs theApp = new XsandOs();
	}
	
	
}
