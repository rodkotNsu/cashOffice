package com.rodkot.security.backend.entity.operation;

import com.rodkot.security.backend.entity.Cash;
import com.rodkot.security.backend.entity.Operation;
import com.rodkot.security.backend.exception.InsufficientFundsException;

@FunctionalInterface
public interface OperationAction {
    void run(Cash cash, Operation operation) throws InsufficientFundsException;
}
