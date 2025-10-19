# Two-Player Tic-Tac-Toe Game in Java

This project implements a complete Two-Player Tic-Tac-Toe game in Java with both console and GUI interfaces.

## Features

### Core Game Features
- ✅ 3x3 grid board display
- ✅ Two-player alternating turns (X and O)
- ✅ Input validation for row/column positions (0-2)
- ✅ Position occupancy checking
- ✅ Win detection (rows, columns, diagonals)
- ✅ Draw detection when board is full
- ✅ Winner announcement and draw messages
- ✅ Replay/reset functionality
- ✅ Smooth turn switching

### Console Version (`TicTacToe.java`)
- Text-based interface using Java Scanner
- Clear board visualization with coordinates
- Input validation with error handling
- Interactive prompts for player moves

### GUI Version (`TicTacToeGUI.java`)
- Modern Swing-based graphical interface
- Clickable buttons for easy interaction
- Visual feedback with colors (X=Red, O=Blue)
- Winning cells highlighted in yellow
- Status updates and "New Game" button
- Responsive design with proper sizing

## How to Run

### Console Version
```bash
javac TicTacToe.java
java TicTacToe
```

### GUI Version
```bash
javac TicTacToeGUI.java
java TicTacToeGUI
```
## Game Rules

1. **Players**: Two players alternate turns
2. **Marks**: Player 1 uses 'X', Player 2 uses 'O'
3. **Objective**: Get three marks in a row (horizontal, vertical, or diagonal)
4. **Input**: Enter row and column numbers (0-2) for console version
5. **Winning**: First player to get 3 in a row wins
6. **Draw**: If board fills up with no winner, it's a draw

## Console Game Flow

1. Game displays empty board with coordinates
2. Player X enters row and column numbers
3. System validates input and updates board
4. Player O takes their turn
5. Game continues until win or draw
6. Option to play again or exit

## GUI Game Flow

1. Click any empty cell to make a move
2. Visual feedback shows current player's turn
3. Winning cells are highlighted in yellow
4. Status bar shows game progress
5. "New Game" button resets the board

## Code Structure

### TicTacToe.java (Console Version)
- `initializeBoard()`: Sets up empty 3x3 grid
- `displayBoard()`: Shows current board state
- `getPlayerMove()`: Handles input validation
- `makeMove()`: Places player mark
- `checkWin()`: Detects winning conditions
- `isBoardFull()`: Checks for draw
- `playGame()`: Main game loop
- `askForReplay()`: Handles game restart

### TicTacToeGUI.java (GUI Version)
- `initializeGUI()`: Sets up Swing components
- `actionPerformed()`: Handles button clicks
- `makeMove()`: Updates GUI and checks win
- `checkWin()`: Win detection logic
- `highlightWinningCells()`: Visual win indication
- `resetGame()`: Clears board for new game

## Input Validation

Both versions include comprehensive input validation:
- **Bounds checking**: Row/column must be 0-2
- **Occupancy checking**: Cannot place on occupied cells
- **Type checking**: Console version handles non-numeric input
- **Game state**: Prevents moves after game ends

## Error Handling

- Invalid input prompts for correct values
- Clear error messages for user guidance
- Graceful handling of edge cases
- Input buffer clearing in console version

## Extensibility

The code is structured for easy modification:
- Add AI player for single-player mode
- Implement different board sizes
- Add sound effects or animations
- Create tournament mode
- Add move history tracking

## Requirements

- Java 8 or higher
- No external dependencies required
- Works on Windows, macOS, and Linux

## Sample Output

### Console Version
```
Welcome to Tic-Tac-Toe!
Players will alternate turns. Player X goes first.

Current Board:
  0   1   2
0   |   |  
  ---------
1   |   |  
  ---------
2   |   |  

Player X, enter row (0-2): 1
Player X, enter column (0-2): 1
```

### GUI Version
- Interactive 3x3 button grid
- Color-coded player moves
- Status updates in real-time
- Winning combinations highlighted

Enjoy playing Tic-Tac-Toe! 🎮
