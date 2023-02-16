package com.example.ztiproj.controller;

import com.example.ztiproj.common.Labels;
import com.example.ztiproj.dto.MinesweeperDto;
import com.example.ztiproj.service.MinesweeperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-09
 */
@RestController
@RequestMapping("/minesweeper")
@Tag(name = "Minesweeper")
public class MinesweeperController {
    private final MinesweeperService service;

    public MinesweeperController(MinesweeperService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get overall ranking",
            description = "Method returns the ranking of top 10 results.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All results returned successfully")})
    public ResponseEntity<Collection<MinesweeperDto>> getRanking() {
        return ResponseEntity.ok(service.getRanking());
    }

    @GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get ranking for user",
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
    public ResponseEntity<Collection<MinesweeperDto>> getUserRanking(@PathVariable("username") String username) {
        return ResponseEntity.ok(service.getUserRanking(username));
    }

    @PostMapping
    @Operation(
            summary = "Add result",
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
                                    mediaType = MediaType.APPLICATION_JSON_VALUE))},
            hidden = true)
    public ResponseEntity<MinesweeperDto> addResult(@RequestBody @Valid MinesweeperDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addResult(dto));
    }

    @DeleteMapping("/{username}")
    @Operation(
            summary = "Delete result",
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
                                    mediaType = MediaType.APPLICATION_JSON_VALUE))},
            hidden = true)
    public void deleteAllUserResults(@PathVariable("username") String username) {
        service.deleteAllUserResults(username);
    }
}
