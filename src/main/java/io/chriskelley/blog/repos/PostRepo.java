package io.chriskelley.blog.repos;

import io.chriskelley.blog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepo extends CrudRepository<Post, Long> {

}
