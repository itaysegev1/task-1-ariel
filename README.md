# Bulls and Cows Game Implementation

This Java implementation provides an automated solver for the Bulls and Cows number guessing game, also known as Bulls and Pgia. The program interacts with a game server (BP_Server) to play multiple rounds of the game and calculate average performance metrics.

## Game Overview

Bulls and Cows is a code-breaking game where:
- The server generates a secret number with a specified number of digits
- The player makes guesses to determine the secret number
- After each guess, the server returns:
  - Bulls: Number of digits that are correct and in the correct position
  - Cows: Number of digits that are correct but in the wrong position

## Implementation Details

### Main Components

1. **Game Setup**
   - Uses BP_Server class for game management
   - Configurable number of digits (2-6)
   - Supports multiple game rounds for averaging performance

2. **Key Functions**
   - `autoEx1Game1`: Main game loop that implements the automated solving strategy
   - `random`: Generates random number arrays for initial guesses
   - `boolArray`: Creates a boolean array tracking possible solutions
   - `bullpgia`: Calculates bulls and cows between two number arrays
   - `changebool`: Updates possible solutions based on game feedback
   - `randomnumarr`: Selects random numbers from remaining valid solutions

### Algorithm Strategy

The implementation uses an elimination strategy:
1. Starts with a random guess
2. Maintains a boolean array of all possible solutions
3. After each guess, eliminates numbers that wouldn't give the same bulls/cows count
4. Randomly selects from remaining possible solutions
5. Continues until the correct number is found

## Usage

```java
BP_Server game = new BP_Server();
long myID = YOUR_ID;             // Replace with your ID
int numOfDigits = 2;            // Choose number of digits (2-6)
game.startGame(myID, numOfDigits);
autoEx1Game1(game);
```

### Performance Tracking

The implementation includes performance monitoring:
- Tracks number of guesses per game
- Calculates average guesses across multiple games
- Outputs game status and performance metrics

## Requirements

- Java Runtime Environment
- BP_Server class (provided separately)
- Valid user ID for game server interaction

## Example Output

The program provides status updates including:
- Current game progress
- Number of guesses made
- Average performance across multiple games
- Final game status

## Notes

- The implementation uses a combination of random guessing and logical elimination
- Performance may vary based on the number of digits and random initial guesses
- Debug output can be enabled/disabled through commented code sections
- The algorithm is optimized for finding solutions efficiently while maintaining reasonable performance

## Error Handling

The implementation includes basic error handling for:
- Invalid number of digits
- Server communication issues
- Game state management

For more detailed debugging, uncomment the print statements in the code.
