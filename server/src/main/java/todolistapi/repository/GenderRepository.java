package todolistapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import todolistapi.entity.Gender;

@RepositoryRestResource
public interface GenderRepository extends JpaRepository<Gender,Long> {
}
