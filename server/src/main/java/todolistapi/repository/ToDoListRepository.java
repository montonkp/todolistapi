package todolistapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import todolistapi.entity.ToDoList;

@RepositoryRestResource
public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
}