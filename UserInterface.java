import javax.swing.*;

import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.awt.Dimension;
public class UserInterface extends JFrame {
    private TicTacToe ttt = null;
    private JButton oneplayerbutton, twoplayerbutton;
    private JButton uiboard[][];
    private JPanel spanel, boardpanel;
    private JLabel gtlabel;
	private boolean doSettings;
	private GridBagConstraints gpgbc, c, rc;
	private Dimension d;
	private int winner;
    public UserInterface(boolean ds) {
        super("Tic Tac Toe");
		doSettings = ds;
        init();
        
        this.pack();
        this.setVisible(true);
    }
    public UserInterface(TicTacToe t) {
        super("Tic Tac Toe");
        ttt = t;
        doSettings = false;
        init();
        
        this.pack();
        this.setVisible(true);
    }
    
    void init() {
		if(doSettings) {
            //SETTINGS MENU
            spanel = new JPanel();
            oneplayerbutton = new JButton("1 Player");
            twoplayerbutton = new JButton("2 Players");
            oneplayerbutton.addActionListener(new PlayerOneButtonListener());
            twoplayerbutton.addActionListener(new PlayerTwoButtonListener());
            spanel.add(oneplayerbutton);
            spanel.add(twoplayerbutton);
            this.add(spanel);
        } else {
            //GAME
            System.out.println("I work");
			JPanel toppanel = new JPanel();
			String gtlabeltext = "";
			if(ttt.getGameType()==0) {
			    gtlabeltext = "Player's Turn";
			} else {
			    gtlabeltext = "Player X's Turn";
			}
			gtlabel = new JLabel(gtlabeltext);
			gtlabel.setFont(gtlabel.getFont().deriveFont(64.0f));
			toppanel.add(gtlabel);
			uiboard = new JButton[3][3];
			boardpanel = new JPanel(new GridBagLayout());
			c = new GridBagConstraints();
			//c.fill = GridBagConstraints.HORIZONTAL;
			for(int i=0; i<3; i++) {
			    for(int k=0; k<3; k++) {
			        c.gridx = i;
			        c.gridy = k;
			        uiboard[i][k] = new JButton("");
			        d = new Dimension(100,100);
			        uiboard[i][k].setPreferredSize(d);
			        uiboard[i][k].setMinimumSize(d);
			        uiboard[i][k].addActionListener(new MoveButtonListener(i,k));
			        boardpanel.add(uiboard[i][k], c);
			    }
			}
			gpgbc = new GridBagConstraints();
			JPanel gamepanel = new JPanel(new GridBagLayout());
			gpgbc.fill = GridBagConstraints.HORIZONTAL;
			gpgbc.gridx = 0;
			gpgbc.gridy = 0;
			gamepanel.add(toppanel, gpgbc);
			gpgbc.gridy = 1;
			gamepanel.add(boardpanel, gpgbc);
			this.add(gamepanel);
			//End Creation of initial screen
			
		}
    }
    public void moveToGame() {
        this.setVisible(false);
        this.dispose();
        UserInterface gameui = new UserInterface(ttt);
    }
    public TicTacToe getTTT() {
        return ttt;
    }
    public void replaceButton(int r, int c, int player) {
        boardpanel.remove(uiboard[r][c]);
        rc = new GridBagConstraints();
        rc.fill = GridBagConstraints.HORIZONTAL;
        rc.gridx = r;
        rc.gridy = c;
        String jlabeltext;
        if(player==2) {
            jlabeltext = "X";
        } else {
            jlabeltext = "O";
        }
        JLabel playermark = new JLabel(jlabeltext);
        playermark.setFont(playermark.getFont().deriveFont(80.0f));
        d = new Dimension(100,100);
	    playermark.setPreferredSize(d);
        playermark.setMinimumSize(d);
        boardpanel.add(playermark, rc);
        this.repaint();
        this.revalidate();
    }
    public void switchGtlabel(int turn) {
        int gametype = ttt.getGameType();
        if(gametype == 1) {
            if(turn == ttt.X) {
                gtlabel.setText("Player O's Turn");
            } else {
                gtlabel.setText("Player X's Turn");
            }
        }
    }
    public void doWin() {
        String winnerguy;
        if (winner==ttt.X) {
            winnerguy = "X";
        } else {
            winnerguy = "O";
        }
        JOptionPane.showMessageDialog(this, "Player "+winnerguy+" wins!");
        System.exit(0);
    }
    public void refreshPanel() {
        this.repaint();
        this.revalidate();
    }
    class MoveButtonListener implements ActionListener {
        int row;
        int col;
        public MoveButtonListener(int r, int c) {
            row = r;
            col = c;
        }
        public void actionPerformed(ActionEvent e) {
            int turn = ttt.addMove(row,col);
            replaceButton(row,col,turn);
            switchGtlabel(turn);
            if(ttt.checkWin()) {
                winner = ttt.getWinner();
                doWin();
            }
            refreshPanel();
        }
    }
    class PlayerOneButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ttt = new TicTacToe(0);
            moveToGame();
        }
    }
    class PlayerTwoButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ttt = new TicTacToe(1);
            moveToGame();
        }
    }
}
