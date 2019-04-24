public class SkyscrapperBoard extends Board{
    SkyscrapperLimits skyscrapperLimits;

    public SkyscrapperBoard (SkyscrapperLimits skyscrapperLimits, int skyscrapperSize){
        this.skyscrapperLimits = skyscrapperLimits;
        board=new int[skyscrapperSize][skyscrapperSize];
    }
    public SkyscrapperBoard (int length, SkyscrapperLimits skyscrapperLimits){
        board=new int[length][length];
        this.skyscrapperLimits=skyscrapperLimits;
    }
    public boolean checkLimits(int row, int column, int value){
        return checkLeftLimit(row,value) && checkRightLimit(row,value) && checkTopLimit(column,value) && checkBotLimit(column,value);
    }
    public boolean checkLeftLimit (int row, int value){
        int leftLimit=skyscrapperLimits.getLeftLimit()[row];

        if(countUnassignedInRow(row)>1 || leftLimit==0){
            return true;
        }
        int visibleBuildings=0;
        int highestBuilding=0;

        for (int i=0; i<board.length; i++){
            if (!isAssigned(row,i)){ //insert position
                if (value>highestBuilding){
                    highestBuilding=value;
                    visibleBuildings++;
                }
            }
            else {
                int buildingHigh=board[row][i];
                if (buildingHigh>highestBuilding){
                    highestBuilding=buildingHigh;
                    visibleBuildings++;
                }
            }
        }
      //  System.out.println("Left " + "Value " + value + " visible buildings "+ visibleBuildings + " limit " + leftLimit);
        return visibleBuildings == leftLimit;
    }
    public boolean checkRightLimit (int row, int value){
        int rightLimit=skyscrapperLimits.getRightLimit()[row];

        if(countUnassignedInRow(row)>1 || rightLimit==0){
            return true;
        }
        int visibleBuildings=0;
        int highestBuilding=0;

        for (int i=board.length-1; i>=0; i--){
            if (!isAssigned(row,i)){ //insert position
                if (value>highestBuilding){
                    highestBuilding=value;
                    visibleBuildings++;
                }
            }
            else {
                int buildingHigh=board[row][i];
                if (buildingHigh>highestBuilding){
                    highestBuilding=buildingHigh;
                    visibleBuildings++;
                }
            }
        }
    //    System.out.println("Right " + "Value " + value + " visible buildings "+ visibleBuildings + " limit " + rightLimit);
        return visibleBuildings == rightLimit;
    }
    public boolean checkTopLimit (int column, int value){
        int topLimit=skyscrapperLimits.getUpperLimit()[column];
    //    System.out.print("Column ");
    //    for (int i=0; i<board.length; i++){
     //       System.out.print(" " + board[i][column]);
      //  }
     //   System.out.println("");
    //    System.out.println(countUnassignedInColumn(column));
        if(countUnassignedInColumn(column)>1 || topLimit==0){
            return true;
        }
        int visibleBuildings=0;
        int highestBuilding=0;

        for (int i=0; i<board.length; i++){
            if (!isAssigned(i,column)){ //insert position
                if (value>highestBuilding){
                    highestBuilding=value;
                    visibleBuildings++;
                }
            }
            else {
                int buildingHigh=board[i][column];
                if (buildingHigh>highestBuilding){
                    highestBuilding=buildingHigh;
                    visibleBuildings++;
                }
            }
        }
      //  System.out.println("Top " + "Value " + value + " visible buildings "+ visibleBuildings + " limit " + topLimit);
        return visibleBuildings == topLimit;
    }
    public boolean checkBotLimit (int column, int value){
        int botLimit=skyscrapperLimits.getLowerLimit()[column];

        if(countUnassignedInColumn(column)>1 || botLimit==0){
            return true;
        }
        int visibleBuildings=0;
        int highestBuilding=0;

        for (int i=board.length-1; i>=0; i--){
            if (!isAssigned(i,column)){ //insert position
                if (value>highestBuilding){
                    highestBuilding=value;
                    visibleBuildings++;
                }
            }
            else {
                int buildingHigh=board[i][column];
                if (buildingHigh>highestBuilding){
                    highestBuilding=buildingHigh;
                    visibleBuildings++;
                }
            }
        }
     //   System.out.println("Bot " +"Value " + value + " visible buildings "+ visibleBuildings + " limit " + botLimit);
        return visibleBuildings == botLimit;
    }

      /*  public int viewCount(boolean forward, int rowOrColumn,int value,boolean isColumn, int positionToInsert){

            int visibleBuildings = 0;
            int highestBuilding = 0;
            int[] rowToCheck;
            if(isColumn){
                rowToCheck = columnToRow(rowOrColumn);
            } else{
                rowToCheck = board[rowOrColumn];
            }

            if(forward){
                if(rowToCheck[0] != 0){
                    highestBuilding = rowToCheck[0];
                    visibleBuildings++;
                }
                else if (rowToCheck[0]==0 && positionToInsert==0){
                    highestBuilding=value;
                    visibleBuildings++;
                }
                for(int i = 1; i < rowToCheck.length; i++){
                    if (i==positionToInsert){
                        if (rowToCheck[i-1]<value && value>highestBuilding){
                            highestBuilding=value;
                            visibleBuildings++;
                        }
                    }
                    else if (i==positionToInsert+1){
                        if (value<rowToCheck[i] && rowToCheck[i]>highestBuilding){
                            highestBuilding=rowToCheck[i];
                            visibleBuildings++;
                        }
                    }
                    else if (rowToCheck[i-1]<rowToCheck[i] && rowToCheck[i]>highestBuilding){
                        highestBuilding=rowToCheck[i];
                        visibleBuildings++;
                    }
                }
            }
            else {
              //  System.out.println("Position to insert  "+ positionToInsert + " length "+ length());
                if (rowToCheck[length()-1]!=0){
                    highestBuilding=rowToCheck[length()-1];
                    visibleBuildings++;
                }
                else if (rowToCheck[length()-1]==0 && positionToInsert==length()-1){
                    highestBuilding=value;
                    visibleBuildings++;
                }
                for (int i=length()-2; i>=0; i--){
                    if (i==positionToInsert){
                    //    System.out.println("wartosc w tablicy i==positionT " + rowToCheck[i] + " value" + value + " najwyzszy budynek "+ highestBuilding);
                        if (value>rowToCheck[i+1] && value>highestBuilding){

                            visibleBuildings++;
                            highestBuilding=value;
                        }
                    }
                    else if (i+1==positionToInsert){
                     //   System.out.println("wartosc w tablicy i+1==rowOrColumn" + rowToCheck[i] + " value" + value + " najwyzszy budynek "+ highestBuilding);
                        if (rowToCheck[i]>value && rowToCheck[i]>highestBuilding){
                            visibleBuildings++;
                            highestBuilding=rowToCheck[i];
                        }
                    }
                   else if (rowToCheck[i]>rowToCheck[i+1]&&rowToCheck[i]>highestBuilding){
                        visibleBuildings++;
                        highestBuilding=rowToCheck[i];
                    }
                }
              //  System.out.println("Widoczne budynki " +visibleBuildings);
            }
        return visibleBuildings;
    }
    public int[]columnToRow(int column){
        int[] colToRow = new int[length()];
        for (int i = 0; i < length(); i++)
            colToRow[i] = board[i][column];
        return colToRow;
    } */

    @Override
    public String toString (){
        StringBuffer result = new StringBuffer();
     //   result.append(board.length).append("\n");
     //   result.append("Board").append("\n");
        String separator = ";";
        String sideSeparator = "|";

        int[]upperOrdinance= skyscrapperLimits.getUpperLimit();
        int[]lowerOrdinance= skyscrapperLimits.getLowerLimit();
        int[]leftOrdinance= skyscrapperLimits.getLeftLimit();
        int[]rightOrdinance= skyscrapperLimits.getRightLimit();

        result.append(" ").append(sideSeparator);
        for (int i=0; i<upperOrdinance.length; i++){
            result.append(upperOrdinance[i]).append(sideSeparator);
        }

        result.append("\n");
        for (int i = 0; i < board.length; ++i)
        {
            result.append(leftOrdinance[i]).append(sideSeparator);
            for (int j = 0; j < board[i].length; ++j){
                result.append(board[i][j]).append(separator);
            }
            result.append(sideSeparator).append(rightOrdinance[i]);
            result.append("\n");
        }

        result.append(" ").append(sideSeparator);
        for (int i=0; i<lowerOrdinance.length; i++){
            result.append(lowerOrdinance[i]).append(sideSeparator);
        }
        return result.toString();
    }

    @Override
    public boolean valid (int rowId, int columnId, int value){
        return isSafe(rowId, columnId, value) && checkLimits(rowId, columnId, value);
    }

    @Override
    public Board copy (){
        Board boardCopy=new SkyscrapperBoard(length(),this.skyscrapperLimits);
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
            for(int column = 0; column < length(); column++){
                if(!isAssigned(row, column)){
                    int leftLimit = skyscrapperLimits.getLeftLimit()[row];
                    int rightLimit = skyscrapperLimits.getRightLimit()[row];
                    int topLimit = skyscrapperLimits.getUpperLimit()[column];
                    int botLimit = skyscrapperLimits.getLowerLimit()[column];
                    if(leftLimit != 0){
                        constrainAmount++;
                    }
                    if(rightLimit != 0){
                        constrainAmount++;
                    }
                    if(topLimit != 0){
                        constrainAmount++;
                    }
                    if(botLimit != 0){
                        constrainAmount++;
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
        int minConstrain=Integer.MAX_VALUE;
        RowAndColumn leastConstrainedCell=new RowAndColumn();
        for (int row=0; row<length(); row++){
            for(int column = 0; column < length(); column++){
                if(!isAssigned(row, column)){
                    int leftLimit = skyscrapperLimits.getLeftLimit()[row];
                    int rightLimit = skyscrapperLimits.getRightLimit()[row];
                    int topLimit = skyscrapperLimits.getUpperLimit()[column];
                    int botLimit = skyscrapperLimits.getLowerLimit()[column];
                    if(leftLimit != 0){
                        constrainAmount++;
                    }
                    if(rightLimit != 0){
                        constrainAmount++;
                    }
                    if(topLimit != 0){
                        constrainAmount++;
                    }
                    if(botLimit != 0){
                        constrainAmount++;
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
}
