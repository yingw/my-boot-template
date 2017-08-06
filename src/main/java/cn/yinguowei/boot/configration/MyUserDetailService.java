package cn.yinguowei.boot.configration;

import cn.yinguowei.boot.model.User;
import cn.yinguowei.boot.repository.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by yingu on 2017/7/24.
 */
@Component
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("MyUserDetailService.loadUserByUsername");
        System.out.println("username = [" + username + "]");
        if (StringUtils.isBlank(username))
            throw new UsernameNotFoundException("怎么传了个空的用户名？");

        Optional<User> user = userRepository.findOneByUsername(username);
        if (!user.isPresent())
            throw new UsernameNotFoundException("没找到用户：" + username);

        List<GrantedAuthority> grantedAuthorities = user.get().getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
        //user.get().getFullname(), 这里原来是username，作为显示的名称
        return new org.springframework.security.core.userdetails.User(username,
                user.get().getPassword(),
                grantedAuthorities);
    }
}
