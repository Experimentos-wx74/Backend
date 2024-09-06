package com.acme.web.services.management.domain.services;

import com.acme.web.services.management.domain.model.aggregates.Expense;
import com.acme.web.services.management.domain.model.commands.CreateExpenseCommand;
import com.acme.web.services.management.domain.model.commands.DeleteExpenseCommand;
import com.acme.web.services.management.domain.model.commands.UpdateExpenseCommand;

import java.util.Optional;

/**
 * Expense command service interface.
 * It defines the methods that must be implemented by the ExpenseCommandServiceImpl class.
 * @author Giacomo Zoppi Rodriguez - u2022210029
 * @version 1.0
 */
public interface ExpenseCommandService {
    Long handle(CreateExpenseCommand command);
    Optional<Expense> handle(UpdateExpenseCommand command);
    Optional<Expense> handle(DeleteExpenseCommand command);
}
