package com.beauty.shopping.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author wuzhenxian
 * @date 2021/03/15
 * 用户信息
 */
@Data
public class UserInfoDO {
    private Integer id;
    private String userName;
    private String password;
    private Date createTime;
    private Date modifyTime;
}
