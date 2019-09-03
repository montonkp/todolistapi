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

    @GetMapping("/search")
    public Optional<ToDoList> getToDoListsByName(@RequestParam(required = false) String name ){
        return toDoListRepository.findByName(name);
    }

    @GetMapping("")
    public Collection<ToDoList> getToDoLists() {
        return toDoListRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/{toDoListID}")
    public ToDoList getToDoList(@PathVariable Long toDoListID) {
        return toDoListRepository.findById(toDoListID).get();
    }

    @DeleteMapping("/{toDoListID}")
    public void deleteToDoList(@PathVariable long toDoListID) {
        toDoListRepository.deleteById(toDoListID);
    }

    @PostMapping("")
    public ResponseEntity<Object> createToDoList(@RequestBody ToDoList toDoList) {
        ToDoList saveToDoList = toDoListRepository.save(toDoList);

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

        return ResponseEntity.noContent().build();
    }

}