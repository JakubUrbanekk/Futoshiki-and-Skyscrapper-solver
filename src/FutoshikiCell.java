public class FutoshikiCell{
    int value;
    Rule rule;
    public FutoshikiCell(int value){
        this.value=value;
        rule=null;
    }

    public Rule getRule (){
        return rule;
    }

    public void setRule (Rule rule){
        this.rule = rule;
    }

    public int getValue (){
        return value;
    }

    @Override
    public String toString (){
        return value+"";
    }
}
