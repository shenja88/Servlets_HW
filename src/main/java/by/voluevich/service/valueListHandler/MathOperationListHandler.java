package by.voluevich.service.valueListHandler;

import by.voluevich.entity.MathOperation;

import java.util.ArrayList;
import java.util.List;

public class MathOperationListHandler implements ValueListIterator<MathOperation> {
    private final List<MathOperation> mathOperations;

    public MathOperationListHandler(List<MathOperation> mathOperations) {
        this.mathOperations = mathOperations;
    }

    @Override
    public int getSize() {
        return mathOperations.size();
    }

    @Override
    public List<MathOperation> getCurrentElement(int numCurrentPage, int numValuesPage) {
        List<MathOperation> list = new ArrayList<>();
        if (numCurrentPage <= 1) {
            for (int i = 0; i < numValuesPage; i ++){
                if (i < mathOperations.size()){
                    list.add(mathOperations.get(i));
                }
            }
        } else {
            int start = ((numCurrentPage - 1) * numValuesPage);
            for (int i = start; i < start + numValuesPage; i++) {
                if(i < mathOperations.size()){
                    list.add(mathOperations.get(i));
                }
            }
        }
        return list;
    }

    @Override
    public List<MathOperation> getPreviousElements(int numCurrentPage, int numValuesPage) {
        List<MathOperation> list = new ArrayList<>();
        if (numCurrentPage <= 1) {
            for (int i = 0; i < numValuesPage - 1; i ++){
                if (i < mathOperations.size()){
                    list.add(mathOperations.get(i));
                }
            }
        } else {
            int end = ((numCurrentPage - 1) * numValuesPage);
            int start = end - numValuesPage;
            if(start >= 0 && end > 0){
                list = mathOperations.subList(start, end);
            }
        }
        return list;
    }

    @Override
    public List<MathOperation> getNextElements(int numCurrentPage, int numValuesPage) {
        List<MathOperation> list = new ArrayList<>();
        if (numCurrentPage <= 1) {
            for (int i = 0; i < numValuesPage - 1; i ++){
                if (i < mathOperations.size()){
                    list.add(mathOperations.get(i));
                }
            }
        } else {
            int startNext = ((numCurrentPage - 1) * numValuesPage) + numValuesPage;
            for (int i = startNext; i < startNext + numValuesPage; i++) {
                if(i < mathOperations.size()){
                    list.add(mathOperations.get(i));
                }
            }
        }
        return list;
    }
}
