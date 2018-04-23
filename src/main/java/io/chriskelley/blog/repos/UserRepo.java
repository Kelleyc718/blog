package io.chriskelley.blog.repos;

import io.chriskelley.blog.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
