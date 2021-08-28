package by.voluevich.service;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import by.voluevich.service.dependencies.DependenciesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class SessionFacade {
    private final Logger logger = LoggerFactory.getLogger(SessionFacade.class.getName());

    public double calculate(User user, String typeOp, double... nums) {
        Optional<MathOperation> mathOperation = DependenciesService.calculation.getResultMathOperation(user, typeOp, nums);
        if (mathOperation.isPresent()) {
            DependenciesService.recordMathOperation.record(mathOperation.get());
            double resultNum = mathOperation.get().getResult();
            logger.debug("Successful request to operation ({}) with num ({},{}) and result({}) for user {}.",
                    typeOp, nums[0], nums[1], resultNum, user.getName());
            return resultNum;
        } else {
            logger.warn("Request to operation ({}) with num ({},{}) and result({}) for user {}.",
                    typeOp, nums[0], nums[1], -1, user.getName());
            return -1;
        }
    }

    public boolean editName(User user, String newName) {
        return DependenciesService.editName.editName(user, newName);
    }

    public boolean editPassword(User user, String oldPass, String newPass) {
        if (DependenciesService.editPassword.checkNewPass(user, newPass)) {
            return DependenciesService.editPassword.editPassword(user, oldPass, newPass);
        } else {
            return false;
        }
    }

    public List<MathOperation> getLogBySession(User user) {
        return DependenciesService.mathOperationHistory.getBySession(user);
    }

    public List<MathOperation> getLogByType(String type, User user) {
        return DependenciesService.mathOperationHistory.getByType(type, user);
    }

    public boolean getRegistration(User user) {
        return DependenciesService.registration.save(user);
    }

    public Optional<User> getLogIn(User user) {
        return DependenciesService.logIn.logIn(user);
    }


    public List<List<MathOperation>> getLogBySession2(int numCurrentPage, int numValuesPage, User user) {
        return DependenciesService.mathOperationLog.listForResponseBySession(numCurrentPage, numValuesPage, user);
    }

    public List<List<MathOperation>> getLogByType2(int numCurrentPage, int numValuesPage, String type, User user) {
        return DependenciesService.mathOperationLog.listForResponseByType(numCurrentPage, numValuesPage, type, user);
    }
}
