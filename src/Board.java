public abstract class Board{
    int[][] board;


    public boolean isAssigned(int rowId, int columnId){
        return board[rowId][columnId] != 0;
    }
    public int getCell(int rowId, int columnId){
        return board[rowId][columnId];
    }
    public int countUnassignedInRow(int row){
        int count=0;
        for (int i=0; i<board.length; i++){
            if (!isAssigned(row,i)){
                count++;
            }
        }
        return count;
    }
    public int countUnassignedInColumn(int column){
        int count=0;
        for (int i=0; i<board.length; i++){
            if (!isAssigned(i,column)){
                count++;
            }
        }
        return count;
    }


    public RowAndColumn firstUnassignedCell(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if (board[i][j]==0){
                    return new RowAndColumn(i,j);
                }
            }
        }
        return null;
    }

    public int length(){
        return board.length;
    }
    public boolean isFilled (){
        for (int i=0; i<board.length; i++){
            for (int j=0; j<board.length; j++){
                if (board[i][j]==0){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isAddToColumnSafe (int columnId, int value){
        for(int i = 0; i < board.length; i++){
            if(board[i][columnId] == value){
                return false;
            }
        }
        return true;
    }
    public boolean isAddToRowSafe(int rowId, int value){
        for (int i = 0; i< board.length; i++){
                if (board[rowId][i]== value){
                    return false;
                }
            }
        return true;
    }
    public boolean isSafe(int rowId, int columnId, int value){
        return isAddToColumnSafe(columnId,value) && isAddToRowSafe(rowId,value);
    }
    public abstract boolean valid(int rowId, int columnId, int value);
    public abstract Board copy();
    public abstract RowAndColumn mostConstrainedCell();
    public abstract RowAndColumn leastConstrainedCell();

    public int[][] getBoard (){
        return board;
    }
    public class RowAndColumn{
        int row;
        int column;
        RowAndColumn(){
            row=-1;
            column=-1;
        }
        RowAndColumn (int row, int column){
            this.row = row;
            this.column = column;
        }

        public int getRow (){
            return row;
        }

        public int getColumn (){
            return column;
        }
    }
}
