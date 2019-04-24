public class SkyscrapperLimits{
    int [] upperLimit;
    int [] lowerLimit;
    int [] leftLimit;
    int [] rightLimit;

    public SkyscrapperLimits (int[] upperLimit, int[] lowerLimit, int[] leftLimit, int[] rightLimit){
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
    }

    public int[] getUpperLimit (){
        return upperLimit;
    }

    public int[] getLowerLimit (){
        return lowerLimit;
    }

    public int[] getLeftLimit (){
        return leftLimit;
    }

    public int[] getRightLimit (){
        return rightLimit;
    }
}
