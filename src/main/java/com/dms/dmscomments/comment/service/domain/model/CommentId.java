package com.dms.dmscomments.comment.service.domain.model;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class CommentId implements Serializable {
    private UUID value;

    public CommentId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("CommentId cannot be null");
        }
        this.value = value;
    }

    public CommentId(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("CommentId cannot be null or empty");
        }
        this.value = UUID.fromString(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
