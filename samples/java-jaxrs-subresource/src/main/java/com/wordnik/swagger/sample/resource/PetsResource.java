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
import com.wordnik.swagger.sample.model.Pet;
import com.wordnik.swagger.sample.subresource.PetResource;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/pets")
@Api(value = "/pets", description = "Operations about pets", position = 0)
public class PetsResource {
  static JavaRestResourceUtil ru = new JavaRestResourceUtil();

  @Context
  UriInfo uriInfo;

  @GET
  @Path("/{petId}")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public PetResource getPetById(
      @ApiParam(value = "ID of pet that needs to be fetched", allowableValues = "range[1,5]", required = true) @PathParam("petId") String petIdStr) {
    long petId = ru.getLong(0, 100000, 0, petIdStr);
    return new PetResource(petId, uriInfo);
  }

  @POST
  @ApiOperation(value = "Create a new Pet", response = Pet.class)
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response createPet(@ApiParam(required = true) String name) {
    Pet pet = new Pet();
    pet.setName(name);
    pet.setId(123);
    URI newPetPath = uriInfo.getAbsolutePathBuilder().path(Pet.class, String.valueOf(pet.getId())).build();
    return Response.created(newPetPath).build();
  }

}
