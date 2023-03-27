package com.lavender.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;

@Data
public class Permission implements GrantedAuthority {

    private long id;
    private Integer pid;
    private String  name;
    private String icon;
    private String value;
    private Integer type;
    private String uri;

    private  Integer status;
    private Date createTime;
    private String sort;

    public Permission() {
    }

    public Permission(long id, String name) {
        this.id = id;
        this.name = name;
    }



    @Override
    public String getAuthority() {
        return this.name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }




    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", value='" + value + '\'' +
                ", type=" + type +
                ", uri='" + uri + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", sort='" + sort + '\'' +
                '}';
    }
}
