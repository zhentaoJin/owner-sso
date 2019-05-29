package com.jzt.sso.security;

import com.jzt.sso.dto.JwtUser;
import com.jzt.sso.model.UserEntity;
import com.jzt.sso.service.MenuService;
import com.jzt.sso.service.RoleService;
import com.jzt.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author:aha
 * @Description:自定义登录验证规则
 * @Date: 2018/8/9 16:00
 * @Modified By:
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    /**
     * 密码加密方式
     * @return
     */
    private BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private ShaPasswordEncoder shaPasswordEncoder(){
        return  new ShaPasswordEncoder();
    }
    /**
     * 根据传进来的参数来确认凭证
     * 可以自定义 例如：用户的手机号->openid 可以对应于（name,password）
     * @param phone
     * @return
     * @throws UsernameNotFoundException
     */
    //TODO
    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {

        /**
         * 根据参数获取凭证 可以对接数据库 redis等
         * 例如 当phone="15512343256"时，openId为"1002222224998989"
         * 验证时需要有密码规则即需要用户注册时将密码加密插入数据库
         * 举例：
         */
        UserEntity userEntity=userService.selectByName(phone);
        String username=userService.selectByName(phone).getUserName();
        String password = "";
        if(username.equals(phone)){
            //bCryptPasswordEncoder().encode("admin")
            password = userService.selectByName(phone).getPassword();
        }
        JwtUser jwtUser=new JwtUser(phone,password);
//        List<Long> menuIds=roleService.selectMenuIdByUserId(userEntity.getId());
//        List<MenuEntity> menuList=menuService.selectBatchIds(menuIds);
//        jwtUser.setAuthorities(menuList);
        return jwtUser;
    }
}
