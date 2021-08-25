package by.voluevich.service.utils;

import by.voluevich.dao.MathOperationDao;
import by.voluevich.entity.MathOperation;

public class RecordMathOperation {
    private MathOperationDao mathOperationDao;

    public RecordMathOperation(MathOperationDao mathOperationDao) {
        this.mathOperationDao = mathOperationDao;
    }

    public RecordMathOperation() {
    }

    public MathOperationDao getMathOperationDao() {
        return mathOperationDao;
    }

    public void setMathOperationDao(MathOperationDao mathOperationDao) {
        this.mathOperationDao = mathOperationDao;
    }

    public void record(MathOperation mathOperation){
        mathOperationDao.save(mathOperation);
    }
}
