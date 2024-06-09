package com.ccb.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Timestamp.class, Timestamp.valueOf(LocalDateTime.now())); // 自动填充创建时间
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时自动填充逻辑
    }
}
