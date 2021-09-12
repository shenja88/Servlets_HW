package by.voluevich.service;

import by.voluevich.dao.HibernateMathOperationDaoIml;
import by.voluevich.dao.InMemoryMathOperationDaoImpl;
import by.voluevich.dao.MathOperationDao;
import by.voluevich.dao.JDBCMathOperationDaoImpl;
import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import by.voluevich.service.valueListHandler.MathOperationListHandler;

import java.util.List;

public class MathOperationHistory {
    private final MathOperationDao mathOperationDao = new HibernateMathOperationDaoIml();
    private int sizeThisListForResp = 0;

    public List<MathOperation> listForResponseBySession(int numCurrentPage, int numValuesPage, User user) {
        MathOperationListHandler mathOperationListHandler = new MathOperationListHandler(mathOperationDao.getBySession(user));
        List<MathOperation> listBySessionPage = mathOperationListHandler.getCurrentElement(numCurrentPage, numValuesPage);
        sizeThisListForResp = setNumPages(mathOperationListHandler.getSize(), numValuesPage);
        return listBySessionPage;
    }

    public List<MathOperation> listForResponseByType(int numCurrentPage, int numValuesPage, String type, User user) {
        MathOperationListHandler mathOperationListHandler = new MathOperationListHandler(mathOperationDao.getByType(type, user));
        List<MathOperation> listByType = mathOperationListHandler.getCurrentElement(numCurrentPage, numValuesPage);
        sizeThisListForResp = setNumPages(mathOperationListHandler.getSize(), numValuesPage);
        return listByType;
    }

    public int getSizeListForResponse(){
        return sizeThisListForResp;
    }

    private int setNumPages(int sizeList, int numValuesPage){
        if(sizeList % numValuesPage == 0){
            return sizeList / numValuesPage;
        }else{
            return sizeList / numValuesPage + 1;
        }
    }

}
