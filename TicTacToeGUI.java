import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI-based Two-Player Tic-Tac-Toe Game using Java Swing
 */
public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private char currentPlayer;
    private JLabel statusLabel;
    private JButton resetButton;
    private boolean gameOver;
    private String playerXName;
    private String playerOName;
    
    // Constructor
    public TicTacToeGUI() {
        currentPlayer = 'X';
        gameOver = false;
        getPlayerNames();
        initializeGUI();
    }
    
    /**
     * Get player names from user input
     */
    private void getPlayerNames() {
        JDialog nameDialog = new JDialog(this, "Enter Player Names", true);
        nameDialog.setSize(400, 300);
        nameDialog.setLocationRelativeTo(null);
        nameDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255)); // Light blue background
        
        // Title
        JLabel titleLabel = new JLabel("<html><center><h2>🎮 Welcome to Tic-Tac-Toe! 🎮</h2>" +
            "<h3>Enter Player Names</h3></center></html>");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
        titleLabel.setForeground(new Color(25, 25, 112)); // Dark blue
        
        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 10, 10));
        inputPanel.setBackground(new Color(240, 248, 255));
        
        JLabel playerXLabel = new JLabel("Player X Name:");
        playerXLabel.setFont(new Font("Arial", Font.BOLD, 14));
        playerXLabel.setForeground(new Color(255, 0, 0)); // Red
        
        JTextField playerXField = new JTextField("Player X");
        playerXField.setFont(new Font("Arial", Font.PLAIN, 14));
        playerXField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLoweredBevelBorder(),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        JLabel playerOLabel = new JLabel("Player O Name:");
        playerOLabel.setFont(new Font("Arial", Font.BOLD, 14));
        playerOLabel.setForeground(new Color(0, 0, 255)); // Blue
        
        JTextField playerOField = new JTextField("Player O");
        playerOField.setFont(new Font("Arial", Font.PLAIN, 14));
        playerOField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLoweredBevelBorder(),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        inputPanel.add(playerXLabel);
        inputPanel.add(playerXField);
        inputPanel.add(playerOLabel);
        inputPanel.add(playerOField);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255));
        
        JButton startButton = new JButton("🚀 START GAME");
        startButton.setFont(new Font("Arial Black", Font.BOLD, 14));
        startButton.setBackground(new Color(0, 255, 0)); // Green
        startButton.setForeground(Color.WHITE);
        startButton.setBorder(BorderFactory.createRaisedBevelBorder());
        startButton.setPreferredSize(new Dimension(150, 40));
        
        startButton.addActionListener(e -> {
            playerXName = playerXField.getText().trim();
            playerOName = playerOField.getText().trim();
            
            // Set default names if empty
            if (playerXName.isEmpty()) playerXName = "Player X";
            if (playerOName.isEmpty()) playerOName = "Player O";
            
            nameDialog.dispose();
        });
        
        buttonPanel.add(startButton);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        nameDialog.add(panel);
        nameDialog.setVisible(true);
    }
    
    /**
     * Initialize the GUI components
     */
    private void initializeGUI() {
        setTitle("Tic-Tac-Toe Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create the game board panel with attractive styling
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3, 8, 8));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        boardPanel.setBackground(new Color(20, 20, 40)); // Dark blue background
        
        // Initialize buttons with modern gradient colors
        buttons = new JButton[3][3];
        Color[] buttonColors = {
            new Color(255, 105, 180), // Hot Pink
            new Color(0, 191, 255),    // Deep Sky Blue
            new Color(50, 205, 50),   // Lime Green
            new Color(255, 165, 0),   // Orange
            new Color(138, 43, 226),  // Blue Violet
            new Color(255, 20, 147),  // Deep Pink
            new Color(0, 255, 255),   // Cyan
            new Color(255, 69, 0),    // Red Orange
            new Color(124, 252, 0)    // Lawn Green
        };
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Impact", Font.BOLD, 52));
                buttons[i][j].setPreferredSize(new Dimension(130, 130));
                buttons[i][j].setBackground(buttonColors[i * 3 + j]);
                buttons[i][j].setForeground(Color.WHITE);
                buttons[i][j].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createRaisedBevelBorder(),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)
                ));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setOpaque(true);
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }
        
        // Create status panel with modern styling
        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(new Color(30, 30, 30)); // Dark background
        statusPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        
        statusLabel = new JLabel("🎯 " + playerXName + "'s Turn", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
        statusLabel.setForeground(new Color(255, 255, 0)); // Bright Yellow
        
        resetButton = new JButton("🔄 NEW GAME");
        resetButton.setFont(new Font("Arial Black", Font.BOLD, 18));
        resetButton.setBackground(new Color(255, 0, 128)); // Magenta
        resetButton.setForeground(Color.WHITE);
        resetButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        resetButton.setPreferredSize(new Dimension(180, 50));
        resetButton.addActionListener(e -> {
            // Ask if user wants to change names
            int option = JOptionPane.showConfirmDialog(this, 
                "Do you want to change player names?", 
                "New Game Options", 
                JOptionPane.YES_NO_OPTION);
            
            if (option == JOptionPane.YES_OPTION) {
                getPlayerNames();
            }
            resetGame();
        });
        
        statusPanel.add(statusLabel);
        statusPanel.add(resetButton);
        
        // Add components to frame
        add(boardPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
        
        // Set frame properties
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        // Enable minimize functionality
        setExtendedState(JFrame.NORMAL);
        setState(JFrame.NORMAL);
    }
    
    /**
     * Handle button clicks
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            return;
        }
        
        JButton clickedButton = (JButton) e.getSource();
        
        // Find which button was clicked
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == clickedButton) {
                    if (buttons[i][j].getText().equals("") || buttons[i][j].getText().trim().isEmpty()) {
                        makeMove(i, j);
                        return;
                    }
                }
            }
        }
    }
    
    /**
     * Make a move on the board
     */
    private void makeMove(int row, int col) {
        // Debug: Print move info
        System.out.println("Making move: Player " + currentPlayer + " at (" + row + "," + col + ")");
        
        buttons[row][col].setText(String.valueOf(currentPlayer));
        buttons[row][col].setForeground(currentPlayer == 'X' ? 
            new Color(255, 255, 0) : new Color(0, 255, 255)); // Bright Yellow for X, Cyan for O
        buttons[row][col].setEnabled(false); // Disable clicked button
        
        // Add hover effect
        buttons[row][col].setBackground(buttons[row][col].getBackground().darker());
        
        // Check for win
        if (checkWin(row, col)) {
            gameOver = true;
            String winnerName = (currentPlayer == 'X') ? playerXName : playerOName;
            statusLabel.setText("🎉 CONGRATULATIONS! " + winnerName + " WINS! 🎉");
            statusLabel.setForeground(new Color(255, 0, 0)); // Bright Red
            highlightWinningCells(row, col);
            disableAllButtons();
            showCongratulationDialog();
            return;
        }
        
        // Check for draw
        if (isBoardFull()) {
            gameOver = true;
            statusLabel.setText("IT'S A DRAW!");
            statusLabel.setForeground(new Color(255, 165, 0)); // Orange
            disableAllButtons();
            showDrawDialog();
            return;
        }
        
        // Switch players
        switchPlayer();
        String currentPlayerName = (currentPlayer == 'X') ? playerXName : playerOName;
        statusLabel.setText("🎯 " + currentPlayerName + "'s Turn");
        statusLabel.setForeground(currentPlayer == 'X' ? Color.RED : Color.BLUE);
    }
    
    /**
     * Disable all buttons when game is over
     */
    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
    
    /**
     * Show congratulation dialog with bomb blast effect
     */
    private void showCongratulationDialog() {
        // Create bomb blast effect
        createBombBlastEffect();
        
        // Show congratulation dialog
        SwingUtilities.invokeLater(() -> {
            JDialog dialog = new JDialog(this, "🎉 WINNER! 🎉", true);
            dialog.setSize(400, 300);
            dialog.setLocationRelativeTo(this);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBackground(new Color(255, 215, 0)); // Gold background
            
            // Congratulation message
            String winnerName = (currentPlayer == 'X') ? playerXName : playerOName;
            JLabel congratsLabel = new JLabel("<html><center><h1>🎉 CONGRATULATIONS! 🎉</h1>" +
                "<h2>" + winnerName + " WINS!</h2>" +
                "<h3>💥 BOMB BLAST EFFECT! 💥</h3>" +
                "<p>Amazing Game Play!</p></center></html>");
            congratsLabel.setHorizontalAlignment(JLabel.CENTER);
            congratsLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
            congratsLabel.setForeground(new Color(255, 0, 0)); // Red text
            
            // Play Again button
            JButton playAgainBtn = new JButton("🎮 PLAY AGAIN");
            playAgainBtn.setFont(new Font("Arial Black", Font.BOLD, 14));
            playAgainBtn.setBackground(new Color(0, 255, 0)); // Green
            playAgainBtn.setForeground(Color.WHITE);
            playAgainBtn.addActionListener(e -> {
                dialog.dispose();
                resetGame();
            });
            
            // Close button
            JButton closeBtn = new JButton("CLOSE");
            closeBtn.setFont(new Font("Arial Black", Font.BOLD, 14));
            closeBtn.setBackground(new Color(255, 0, 0)); // Red
            closeBtn.setForeground(Color.WHITE);
            closeBtn.addActionListener(e -> dialog.dispose());
            
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(playAgainBtn);
            buttonPanel.add(closeBtn);
            buttonPanel.setBackground(new Color(255, 215, 0));
            
            panel.add(congratsLabel, BorderLayout.CENTER);
            panel.add(buttonPanel, BorderLayout.SOUTH);
            
            dialog.add(panel);
            dialog.setVisible(true);
        });
    }
    
    /**
     * Create bomb blast effect by changing colors rapidly
     */
    private void createBombBlastEffect() {
        Thread blastThread = new Thread(() -> {
            Color[] blastColors = {
                new Color(255, 0, 0),     // Red
                new Color(255, 255, 0),   // Yellow
                new Color(0, 255, 0),     // Green
                new Color(0, 255, 255),   // Cyan
                new Color(0, 0, 255),     // Blue
                new Color(255, 0, 255),   // Magenta
                new Color(255, 255, 255), // White
                new Color(0, 0, 0)        // Black
            };
            
            for (int blast = 0; blast < 20; blast++) {
                final int currentBlast = blast;
                SwingUtilities.invokeLater(() -> {
                    // Change all button colors rapidly
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            buttons[i][j].setBackground(blastColors[currentBlast % blastColors.length]);
                        }
                    }
                    // Change status panel background
                    getContentPane().setBackground(blastColors[currentBlast % blastColors.length]);
                });
                
                try {
                    Thread.sleep(100); // 100ms delay for rapid color change
                } catch (InterruptedException e) {
                    break;
                }
            }
            
            // Restore winning cells to magenta
            SwingUtilities.invokeLater(() -> {
                highlightWinningCells(0, 0); // This will restore winning cells
                getContentPane().setBackground(new Color(20, 20, 40)); // Restore dark background
            });
        });
        
        blastThread.start();
    }
    
    /**
     * Show draw dialog
     */
    private void showDrawDialog() {
        SwingUtilities.invokeLater(() -> {
            JDialog dialog = new JDialog(this, "DRAW!", true);
            dialog.setSize(350, 250);
            dialog.setLocationRelativeTo(this);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBackground(new Color(255, 165, 0)); // Orange background
            
            // Draw message
            JLabel drawLabel = new JLabel("<html><center><h1>IT'S A DRAW!</h1>" +
                "<h2>Great Game Play!</h2>" +
                "<p>Both Players Played Well!</p></center></html>");
            drawLabel.setHorizontalAlignment(JLabel.CENTER);
            drawLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
            drawLabel.setForeground(new Color(0, 0, 0)); // Black text
            
            // Play Again button
            JButton playAgainBtn = new JButton("🎮 PLAY AGAIN");
            playAgainBtn.setFont(new Font("Arial Black", Font.BOLD, 14));
            playAgainBtn.setBackground(new Color(0, 255, 0)); // Green
            playAgainBtn.setForeground(Color.WHITE);
            playAgainBtn.addActionListener(e -> {
                dialog.dispose();
                resetGame();
            });
            
            // Close button
            JButton closeBtn = new JButton("CLOSE");
            closeBtn.setFont(new Font("Arial Black", Font.BOLD, 14));
            closeBtn.setBackground(new Color(255, 0, 0)); // Red
            closeBtn.setForeground(Color.WHITE);
            closeBtn.addActionListener(e -> dialog.dispose());
            
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(playAgainBtn);
            buttonPanel.add(closeBtn);
            buttonPanel.setBackground(new Color(255, 165, 0));
            
            panel.add(drawLabel, BorderLayout.CENTER);
            panel.add(buttonPanel, BorderLayout.SOUTH);
            
            dialog.add(panel);
            dialog.setVisible(true);
        });
    }
    
    /**
     * Check if the current player has won
     */
    private boolean checkWin(int row, int col) {
        String player = String.valueOf(currentPlayer);
        
        // Check row
        if (buttons[row][0].getText().equals(player) &&
            buttons[row][1].getText().equals(player) &&
            buttons[row][2].getText().equals(player)) {
            return true;
        }
        
        // Check column
        if (buttons[0][col].getText().equals(player) &&
            buttons[1][col].getText().equals(player) &&
            buttons[2][col].getText().equals(player)) {
            return true;
        }
        
        // Check main diagonal
        if (row == col) {
            if (buttons[0][0].getText().equals(player) &&
                buttons[1][1].getText().equals(player) &&
                buttons[2][2].getText().equals(player)) {
                return true;
            }
        }
        
        // Check anti-diagonal
        if (row + col == 2) {
            if (buttons[0][2].getText().equals(player) &&
                buttons[1][1].getText().equals(player) &&
                buttons[2][0].getText().equals(player)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Highlight winning cells
     */
    private void highlightWinningCells(int row, int col) {
        String player = String.valueOf(currentPlayer);
        
        Color winColor = new Color(255, 0, 255); // Bright Magenta for winning cells
        
        // Check row
        if (buttons[row][0].getText().equals(player) &&
            buttons[row][1].getText().equals(player) &&
            buttons[row][2].getText().equals(player)) {
            buttons[row][0].setBackground(winColor);
            buttons[row][1].setBackground(winColor);
            buttons[row][2].setBackground(winColor);
            return;
        }
        
        // Check column
        if (buttons[0][col].getText().equals(player) &&
            buttons[1][col].getText().equals(player) &&
            buttons[2][col].getText().equals(player)) {
            buttons[0][col].setBackground(winColor);
            buttons[1][col].setBackground(winColor);
            buttons[2][col].setBackground(winColor);
            return;
        }
        
        // Check main diagonal
        if (row == col) {
            if (buttons[0][0].getText().equals(player) &&
                buttons[1][1].getText().equals(player) &&
                buttons[2][2].getText().equals(player)) {
                buttons[0][0].setBackground(winColor);
                buttons[1][1].setBackground(winColor);
                buttons[2][2].setBackground(winColor);
                return;
            }
        }
        
        // Check anti-diagonal
        if (row + col == 2) {
            if (buttons[0][2].getText().equals(player) &&
                buttons[1][1].getText().equals(player) &&
                buttons[2][0].getText().equals(player)) {
                buttons[0][2].setBackground(winColor);
                buttons[1][1].setBackground(winColor);
                buttons[2][0].setBackground(winColor);
            }
        }
    }
    
    /**
     * Check if the board is full
     */
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Switch to the other player
     */
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    
    /**
     * Reset the game
     */
    private void resetGame() {
        currentPlayer = 'X';
        gameOver = false;
        
        // Restore original modern button colors
        Color[] buttonColors = {
            new Color(255, 105, 180), // Hot Pink
            new Color(0, 191, 255),    // Deep Sky Blue
            new Color(50, 205, 50),   // Lime Green
            new Color(255, 165, 0),   // Orange
            new Color(138, 43, 226),  // Blue Violet
            new Color(255, 20, 147),  // Deep Pink
            new Color(0, 255, 255),   // Cyan
            new Color(255, 69, 0),    // Red Orange
            new Color(124, 252, 0)    // Lawn Green
        };
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(buttonColors[i * 3 + j]);
                buttons[i][j].setEnabled(true); // Re-enable all buttons
            }
        }
        
        statusLabel.setText("🎯 " + playerXName + "'s Turn");
        statusLabel.setForeground(new Color(255, 255, 0)); // Bright Yellow
        
        System.out.println("Game Reset - " + playerXName + "'s Turn");
    }
    
    /**
     * Main method to start the GUI game
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                // Use default look and feel
                System.out.println("Using default look and feel");
            }
            
            new TicTacToeGUI().setVisible(true);
        });
    }
}
