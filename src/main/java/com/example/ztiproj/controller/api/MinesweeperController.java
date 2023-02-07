package com.example.ztiproj.controller.api;

import com.example.ztiproj.common.Labels;
import com.example.ztiproj.dto.MinesweeperDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-09
 */
@RequestMapping("/minesweeper")
@Tag(name = "Minesweeper")
public interface MinesweeperController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get overall ranking",
            description = "Method returns the ranking of top 10 results.",
    responses = {
            @ApiResponse(responseCode = "200", description = "All results returned successfully")})
    ResponseEntity<List<MinesweeperDto>> getRanking();

    @GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get ranking for user",
            description = "Method returns the top results for user specified in username parameter. Ranking contains up to 10 results.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All user results returned successfully"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Invalid username",
                            content = @Content(
                                    examples = {@ExampleObject(
                                            value = "{\"message\":\"" + Labels.INVALID_MINESWEEPER_DTO_EXCEPTION_MESSAGE + "\", \"status\":\"BAD_REQUEST\"}"
                                    )},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE))})
    ResponseEntity<List<MinesweeperDto>> getUserRanking(@PathVariable("username") String username);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add result",
            description = "Method add the result to database.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Result successfully added"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Invalid dto!",
                            content = @Content(
                                    examples = {@ExampleObject(
                                            value = "{\"message\":\"" + Labels.INVALID_MINESWEEPER_DTO_EXCEPTION_MESSAGE + "\", \"status\":\"BAD_REQUEST\"}"
                                    )},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE))})
    ResponseEntity<MinesweeperDto> addResult(@RequestBody MinesweeperDto dto);

    @DeleteMapping("/{username}")
    @Operation(summary = "Delete result",
            description = "Method deletes all user results.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "All user results deleted successfully"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Invalid username",
                            content = @Content(
                                    examples = {@ExampleObject(
                                            value = "{\"message\":\"" + Labels.INVALID_MINESWEEPER_DTO_EXCEPTION_MESSAGE + "\", \"status\":\"BAD_REQUEST\"}"
                                    )},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE))})
    void deleteAllUserResults(@PathVariable("username") String username);
}
