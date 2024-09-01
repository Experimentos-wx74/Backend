package com.acme.web.services.management.interfaces.rest.transform;

import com.acme.web.services.management.domain.model.commands.UpdateExpenseCommand;
import com.acme.web.services.management.interfaces.rest.resources.UpdateExpenseResource;

/**
 * This class represents the assembler for the UpdateExpenseCommand from the UpdateExpenseResource.
 * @author Giacomo Zoppi Rodriguez - u202210029
 * @version 1.0
 */
public class UpdateExpenseCommandFromResourceAssembler {
    public static UpdateExpenseCommand toCommandFromResource(Long expenseId, UpdateExpenseResource resource) {
        return new UpdateExpenseCommand(
            expenseId, resource.name(), resource.type(), resource.amount(), resource.date(), resource.observations(), resource.breederId()
        );
    }
}
