package todolistapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import todolistapi.entity.Type;

@RepositoryRestResource
public interface TypeRepository extends JpaRepository<Type,Long> {
    
}
