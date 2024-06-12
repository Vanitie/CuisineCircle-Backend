// PostingMapper.java
package com.ccb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccb.model.pojo.Posting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostingMapper extends BaseMapper<Posting> {
    List<Integer> getPostingByUserId(@Param("userId") Integer userId);
    Integer getUserIdByPostingId(@Param("postingId") Integer postingId);
    Integer getCommentCountByPostingId(@Param("postingId") Integer postingId);

    Integer getMaxId();

}
