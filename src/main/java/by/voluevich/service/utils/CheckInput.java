package by.voluevich.service.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CheckInput {
    private static final List<String> OPERATION_LIST = Arrays.asList("add", "sub", "mp", "div", "mod");

    public static boolean isExistOperation(String typeOp) {
        for (String s : OPERATION_LIST) {
            if (Pattern.matches(s, typeOp)){
                return true;
            }
        }
        return false;
    }
}
