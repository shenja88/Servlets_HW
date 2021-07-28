package by.voluevich.entity;

import java.util.Objects;

public class MathOperation {
    private double numOne;
    private double numTwo;
    private String typeOp;
    private double result;
    private User user;

    public MathOperation(double numOne, double numTwo, String typeOp, double result, User user) {
        this.numOne = numOne;
        this.numTwo = numTwo;
        this.typeOp = typeOp;
        this.result = result;
        this.user = user;
    }

    public double getNumOne() {
        return numOne;
    }

    public void setNumOne(double numOne) {
        this.numOne = numOne;
    }

    public double getNumTwo() {
        return numTwo;
    }

    public void setNumTwo(double numTwo) {
        this.numTwo = numTwo;
    }

    public String getTypeOp() {
        return typeOp;
    }

    public void setTypeOp(String typeOp) {
        this.typeOp = typeOp;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathOperation that = (MathOperation) o;
        return numOne == that.numOne && numTwo == that.numTwo && Objects.equals(typeOp, that.typeOp)
                && Objects.equals(result, that.result) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numOne, numTwo, typeOp, result, user);
    }

    @Override
    public String toString() {
        return "Operation: " + typeOp + ", with number " + numOne + " and " + numTwo + ". Result: " + result +
                ". Operator: " + user.getLogin();
    }
}
