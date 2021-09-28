package com.example.core.team.api;

import com.example.core.team.api.dto.TeamDto;
import com.example.core.team.domain.TeamService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/team", produces = APPLICATION_JSON_VALUE)
public class TeamController {

    private final TeamService teamService;

    @ApiOperation(value = "Add new team ")
    @PostMapping("/v1")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeam(@Validated @RequestBody TeamDto teamDto) {
        teamService.addNewTeam(teamDto);
    }

    @ApiOperation(value = "Update existing team ")
    @PutMapping("/v1")
    @ResponseStatus(HttpStatus.OK)
    public void updateTeam(@RequestBody TeamDto teamDto) {
        teamService.updateTeam(teamDto);
    }

    @ApiOperation(value = "Delete existing team ")
    @DeleteMapping("/v1/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }

}
