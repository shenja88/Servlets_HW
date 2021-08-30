package by.voluevich.service;

import by.voluevich.dao.MathOperationDao;
import by.voluevich.dao.MathOperationDaoImpl;
import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import by.voluevich.service.valueListHandler.MathOperationListHandler;

import java.util.ArrayList;
import java.util.List;

public class MathOperationHistory {
    private final MathOperationDao mathOperationDao = new MathOperationDaoImpl();
    private int sizeThisListForResp = 0;

    public List<List<MathOperation>> listForResponseBySession(int numCurrentPage, int numValuesPage, User user) {
        MathOperationListHandler mathOperationListHandler = new MathOperationListHandler(mathOperationDao.getBySession(user));
        List<List<MathOperation>> listBySession = new ArrayList<>();

        listBySession.add(mathOperationListHandler.getCurrentElement(numCurrentPage, numValuesPage));
        listBySession.add(mathOperationListHandler.getPreviousElements(numCurrentPage, numValuesPage));
        listBySession.add(mathOperationListHandler.getNextElements(numCurrentPage, numValuesPage));
        sizeThisListForResp = (mathOperationListHandler.getSize() / numValuesPage) + 1;
        return listBySession;
    }

    public List<List<MathOperation>> listForResponseByType(int numCurrentPage, int numValuesPage, String type, User user) {
        MathOperationListHandler mathOperationListHandler = new MathOperationListHandler(mathOperationDao.getByType(type, user));
        List<List<MathOperation>> listByType = new ArrayList<>();

        listByType.add(mathOperationListHandler.getCurrentElement(numCurrentPage, numValuesPage));
        listByType.add(mathOperationListHandler.getPreviousElements(numCurrentPage, numValuesPage));
        listByType.add(mathOperationListHandler.getNextElements(numCurrentPage, numValuesPage));
        sizeThisListForResp = (mathOperationListHandler.getSize() / numValuesPage) + 1;
        return listByType;
    }

    public int getSizeListForResponse(){
        return sizeThisListForResp;
    }

}
