package by.voluevich.service.utils;

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
            if (Pattern.matches(s, typeOp)){
                return true;
            }
        }
        return false;
    }

    public static boolean checkDouble (String num){
        return Pattern.matches("^[0-9]*[.,]?[0-9]+$", num);
    }

    public static boolean zeroDiv (double divisor, String operation){
        return (divisor == 0 && operation.equals("division"));
    }
}

