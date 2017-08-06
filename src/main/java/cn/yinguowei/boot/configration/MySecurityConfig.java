package cn.yinguowei.boot.configration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

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
        http.authorizeRequests().antMatchers("/h2-console/**", "/docs/**", "/actuator/**", "/management/**", "/admin/**", "/druid/**").permitAll();
        http.authorizeRequests().antMatchers("/css/**", "/img/**", "/js/**", "/plugins/**", "/static/**").permitAll();
//        http.authorizeRequests().anyRequest().permitAll();
        http.authorizeRequests().antMatchers("/settings/**").hasRole("admin");
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin()
                .loginPage("/login").defaultSuccessUrl("/").permitAll().successHandler(loginSuccessHandler)
                .and()
                .logout().invalidateHttpSession(true).logoutSuccessUrl("/login").permitAll();
        http.csrf().disable();
        http.rememberMe().tokenValiditySeconds(24*3600).tokenRepository(persistentTokenRepository());
        http.headers().frameOptions().disable();
    }

    @Autowired
    LoginSuccessHandler loginSuccessHandler;
/*    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }*/

    @Autowired
    MyUserDetailService userDetailService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(myPasswordEncoder());
        auth.eraseCredentials(false);
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

    // TODO why double def?
    @Autowired
    DataSource dataSource;

/*    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }*/
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
/*    @Bean
    public MyUserDetailService myUserDetailService() {
        return new MyUserDetailService();
    }*/

/*@Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new AuthenticationSuccessHandler() {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            request.getSession().setAttribute("userFullname",authentication.);
        }
    }
}*/
}
