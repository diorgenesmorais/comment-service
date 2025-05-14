package com.dms.dmscomments.comment.service.api.controller;

import com.dms.dmscomments.comment.service.api.client.ModerationClient;
import com.dms.dmscomments.comment.service.api.model.CommentInput;
import com.dms.dmscomments.comment.service.api.model.CommentOutput;
import com.dms.dmscomments.comment.service.api.model.ModerationInput;
import com.dms.dmscomments.comment.service.api.model.ModerationOutput;
import com.dms.dmscomments.comment.service.common.IdGenerator;
import com.dms.dmscomments.comment.service.domain.CommentService;
import com.dms.dmscomments.comment.service.domain.model.Comment;
import com.dms.dmscomments.comment.service.domain.model.CommentId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final ModerationClient moderationClient;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentOutput> createComment(@RequestBody CommentInput input) {
        ModerationInput moderationInput = new ModerationInput(
                IdGenerator.generateTimeBaseUUID().toString(),
                input.getText()
        );
        ModerationOutput moderationOutput = moderationClient.moderateComment(moderationInput);
        if (!moderationOutput.approved()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Comment not approved");
        }

        Comment comment = Comment.builder()
                .id(new CommentId(moderationInput.commentId()))
                .text(input.getText())
                .author(input.getAuthor())
                .build();

        CommentOutput commentOutput = commentService.saveComment(comment);

        return ResponseEntity.ok(commentOutput);
    }

    @GetMapping
    public ResponseEntity<Page<CommentOutput>> list(@PageableDefault Pageable pageable) {
        Page<CommentOutput> comments = commentService.list(pageable);
        return ResponseEntity.ok(comments);
    }
}
