import java.util.*;

public class Sudoku {
    //set a 2D array sudoku board to be solved
    public static int[][] solveSudokuBoard = {
            {9,0,0,1,0,0,0,0,5},
            {0,0,5,0,9,0,2,0,1},
            {8,0,0,0,4,0,0,0,0},
            {0,0,0,0,8,0,0,0,0},
            {0,0,0,7,0,0,0,0,0},
            {0,0,0,0,2,6,0,0,9},
            {2,0,0,3,0,0,0,0,6},
            {0,0,0,2,0,0,9,0,0},
            {0,0,1,9,0,4,5,7,0},
    };
    //initialize variables
    private int[][] board;
    public static final int AVAIL=0;
    public static final int SIZE=9;

    public Sudoku(int[][] board1){
        this.board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                this.board[i][j] = board1[i][j];
            }
        }

    }

    //method that checks if row has a duplicate num
    public boolean checkRow (int row, int num) {
        //checks each cell in board's row
        for (int i = 0; i < SIZE; i++) {
            //if the value we attempt to input in that row is present, return true
            if (board[row][i] == num) {
                return true;
            }
        }
        //else, return false
        return false;
    }
    //method that checks if column has a duplicate num
    public boolean checkCol (int col, int num) {
        //checks each cell in board's col
        for (int j = 0; j < SIZE; j++) {
            //if the value we attempt to input in that col is present, return true
            if (board[j][col] == num) {
                return true;
            }
        }
        //else, return false
        return false;
    }
    //method that checks if grid has a duplicate num
    public boolean checkGrid (int row, int col, int num) {
        int gridRow = row - row % 3;
        int gridCol = col - col % 3;
        //check each cell in each grid(9)
        for (int i = gridRow; i < gridRow + 3; i++) {
            for (int j = gridCol; j < gridCol + 3; j++) {
                //if the value we attempt to input in that grid NOT present, return true
                if (board[i][j] == num) {
                    return true;
                }
            }
        }
        //else, return false
        return false;
    }
    //method checks if cell is empty and safe to put a value
    private boolean isSafe (int row, int col, int num){
        return !(checkRow(row, num) || checkCol(col, num) || checkGrid(row, col, num));
    }
    //method to solve the board
    public boolean solve(){
        for (int row = 0; row < SIZE; row++){
            for (int col = 0; col < SIZE; col++){
                if (board[row][col] == AVAIL){
                    for (int num = 1; num < SIZE; num++){
                        if (isSafe(row, col, num)){
                            board[row][col] = num;

                            if (solve()){
                                return true;
                            } else{
                                board[row][col] = AVAIL;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public void printBoard(){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                System.out.print(" "+board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args){
        Sudoku sudoku = new Sudoku(solveSudokuBoard);
        sudoku.printBoard();
        sudoku.solve();
        sudoku.printBoard();
//        if (sudoku.solve()){
//            sudoku.printBoard();
//        } else{
//            System.out.println("abcde");
//        }

    }
}
