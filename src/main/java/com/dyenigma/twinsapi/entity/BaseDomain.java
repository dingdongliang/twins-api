package com.dyenigma.twinsapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * twins/com.dyenigma.twinsapi.entity
 *
 * @Description : 实体类基类，重写toString()方法，实现序列化接口， 否则Spring将对象存入Redis时会报错
 * @Author : dingdongliang
 * @Date : 2018/4/9 11:38
 */
@Getter
@Setter
public class BaseDomain implements Serializable {

    /**
     * 创造日期
     */
    @Column(name = "CREATED")
    protected Date created;
    /**
     * 修改日期
     */
    @Column(name = "LASTMOD")
    protected Date lastmod;
    /**
     * 创建人
     */
    @Column(name = "CREATER")
    protected String creater;
    /**
     * 修改人
     */
    @Column(name = "MODIFYER")
    protected String modifyer;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * 数据库记录字段赋值，新添加记录时调用
     * param domain
     * param userId
     * return
     */
    public static BaseDomain createLog(BaseDomain domain, String userId) {
        return createLog(domain, userId, true);
    }

    /**
     * 数据库记录字段赋值，修改记录时调用
     * param domain
     * param userId
     * return
     */
    public static BaseDomain editLog(BaseDomain domain, String userId) {
        return createLog(domain, userId, false);
    }

    /**
     * 数据库记录字段赋值，统一处理方法
     * param domain
     * param userId
     * param flag 当为true时，为新数据，false为修改数据
     * return
     */
    public static BaseDomain createLog(BaseDomain domain, String userId, boolean flag) {
        if (flag) {
            domain.setCreated(new Date());
            domain.setLastmod(new Date());
            domain.setCreater(userId);
            domain.setModifyer(userId);
        } else {
            domain.setLastmod(new Date());
            domain.setModifyer(userId);
        }
        return domain;
    }
}
