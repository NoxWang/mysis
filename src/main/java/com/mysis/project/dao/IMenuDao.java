package com.mysis.project.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Mapper
public interface IMenuDao {
    Set<String> selectMenuPermByUserId(Long userId);
}
