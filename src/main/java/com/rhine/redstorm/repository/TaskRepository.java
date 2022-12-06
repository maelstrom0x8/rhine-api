package com.rhine.redstorm.repository;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import com.rhine.redstorm.domain.Task;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class TaskRepository implements PanacheRepositoryBase<Task, Long> {
    
    public Optional<Task> findByName(String name) {
        return find("name", name).firstResultOptional();
    }
}
