import java.util.*;
public class ArtificialIntelligence {
    private int board[][];
    private TicTacToe ttt;
    public ArtificialIntelligence(int[][] b, TicTacToe t) {
        board = b;
        ttt = t;
    }
    public void makeMove() {
        List<int[]> movecoords = new ArrayList<int[]>();
        for(int i = 0; i<3; i++) {
            int indexcol = 0;
            int indexrow = 0;
            int amountc = 0;
            int amountr = 0;
            for(int k = 0; k<3; k++) {
                if(board[i][k]==ttt.X) {
                    indexcol+=k;
                    amountc++;
                }
                if(board[k][i]==ttt.X) {
                    indexrow+=i;
                    amountr++;
                }
            }
            if(amountc==2) {
                int[] coords = new int[2];
                coords[0] = i;
                coords[1] = 3-indexcol;
                movecoords.add(coords);
            }
            if(amountr==2) {
                int[] coords = new int[2];
                coords[0] = 3-indexcol;
                coords[1] = i;
                movecoords.add(coords);
            }
        }
        if(movecoords.size()==0) {
            int[][] goodcoords = {{0,0},{0,2},{2,2},{2,0}};
            int[][] othercoords = {{0,1},{1,0},{1,1},{1,2},{2,1}};
            int done = 0;
            int i = 0;
            for(i=0; done==0 || i==5; i++) {
                done = ttt.addMove(goodcoords[i][0],goodcoords[i][1]);
            }
            if(i==4 && done==0) {
                for(int k=0; done==0; k++) {
                    done = ttt.addMove(othercoords[i][0],othercoords[i][1]);
                }
            }
        } else {
            Random rand = new Random();
            int[] coords = movecoords.get(rand.nextInt(movecoords.size()));
            ttt.addMove(coords[0],coords[1]);
        }
    }
}
