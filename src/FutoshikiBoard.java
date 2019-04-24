import java.util.ArrayList;

public class FutoshikiBoard extends Board{
    ArrayList<Rule> rules;

    public FutoshikiBoard (int[][] board, ArrayList<Rule> rules){
        this.board = board;
        this.rules = rules;
    }
    public FutoshikiBoard (int length, ArrayList<Rule> rules){
        board=new int[length][length];
        this.rules = rules;
    }
    public boolean futoshikiRuleValid(int rowID, int columnID, int value){
        int greaterCell;
        int lowerCell;
        for (Rule rule:rules){
            if (rule.contains(rowID,columnID)){
                greaterCell = board[rule.getGreaterRow()][rule.getGreaterColumn()];
                lowerCell = board[rule.getLowerRow()][rule.getLowerColumn()];
                if (rule.isGreaterCell(rowID,columnID)){
                    if (value<lowerCell){
                        return false;
                    }
                }
                else{
                    if (value>greaterCell && greaterCell!=0){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    @Override
    public boolean valid (int rowId, int columnId, int value){
        return isSafe(rowId, columnId, value) && futoshikiRuleValid(rowId, columnId, value);
    }

    @Override
    public Board copy (){
        Board boardCopy= new FutoshikiBoard(this.length(),rules);
        for (int row=0; row<length(); row++){
            for (int column=0; column<length();column++){
                boardCopy.board[row][column]=this.board[row][column];
            }
        }
        return boardCopy;
    }

    @Override
    public RowAndColumn mostConstrainedCell (){
        int constrainAmount=0;
        int maxConstrain=-1;
        RowAndColumn mostConstrainedCell=new RowAndColumn();
        for (int row=0; row<length(); row++){
            for (int column=0; column<length(); column++){
                if(!isAssigned(row, column)){
                    for(Rule rule : rules){
                        if(rule.contains(row, column)){
                            constrainAmount++;
                        }
                    }
                    if(constrainAmount > maxConstrain){
                        mostConstrainedCell = new RowAndColumn(row, column);
                        maxConstrain = constrainAmount;
                    }
                }
                constrainAmount = 0;
            }
        }
        return mostConstrainedCell;
    }

    @Override
    public RowAndColumn leastConstrainedCell (){
        int constrainAmount=0;
        int minConstrain= Integer.MAX_VALUE;
        RowAndColumn leastConstrainedCell=new RowAndColumn();
        for (int row=0; row<length(); row++){
            for (int column=0; column<length(); column++){
                if(!isAssigned(row, column)){
                    for(Rule rule : rules){
                        if(rule.contains(row, column)){
                            constrainAmount++;
                        }
                    }
                    if(constrainAmount < minConstrain){
                        leastConstrainedCell = new RowAndColumn(row, column);
                        minConstrain = constrainAmount;
                    }
                }
                constrainAmount = 0;
            }
        }
        return leastConstrainedCell;
    }

    @Override
    public String toString (){
        StringBuffer result = new StringBuffer();
        String separator = ";";

        for(int i = 0; i < this.board.length; ++i){
            for(int j = 0; j < this.board[i].length; ++j){
                result.append(this.board[i][j]).append(separator);
            }
            result.append("\n");
        }
        result.append("\n" + "Rules" + "\n");
        Rule rule = null;
       System.out.println("Ilosc zasad "+ (rules.size()));
       for(int i = 0; i < rules.size(); ++i){
            rule = rules.get(i);
            if(result.indexOf(rule.toString()) == -1){
                result.append(rule + "\n");
            }
        }
        return result.toString();
    }

}
