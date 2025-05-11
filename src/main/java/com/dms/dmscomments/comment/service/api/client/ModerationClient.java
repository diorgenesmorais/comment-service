package com.dms.dmscomments.comment.service.api.client;

import com.dms.dmscomments.comment.service.api.model.ModerationInput;
import com.dms.dmscomments.comment.service.api.model.ModerationOutput;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api/moderate")
public interface ModerationClient {

    @PostExchange
    ModerationOutput moderateComment(@RequestBody ModerationInput input);
}
