package by.voluevich.service;

import by.voluevich.entity.Address;
import by.voluevich.entity.MathOperation;
import by.voluevich.entity.Telephone;
import by.voluevich.entity.User;
import by.voluevich.service.dependencies.ServiceDependencies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class SessionFacade {
    private final Logger logger = LoggerFactory.getLogger(SessionFacade.class.getName());

    public double calculate(User user, String typeOp, double... nums) {
        Optional<MathOperation> mathOperation = ServiceDependencies.CALCULATION.getResultMathOperation(user, typeOp, nums);
        if (mathOperation.isPresent()) {
            ServiceDependencies.RECORD_MATH_OPERATION.record(mathOperation.get());
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
        return ServiceDependencies.EDIT_NAME.editName(user, newName);
    }

    public boolean editPassword(User user, String oldPass, String newPass) {
        return ServiceDependencies.EDIT_PASSWORD.editPassword(user, oldPass, newPass);
    }

    public boolean getRegistration(User user) {
        return ServiceDependencies.REGISTRATION.save(user);
    }

    public Optional<User> getLogIn(User user) {
        return ServiceDependencies.LOG_IN.logIn(user);
    }

    public boolean addAddress(Address address){
        return ServiceDependencies.USER_ADDRESSES.save(address);
    }

    public boolean addTelephone(Telephone telephone){
        return ServiceDependencies.USER_TELEPHONES.save(telephone);
    }

    public List<Address> getUserAddresses(User user){
        return ServiceDependencies.USER_ADDRESSES.getByUser(user);
    }

    public List<Telephone> getUserTelephones(User user){
        return ServiceDependencies.USER_TELEPHONES.getByUser(user);
    }

    public boolean updateAddress(Address address){
        return ServiceDependencies.USER_ADDRESSES.update(address);
    }

    public boolean updateTelephone(Telephone telephone){
        return ServiceDependencies.USER_TELEPHONES.update(telephone);
    }

    public boolean removeTelephone(int id, User user){
        return ServiceDependencies.USER_TELEPHONES.remove(id, user);
    }

    public boolean removeAddress(int id, User user){
        return ServiceDependencies.USER_ADDRESSES.remove(id, user);
    }
}
