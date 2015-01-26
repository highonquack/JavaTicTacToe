public class TranslateBoard extends Thread {
    boolean run = true;
    int[][] board;
    String[][] newboard;
    public TranslateBoard(String name, int[][] b, String[][] nb) {
        super(name);
        board = b;
        newboard = nb;
    }
    
    public void run() {
        System.out.println("Run's run()");
        String boardstringcopy = "";
        for(int i = 0; i<3; i++) {
            for(int k = 0; k<3; k++) {
                boardstringcopy = boardstringcopy+Integer.toString(board[i][k]);
            }
        }
        while(run) {
            String boardstring = "";
            for(int i = 0; i<3; i++) {
                for(int k = 0; k<3; k++) {
                    boardstring = boardstring+Integer.toString(board[i][k]);
                }
            }
            if(!boardstringcopy.equals(boardstring)) {
                for(int i = 0; i<3; i++) {
                    for(int k = 0; k<3; k++) {
                        switch(board[i][k]) {
                            case 0:
                            newboard[i][k] = " ";
                            break;
                            case 1:
                            newboard[i][k] = "O";
                            break;
                            case 2: 
                            newboard[i][k] = "X";
                            break;
                        }
                    }
                }
                for(int i = 0; i<3; i++) {
                    for(int k = 0; k<3; k++) {
                        boardstringcopy = boardstring;
                    }
                }
            }
        }
    }
}
