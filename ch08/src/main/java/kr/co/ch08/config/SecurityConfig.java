package kr.co.ch08.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //인가 설정 (로그인)
        httpSecurity.formLogin(login -> login
                                        .loginPage("/user1/login")
                                        .defaultSuccessUrl("/user1/success")
                                        .failureUrl("/user1/login?success=100")
                                        .usernameParameter("uid")
                                        .passwordParameter("pass")
                                        );

        //로그아웃 설정
        httpSecurity.logout(logout -> logout
                                        .invalidateHttpSession(true)
                                        .logoutRequestMatcher(new AntPathRequestMatcher("/user1/logout"))
                                        .logoutSuccessUrl("/user1/login?success=200")
                                        );

        //인가 설정
        httpSecurity.authorizeHttpRequests(authorize -> authorize
                                                        .requestMatchers("/").permitAll()
                                                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                                                        .requestMatchers("/manager/**").hasAnyAuthority("ADMIN","MANAGER")
                                                        .requestMatchers("/user1/**").permitAll()
                                                        .requestMatchers("/user2/**").permitAll()
                                                        .requestMatchers("/user3/**").permitAll()
                                                        . anyRequest().permitAll()
                                                        );

        //사이트 위변조 방지 설정
        httpSecurity.csrf(CsrfConfigurer::disable);
        
        return  httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        //비밀번호를 암호화해서 저장하는 method
        return  new BCryptPasswordEncoder();
    }
}
