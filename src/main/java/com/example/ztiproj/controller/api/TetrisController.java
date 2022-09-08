package com.example.ztiproj.controller.api;

import com.example.ztiproj.dto.TetrisDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-11
 */
@RestController
@RequestMapping("/tetris")
public interface TetrisController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get overall ranking", notes = "Method returns the ranking of top 10 results.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All results returned successfully"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Server crashed")})
    ResponseEntity<List<TetrisDto>> getRanking();

    @GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get ranking for user", notes = "Method returns the top results for user" +
            " specified in username parameter. Ranking contains up to 10 results.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All user results returned successfully"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Server crashed")})
    ResponseEntity<List<TetrisDto>> getUserRanking(@PathVariable("username") String username);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add result", notes = "Method add the result to database.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All results returned"),
            @ApiResponse(code = 404, message = "Result not found"),
            @ApiResponse(code = 500, message = "Server crashed")})
    ResponseEntity<TetrisDto> addResult(@RequestBody TetrisDto dto);

    @DeleteMapping("/{username}")
    @ApiOperation(hidden = true, value = "Delete result", notes = "Method deletes all user results.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All user results deleted successfully"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Server crashed")})
    void deleteAllUserResults(@PathVariable("username") String username);
}
