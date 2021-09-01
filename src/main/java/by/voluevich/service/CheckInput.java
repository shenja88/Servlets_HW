package by.voluevich.service;

import java.util.regex.Pattern;

public class CheckInput {

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

