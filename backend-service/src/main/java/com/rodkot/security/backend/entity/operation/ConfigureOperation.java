package com.rodkot.security.backend.entity.operation;

import com.rodkot.security.backend.exception.InsufficientFundsException;

public class ConfigureOperation {
    static OperationAction getOperationAction(TypeOperation typeOperation){
        switch (typeOperation){
            case WITHDRAWALS:
                return withdrawalsAction;
            case DEPOSITS:
                return depositsAction;
            default:
                return null;
        }
    }
    private static final OperationAction withdrawalsAction = (cash, operation) -> {
        if (cash.getMoney() < operation.getAmount())
            throw new InsufficientFundsException("нехватает средств для совершения операции");
        else cash.setMoney(cash.getMoney() - operation.getAmount());
    };
    private static final OperationAction depositsAction = (cash, operation) -> {
        cash.setMoney(cash.getMoney() + operation.getAmount());
    };
}
