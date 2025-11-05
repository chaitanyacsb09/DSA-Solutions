class Solution {
    //L R U D
    int[] delX = { 0, 1, 0, -1 };
    int[] delY = { -1, 0, 1, 0 };

    public boolean exist(char[][] board, String word) {
        int numRows = board.length, numCols = board[0].length;
        if(numRows * numCols < word.length()){
            return false; //Not enough characters for the entire word to be in the board
        }
        int wordStartIdx = 0;

        for (int rowIdx = 0; rowIdx < numRows; rowIdx++) {
            for (int colIdx = 0; colIdx < numCols; colIdx++) {
                if (board[rowIdx][colIdx] == word.charAt(wordStartIdx)) {
                    char originalCellVal = board[rowIdx][colIdx];
                    board[rowIdx][colIdx] = '.';
                    boolean doesExists = exist(rowIdx, colIdx, wordStartIdx + 1, word, board);

                    board[rowIdx][colIdx] = originalCellVal;
                    if (doesExists)
                        return true;
                }
            }
        }
        return false;
    }

    private boolean exist(int rowIdx, int colIdx, int toMatchIdx, String word, char[][] board) {
        if (toMatchIdx == word.length()) {
            return true;
        }
        int numRows = board.length, numCols = board[0].length;
        if (rowIdx == numRows || colIdx == numCols) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            int adjX = rowIdx + delX[i], adjY = colIdx + delY[i];
            //TODO: implementation
            if (!isValid(adjX, adjY, numRows, numCols) || board[adjX][adjY] != word.charAt(toMatchIdx)) {
                continue;
            }
            char originalCellVal = board[rowIdx][colIdx];
            board[rowIdx][colIdx] = '.';
            boolean doesExists = exist(adjX, adjY, toMatchIdx + 1, word, board);

            board[rowIdx][colIdx] = originalCellVal;
            if (doesExists)
                return true;
        }
        return false;
    }

    private boolean isValid(int rowIdx, int colIdx, int numRows, int numCols) {
        return rowIdx >= 0 && rowIdx < numRows && colIdx >= 0 && colIdx < numCols;
    }
}
