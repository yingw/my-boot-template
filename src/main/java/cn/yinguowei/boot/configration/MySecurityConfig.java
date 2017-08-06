package cn.yinguowei.boot.configration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * Created by yingu on 2017/7/12.
 */
@Component
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        System.out.println("MySecurityConfig.configure");
        http.authorizeRequests().antMatchers("/", "/login", "/register").permitAll();
        http.authorizeRequests().antMatchers("/h2-console/**", "/docs/**", "/actuator/**", "/admin/**", "/druid/**").permitAll();
        http.authorizeRequests().antMatchers("/css/**", "/img/**", "/js/**", "/plugins/**", "/static/**").permitAll();
//        http.authorizeRequests().anyRequest().permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll().and().logout().invalidateHttpSession(true).logoutSuccessUrl("/login").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Autowired
    MyUserDetailService userDetailService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(myPasswordEncoder());
//        myPasswordEncoder().encodePassword()
        System.out.println("MySecurityConfig.configureGlobal");
        System.out.println("encoding... \"admin\" -> " + myPasswordEncoder().encodePassword("admin", passwordEncoderSalt));
        System.out.println("encoding... \"111111\" -> " + myPasswordEncoder().encodePassword("111111", passwordEncoderSalt));
        System.out.println("encoding... \"user\" -> " + myPasswordEncoder().encodePassword("user", passwordEncoderSalt));
        System.out.println("encoding... \"password\" -> " + myPasswordEncoder().encodePassword("password", passwordEncoderSalt));
        System.out.println("encoding... \"yinguowei\" -> " + myPasswordEncoder().encodePassword("yinguowei", passwordEncoderSalt));
    }

//    private static final PasswordEncoder encoder = new StandardPasswordEncoder("my-secret-key");//秘钥值

    private static final String passwordEncoderSalt = "mYBoOT2O!7";

    @Bean
    public Md5PasswordEncoder myPasswordEncoder() {
        return new Md5PasswordEncoder() {
            @Override
            public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
                return super.isPasswordValid(encPass, rawPass, passwordEncoderSalt);
            }
        };
    }

/*    @Bean
    public MyUserDetailService myUserDetailService() {
        return new MyUserDetailService();
    }*/
}
