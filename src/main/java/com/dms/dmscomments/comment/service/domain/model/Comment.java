package com.dms.dmscomments.comment.service.domain.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Comment {
    @Id
    @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "uuid"))
    private CommentId id;
    private String text;
    private String author;
    @Column(updatable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    public void instanceCreateAt() {
        this.createdAt = OffsetDateTime.now(ZoneOffset.UTC);
    }
}
