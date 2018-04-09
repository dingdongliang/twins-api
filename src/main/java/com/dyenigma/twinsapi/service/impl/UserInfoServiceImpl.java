package com.dyenigma.twinsapi.service.impl;


import com.dyenigma.twinsapi.core.SystemConstant;
import com.dyenigma.twinsapi.mongo.UserInfo;
import com.dyenigma.twinsapi.service.UserInfoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

/**
 * twins/com.dyenigma.twinsapi.service.impl
 *
 * @Description : SpringBoot集成MongoDB示例，业务类-用户信息操作（CURD+分页）
 * @Author : dingdongliang
 * @Date : 2018/3/11 18:57
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {


    /**
     * MongoDB多数据源配置，声明主数据源，如果想要使用次数据源，把Qualifier换成slaveMongoTemplate
     */
    @Resource
    @Qualifier("masterMongoTemplate")
    private MongoTemplate mongoTemplate;


    /**
     * @param userInfo
     * @return void
     * @Description: 单个对象信息保存
     * @author dingdongliang
     * @date 2018/3/29 14:09
     */
    @Override
    public void save(UserInfo userInfo) {
        mongoTemplate.save(userInfo);
    }

    /**
     * @param userInfo
     * @return void
     * @Description: 对象整体更新
     * @author dingdongliang
     * @date 2018/3/29 14:10
     */
    @Override
    public void update(UserInfo userInfo) {
        Query query = new Query(Criteria.where("userId").is(userInfo.getUserId()));
        Update update = new Update().set("userName", userInfo.getUserName()).set("userAddr", userInfo.getUserAddr());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, UserInfo.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,updateUser,UserInfo.class);
    }

    /**
     * @param id 自己生成UUID，类型为String
     * @return void
     * @Description: 根据ID删除信息，
     * @author dingdongliang
     * @date 2018/3/29 14:10
     */
    @Override
    public void delete(String id) {
        Query query = new Query(Criteria.where("userId").is(id));
        mongoTemplate.remove(query, UserInfo.class);
    }

    /**
     * @param id
     * @return T
     * @Description: 根据ID查询对象信息
     * @author dingdongliang
     * @date 2018/3/29 14:11
     */
    @Override
    public UserInfo findById(String id) {
        Query query = new Query(Criteria.where("userId").is(id));
        UserInfo userInfo = mongoTemplate.findOne(query, UserInfo.class);
        return userInfo;
    }

    /**
     * @return java.util.List<T>
     * @Description: 查找所有记录
     * @author dingdongliang
     * @date 2018/3/29 14:12
     */
    @Override
    public List<UserInfo> findAll() {
        return mongoTemplate.findAll(UserInfo.class);
    }

    /**
     * @param pageNo 当前页号码
     * @return java.util.Map 包含查询结果集合，以及总页数
     * @Description: 全量分页查询
     * @author dingdongliang
     * @date 2018/3/29 14:14
     */
    @Override
    public Map findByPage(int pageNo) {
        Criteria criteria = new Criteria();

        //修改long  为double类型
        double total = mongoTemplate.count(new Query(criteria), UserInfo.class);
        //总页数
        int totalPages = (int) Math.ceil(total / SystemConstant.PAGE_SIZE);
        if (totalPages < 1) {
            totalPages = 1;
        }
        long skip = (pageNo - 1) * SystemConstant.PAGE_SIZE;

        AggregationOptions options = new AggregationOptions(true, false, null);

        Aggregation agg = newAggregation(match(criteria), Aggregation.sort(Sort.Direction.DESC, "userName"),
                Aggregation.skip(skip), Aggregation.limit(SystemConstant.PAGE_SIZE));
        agg.withOptions(options);
        AggregationResults<UserInfo> groupResults = mongoTemplate.aggregate(agg, UserInfo.class, UserInfo.class);

        List<UserInfo> result = groupResults.getMappedResults();

        Map map = new HashMap<>(2);
        map.put(SystemConstant.RESULT, result);
        map.put(SystemConstant.TOTAL, totalPages);
        return map;
    }
}
