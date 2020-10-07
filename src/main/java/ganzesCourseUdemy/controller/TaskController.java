package ganzesCourseUdemy.controller;

import ganzesCourseUdemy.model.Task;
import ganzesCourseUdemy.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private final TaskRepository taskRepository;

    public TaskController(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping(path = "/tasks", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Task>> readAllTasks(){
        logger.warn("Exposing all the tasks!");
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @GetMapping(path = "/tasks")
    ResponseEntity<List<Task>> readAllTasks(Pageable page){
        logger.info("Custom pageable!");
        return ResponseEntity.ok(taskRepository.findAll(page).getContent());
    }

    @PutMapping("/tasks/{id}")
    ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody @Valid Task toUpdate){
        if (!taskRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        taskRepository.findById(id)
                .ifPresent(task -> {
                    task.updateFrom(toUpdate);
                    taskRepository.save(task);
                });
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tasks/{id}")
    ResponseEntity<?> getTask(@PathVariable int id){
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()){
            logger.info("Single task not found.");
            return ResponseEntity.notFound().build();
        }

        logger.info("Single task has been found.");
        return ResponseEntity.ok(taskOptional);
    }

    @PostMapping("/tasks")
    ResponseEntity<?> createTask(@RequestBody @Valid Task newTask){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(taskRepository.save(newTask))
                .toUri();

        logger.info("New task has been created!");
        return ResponseEntity.created(location).build();
    }

    @Transactional
    @PatchMapping("/tasks/{id}")
    public ResponseEntity<?> toggleTask(@PathVariable int id){
        if (!taskRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
            taskRepository.findById(id)
                    .ifPresent(task -> task.setDone(!task.isDone()));
        return ResponseEntity.noContent().build();
    }
}