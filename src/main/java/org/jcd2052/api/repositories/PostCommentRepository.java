package org.jcd2052.api.repositories;

import org.jcd2052.api.entities.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
