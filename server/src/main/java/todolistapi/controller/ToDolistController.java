package todolistapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import todolistapi.entity.ToDoList;
import todolistapi.repository.ToDoListRepository;
import todolistapi.service.FileService;

import java.net.URI;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todolists")
class ToDolistController {
    @Autowired
    private ToDoListRepository toDoListRepository;
    @Autowired
    private FileService fileService;

    @GetMapping("/search")
    public Optional<ToDoList> getToDoListsByName(@RequestParam(required = false) String name ){
        Optional<ToDoList> toDoListOptional = toDoListRepository.findByName(name);
        fileService.saveLogFileAsJson(toDoListOptional);
        return toDoListOptional;
    }

    @GetMapping("")
    public Collection<ToDoList> getToDoLists() {
        Collection<ToDoList> toDoListCollection = toDoListRepository.findAll().stream().collect(Collectors.toList());
        fileService.saveLogFileAsJson(toDoListCollection);
        return toDoListCollection;
    }
    @GetMapping("/{toDoListID}")
    public ToDoList getToDoList(@PathVariable Long toDoListID) {
        ToDoList toDoList = toDoListRepository.findById(toDoListID).get();
        fileService.saveLogFileAsJson(toDoList);
        return toDoList;
    }

    @DeleteMapping("/{toDoListID}")
    public void deleteToDoList(@PathVariable long toDoListID) {
        ToDoList toDoList = toDoListRepository.findById(toDoListID).get();
        fileService.saveLogFileAsJson(toDoList);
        toDoListRepository.deleteById(toDoListID);
    }

    @PostMapping("")
    public ResponseEntity<Object> createToDoList(@RequestBody ToDoList toDoList) {
        ToDoList saveToDoList = toDoListRepository.save(toDoList);
        fileService.saveLogFileAsJson(saveToDoList);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saveToDoList.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{toDoListID}")
    public ResponseEntity<Object> updateToDoList(@RequestBody ToDoList toDoList, @PathVariable long toDoListID) {

        Optional<ToDoList> toDoListOptional = toDoListRepository.findById(toDoListID);

        if (!toDoListOptional.isPresent())
            return ResponseEntity.notFound().build();

        toDoList.setId(toDoListID);

        toDoListRepository.save(toDoList);
        fileService.saveLogFileAsJson(toDoListOptional);
        return ResponseEntity.noContent().build();
    }

}