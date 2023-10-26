package dev.edugovi.dir.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


@RepositoryRestResource(excerptProjection = UserProtected.class)
public interface UserRepository extends PagingAndSortingRepository<User, Long>, CrudRepository<User, Long>, CustomizedUserRepository {
    @RestResource(path = "idstr", rel = "idstr")
    User findByIdStr(@Param("is") String idStr);

    @RestResource(path = "idnum", rel = "idnum")
    User findByIdNum(@Param("is") Long idNum);

    @RestResource(path = "enabled", rel = "enabled")
    List<User> findByEnabled(@Param("is") boolean enabled);

    @RestResource(path = "emailIsNotNull", rel = "emailIsNotNull")
    List<User> findByEmailIsNotNull();

    @RestResource(path = "emailIs", rel = "emailIs")
    List<User> findByEmailIs(@Param("email") String email);

    @RestResource(path = "emailIsNot", rel = "emailIsNot")
    List<User> findByEmailIsNot(@Param("email") String email);

    Optional<User> findById(Long id);
    
}
