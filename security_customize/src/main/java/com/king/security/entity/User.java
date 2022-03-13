package com.king.security.entity;


import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-02-25 03:41
 */
@Entity
@Builder
@Data
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 3562500829301152222L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//主键自增
    private String name;
    //密码
    private String password;
    //角色 这个注解表示使用枚举类中的int
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    //头像
    private String headImg;
    //邮箱
    private String email;
    //电话号码
    private Long phone;
    //是否已经注销
    private Integer flag;
    //创建时间
    private LocalDateTime createTime;

    public User() {

    }

    //@builder默认用的是全参数构造函数  没有这个会报错 也可以加@AllArgsConstructor
    public User(Long id, String name, String password, Role role, String headImg, String email, Long phone, Integer flag, LocalDateTime createTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.headImg = headImg;
        this.email = email;
        this.phone = phone;
        this.flag = flag;
        this.createTime = createTime;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //角色列表
        Collection<GrantedAuthority> roles = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(this.role.getText());
        roles.add(grantedAuthority);
        return roles;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

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
