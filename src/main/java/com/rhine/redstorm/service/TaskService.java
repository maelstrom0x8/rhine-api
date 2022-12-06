package com.rhine.redstorm.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import com.rhine.redstorm.domain.Task;
import com.rhine.redstorm.domain.TaskList;
import com.rhine.redstorm.repository.TaskListRepository;

@ApplicationScoped
public class TaskService implements ITaskService {

    
    private final TaskListRepository taskListRepository;

    public TaskService(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public TaskList createList(String name) {
        TaskList list = new TaskList();
        list.setName(name);
        taskListRepository.persist(list);
        return list;
    }

    @Override
    public void deleteList(Long id) {
        taskListRepository.deleteById(id);
    }

    @Override
    public void renameList(Long id, String name) {
        
    }

    @Override
    public void addTask(Long list_id, Task task) {
        Optional<TaskList> list = Optional.ofNullable(taskListRepository.findById(list_id));
        
        if(list.isPresent()) {
            list.get().getTasks().add(task);
            task.setList(list.get());

            taskListRepository.persist(list.get());
        }
    }

    @Override
    public void removeTask(Long id) {
        taskListRepository.deleteById(id);
    }

    @Override
    public void clearList(Long id) {

    }

    @Override
    public void removeTasks(List<Long> ids) {
        ids.stream().forEach(i -> taskListRepository.deleteById(i));
    }

    @Override
    public void updateTask(Long id, Task task) {
        
    }
    
}
