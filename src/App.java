public class App{
    public static void main (String[] args){
        String nazwaPliku="dane_badawcze/test_sky_6_4.txt";
   //     Loader loader=new Loader(nazwaPliku); // futoshiki 9.2 nie dziala i 8.0
     //   Board futoshikiBoard=loader.load();
       // System.out.println(futoshikiBoard);
     SkyscrapperLoader skyscrapperLoader=new SkyscrapperLoader(nazwaPliku);
        SkyscrapperBoard skyscrapperBoard=skyscrapperLoader.load();
         System.out.println(skyscrapperBoard);

        long startTime=System.nanoTime();
        Solver solver=new Solver();
        solver.forwardChekingSolve(skyscrapperBoard);
        long endTime=System.nanoTime();
        long elapsedTime=endTime-startTime;
        double seconds=(double)elapsedTime /1_000_000_000.0;
        System.out.println("Nazwa pliku " +nazwaPliku+  " liczba rozwiazan " + solver.solutions.size()+ " Czas wykonania " + seconds + " liczba iteracji "+ solver.iterationTime);
        System.out.println("Solutions");
       // solver.printSolutions();
       // Solver solver1=new Solver(skyscrapperBoard);
      //  solver1.backTrackingSolve();
       // System.out.println("Solutions");
       // solver1.printSolutions();

    }

}
