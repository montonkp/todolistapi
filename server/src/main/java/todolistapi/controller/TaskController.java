package todolistapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import todolistapi.entity.Task;
import todolistapi.repository.TaskRepository;
import todolistapi.service.FileService;

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
    @Autowired
    private FileService fileService;

    @GetMapping("/search")
    public Optional<Task> getTaskByTitle(@RequestParam(required = false) String title ){
        Optional<Task> taskOptional = taskRepository.findByTitle(title);
        fileService.saveLogFileAsJson(taskOptional);
        return taskOptional;
    }

    @GetMapping("")
    public Collection<Task> getTasks() {
        Collection<Task> taskCollection = taskRepository.findAll().stream().collect(Collectors.toList());
        fileService.saveLogFileAsJson(taskCollection);
        return taskCollection;
    }
    @GetMapping("/{taskID}")
    public Task getTask(@PathVariable Long taskID) {
        Task task = taskRepository.findById(taskID).get();
        fileService.saveLogFileAsJson(task);
        return task;
    }

    @DeleteMapping("/{taskID}")
    public void deleteTask(@PathVariable long taskID) {
        Task task = taskRepository.findById(taskID).get();
        fileService.saveLogFileAsJson(task);
        taskRepository.deleteById(taskID);
    }

    @PostMapping("")
    public ResponseEntity<Object> createTask(@RequestBody Task task) {
        Task saveTask = taskRepository.save(task);
        fileService.saveLogFileAsJson(saveTask);
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
        fileService.saveLogFileAsJson(taskOptional);
        return ResponseEntity.noContent().build();
    }
}
