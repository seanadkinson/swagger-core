package com.wordnik.swagger.sample.resource;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.sample.subresource.PickListSubResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/picklists")
@Api(value = "/picklists", description = "Operations on Pick Lists")
public class PickListsResource {

    @Path("/{id}")
    @ApiOperation(value = "Gets the PickList sub-resource", response = PickListSubResource.class)
    public PickListSubResource getPickList(@PathParam("id") final Integer id) {
        PickListSubResource pickListSubResource = new PickListSubResource();
        pickListSubResource.setId(id);
        return pickListSubResource;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Get all Pick Lists", response = String.class, responseContainer = "List")
    public Response getPickLists() {
        List<String> picklists = Arrays.asList("First list", "Second list", "Third list");
        return Response.ok(picklists).build();
    }

}

