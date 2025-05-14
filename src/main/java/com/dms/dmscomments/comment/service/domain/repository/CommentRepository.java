package com.dms.dmscomments.comment.service.domain.repository;

import com.dms.dmscomments.comment.service.domain.model.Comment;
import com.dms.dmscomments.comment.service.domain.model.CommentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, CommentId> {
}
