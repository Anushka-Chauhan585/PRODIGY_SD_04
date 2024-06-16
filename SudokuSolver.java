import java.util.Scanner;

public class SudokuSolver {
    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        int[][] board = new int[GRID_SIZE][GRID_SIZE];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Sudoku puzzle to solve (use 0 for empty cells):");
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                board[row][col] = scanner.nextInt();
            }
        }

        System.out.println("Unsolved Sudoku Puzzle:");
        printBoard(board);

        if (solveBoard(board)) {
            System.out.println("\nSudoku puzzle solved successfully!");
        } else {
            System.out.println("\nUnsolvable Sudoku puzzle.");
        }

        System.out.println("Solved Sudoku Puzzle:");
        printBoard(board);

        scanner.close();
    }

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidPlacement(board, numberToTry, row, col)) {
                            board[row][col] = numberToTry;

                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int col) {
        return !isNumberInRow(board, number, row) &&
               !isNumberInColumn(board, number, col) &&
               !isNumberInBox(board, number, row, col);
    }

    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int col) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int row, int col) {
        int localBoxRow = row - row % 3;
        int localBoxCol = col - col % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxCol; j < localBoxCol + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }
}
