package by.voluevich.service.ValueListHandler;

import by.voluevich.entity.MathOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MathOperationListHandler implements ValueListIterator<MathOperation> {
    private final List<MathOperation> mathOperations;
    private ListIterator<MathOperation> iterator;

    public MathOperationListHandler(List<MathOperation> mathOperations) {
        this.mathOperations = mathOperations;
        iterator = mathOperations.listIterator();
    }

    @Override
    public int getSize() {
        return mathOperations.size();
    }

    @Override
    public List<MathOperation> getCurrentElement(int numCurrentPage, int numValuesPage) {
        List<MathOperation> list = new ArrayList<>();
        setIndex(numCurrentPage, numValuesPage);
        for (int i = 0; i < numValuesPage; i++) {
            if(iterator.hasNext()){
                list.add(iterator.next());
            }
        }
        return list;
    }

    @Override
    public List<MathOperation> getPreviousElements(int numCurrentPage, int numValuesPage) {
        List<MathOperation> list = new ArrayList<>();
        setIndex(numCurrentPage, numValuesPage);
        if (numCurrentPage == 1) {
            for (int i = 0; i < numValuesPage; i++) {
                if(iterator.hasNext()){
                    list.add(iterator.next());
                }
            }
            return list;
        } else {
            for (int i = numValuesPage; i > 0; i--) {
                if(iterator.hasPrevious()){
                    list.add(iterator.previous());
                }
            }
        }
        return list;
    }

    @Override
    public List<MathOperation> getNextElements(int numCurrentPage, int numValuesPage) {
        List<MathOperation> list = new ArrayList<>();
        setIndex(numCurrentPage, numValuesPage);

        boolean checkNextPage = true;
        for (int i = 0; i < numValuesPage; i++){
            if(iterator.hasNext()){
                iterator.next();
            }else{
                checkNextPage = false;
            }
        }
        if(checkNextPage){
            for (int i = 0; i < numValuesPage; i++) {
                if(iterator.hasNext()){
                list.add(iterator.next());
                }
            }
        }
        return list;
    }

    @Override
    public void setIndex(int numCurrentPage, int numValuesPage) {
        if (numCurrentPage == 1) {
            iterator = mathOperations.listIterator();
        } else {
            iterator = mathOperations.listIterator((numCurrentPage - 1) * numValuesPage);
        }
    }
}
