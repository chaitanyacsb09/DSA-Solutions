//Implementation could be way better
class Solution {
    char[][] solution = new char[9][9];
    
    public void solveSudoku(char[][] board) {
        List<Set<Character>> rowSets = new ArrayList<>();
        List<Set<Character>> colSets = new ArrayList<>();
        List<Set<Character>> boxSets = new ArrayList<>();
        
        for (int i = 0; i < 9; i++) {
            rowSets.add(new HashSet<>());
            colSets.add(new HashSet<>());
            boxSets.add(new HashSet<>());
        }
        
        // Fill sets with existing numbers
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char val = board[r][c];
                if (val != '.') {
                    rowSets.get(r).add(val);
                    colSets.get(c).add(val);
                    boxSets.get((r / 3) * 3 + (c / 3)).add(val);
                }
            }
        }
        
        solve(board, 0, 0, rowSets, colSets, boxSets);
        
        // Copy solution back
        for (int i = 0; i < 9; i++)
            board[i] = solution[i].clone();
    }
    
    private boolean solve(char[][] board, int r, int c,
                          List<Set<Character>> rowSets,
                          List<Set<Character>> colSets,
                          List<Set<Character>> boxSets) {
        
        if (r == 9) {
            copySolution(board);
            return true;
        }
        
        if (c == 9) return solve(board, r + 1, 0, rowSets, colSets, boxSets);
        
        if (board[r][c] != '.') return solve(board, r, c + 1, rowSets, colSets, boxSets);
        
        int boxIdx = (r / 3) * 3 + (c / 3);
        
        for (char num = '1'; num <= '9'; num++) {
            if (rowSets.get(r).contains(num) || colSets.get(c).contains(num) || boxSets.get(boxIdx).contains(num))
                continue;
            
            board[r][c] = num;
            rowSets.get(r).add(num);
            colSets.get(c).add(num);
            boxSets.get(boxIdx).add(num);
            
            if (solve(board, r, c + 1, rowSets, colSets, boxSets)) return true;
            
            // backtrack
            board[r][c] = '.';
            rowSets.get(r).remove(num);
            colSets.get(c).remove(num);
            boxSets.get(boxIdx).remove(num);
        }
        
        return false;
    }
    
    private void copySolution(char[][] board) {
        for (int i = 0; i < 9; i++)
            solution[i] = board[i].clone();
    }
}
