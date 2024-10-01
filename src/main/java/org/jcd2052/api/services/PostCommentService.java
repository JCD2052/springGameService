package org.jcd2052.api.services;

import org.jcd2052.api.entities.PostComment;
import org.jcd2052.api.exceptionhandler.exceptions.PostNotFoundExecution;
import org.jcd2052.api.repositories.PostCommentRepository;
import org.springframework.stereotype.Service;

@Service
public class PostCommentService extends BaseService<PostComment> {
    protected PostCommentService(PostCommentRepository repository) {
        super(repository);
    }

    public PostComment findByIdOrThrowError(long postCommentId) {
        return repository.findById(postCommentId).orElseThrow(() -> new PostNotFoundExecution(postCommentId));
    }
}
