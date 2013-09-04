package com.wordnik.swagger.sample.subresource;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Api(value = "/")
public class PickListSubResource {

    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Get a Pick List by its API ID", response = String.class)
    public Response getPickList() {
        return Response.ok("Picklist: " + id).build();
    }

    @GET
    @Path("/options")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Get the options for a Pick List by its API ID", response = String.class, responseContainer = "List")
    public Response getPickListOptions() {
        List<String> options = Arrays.asList("One", "Two", "Three");
        return Response.ok(options).build();
    }

}
