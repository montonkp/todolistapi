package todolistapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import todolistapi.entity.Task;

import java.util.Optional;

@RepositoryRestResource
public interface TaskRepository extends JpaRepository<Task,Long> {
    Optional<Task> findByTitle(String title);
}
