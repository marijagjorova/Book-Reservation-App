package mk.ukim.finki.lab1.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;



@NoRepositoryBean
public interface JavaSpecificationRepository<T, ID> extends JpaRepository<T, ID> {
    Page<T> findAll(Specification<T> filter, Pageable pageable);
}