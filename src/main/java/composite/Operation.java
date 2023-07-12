package composite;

public class Operation extends Parser {

    char type;

    Operation(char type) {
        this.type = type;
    }

    public void parse() {}
    public double eval() {return 0.0;}
    
}
