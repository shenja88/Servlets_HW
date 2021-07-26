package by.voluevich.service;

import by.voluevich.entity.MathOperation;

public class Operation {

    public static MathOperation getOperation(int one, int two, String operation) {
        String result = "";
        MathOperation mathOperation = new MathOperation(one, two, operation, result);
        switch (operation) {
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
                result = "Result: " + ((double) one / two) + ".";
                break;
            case "mod":
                result = "Result: " + Math.floorMod(one, two) + ".";
                break;
        }
        mathOperation.setResult(result);
        return mathOperation;
    }
}
