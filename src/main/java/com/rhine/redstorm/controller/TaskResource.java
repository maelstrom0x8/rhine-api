package com.rhine.redstorm.controller;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.rhine.redstorm.domain.TaskList;
import com.rhine.redstorm.service.TaskService;


@Path("/api/v1/task")
public class TaskResource {

    @Inject
    TaskService taskService;

    @Path("/create/{name}")
    public TaskList createNewList(@QueryParam("name") String name) {
        return taskService.createList(name);
    }
    
}
