package by.voluevich.service;

public class Operation {
    private int one;
    private int two;

    public Operation(int one, int two) {
        this.one = one;
        this.two = two;
    }

    public int getOne() {
        return one;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public String getOperation(String operation){
        String result = "";
        switch (operation){
            case "add":
                result = "Result: " + Math.addExact(one, two) + ".";
                break;
            case "sub":
                result = "Result: " + Math.subtractExact(one, two) + ".";
                break;
            case "mp":
                result = "Result: " + Math.multiplyExact(one, two) + ".";
                break;
            case "div":
                result = "Result: " + Math.floorDiv(one, two) + ".";
                break;
            case "mod":
                result = "Result: " + Math.floorMod(one, two) + ".";
                break;
        }
        return result;
    }
}
