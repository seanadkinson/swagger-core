package com.wordnik.swagger.sample.resource;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.sample.model.Category;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Path("/categories")
@Api(value = "/categories", description = "Operations on categories")
public class CategoriesResource {

  @Context
  UriInfo uriInfo;

//  @Path("/{categoryId}")
//  public CategoryResource getCategory(@PathParam("categoryId") final Integer id) {
//      CategoryResource categoryResource = new CategoryResource();
//      categoryResource.setId(id);
//      return categoryResource;
//  }

  @GET
  @ApiOperation(value = "Get all catgeories", response = Category.class, responseContainer = "List")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<Category> getPickLists() {
    Category c1 = new Category();
    c1.setId(1);
    c1.setName("First category");

    Category c2 = new Category();
    c2.setId(2);
    c2.setName("Second category");

    return Arrays.asList(c1, c2);
  }

  @POST
  @ApiOperation(value = "Add a new category")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response createCategory(@ApiParam(required = true) String name) {
    Category category = new Category();
    category.setName(name);

    URI path = uriInfo.getAbsolutePathBuilder().path(Category.class, "123").build();
    return Response.created(path).build();
  }
}

