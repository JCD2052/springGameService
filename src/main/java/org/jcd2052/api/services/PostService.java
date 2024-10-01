package org.jcd2052.api.services;

import org.jcd2052.api.entities.Post;
import org.jcd2052.api.exceptionhandler.exceptions.PostNotFoundExecution;
import org.jcd2052.api.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService extends BaseService<Post> {
    protected PostService(PostRepository repository) {
        super(repository);
    }

    public Post findByIdOrThrowError(long id) {
        return repository.findById(id).orElseThrow(() -> new PostNotFoundExecution(id));
    }

    public List<Post> fetchPostsByUserId(long userId) {
        return ((PostRepository) repository).findPostsByPostUserId(userId);
    }
}
