package com.wordnik.swagger.sample.subresource;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.sample.model.Owner;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("")
public class OwnerResource {

  private Owner owner;

  public OwnerResource(Owner owner) {
    this.owner = owner;
  }

  @GET
  @ApiOperation("Get owner by id")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Owner getOwner() {
    return owner;
  }

  @PUT
  @ApiOperation("Update the name of an Owner")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response updateName(@ApiParam(required = true) String name) {
    owner.setName(name);
    return Response.ok().build();
  }

}
