package todolistapi.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todolistapi.entity.ToDoList;
import todolistapi.repository.ToDoListRepository;

import java.time.LocalDate;

@Service
@NoArgsConstructor
public class ToDoListService {

    public Boolean isToDoListDueDate(ToDoList toDoList){
        return LocalDate.now().isAfter(toDoList.getDueDate());
    }

}
