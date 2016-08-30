package com.normativeanimal.social.rest.resources;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/")
@Singleton
public class CurationEntryPoint{

	@GET
	@Path("list/")
	public Response getRawList(@QueryParam("searchTerm") String searchTerm) {
		return Response.ok().build();
	}
}
