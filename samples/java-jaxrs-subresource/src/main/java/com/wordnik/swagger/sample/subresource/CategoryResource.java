package com.wordnik.swagger.sample.subresource;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "")
public class CategoryResource {

    private Integer categoryId;

    public void setId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Get a category by id", response = String.class)
    public Response getCategory() {
        return Response.ok("Category: " + categoryId).build();
    }

}
