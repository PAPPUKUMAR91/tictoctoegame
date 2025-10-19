import java.util.Scanner;

/**
 * Two-Player Tic-Tac-Toe Game
 * Console-based implementation with full game logic
 */
public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private Scanner scanner;
    
    // Constructor
    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        scanner = new Scanner(System.in);
        initializeBoard();
    }
    
    /**
     * Initialize the board with empty spaces
     */
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }
    
    /**
     * Display the current state of the board
     */
    public void displayBoard() {
        System.out.println("\nCurrent Board:");
        System.out.println("  0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("  ---------");
            }
        }
        System.out.println();
    }
    
    /**
     * Get player input for row and column
     */
    private int[] getPlayerMove() {
        int[] move = new int[2];
        
        while (true) {
            try {
                System.out.print("Player " + currentPlayer + ", enter row (0-2): ");
                move[0] = scanner.nextInt();
                
                System.out.print("Player " + currentPlayer + ", enter column (0-2): ");
                move[1] = scanner.nextInt();
                
                // Validate input bounds
                if (move[0] < 0 || move[0] > 2 || move[1] < 0 || move[1] > 2) {
                    System.out.println("Invalid input! Please enter numbers between 0 and 2.");
                    continue;
                }
                
                // Check if position is already occupied
                if (board[move[0]][move[1]] != ' ') {
                    System.out.println("Position already occupied! Please choose another position.");
                    continue;
                }
                
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter valid numbers.");
                scanner.nextLine(); // Clear the input buffer
            }
        }
        
        return move;
    }
    
    /**
     * Make a move on the board
     */
    public void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
    }
    
    /**
     * Check if the current player has won
     */
    public boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                return true;
            }
        }
        
        // Check diagonals
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Check if the board is full (draw condition)
     */
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Switch to the other player
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    
    /**
     * Get the current player
     */
    public char getCurrentPlayer() {
        return currentPlayer;
    }
    
    /**
     * Reset the game for a new round
     */
    public void resetGame() {
        initializeBoard();
        currentPlayer = 'X';
    }
    
    /**
     * Main game loop
     */
    public void playGame() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Players will alternate turns. Player X goes first.");
        
        while (true) {
            displayBoard();
            
            // Get player move
            int[] move = getPlayerMove();
            makeMove(move[0], move[1]);
            
            // Check for win
            if (checkWin()) {
                displayBoard();
                System.out.println("Congratulations! Player " + currentPlayer + " wins!");
                break;
            }
            
            // Check for draw
            if (isBoardFull()) {
                displayBoard();
                System.out.println("It's a draw! The board is full.");
                break;
            }
            
            // Switch players
            switchPlayer();
        }
        
        // Ask for replay
        askForReplay();
    }
    
    /**
     * Ask if players want to play again
     */
    private void askForReplay() {
        System.out.print("\nDo you want to play again? (y/n): ");
        String response = scanner.next().toLowerCase();
        
        if (response.equals("y") || response.equals("yes")) {
            resetGame();
            playGame();
        } else {
            System.out.println("Thanks for playing Tic-Tac-Toe!");
        }
    }
    
    /**
     * Main method to start the game
     */
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.playGame();
    }
}
