import java.util.*;

public class Sudoku {

    //initialize variables
    private int[][] board;
    public static final int AVAIL=0;
    public static final int SIZE=9;

    public Sudoku(int[][] board){
        this.board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                this.board[i][j] = board[i][j];
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
                //search for empty (0) cell
                if (board[row][col] == AVAIL){
                    //attempt to input all possible numbers
                    for (int num = 1; num <= SIZE; num++){
                        //number input is valid
                        if (isSafe(row, col, num)){
                            board[row][col] = num;
                            //backtracking recursively
                            if (solve()){
                                return true;
                            //set cell to empty (0) and continue
                            } else{
                                board[row][col] = AVAIL;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        //board solved
        return true;
    }
    //method to print board
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
        //get partially filled user input for sudoku board
        System.out.println("Enter number per cell.\nClick on the space bar to type next number.\nClick on enter after inputting 9 values in a row\n90 Input missing cells as '0'");
        Scanner scan = new Scanner(System.in);
        int[][] solveSudokuBoard = new int[SIZE][SIZE];
        for (int row = 0; row < solveSudokuBoard.length; row++){
            for (int col = 0; col < solveSudokuBoard[row].length; col++){
                solveSudokuBoard[row][col] = scan.nextInt();
            }
        }

        Sudoku sudoku = new Sudoku(solveSudokuBoard);
        System.out.println();
        System.out.println("Partially filled board entered by user:");
        sudoku.printBoard();
        if (sudoku.solve()){
            System.out.println();
            System.out.println("Sudoku board solved:");
            sudoku.printBoard();
        } else{
            System.out.println("Unsolvable");
        }
    }
}

