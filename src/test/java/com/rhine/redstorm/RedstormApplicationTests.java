package com.rhine.redstorm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.inject.Inject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import com.rhine.redstorm.domain.Task;
import com.rhine.redstorm.domain.TaskList;
import com.rhine.redstorm.repository.TaskListRepository;
import com.rhine.redstorm.repository.TaskRepository;
import com.rhine.redstorm.service.TaskService;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestTransaction
class RedstormApplicationTests {

	@Inject
	private TaskService taskService;

	@Inject
	private TaskListRepository taskListRepository;

	@Inject
	private TaskRepository taskRepository;

	Optional<TaskList> daily;
	Optional<TaskList> mondays;

	@BeforeEach
	void setUp() {
		taskService.createList("Daily");
		taskService.createList("Mondays");

		daily = taskListRepository.findByName("Daily");
		mondays = taskListRepository.findByName("Mondays");

	}
  
	@AfterEach
	void tearDown() {
		taskListRepository.deleteAll();
	}

	@Test
	public void testTaskListCreate() {
		TaskList list = new TaskList();
		list.setName("Weekends");
		taskListRepository.persist(list);

		Optional<TaskList> d1 = taskListRepository.findByName("Weekends");
		
		assertThat(d1)
			.isNotNull()
			.isPresent()
			.get()
			.extracting(TaskList::getName)
			.isEqualTo("Weekends");

		assertThat(d1.get().getId())
			.isNotNull();
	}

	@Test
	public void testNewTaskState() {
		Task task = new Task();
		task.setName("Workout");
		task.setList(daily.get());

		taskRepository.persist(task);
		Task t1 = task;

		assertTrue(t1.getId() != null);
		
	}

	@Test
	public void testTaskAndListCardinality() {

		String taskName = "Brush";
		String desc = "Brushing routine for good oral health";
		LocalDateTime sched = LocalDateTime.now();

		Task task = Task.builder()
			.name(taskName).description(desc).schedule(sched)
			.build();

		TaskList list = taskService.createList("Tasks");

		taskService.addTask(list.getId(), task);

		Optional<Task> t0 = taskRepository.findByName(taskName);


		assertTrue(t0.isPresent());
		assertNotEquals(null, t0.get().getId());
		assertEquals(list.getId(), t0.get().getList().getId());

	}

}
