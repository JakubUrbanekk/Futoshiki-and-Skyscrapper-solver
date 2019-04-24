import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SkyscrapperLoader{
    String filenme;
    int size;
    Scanner scanner;

    public SkyscrapperLoader (String filenme){
        this.filenme = filenme;
        size=0;
    }

    public SkyscrapperBoard load (){
        int[] upperOrdinance=null;
        int[] lowerOrdinance=null;
        int[] leftOrdinance=null;
        int[] rightOrdinance=null;
        try{
            scanner = new Scanner(new File(filenme));
            size = scanner.nextInt();
            upperOrdinance = new int[size];
            lowerOrdinance = new int[size];
            rightOrdinance = new int[size];
            leftOrdinance = new int[size];
            scanner.nextLine();
            scanner.useDelimiter("\\s|;");
            for(int i = 0; i < 4; i++){
                if(i == 0){
                    upperOrdinance=loadOrdinance(upperOrdinance);
                }
                if(i == 1){
                    lowerOrdinance=loadOrdinance(lowerOrdinance);
                }
                if (i==2){
                    leftOrdinance=loadOrdinance(leftOrdinance);
                }
                if (i==3){
                    rightOrdinance=loadOrdinance(rightOrdinance);
                }
                if (scanner.hasNext()) scanner.nextLine();
            }

        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        SkyscrapperLimits skyscrapperLimits =new SkyscrapperLimits(upperOrdinance,lowerOrdinance,leftOrdinance,rightOrdinance);
        SkyscrapperBoard skyscrapperBoard=new SkyscrapperBoard(skyscrapperLimits,size);
        return skyscrapperBoard;
    }
        public int[] loadOrdinance (int[] ordinance){
        scanner.next();
            for(int i = 0; i < size; i++){
               // System.out.println("Scanner " + scanner.next());
              ordinance[i]=scanner.nextInt();
            }
            return ordinance;
        }
    }

