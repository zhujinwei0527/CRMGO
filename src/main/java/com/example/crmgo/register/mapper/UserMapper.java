package com.example.crmgo.register.mapper;

import com.example.crmgo.register.entity.User;

import java.util.List;

public interface UserMapper {
    /**
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(Integer id);

    List<User> selectByOuterKey(String customerId);

    int selectEffectiveCount(String certificatesType, String certificatesNo);

    int selectInvalidCount(String certificatesType, String certificatesNo);

    int updateByUnionKey(User user);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);
}