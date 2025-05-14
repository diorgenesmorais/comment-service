package com.dms.dmscomments.comment.service.domain;

import com.dms.dmscomments.comment.service.api.model.CommentOutput;
import com.dms.dmscomments.comment.service.domain.model.Comment;
import com.dms.dmscomments.comment.service.domain.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public CommentOutput saveComment(Comment comment) {

        Comment commentSaved = commentRepository.save(comment);

        return CommentOutput.builder()
                .id(commentSaved.getId().toString())
                .text(commentSaved.getText())
                .author(commentSaved.getAuthor())
                .createdAt(commentSaved.getCreatedAt().toString())
                .build();
    }

    public Page<CommentOutput> list(Pageable pageable) {
        return commentRepository.findAll(pageable)
                .map(comment -> CommentOutput.builder()
                        .id(comment.getId().toString())
                        .text(comment.getText())
                        .author(comment.getAuthor())
                        .createdAt(comment.getCreatedAt().toString())
                        .build());
    }
}
