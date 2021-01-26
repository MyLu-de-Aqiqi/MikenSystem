package com.miken.sys.login.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miken.sys.util.CommRes;
import com.miken.sys.util.SpringMvcUtil;
import com.miken.sys.util.WebNbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;

import com.miken.sys.login.entity.Admin;
import com.miken.sys.login.mapper.AdminMapper;
import com.miken.sys.login.service.AdminService;
import org.springframework.util.DigestUtils;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;


    @Override
    public int updateBatch(List<Admin> list) {
        return baseMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<Admin> list) {
        return baseMapper.batchInsert(list);
    }

    /**
     * 登陆接口
     *
     * @param key
     * @param password
     * @return
     */
    @Override
    public CommRes login(String key, String password) {
        //通过key（账号name）查询
        Admin admin = this.getOne(new QueryWrapper<Admin>().eq(Admin.COL_NAME, key));

        if (admin == null) {
            return CommRes.failed("没有该账号");
        }

        if (StrUtil.isEmpty(admin.getPassword())) {
            return CommRes.failed("此账号没有设置密码，无法登陆");
        }

        //通过md5加密校验
        String md5Str = DigestUtils.md5DigestAsHex(password.getBytes());

        // 4、是否禁用
        if (admin.getStatus() == 2) {
            return CommRes.failed("此账号已被禁用，如有疑问，请联系管理员");
        }

        if (!md5Str.equals(admin.getPassword())) {
            Integer errCount = admin.getErrCount();
            if (errCount == 5){
                admin.setStatus(2);
                this.updateById(admin);
            }else {
                errCount++;
                admin.setErrCount(errCount);
                this.updateById(admin);
            }

            return CommRes.failed("密码错误");
        }

        successLogin(admin);
        // 写入session
        StpUtil.setLoginId(admin.getId());

        //返回权限map
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("admin", admin);
        // 当前拥有的权限集合
//        map.put("per_list", adminMapper.getPcodeByRoleId(admin.getRoleId()));
        map.put("tokenInfo", StpUtil.getTokenInfo());

        //当前拥有菜单权限

        // 将信息返回到前台
        return CommRes.ok(map,"ok");






    }

    /**
     * 指定id的账号成功登录一次 （修改最后登录时间等数据 ）
     * @param s
     * @return
     */
    public int successLogin(Admin s){
        String login_ip = WebNbUtil.getIP(SpringMvcUtil.getRequest());
        int line = adminMapper.successLogin(s.getId(), login_ip);
        if(line > 0) {
            s.setLoginIp(login_ip);
            s.setLoginTime(new Date());
            s.setLoginCount(s.getLoginCount() + 1);
        }
        return line;
    }
}


