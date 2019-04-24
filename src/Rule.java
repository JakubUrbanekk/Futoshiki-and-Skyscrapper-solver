public class Rule{
    int greaterColumn;
    int greaterRow;
    int lowerColumn;
    int lowerRow;

    public Rule (int greaterColumn, int greaterRow, int lowerColumn, int lowerRow){
        this.greaterColumn = greaterColumn;
        this.greaterRow = greaterRow;
        this.lowerColumn = lowerColumn;
        this.lowerRow = lowerRow;
    }

    public int getGreaterColumn (){
        return greaterColumn;
    }

    public int getGreaterRow (){
        return greaterRow;
    }

    public int getLowerColumn (){
        return lowerColumn;
    }

    public int getLowerRow (){
        return lowerRow;
    }
    public boolean contains(int rowID,int columndID){
        return (rowID == greaterRow && columndID == greaterColumn) || (rowID == lowerRow && columndID == lowerColumn);
    }
    public boolean isGreaterCell(int rowId, int columnId){
        return rowId == greaterRow && columnId == greaterColumn;
    }
    public boolean isLowerCell(int rowId, int columnId){
        return rowId == lowerRow && columnId == lowerColumn;
    }
    @Override
    public String toString (){
        return Character.toString((char)(lowerRow+'A'))+Integer.toString(lowerColumn+1)+ ";"+Character.toString((char)(greaterRow+'A'))+Integer.toString(greaterColumn+1);
    }
}
