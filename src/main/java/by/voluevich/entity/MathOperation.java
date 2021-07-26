package by.voluevich.entity;

public class MathOperation {
    private int numOne;
    private int numTwo;
    private String typeOp;
    private String result;

    public MathOperation(int numOne, int numTwo, String typeOp, String result) {
        this.numOne = numOne;
        this.numTwo = numTwo;
        this.typeOp = typeOp;
        this.result = result;
    }

    public MathOperation() {
    }

    public int getNumOne() {
        return numOne;
    }

    public void setNumOne(int numOne) {
        this.numOne = numOne;
    }

    public int getNumTwo() {
        return numTwo;
    }

    public void setNumTwo(int numTwo) {
        this.numTwo = numTwo;
    }

    public String getTypeOp() {
        return typeOp;
    }

    public void setTypeOp(String typeOp) {
        this.typeOp = typeOp;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Operation: " + typeOp + ", with number " + numOne + " and " + numTwo + ". " + result;
    }
}
