// PostingService.java
package com.ccb.service;

import com.ccb.model.pojo.Posting;
import org.springframework.stereotype.Service;

@Service
public interface PostingService {
    Posting getPostingById(Integer id);
    void addPosting(Posting posting);

    void likePosting(Integer postingId);
    void dislikePosting(Integer postingId);

    void deletePosting(Integer postingId);
}
