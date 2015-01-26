import java.util.*;
public class TicTacToe {
    public int board[][];
    //0 is 1 Player, 1 is 2 Players
    private int gametype;
    public String[][] readableBoard;
    public static final int X = 2;
    public static final int O = 1;
    private int turn;
    private int winner;
	private ArtificialIntelligence ai;
    
    public TicTacToe(int gt) {
        gametype = gt;
		if(gametype==0) {
			ai = new ArtificialIntelligence(board, this);
		}
        turn = X;
        board = new int[3][3];
        readableBoard = new String[3][3];
    }
    
    public int addMove(int r, int c) {
        boolean theReturn = true;
        int num = turn;
        if(board[r][c]==0) {
            board[r][c] = turn; 
            if(gametype==0) {
			    ai.makeMove();
			}
            if(turn==X) {
                turn=O;
            } else {
                turn=X;
            }
        } else {
            num = 0;
        }
        return num;
    }
    public boolean checkWin() {
        boolean won = false;
        int inarowX[] = {0,0,0};
        int inacolumnX[] = {0,0,0};
        int inarowO[] = {0,0,0};
        int inacolumnO[] = {0,0,0};
        int inforwardslashX = 0;
        int inforwardslashO = 0;
        int inbackwardslashX = 0;
        int inbackwardslashO = 0;
        for(int i=0; i<3; i++) {
            for(int k=0; k<3; k++) {
                if(board[i][k]==X) {
                    inarowX[i]++;
                    inacolumnX[k]++;
                } else if(board[i][k]==O) {
                    inarowO[i]++;
                    inacolumnO[k]++;
                }
            }
            if(board[i][i]==X) {
                inforwardslashX++;
            } else if(board[i][i]==O) {
                inforwardslashO++;
            }
            if(board[i][(2-i)]==X) {
                inbackwardslashX++;
            } else if(board[i][(2-i)]==O) {
                inbackwardslashO++;
            }
        }
        for(int i = 0; i<3; i++) {
            if(inarowX[i]==3 || inacolumnX[i]==3 || inforwardslashX==3 || inbackwardslashX==3) {
                won = true;
                winner = X;
            } else if(inarowO[i]==3 || inacolumnO[i]==3 || inforwardslashO==3 || inbackwardslashO==3) {
                won = true;
                winner = O;
            }
        }
        return won;
    }
	public boolean checkBoardFull() {
		boolean theReturn = false;
		int fullamount = 0;
		for(int i=0; i<3; i++) {
			for(int k=0; k<3; k++) {
				if(board[i][k]!=0) {
					fullamount++;
				}
			}
		}
		if(fullamount==9) {
			theReturn=true;
		}
		return theReturn;
	}
    public int getWinner() {
        return winner;
    }
    public int getTurn() {
        return turn;
    }
    public int getGameType() {
        return gametype;
    }
}
/*private class AI {
    AI()
}*/
