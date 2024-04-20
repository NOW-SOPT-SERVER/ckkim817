package org.sopt.springPractice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.springPractice.common.dto.SuccessMessage;
import org.sopt.springPractice.common.dto.SuccessStatusResponse;
import org.sopt.springPractice.service.BlogService;
import org.sopt.springPractice.service.dto.BlogCreateRequest;
import org.sopt.springPractice.service.dto.BlogTitleUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/blog")
    public ResponseEntity<SuccessStatusResponse> createBlog(
            @RequestHeader Long memberId,
            @RequestBody BlogCreateRequest blogCreateRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", blogService.create(memberId, blogCreateRequest))
                .body(SuccessStatusResponse.of(SuccessMessage.BLOG_CREATE_SUCCESS));
    }

    @PatchMapping("/blog/{blogId}/title")
    public ResponseEntity<Void> updateBlogTitle(
            @PathVariable Long blogId,
            @Valid @RequestBody BlogTitleUpdateRequest blogTitleUpdateRequest
    ) {
        blogService.updateTitle(blogId, blogTitleUpdateRequest);

        return ResponseEntity.noContent().build();
    }
}
