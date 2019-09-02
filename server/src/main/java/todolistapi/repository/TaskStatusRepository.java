package todolistapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import todolistapi.entity.TaskStatus;

@RepositoryRestResource
public interface TaskStatusRepository extends JpaRepository<TaskStatus,Long> {
    
}
