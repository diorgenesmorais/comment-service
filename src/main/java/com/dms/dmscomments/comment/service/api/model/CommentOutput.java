package com.dms.dmscomments.comment.service.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentOutput {
    private String id;
    private String text;
    private String author;
    private String createdAt;
}
