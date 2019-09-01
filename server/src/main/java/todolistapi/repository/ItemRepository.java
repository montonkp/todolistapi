package todolistapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import todolistapi.entity.Item;

@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item,Long> {
}
