package composite;

public class Number extends Parser{

    int value;
    String valueString;

    Number(String num) {
        this.valueString = num;
        parse();
    }

    public void parse() {
        this.value = Integer.parseInt(valueString);
    }

    public double eval() {return 0.0;}
    
}
