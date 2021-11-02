package practice.ssm.warehouse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring-Security 安全配置。
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    /**
     * 配置 Spring Security 的 Filter 链。
     * @param webSecurity
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity webSecurity) throws Exception{
        super.configure(webSecurity);
    }

    /**
     * 配置 user-detail 服务。
     * @param builder
     */
    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin")
                    .password(new BCryptPasswordEncoder().encode("admin"))
                    .roles("Manager", "admin")
                .and()
                .withUser("manager")
                    .password(new BCryptPasswordEncoder().encode("manager"))
                    .roles("Manager");
    }

    /**
     * 配置拦截器保护请求。
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.httpBasic().realmName("realmFiled")
                .and()
                    .formLogin()
                    //.loginPage("/login.jsp")
                    .loginProcessingUrl("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/index.jsp")
                .and().rememberMe()
                    .tokenValiditySeconds(60 *  5)
                    .key("graduate")
                .and().logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/index.jsp")
                .and()
                    .authorizeRequests()
                        .antMatchers("/index.jsp", "/login.jsp", "/login", "/logout")
                            .permitAll()
                        .anyRequest()
//                            .access("isAuthenticated() or isRememberMe()")
                            .permitAll()
                .and()
                    .csrf().disable();

    }
}
