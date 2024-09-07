package com.acme.web.services.management.interfaces.rest.transform;

import com.acme.web.services.management.domain.model.commands.UpdateResourceCommand;
import com.acme.web.services.management.interfaces.rest.resources.UpdateResourceResource;

/**
 * This class represents the assembler for the UpdateResourceCommand from the UpdateResourceResource.
 * It is used to transform the UpdateResourceResource into the UpdateResourceCommand.
 * @author Giacomo Zoppi Rodriguez - u202210029
 * @version 1.0
 */
public class UpdateResourceCommandFromResourceAssembler {
    public static UpdateResourceCommand toCommandFromResource(Long resourceId, UpdateResourceResource resource) {
        return new UpdateResourceCommand(
            resourceId, resource.name(), resource.type(), resource.quantity(), resource.date(), resource.observations(), resource.breederId()
        );
    }
}
