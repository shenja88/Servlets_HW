package by.voluevich.service.utils;

import by.voluevich.dao.MathOperationDao;
import by.voluevich.dao.MathOperationDaoImpl;
import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import by.voluevich.service.ValueListHandler.MathOperationListHandler;

import java.util.ArrayList;
import java.util.List;

public class MathOperationLog {
    private final MathOperationDao mathOperationDao = new MathOperationDaoImpl();
    private final  List<List<MathOperation>> cashPages = new ArrayList<>();

    public List<List<MathOperation>> listForResponseBySession (int numCurrentPage, int numValuesPage, User user){
        MathOperationListHandler mathOperationListHandler = new MathOperationListHandler(mathOperationDao.getBySession(user));
        List<List<MathOperation>> listBySession = new ArrayList<>();
        listBySession.add(mathOperationListHandler.getCurrentElement(numCurrentPage, numValuesPage));
        listBySession.add(mathOperationListHandler.getPreviousElements(numCurrentPage, numValuesPage));
        listBySession.add(mathOperationListHandler.getNextElements(numCurrentPage, numValuesPage));
        recordToCash(listBySession);
        return listBySession;
    }

    public List<List<MathOperation>> listForResponseByType (int numCurrentPage, int numValuesPage, String type, User user){
        MathOperationListHandler mathOperationListHandler = new MathOperationListHandler(mathOperationDao.getByType(type, user));
        List<List<MathOperation>> listByType = new ArrayList<>();
        listByType.add(mathOperationListHandler.getCurrentElement(numCurrentPage, numValuesPage));
        listByType.add(mathOperationListHandler.getPreviousElements(numCurrentPage, numValuesPage));
        listByType.add(mathOperationListHandler.getNextElements(numCurrentPage, numValuesPage));
        recordToCash(listByType);
        return listByType;
    }

    private void recordToCash(List<List<MathOperation>> list){
        cashPages.addAll(list);
    }
}
