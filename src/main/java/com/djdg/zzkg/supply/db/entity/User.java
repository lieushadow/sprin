package com.djdg.zzkg.supply.db.entity;

import com.djdg.zzkg.supply.common.BaseEntity;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.DATE;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:刘敏华 shadow.liu@hey900.com
 * Date: 2017/9/8
 * Time: 16:00
 */

@Entity
@Table(name = "user")
public class User extends BaseEntity {


    @Id
    @SequenceGenerator(name = "supplySeq", sequenceName = "supply_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = IDENTITY, generator = "supplySeq")
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(32) default null COMMENT '账号名'")
    private String name;

    @Column(name = "password", columnDefinition = "varchar(32) default null COMMENT '密码'")
    private String password;

    @Column(name = "salt", columnDefinition = "varchar(8) default null COMMENT '盐'")
    private String salt;

    @Column(name = "email", columnDefinition = "varchar(128) default null COMMENT '邮箱'")
    private String email;

    @Temporal(DATE)
    @Column(name = "last_login_time", columnDefinition = "DATE COMMENT ''")
    private Date lastLoginTime;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}

