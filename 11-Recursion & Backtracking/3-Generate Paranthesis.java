class Solution {
    public List < String > generateParenthesis(int n) {
        List < String > validSequences = new ArrayList < > ();
        StringBuilder currSeq = new StringBuilder("(");
        generateParanthesis(1, 0, n, currSeq, validSequences);
        return validSequences;
    }
    private void generateParanthesis(int numOpen, int numClosed, int n, StringBuilder currSeq, List < String > validSequences) {

        if (numOpen == numClosed && numOpen == n) {
            validSequences.add(currSeq.toString());
            return;
        }
        if (numOpen < n) {
            currSeq.append("(");
            generateParanthesis(numOpen + 1, numClosed, n, currSeq, validSequences);
            currSeq.deleteCharAt(currSeq.length() - 1);
        }
        if (numClosed < numOpen) {
            currSeq.append(")");
            generateParanthesis(numOpen, numClosed + 1, n, currSeq, validSequences);
            currSeq.deleteCharAt(currSeq.length() - 1);
        }

    }

}

//With String, but will run slower in java
class Solution {
    public List < String > generateParenthesis(int n) {
        List < String > validSequences = new ArrayList < > ();
        generateParanthesis(1, 0, n, "(", validSequences);
        return validSequences;
    }
    private void generateParanthesis(int numOpen, int numClosed, int n, String currSeq, List < String > validSequences) {

        if (numOpen == numClosed && numOpen == n) {
            validSequences.add(currSeq);
            return;
        }
        if (numOpen < n) {
            generateParanthesis(numOpen + 1, numClosed, n, currSeq + "(", validSequences);
        }
        if (numClosed < numOpen) {
            generateParanthesis(numOpen, numClosed + 1, n, currSeq + ")", validSequences);
        }

    }

}