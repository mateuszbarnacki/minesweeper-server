package com.example.ztiproj.controller.api;

import com.example.ztiproj.dto.MinesweeperDto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/minesweeper")
@RequestScoped
public interface MinesweeperController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get overall ranking", description = "Method returns the ranking of top 10 results.")
    Response getRanking();

    @GET
    @Path("/user-results")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get ranking for user", description = "Method returns the top results for user" +
            " specified in username parameter. Ranking contains up to 10 results.")
    Response getUserRanking(@QueryParam("username") String username);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add result", description = "Method add the result to database.")
    Response addResult(@RequestBody(required = true) MinesweeperDto dto);

    @DELETE
    @Operation(hidden = true, summary = "Delete result", description = "Method deletes all user results.")
    @APIResponse(responseCode = "200", description = "All user results deleted successfully")
    @APIResponse(responseCode = "404", description = "User not found")
    @APIResponse(responseCode = "500", description = "Server crashed")
    void deleteResult(@QueryParam("username") String username);

}
