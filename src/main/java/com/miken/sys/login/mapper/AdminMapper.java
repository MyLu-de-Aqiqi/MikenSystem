package com.miken.sys.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miken.sys.login.entity.Admin;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    int updateBatch(List<Admin> list);

    int batchInsert(@Param("list") List<Admin> list);

    // 指定id的账号成功登录一次
    @Update("update sp_admin set login_ip = #{login_ip},login_time = NOW(),login_count = login_count + 1 where id = #{id}")
    public int successLogin(@Param("id")long id, @Param("login_ip")String login_ip);
}