package com.jzt.sso.security;


import com.jzt.sso.filter.JWTAuthenticationFilter;
import com.jzt.sso.filter.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OwnerSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private ShaPasswordEncoder shaPasswordEncoder(){
        return  new ShaPasswordEncoder();
    }

    @Autowired
    private OwnerSuccessHandler successHandler;
    @Autowired
    private OwnerFailureHandler failureHandler;


    /**
     * 配置这个bean会在做AuthorizationServerConfigurer配置的时候使用
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return new OAuth2AuthenticationManager();
        return super.authenticationManagerBean();
    }

//    @Bean(name="daoAuhthenticationProvider")
//    public AuthenticationProvider daoAuhthenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
//        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
//        return daoAuthenticationProvider;
//    }
//    @Bean(name="daoAuhthenticationOauthProvider")
//    public AuthenticationProvider daoAuhthenticationOauthProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
//        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
//        return daoAuthenticationProvider;
//    }
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers( "/static/**");
        }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                //处理跨域请求中的Preflight请求
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(macLoginUrlAuthenticationEntryPoint())
                 .and()
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/bulid/index.html").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/sys/tarea/**" +
                        "").permitAll()
                .antMatchers("/door/me").permitAll()
                .antMatchers("/configuration/security").permitAll()
                .antMatchers("/static/css/**/*.css").permitAll()
                .antMatchers("/static/js/**/*.js").permitAll()
                .antMatchers("/static/media/**/*.png").permitAll()
                .antMatchers( "/oauth/authorize", "/oauth/confirm_access","/login/**","/logout","/","/index.html")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .permitAll()
                .and()
                .logout()
                .addLogoutHandler(new MyLogoutHandler())
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()));

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuhthenticationProvider());
        auth.userDetailsService(userDetailsService).passwordEncoder(shaPasswordEncoder());
    }

    @Bean
    public AuthenticationEntryPoint macLoginUrlAuthenticationEntryPoint() {
        return new LoginUrlEntryPoint("/login");
    }



}


