package com.ccb.vo;

import com.ccb.model.pojo.PostingComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostingCommentVo {
    PostingComment postingComment;
    UserVo userVo;
    List<Integer> likeUser;
}
