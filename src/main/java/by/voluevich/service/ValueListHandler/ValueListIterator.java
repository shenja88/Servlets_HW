package by.voluevich.service.ValueListHandler;

import java.util.List;

public interface ValueListIterator<T> {

    int getSize();

    List<T> getCurrentElement(int numCurrentPage, int numValuesPage);

    List<T> getPreviousElements(int numCurrentPage, int numValuesPage);

    List<T> getNextElements(int numCurrentPage, int numValuesPage);

    void setIndex(int numCurrentPage, int numValuesPage);
}
