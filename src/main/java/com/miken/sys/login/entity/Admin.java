package com.miken.sys.login.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * sp_admin
 */
@ApiModel(value = "com-miken-sys-login-entity-Admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sp_admin")
public class Admin implements Serializable {
    /**
     * id，--主键、自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id，--主键、自增")
    private Long id;

    /**
     * admin名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "admin名称")
    private String name;

    /**
     * 头像地址
     */
    @TableField(value = "avatar")
    @ApiModelProperty(value = "头像地址")
    private String avatar;

    /**
     * 密码
     */
    @TableField(value = "password")
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 明文密码
     */
    @TableField(value = "pw")
    @ApiModelProperty(value = "明文密码")
    private String pw;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 所属角色id
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value = "所属角色id")
    private Integer roleId;

    /**
     * 账号状态(1=正常, 2=禁用)
     */
    @TableField(value = "status")
    @ApiModelProperty(value = "账号状态(1=正常, 2=禁用)")
    private Integer status;

    /**
     * 创建自哪个管理员
     */
    @TableField(value = "create_by_aid")
    @ApiModelProperty(value = "创建自哪个管理员")
    private Long createByAid;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 上次登陆时间
     */
    @TableField(value = "login_time")
    @ApiModelProperty(value = "上次登陆时间")
    private Date loginTime;

    /**
     * 上次登陆IP
     */
    @TableField(value = "login_ip")
    @ApiModelProperty(value = "上次登陆IP")
    private String loginIp;

    /**
     * 登陆次数
     */
    @TableField(value = "login_count")
    @ApiModelProperty(value = "登陆次数")
    private Integer loginCount;

    /**
     * 错误次数，如果到达5次，账号就禁用
     */
    @TableField(value = "err_count")
    @ApiModelProperty(value = "错误次数，如果到达5次，账号就禁用")
    private Integer errCount;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_AVATAR = "avatar";

    public static final String COL_PASSWORD = "password";

    public static final String COL_PW = "pw";

    public static final String COL_PHONE = "phone";

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_BY_AID = "create_by_aid";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_LOGIN_TIME = "login_time";

    public static final String COL_LOGIN_IP = "login_ip";

    public static final String COL_LOGIN_COUNT = "login_count";

    public static final String COL_ERR_COUNT = "err_count";
}