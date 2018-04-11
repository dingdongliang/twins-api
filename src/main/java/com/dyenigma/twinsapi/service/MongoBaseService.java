package com.dyenigma.twinsapi.service;

import java.util.List;
import java.util.Map;

/**
 * twins-api/com.dyenigma.twinsapi.service
 *
 * @Description : 通用方法接口,MongoDB用
 * @Author : dingdongliang
 * @Date : 2018/4/9 17:31
 */
public interface MongoBaseService<T> {
    /**
     * @param t
     * @return void
     * @Description: 单个对象信息保存
     * @author dingdongliang
     * @date 2018/3/29 14:09
     */
    void save(T t);

    /**
     * @param t
     * @return void
     * @Description: 对象整体更新
     * @author dingdongliang
     * @date 2018/3/29 14:10
     */
    void update(T t);

    /**
     * @param id 自己生成UUID，类型为String
     * @return void
     * @Description: 根据ID删除信息，
     * @author dingdongliang
     * @date 2018/3/29 14:10
     */
    void delete(String id);

    /**
     * @param id
     * @return T
     * @Description: 根据ID查询对象信息
     * @author dingdongliang
     * @date 2018/3/29 14:11
     */
    T findById(String id);

    /**
     * @return java.util.List<T>
     * @Description: 查找所有记录
     * @author dingdongliang
     * @date 2018/3/29 14:12
     */
    List<T> findAll();


    /**
     * @param pageNo 当前页号码
     * @return java.util.Map 包含查询结果集合，以及总页数
     * @Description: 全量分页查询
     * @author dingdongliang
     * @date 2018/3/29 14:14
     */
    Map findByPage(int pageNo);
}
