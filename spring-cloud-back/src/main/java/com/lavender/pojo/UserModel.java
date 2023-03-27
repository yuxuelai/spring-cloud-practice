package com.lavender.pojo;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@ApiModel(value = "User",description = "for description of User")
public class UserModel implements UserDetails {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户ID",example = "12")
    @JSONField(serializeUsing = com.alibaba.fastjson.serializer.ToStringSerializer.class)
    private Long id;
    private String createTime;
    private String updateTime;
    private String updateTimeStart;
    private String updateTimeEnd;
    private Integer enable;
    private String remark;

    private Integer page=1;
    private Integer limit=10;

    private String username;
    private String nickName;
    private String repassword;
    private String icon;
    private String password;
    private String newPassword;
    private String gender;
    private String email;
    private String phone;
    private String birthday;
    private String birthdayStart;
    private String birthdayEnd;
    private String note;
    private String loginTime;
    private Integer status;

    private Set<? extends GrantedAuthority> authorities;
    private Integer age;
    private String address;
    private String hobby;


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
