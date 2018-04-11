package com.dyenigma.twinsapi.service.impl;

import com.dyenigma.twinsapi.dao.MyMapper;
import com.dyenigma.twinsapi.service.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * twins-api/com.dyenigma.twinsapi.service.impl
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/11 16:39
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private MyMapper<T> mapper;

    /**
     * @param id
     * @return T
     * @Description: 根据id查询实体
     * @author dingdongliang
     * @date 2018/4/11 16:17
     */
    @Override
    public T selectById(String id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    /**
     * @return java.util.List<T>
     * @Description: 查询所有
     * @author dingdongliang
     * @date 2018/4/11 16:17
     */
    @Override
    public List<T> selectAll() {
        return this.mapper.select(null);
    }

    /**
     * @param param
     * @return java.util.List<T>
     * @Description: 条件查询
     * @author dingdongliang
     * @date 2018/4/11 16:17
     */
    @Override
    public List<T> selectByCondition(T param) {
        return this.mapper.select(param);
    }

    /**
     * @param param
     * @return java.lang.Integer
     * @Description: 查询记录数
     * @author dingdongliang
     * @date 2018/4/11 16:17
     */
    @Override
    public Integer selectCount(T param) {
        return this.mapper.selectCount(param);
    }

    /**
     * @param param
     * @param page
     * @param rows
     * @return com.github.pagehelper.PageInfo<T>
     * @Description: 分页
     * @author dingdongliang
     * @date 2018/4/11 16:17
     */
    @Override
    public PageInfo<T> selectPageByCondition(T param, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<T> list = this.selectByCondition(param);
        return new PageInfo<T>(list);

    }

    /**
     * @param param
     * @return T
     * @Description: 查询一条记录
     * @author dingdongliang
     * @date 2018/4/11 16:17
     */
    @Override
    public T selectOne(T param) {
        return this.mapper.selectOne(param);
    }

    /**
     * @param param
     * @return java.lang.Integer
     * @Description: 插入
     * @author dingdongliang
     * @date 2018/4/11 16:17
     */
    @Override
    public Integer insert(T param) {
        return this.mapper.insert(param);
    }

    /**
     * @param param
     * @return java.lang.Integer
     * @Description: 新增非空字段
     * @author dingdongliang
     * @date 2018/4/11 16:18
     */
    @Override
    public Integer insertSelective(T param) {
        return this.mapper.insertSelective(param);
    }

    /**
     * @param param
     * @return java.lang.Integer
     * @Description: 根据主键更新
     * @author dingdongliang
     * @date 2018/4/11 16:18
     */
    @Override
    public Integer update(T param) {
        return this.mapper.updateByPrimaryKey(param);
    }

    /**
     * @param param
     * @return java.lang.Integer
     * @Description: 根据主键更新非空字段
     * @author dingdongliang
     * @date 2018/4/11 16:18
     */
    @Override
    public Integer updateSelective(T param) {
        return this.mapper.updateByPrimaryKeySelective(param);
    }

    /**
     * @param id
     * @return java.lang.Integer
     * @Description: 根据主键删除
     * @author dingdongliang
     * @date 2018/4/11 16:18
     */
    @Override
    public Integer deleteById(String id) {
        return this.mapper.deleteByPrimaryKey(id);
    }
}
