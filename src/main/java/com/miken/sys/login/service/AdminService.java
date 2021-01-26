package com.miken.sys.login.service;

import java.util.List;
import com.miken.sys.login.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.miken.sys.util.CommRes;

public interface AdminService extends IService<Admin> {


    int updateBatch(List<Admin> list);

    int batchInsert(List<Admin> list);


    public CommRes login(String key, String password);

}


