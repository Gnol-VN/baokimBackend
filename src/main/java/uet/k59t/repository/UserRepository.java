package uet.k59t.repository;

import org.springframework.data.repository.CrudRepository;
import uet.k59t.model.User;

/**
 * Created by Long on 11/21/2016.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);
}
