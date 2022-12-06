package com.rhine.redstorm.repository;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import com.rhine.redstorm.domain.TaskList;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class TaskListRepository implements PanacheRepositoryBase<TaskList, Long> {

    public Optional<TaskList> findByName(String name) {
        return find("name", name).firstResultOptional();
    }
}
