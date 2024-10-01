package org.jcd2052.api.repositories;

import org.jcd2052.api.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByPostUserId(long userId);
}
