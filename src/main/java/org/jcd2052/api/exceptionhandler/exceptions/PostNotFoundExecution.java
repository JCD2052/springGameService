package org.jcd2052.api.exceptionhandler.exceptions;

public class PostNotFoundExecution extends RuntimeException {
    public PostNotFoundExecution() {
        super("Post id should not be null or empty");
    }

    public PostNotFoundExecution(long postId) {
        super("Post with id %s not found".formatted(postId));
    }
}
