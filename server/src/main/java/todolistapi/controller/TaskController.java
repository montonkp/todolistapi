package todolistapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import todolistapi.entity.Task;
import todolistapi.repository.TaskRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/search")
    public Optional<Task> getTaskByTitle(@RequestParam(required = false) String title ){
        return taskRepository.findByTitle(title);
    }

    @GetMapping("")
    public Collection<Task> getTasks() {
        return taskRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/{taskID}")
    public Task getTask(@PathVariable Long taskID) {
        return taskRepository.findById(taskID).get();
    }

    @DeleteMapping("/{taskID}")
    public void deleteTask(@PathVariable long taskID) {
        taskRepository.deleteById(taskID);
    }

    @PostMapping("")
    public ResponseEntity<Object> createTask(@RequestBody Task task) {
        Task saveTask = taskRepository.save(task);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saveTask.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{taskID}")
    public ResponseEntity<Object> updateTask(@RequestBody Task task, @PathVariable long taskID) {

        Optional<Task> taskOptional = taskRepository.findById(taskID);

        if (!taskOptional.isPresent())
            return ResponseEntity.notFound().build();

        task.setId(taskID);

        taskRepository.save(task);

        return ResponseEntity.noContent().build();
    }
}
