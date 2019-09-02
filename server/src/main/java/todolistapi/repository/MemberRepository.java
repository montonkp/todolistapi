package todolistapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import todolistapi.entity.Member;

@RepositoryRestResource
public interface MemberRepository extends JpaRepository<Member, Long> {
}
