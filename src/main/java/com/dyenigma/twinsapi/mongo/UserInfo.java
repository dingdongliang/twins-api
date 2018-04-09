package com.dyenigma.twinsapi.mongo;

import com.dyenigma.twinsapi.util.UUidUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * twins/com.dyenigma.twinsapi.mongo
 *
 * @Description : SpringBoot集成MongoDB示例，实体类-用户信息表（用户名、用户地址）
 * @Author : dingdongliang
 * @Date : 2018/3/28 9:10
 */
@Document(collection = "userInfo")
@Getter
@Setter
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -1L;

    public UserInfo() {
    }

    public UserInfo(String userName, String userAddr) {
        this.userId = UUidUtil.getUUID();
        this.userName = userName;
        this.userAddr = userAddr;
    }

    /**
     * 如果有自己设置的主键，这个Id注解必须要加上，否则mongodb会自动再生成一个_id
     */
    @Id
    private String userId;
    private String userName;
    private String userAddr;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
