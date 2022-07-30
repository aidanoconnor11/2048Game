package cs;

import java.util.Random;
import java.util.Scanner;

public class Game {
    Scanner in= new Scanner(System.in);
    private final int SIZE= 4;
    private final int WIN_SCORE= 2048;
    private int points;
    private int moves;
    private String move= "", answer= "";

    private int[][] board;
    private boolean running;
    private Random rand= new Random();

    public Game() {
        initBoard();
    }

    public void initBoard() {
        board= new int[SIZE][SIZE];
        populateBoard();
        populateBoard();
        points= 0;
        moves= 0;
    }

    // CLI main game loop **UNUSED IN GUI VERSION**
    public void runCLIGame() {
        running= true;
        initBoard();
        populateBoard();
        printBoard();
        while (running) {
            if (canMove() == true && hasWon() == false) {
                System.out.println("What's your move? ");
                move= in.next();
                if (move.equals("up"))
                    moveUp();
                else if (move.equals("down"))
                    moveDown();
                else if (move.equals("right"))
                    moveRight();
                else
                    moveLeft();
            } else if (hasWon() == true) {
                System.out.println("You win!");
                running= false;
            } else {
                System.out.println("You Lose!");
                running= false;
            }
        }
        System.out.println("Would you like to play again?");
        answer= in.next();
        if (answer.equals("yes"))
            runCLIGame();
    }

    public void printBoard() {
        for (int[] x : board) {
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }

    public boolean populateBoard() {
        if (contains(0)) {
            int value= rand.nextInt(10) == 0 ? 4 : 2; // 90% chance 2, 10% 4
            int row, col;
            do {
                row= rand.nextInt(SIZE);
                col= rand.nextInt(SIZE);
            } while (board[row][col] != 0);

            board[row][col]= value;
            return true;
        }
        return false;
    }

    public boolean contains(int num) // returns true if num is in
    {
        for (int[] x : board) {
            for (int y : x) {
                if (y == num)
                    return true;
            }
        }
        return false;
    }

    
    public void moveUp() 
    {
        boolean validMove= false;
        for (int r= 0; r < SIZE; r++ ) {
            for (int c= 0; c < SIZE; c++ ) {
                if (r > 0) {
                    if (board[r][c] != 0 && board[r][c] == board[r - 1][c] ||
                        board[r][c] != 0 && board[r - 1][c] == 0)
                        validMove= true;
                }
            }
        }
        if (validMove) {
            int row= 0, count= 0;
            for (int r= 0; r < SIZE; r++ ) {
                for (int c= 0; c < SIZE; c++ ) {
                    row= r;
                    count= 0;
                    while (row > 0) {
                        if (board[row - 1][c] == 0 && board[row][c] != 0) {
                            board[row - 1][c]+= board[row][c];
                            board[row][c]= 0;
                        } else if (board[row - 1][c] == board[row][c] && board[row - 1][c] != 0 &&
                            count < 1) {
                                board[row - 1][c]+= board[row][c];
                                board[row][c]= 0;
                                count++ ;
                            }
                        row-- ;
                    }
                }
            }
            moves++ ;
            populateBoard();
            printBoard();
        } else {
            System.out.println("Not a valid move!");
        }
    }

    public void moveDown() {
        boolean validMove= false;
        for (int r= 0; r < SIZE; r++ ) {
            for (int c= 0; c < SIZE; c++ ) {
                if (r < 3) {
                    if (board[r][c] != 0 && board[r][c] == board[r + 1][c] ||
                        board[r][c] != 0 && board[r + 1][c] == 0)
                        validMove= true;
                }
            }
        }
        if (validMove == true) {
            int row= 0, count= 0;
            for (int r= 0; r < SIZE; r++ ) {
                for (int c= 0; c < SIZE; c++ ) {
                    row= r;
                    count= 0;
                    while (row < 3) {
                        if (board[row + 1][c] == 0 && board[row][c] != 0) {
                            board[row + 1][c]+= board[row][c];
                            board[row][c]= 0;
                        } else if (board[row + 1][c] == board[row][c] && board[row + 1][c] != 0 &&
                            count < 1) {
                                board[row + 1][c]+= board[row][c];
                                board[row][c]= 0;
                                count++ ;
                            }
                        row++ ;
                    }
                }
            }
            moves++ ;
            populateBoard();
            printBoard();
        } else {
            System.out.println("Not a valid move!");
        }
    }

    public void moveLeft() {
        boolean validMove= false;
        for (int r= 0; r < SIZE; r++ ) {
            for (int c= 0; c < SIZE; c++ ) {
                if (c > 0) {
                    if (board[r][c] != 0 && board[r][c] == board[r][c - 1] ||
                        board[r][c] != 0 && board[r][c - 1] == 0)
                        validMove= true;
                }
            }
        }
        if (validMove) {
            int col= 0, count= 0;
            for (int r= 0; r < SIZE; r++ ) {
                for (int c= 0; c < SIZE; c++ ) {
                    col= c;
                    count= 0;
                    while (col > 0) {
                        if (board[r][col - 1] == 0 && board[r][col] != 0) {
                            board[r][col - 1]+= board[r][col];
                            board[r][col]= 0;
                        } else if (board[r][col - 1] == board[r][col] && board[r][col] != 0 &&
                            count < 1) {
                                board[r][col - 1]+= board[r][col];
                                board[r][col]= 0;
                                count++ ;
                            }
                        col-- ;
                    }
                }
            }
            moves++ ;
            populateBoard();
            printBoard();
        } else {
            System.out.println("Not a valid move!");
        }
    }

    public void moveRight() {
        boolean validMove= false;
        for (int r= 0; r < SIZE; r++ ) {
            for (int c= 0; c < SIZE; c++ ) {
                if (c < 3) {
                    if (board[r][c] != 0 && board[r][c] == board[r][c + 1] ||
                        board[r][c] != 0 && board[r][c + 1] == 0)
                        validMove= true;
                }

            }
        }
        if (validMove) {
            int col= 0, count= 0;
            for (int r= 0; r < SIZE; r++ ) {
                for (int c= 0; c < SIZE; c++ ) {
                    col= c;
                    count= 0;
                    while (col < 3) {
                        if (board[r][col + 1] == 0 && board[r][col] != 0) {
                            board[r][col + 1]+= board[r][col];
                            board[r][col]= 0;
                        } else if (board[r][col + 1] == board[r][col] && board[r][col] != 0 &&
                            count < 1) {
                                board[r][col + 1]+= board[r][col];
                                board[r][col]= 0;
                                count++ ;
                            }
                        col++ ;
                    }
                }
            }
            moves++ ;
            populateBoard();
            printBoard();
        } else {
            System.out.println("Not a valid move!");
        }
    }

    public boolean canMove() 
    {
        for (int r= 0; r < SIZE; r++ ) {
            for (int c= 0; c < SIZE; c++ ) {
                if (board[r][c] == 0) { return true; }
            }
        }
        return false;
    }

    public boolean hasWon() {
        return contains(WIN_SCORE);
    }

    public int getTile(int r, int c) {
        return board[r][c];
    }

    public int getPoints() {
        return points;
    }

    public int getMoves() {
        return moves;
    }
}
