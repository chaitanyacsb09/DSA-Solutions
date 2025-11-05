class Solution {
    List < List < String >> validConfigs;
    boolean[] blockedCols, blockedFwdDiagonals, blockedBwdDiagonals;

    public List < List < String >> solveNQueens(int n) {
        validConfigs = new ArrayList<>();
        if(n == 2 || n == 3) return validConfigs;

        blockedCols = new boolean[n]; //0..n-1
        blockedFwdDiagonals = new boolean[2 * n - 1]; //total n + n - 1 Fwd Diagonals
        blockedBwdDiagonals = new boolean[2 * n - 1]; //total n + n - 1 Bwd Diagonals
        
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++) Arrays.fill(board[i], '.'); 
        
        placeQueen(0, n, board);
        return validConfigs;
    }
    private void placeQueen(int rowIdx, int n, char[][] board) {
        if (rowIdx >= n) {            
            List<String> currConfig = new ArrayList<>();
            //O(N^2)
            for(int i = 0; i < n; i++){
                currConfig.add(new String(board[i]));
            }
            validConfigs.add(currConfig); 
            return;
        }

        //O(N)
        for (int colIdx = 0; colIdx < n; colIdx++) {
            if (!isQueenSafe(rowIdx, colIdx, n)) { //O(1)
                continue;
            }
            
            board[rowIdx][colIdx] = 'Q';
            updateBlockedState(rowIdx, colIdx, n, true);

            placeQueen(rowIdx + 1, n, board);

            board[rowIdx][colIdx] = '.';
            updateBlockedState(rowIdx, colIdx, n, false);
        }
    }

    private boolean isQueenSafe(int rowIdx, int colIdx, int n) {
        //For forward diagonal direction, the rowIdx - colIdx, is constant for all elements in same fwdDiagline
        int fwdDiagIdx = rowIdx - colIdx + (n-1); //Min: 0 - (n-1) => therfore to normalize idx, adding (n-1) 
        int bwdDiagIdx = rowIdx + colIdx; //rowIdx + colIdx is constant for all elements in same bwdDiagLine

        return !(blockedCols[colIdx] || blockedFwdDiagonals[fwdDiagIdx] || blockedBwdDiagonals[bwdDiagIdx]);
    }

    private void updateBlockedState(int rowIdx, int colIdx, int n, boolean isPlaced) {
        int fwdDiagIdx = rowIdx - colIdx + (n-1); 
        int bwdDiagIdx = rowIdx + colIdx;

        blockedCols[colIdx] = isPlaced;
        blockedFwdDiagonals[fwdDiagIdx] = isPlaced;
        blockedBwdDiagonals[bwdDiagIdx] = isPlaced;
    }
}