import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class App implements ActionListener{
    
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel txt = new JLabel();
    JLabel title = new JLabel();
    int[] board = new int[9];
    JButton[] buttons = new JButton[9];
    boolean player_turn; //True if player 1's turn, False if player 2's turn

    App(){
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        txt.setBackground(Color.WHITE);
        txt.setForeground(Color.BLACK);
        title.setFont(new Font("Serif",Font.BOLD , 80));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setText("TicTacToe");
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);
        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(Color.red);

        txt.setFont(new Font("Serif",Font.BOLD , 80));
        txt.setHorizontalAlignment(JLabel.CENTER);
        txt.setOpaque(true);
        for(int i=0;i<9;i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Serif",Font.ITALIC, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }
        // Add txt and title to title_panel and title_panel to frame
        title_panel.add(txt);
        title_panel.add(title, BorderLayout.NORTH);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);
        firstTurn();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<9;i++) {
            if (e.getSource()==buttons[i]) {
                if (player_turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(Color.RED);
                        buttons[i].setText("X");
                        board[i] = 1;
                        player_turn = false;
                        txt.setText("O Turn");
                        checkWin();                   }
                }
                else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(Color.MAGENTA);
                        buttons[i].setText("O");
                        board[i] = -1;
                        player_turn = true;
                        txt.setText("X Turn");
                        checkWin();                    }           
                }
            }
        }
    }
    public void firstTurn(){
        if(random.nextInt(2)==0) {
            player_turn = true;
            txt.setText("X Turn");
        } 
        else {
            player_turn = false;
            txt.setText("O Turn");
        }
    }
    public void checkWin(){
        // Rows Check 
        for (int i=0;i<9;i += 3) {
            if ((board[i] + board[i + 1] + board[i + 2]) == 3) {
              xWins(i, i+1 , i+2);
              return;  
            }
            if ((board[i] + board[i + 1] + board[i + 2]) == -3) {
                oWins(i, i+1 , i+2);
                return;
            }
        }
        // Coloumns Check
        for (int i = 0; i < 3; i++) {
            if ((board[i] + board[i + 3] + board[i + 6]) == 3) {
                xWins(i, i+3 , i+6);
                return;
            }
            if ((board[i] + board[i + 3] + board[i + 6]) == -3) {
                oWins(i, i+3 , i+6);
                return;    
            }
        }
        // Diagonal Check
        if ((board[0] + board[4] + board[8]) == 3) {
            xWins(0, 4, 8);
            return;
        }
        if ((board[0] + board[4] + board[8]) == -3) {
           oWins(0, 4, 8);
           return; 
        }
        if ((board[2] + board[4] + board[6]) == 3) {
            xWins(2, 4, 6);
            return;
        }
        if ((board[2] + board[4] + board[6]) == -3) {
            oWins(2, 4, 6);
            return;   
        }
        // Tie Check
        boolean tie = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) {
                tie = false;
                break;
            }
        }
        if (tie) {
            txt.setText("Tie Game!");
            for (JButton button : buttons) {
                button.setEnabled(false);
            }
        }
    }
    public void xWins(int a, int b, int c){
        System.out.println("X wins");
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        txt.setText("X WINS!!");
        txt.setForeground(Color.BLUE);
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
    public void oWins(int a, int b, int c){
        System.out.println("O wins");
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        txt.setText("O WINS!!");
        txt.setForeground(Color.BLUE);
        for (JButton button : buttons) {
            button.setEnabled(false);

        }
    }
}
