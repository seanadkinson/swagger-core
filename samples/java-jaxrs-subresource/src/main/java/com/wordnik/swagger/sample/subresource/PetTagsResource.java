package com.wordnik.swagger.sample.subresource;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.sample.model.Pet;
import com.wordnik.swagger.sample.model.Tag;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Api("")
public class PetTagsResource {

  private long petId;
  private final UriInfo uriInfo;

  public PetTagsResource(long petId, UriInfo uriInfo) {
    this.petId = petId;
    this.uriInfo = uriInfo;
  }

  @GET
  @ApiOperation(value = "Gets the tags for a pet")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response getTags() {
    List<Tag> tags = getTagsForPet(petId);
    return Response.ok(tags).build();
  }

  @POST
  @ApiOperation(value = "Adds a tag to a pet")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response addTag(@ApiParam(required = true) String name) {
    Tag tag = new Tag();
    tag.setName(name);
    saveTag(tag);
    URI newTagPath = uriInfo.getAbsolutePathBuilder().path(Pet.class, String.valueOf(tag.getId())).build();
    return Response.created(newTagPath).build();
  }

  // This would normally be implemented as a service method or whatnot
  private List<Tag> getTagsForPet(long petId) {
    Tag tag1 = new Tag();
    tag1.setId(1);
    tag1.setName("Tag 1");

    Tag tag2 = new Tag();
    tag2.setId(2);
    tag2.setName("Tag 2");

    return Arrays.asList(tag1, tag2);
  }

  // This would be a database INSERT operation
  private void saveTag(Tag tag) {
    tag.setId(33);
  }


}
