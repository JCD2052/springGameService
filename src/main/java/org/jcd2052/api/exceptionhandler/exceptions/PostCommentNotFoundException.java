package org.jcd2052.api.exceptionhandler.exceptions;

public class PostCommentNotFoundException extends RuntimeException {
    public PostCommentNotFoundException(long postCommentId) {
        super("Comment with id %s not found".formatted(postCommentId));
    }
}
