package org.jcd2052.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.jcd2052.api.auth.IAuthenticationFacade;
import org.jcd2052.api.constants.ApiConstants;
import org.jcd2052.api.dto.input.PostCommentInputDto;
import org.jcd2052.api.dtoconverters.PostCommentDtoConverter;
import org.jcd2052.api.entities.Post;
import org.jcd2052.api.entities.PostComment;
import org.jcd2052.api.entities.User;
import org.jcd2052.api.exceptionhandler.exceptions.PostNotFoundExecution;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.repsonses.ResponseFactory;
import org.jcd2052.api.services.PostCommentService;
import org.jcd2052.api.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comments")
@Tag(name = "Post comments")
public class PostCommentsController {
    private final PostService postService;
    private final PostCommentService postCommentService;
    private final PostCommentDtoConverter postCommentDtoConverter;
    private final IAuthenticationFacade authenticationFacade;

    @Operation(summary = "Fetch comment records by post")
    @GetMapping(produces = ApiConstants.APPLICATION_CONTENT_TYPE)
    public ResponseEntity<BaseResponse> fetchComments(@RequestParam(required = false) Long postId,
                                                      @RequestParam(required = false) Long userId) {
        PostComment postCommentProbe = PostComment.builder()
                .post(Post.builder().id(postId).build())
                .commentUser(User.builder().id(userId).build())
                .build();

        return ResponseFactory.createResponse(
                postCommentDtoConverter.createDtoListFromEntities(postCommentService.fetchEntities(postCommentProbe)),
                HttpStatus.OK);
    }

    @Operation(summary = "Create a post comment record")
    @Transactional
    @PostMapping(
            consumes = ApiConstants.APPLICATION_CONTENT_TYPE,
            produces = ApiConstants.APPLICATION_CONTENT_TYPE)
    public ResponseEntity<BaseResponse> addComment(@RequestBody PostCommentInputDto input) {
        if (input.getPostId() == null) {
            throw new PostNotFoundExecution();
        }

        Post postToAddComment = postService.findByIdOrThrowError(input.getPostId());
        PostComment postComment = PostComment.builder()
                .comment(input.getContent())
                .post(postToAddComment)
                .commentUser(authenticationFacade.getAuthenticatedUser())
                .build();

        postCommentService.save(postComment);
        return ResponseFactory.createResponse(postCommentDtoConverter.convertToDto(postComment), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a post comment record")
    @Transactional
    @PutMapping(
            consumes = ApiConstants.APPLICATION_CONTENT_TYPE,
            produces = ApiConstants.APPLICATION_CONTENT_TYPE,
            value = "/{commentId}")
    public ResponseEntity<BaseResponse> updateComment(
            @PathVariable long commentId,
            @RequestBody PostCommentInputDto input) {
        PostComment postComment = postCommentService.findByIdOrThrowError(commentId);
        postComment.setComment(input.getContent());

        postCommentService.save(postComment);
        return ResponseFactory.createResponse(postCommentDtoConverter.convertToDto(postComment), HttpStatus.OK);
    }

    @Operation(summary = "Delete a post comment record")
    @Transactional
    @DeleteMapping(
            consumes = ApiConstants.APPLICATION_CONTENT_TYPE,
            produces = ApiConstants.APPLICATION_CONTENT_TYPE,
            value = "/{commentId}")
    public ResponseEntity<BaseResponse> deleteComment(@PathVariable long commentId) {
        postCommentService.delete(postCommentService.findByIdOrThrowError(commentId));
        return ResponseFactory.createResponse(
                "Post comment with id %s has been deleted".formatted(commentId),
                HttpStatus.OK);
    }
}
