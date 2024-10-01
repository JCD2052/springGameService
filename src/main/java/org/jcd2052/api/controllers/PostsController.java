package org.jcd2052.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.jcd2052.api.auth.IAuthenticationFacade;
import org.jcd2052.api.constants.ApiConstants;
import org.jcd2052.api.dto.input.PostInputDto;
import org.jcd2052.api.dtoconverters.PostDtoConverter;
import org.jcd2052.api.entities.Post;
import org.jcd2052.api.entities.User;
import org.jcd2052.api.entities.enums.PostType;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.repsonses.ResponseFactory;
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

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
@Tag(name = "Posts")
public class PostsController {
    private final PostDtoConverter postDtoConverter;
    private final PostService postService;
    private final IAuthenticationFacade authenticationFacade;

    @Operation(summary = "Fetch posts record")
    @GetMapping(produces = ApiConstants.APPLICATION_CONTENT_TYPE)
    public ResponseEntity<BaseResponse> fetchPosts(@RequestParam(required = false) Long userId,
                                                   @RequestParam(required = false) String postType) {
        Post postProbe = Post.builder()
                .postUser(User.builder().id(userId).build())
                .postType(Enum.valueOf(PostType.class, postType))
                .build();
        return ResponseFactory.createResponse(
                postDtoConverter.createDtoListFromEntities(postService.fetchEntities(postProbe)),
                HttpStatus.OK);
    }

    @Operation(summary = "Fetch posts record from auth user")
    @GetMapping(produces = ApiConstants.APPLICATION_CONTENT_TYPE, value = "/me")
    public ResponseEntity<BaseResponse> fetchPostsFromAuthUser() {
        return ResponseFactory.createResponse(
                postDtoConverter.createDtoListFromEntities(
                        postService.fetchPostsByUserId(authenticationFacade.getAuthenticatedUser().getId())),
                HttpStatus.OK);
    }

    @Operation(summary = "Create a post record")
    @Transactional
    @PostMapping(consumes = ApiConstants.APPLICATION_CONTENT_TYPE, produces = ApiConstants.APPLICATION_CONTENT_TYPE)
    public ResponseEntity<BaseResponse> addPost(@RequestBody PostInputDto input) {
        Post post = Post.builder()
                .title(input.getTitle())
                .content(input.getContent())
                .postType(input.getType())
                .postUser(authenticationFacade.getAuthenticatedUser())
                .build();

        postService.save(post);
        return ResponseFactory.createResponse(postDtoConverter.convertToDto(post), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a post record")
    @Transactional
    @PutMapping(
            consumes = ApiConstants.APPLICATION_CONTENT_TYPE,
            produces = ApiConstants.APPLICATION_CONTENT_TYPE,
            value = "/{postId}")
    public ResponseEntity<BaseResponse> updatePost(@PathVariable long postId, @RequestBody PostInputDto input) {
        Post postToUpdate = postService.findByIdOrThrowError(postId);

        postToUpdate.setTitle(input.getTitle());
        postToUpdate.setContent(input.getContent());
        Optional.ofNullable(input.getType()).ifPresent(postToUpdate::setPostType);

        postService.save(postToUpdate);
        return ResponseFactory.createResponse(postDtoConverter.convertToDto(postToUpdate), HttpStatus.OK);
    }

    @Operation(summary = "Delete a post record")
    @Transactional
    @DeleteMapping(
            consumes = ApiConstants.APPLICATION_CONTENT_TYPE,
            produces = ApiConstants.APPLICATION_CONTENT_TYPE,
            value = "/{postId}")
    public ResponseEntity<BaseResponse> deletePost(@PathVariable long postId) {
        Post postToUpdate = postService.findByIdOrThrowError(postId);
        postService.delete(postToUpdate);
        return ResponseFactory.createResponse("Post with %d has been deleted".formatted(postId), HttpStatus.OK);
    }
}