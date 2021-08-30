package by.voluevich.service;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import by.voluevich.service.dependencies.ServiceDependencies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class SessionFacade {
    private final Logger logger = LoggerFactory.getLogger(SessionFacade.class.getName());

    public double calculate(User user, String typeOp, double... nums) {
        Optional<MathOperation> mathOperation = ServiceDependencies.calculation.getResultMathOperation(user, typeOp, nums);
        if (mathOperation.isPresent()) {
            ServiceDependencies.recordMathOperation.record(mathOperation.get());
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
        return ServiceDependencies.editName.editName(user, newName);
    }

    public boolean editPassword(User user, String oldPass, String newPass) {
        if (ServiceDependencies.editPassword.checkNewPass(user, newPass)) {
            return ServiceDependencies.editPassword.editPassword(user, oldPass, newPass);
        } else {
            return false;
        }
    }

    public boolean getRegistration(User user) {
        return ServiceDependencies.registration.save(user);
    }

    public Optional<User> getLogIn(User user) {
        return ServiceDependencies.logIn.logIn(user);
    }

}
