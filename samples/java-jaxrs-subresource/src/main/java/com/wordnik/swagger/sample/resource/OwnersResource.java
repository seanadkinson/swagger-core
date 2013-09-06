/**
 *  Copyright 2013 Wordnik, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.wordnik.swagger.sample.resource;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.sample.exception.NotFoundException;
import com.wordnik.swagger.sample.model.Owner;
import com.wordnik.swagger.sample.subresource.OwnerResource;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Path("/owners")
@Api(value = "/owners", description = "Operations about the owners", position = 1)
public class OwnersResource {

  @Context
  UriInfo uriInfo;

  @GET
  @ApiOperation(
    value = "Gets the list of owners",
    response = Owner.class)
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<Owner> getOwners() throws NotFoundException {
    Owner o1 = new Owner();
    o1.setName("Tony");
    o1.setId(1);

    Owner o2 = new Owner();
    o2.setName("Sean");
    o2.setId(2);

    return Arrays.asList(o1, o2);
  }

  @Path("/{ownerId}")
  public OwnerResource getOwner(@ApiParam(value = "ID of owner", required = true) @PathParam("ownerId") long ownerId) {
    Owner owner = findOwnerById(ownerId);
    return new OwnerResource(owner);
  }

  @POST
  @ApiOperation(value = "Add an owner")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response createOwner(@ApiParam(required = true) String name) {
    URI path = uriInfo.getAbsolutePathBuilder().path(Owner.class, "123").build();
    return Response.created(path).build();
  }

  // this might go to a database to find the owner
  private Owner findOwnerById(long ownerId) {
    Owner owner = new Owner();
    owner.setId(ownerId);
    owner.setName("Owner #" + ownerId);
    return owner;
  }

}
