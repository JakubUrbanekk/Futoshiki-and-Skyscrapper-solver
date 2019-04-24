import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Loader{
    String filename;
    public Loader(String filename){
        this.filename=filename;
    }
    public FutoshikiBoard load(){
        int size=0;
        ArrayList<Rule> rules=new ArrayList<>();
        int[][] cells=null;
        try{
            Scanner sc=new Scanner(new File(filename));
            sc.useLocale(Locale.UK);
            size=sc.nextInt();
            cells=new int[size][size];
            sc.nextLine();
            sc.nextLine();
            sc.useDelimiter("\\s|;");
            for (int row=0; row<size; row++){
                for (int column=0; column<size; column++){
                    cells[row][column]=(sc.nextInt());
                }
                sc.nextLine();
            }
            sc.nextLine();
            String firstRelation;
            String secondRelation;
            int greaterColumn;
            int greaterRow;
            int lowerColumn;
            int lowerRow;
            Rule rule;
            while(sc.hasNext()){
                firstRelation=sc.next();
                secondRelation=sc.next();
                if (sc.hasNextLine()){
                    sc.nextLine();
                }
                lowerRow=firstRelation.charAt(0)-'A';
                greaterRow=secondRelation.charAt(0)-'A';
                lowerColumn=firstRelation.charAt(1)-49;
                greaterColumn=secondRelation.charAt(1)-49;
                rule=new Rule(greaterColumn,greaterRow,lowerColumn,lowerRow);
                rules.add(rule);
            }

        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        FutoshikiBoard board=new FutoshikiBoard(cells,rules);
        return board;
    }
}
