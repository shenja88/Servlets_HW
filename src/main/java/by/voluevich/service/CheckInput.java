package by.voluevich.service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CheckInput {
    private static final List<String> OPERATION_LIST = Arrays.asList(
            "addition",
            "subtraction",
            "multiplication",
            "division",
            "modulo");

    public static boolean isExistOperation(String typeOp) {
        for (String s : OPERATION_LIST) {
            if (Pattern.matches(s, typeOp)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDouble(String num) {
        return Pattern.matches("^[0-9]*[.,]?[0-9]+$", num);
    }

    public static boolean zeroDiv(double divisor, String operation) {
        return (divisor == 0 && operation.equals("division"));
    }

    public static double[] parseToDouble(String[] numStr) {
        double[] nums = new double[numStr.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Double.parseDouble(numStr[i]);
        }
        return nums;
    }
}

