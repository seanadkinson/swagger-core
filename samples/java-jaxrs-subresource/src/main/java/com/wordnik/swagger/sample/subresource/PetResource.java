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

package com.wordnik.swagger.sample.subresource;

import com.wordnik.swagger.annotations.*;
import com.wordnik.swagger.sample.data.PetData;
import com.wordnik.swagger.sample.exception.NotFoundException;
import com.wordnik.swagger.sample.model.Owner;
import com.wordnik.swagger.sample.model.Pet;
import com.wordnik.swagger.sample.resource.JavaRestResourceUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Api("")
public class PetResource {
  static PetData petData = new PetData();
  static JavaRestResourceUtil ru = new JavaRestResourceUtil();

  private final long petId;
  private final UriInfo uriInfo;

  public PetResource(long petId, UriInfo uriInfo) {
    this.petId = petId;
    this.uriInfo = uriInfo;
  }

  @Path("/owner")
  public OwnerResource getOwners(@PathParam("petId") String petId) throws NotFoundException {
    Owner ownerForPet = findOwnerForPet(petId);
    return new OwnerResource(ownerForPet);
  }

  @GET
  @ApiOperation(value = "Find pet by ID",
    notes = "Returns a pet when ID < 10.  ID > 10 or nonintegers will simulate API error conditions",
    response = Pet.class)
  @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
      @ApiResponse(code = 404, message = "Pet not found") })
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Pet getPetById(
      @ApiParam(value = "ID of pet that needs to be fetched", allowableValues = "range[1,5]", required = true) @PathParam("petId") String petId)
      throws NotFoundException {
    return findPet(petId);
  }

  @GET
  @Path("/tags")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public PetTagsResource getPetTags() {
    return new PetTagsResource(petId, uriInfo);
  }

  private Pet findPet(String petId) throws NotFoundException {
    Pet pet = petData.getPetbyId(ru.getLong(0, 100000, 0, petId));
    if (null != pet) {
      return pet;
    } else {
      throw new NotFoundException(404, "Pet not found");
    }
  }

  private Owner findOwnerForPet(String petId) throws NotFoundException {
    Pet pet = findPet(petId);
    Owner owner = new Owner();
    owner.setId(pet.getId() + 500);
    owner.setName(pet.getName() + "'s Owner");
    return owner;
  }



}
