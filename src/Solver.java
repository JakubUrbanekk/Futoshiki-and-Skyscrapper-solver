import java.util.ArrayList;

public class Solver{
    public ArrayList<Board> solutions;
    public static int iterationTime;

    public Solver (){
        iterationTime = 0;
        solutions = new ArrayList<>();
    }

    public Boolean backTrackingSolve (Board board){
        if(board.isFilled()){
            return true;
        }
        Board.RowAndColumn rowAndColumn = board.firstUnassignedCell();
        int row = rowAndColumn.getRow();
        int column = rowAndColumn.getColumn();
        int maxValue = board.length();

        for(int value = 1; value <= maxValue; value++){
            if(board.valid(row, column, value)){
                updateBoard(row, column, value, board);
                iterationTime++;
               // System.out.println(board);
                if(backTrackingSolve(board)){
                    Board boardCopy = board.copy();
                    solutions.add(boardCopy);
                } else{
                    board.getBoard()[row][column] = 0;
                }
            }
        }
        return false;
    }
    public Boolean backTrackingSolveWithMostConstrained (Board board){
        if(board.isFilled()){
            return true;
        }
        Board.RowAndColumn rowAndColumn = board.mostConstrainedCell();
        int row = rowAndColumn.getRow();
        int column = rowAndColumn.getColumn();
        int maxValue = board.length();

        for(int value = 1; value <= maxValue; value++){
            if(board.valid(row, column, value)){
                updateBoard(row, column, value, board);
                iterationTime++;
               //  System.out.println(board);
                if(backTrackingSolve(board)){
                    Board boardCopy = board.copy();
                    solutions.add(boardCopy);
                } else{
                    board.getBoard()[row][column] = 0;
                }
            }
        }
        return false;
    }

    public Boolean backTrackingSolveWithLeastConstrained (Board board){
        if(board.isFilled()){
            return true;
        }
        Board.RowAndColumn rowAndColumn = board.leastConstrainedCell();
        int row = rowAndColumn.getRow();
        int column = rowAndColumn.getColumn();
        int maxValue = board.length();

        for(int value = 1; value <= maxValue; value++){
            if(board.valid(row, column, value)){
                updateBoard(row, column, value, board);
                iterationTime++;
                //  System.out.println(board);
                if(backTrackingSolve(board)){
                    Board boardCopy = board.copy();
                    solutions.add(boardCopy);
                } else{
                    board.getBoard()[row][column] = 0;
                }
            }
        }
        return false;
    }


    public boolean forwardChekingSolve(Board board){
        if(board.isFilled()){
            return true;
        }
        ArrayList<Integer>[][] domains=updateDomains(board);
        if(isAnyDomainEmpty(domains,board)){
            return false;
        }
        else{
            Board.RowAndColumn rowAndColumn = board.firstUnassignedCell();
            int row = rowAndColumn.getRow();
            int column = rowAndColumn.getColumn();
            ArrayList<Integer> domain = domains[row][column];
            for(int i = 0; i < domain.size(); i++){
                iterationTime++;
                updateBoard(row, column, domain.get(i), board);
              //  System.out.println(board);
                if(forwardChekingSolve(board)){
                    Board boardCopy = board.copy();
                    solutions.add(boardCopy);
                } else{
                    board.getBoard()[row][column] = 0;
                }
            }
        }
        return false;
    }
    public boolean forwardChekingSolveWithMostConstrained(Board board){
        if(board.isFilled()){
            return true;
        }
        ArrayList<Integer>[][] domains=updateDomains(board);
        if(isAnyDomainEmpty(domains,board)){
            return false;
        }
        else{
            Board.RowAndColumn rowAndColumn = board.mostConstrainedCell();
            int row = rowAndColumn.getRow();
            int column = rowAndColumn.getColumn();
            ArrayList<Integer> domain = domains[row][column];
            for(int i = 0; i < domain.size(); i++){
                iterationTime++;
                updateBoard(row, column, domain.get(i), board);
               // System.out.println(board);
                if(forwardChekingSolve(board)){
                    Board boardCopy = board.copy();
                    solutions.add(boardCopy);
                } else{
                    board.getBoard()[row][column] = 0;
                }
            }
        }
        return false;
    }
    public boolean forwardChekingSolveWithLeastConstrained(Board board){
        if(board.isFilled()){
            return true;
        }
        ArrayList<Integer>[][] domains=updateDomains(board);
        if(isAnyDomainEmpty(domains,board)){
            return false;
        }
        else{
            Board.RowAndColumn rowAndColumn = board.leastConstrainedCell();
            int row = rowAndColumn.getRow();
            int column = rowAndColumn.getColumn();
            ArrayList<Integer> domain = domains[row][column];
            for(int i = 0; i < domain.size(); i++){
                iterationTime++;
                updateBoard(row, column, domain.get(i), board);
                // System.out.println(board);
                if(forwardChekingSolve(board)){
                    Board boardCopy = board.copy();
                    solutions.add(boardCopy);
                } else{
                    board.getBoard()[row][column] = 0;
                }
            }
        }
        return false;
    }
    public Board updateBoard (int row, int column, int value, Board board){
        board.getBoard()[row][column] = value;
        return board;
    }
    public boolean isAnyDomainEmpty(ArrayList<Integer>[][] domains,Board board){
        for (int row=0; row<board.length(); row++){
            for (int column=0; column<board.length(); column++){
                if (!board.isAssigned(row,column)){
                    ArrayList<Integer> domain=domains[row][column];
                    if (domain.isEmpty()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Integer>[][] updateDomains (Board board){
        int maxValue = board.length();
        int length=board.length();
        ArrayList<Integer>[][] domains = initDomains(board);
        for(int value = 1; value <= maxValue; value++){
                for(int row = 0; row < length; row++){
                    for(int column = 0; column < length; column++){
                        if (!board.isAssigned(row,column)){
                            if(board.valid(row, column, value)){
                                domains[row][column].add(value);
                            }
                        }
                    }
            }
        }
            return domains;
    }
    public ArrayList<Integer>[][] initDomains(Board board){
        int length = board.length();
        ArrayList<Integer>[][] domains = new ArrayList[length][length];
        for(int row = 0; row < length; row++){
            for(int column = 0; column < length; column++){
                if(!board.isAssigned(row, column)){
                    domains[row][column] = new ArrayList<>();
                }
            }
        }
        return domains;
    }

    public void printSolutions (){
        for(int i = 0; i < solutions.size(); i++){
            System.out.println(solutions.get(i));
        }
    }
}

