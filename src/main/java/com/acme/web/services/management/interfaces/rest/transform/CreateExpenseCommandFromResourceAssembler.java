package com.acme.web.services.management.interfaces.rest.transform;

import com.acme.web.services.management.domain.model.commands.CreateExpenseCommand;
import com.acme.web.services.management.interfaces.rest.resources.CreateExpenseResource;

/**
 * This class represents the assembler for the CreateExpenseCommand from the CreateExpenseResource.
 * @author Giacomo Zoppi Rodriguez - u202210029
 * @version 1.0
 */

public class CreateExpenseCommandFromResourceAssembler {
    public static CreateExpenseCommand toCommandFromResource(CreateExpenseResource resource){
        return new CreateExpenseCommand(
                resource.name(),
                resource.type(),
                resource.amount(),
                resource.date(),
                resource.observations(),
                resource.breederId()
        );
    }
}
