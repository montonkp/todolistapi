package todolistapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import todolistapi.entity.Reservation;

@RepositoryRestResource
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}