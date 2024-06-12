// PostingService.java
package com.ccb.service;

import com.ccb.model.pojo.Posting;
import org.springframework.stereotype.Service;

@Service
public interface PostingService {
    Posting getPostingById(Integer id);
    void addPosting(Posting posting);
    Integer getMaxPostingId();

    void addFollow(Integer fanId, Integer followerId);
    void deleteFollow(Integer fanId, Integer followerId);
    Integer getPostingSenderByPostingId(Integer postingId);

}
