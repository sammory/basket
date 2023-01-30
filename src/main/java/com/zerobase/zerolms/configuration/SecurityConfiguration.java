package com.zerobase.zerolms.configuration;

import com.zerobase.zerolms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // user 정보를 받기 위해
    private MemberService memberService;
    @Bean
    PasswordEncoder getPasswordEncoder() {
        // 비밀번호 암호화
        return new BCryptPasswordEncoder();
    }
    @Bean
    UserAuthenticationFailureHandler getFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        // text 상자 폼 가져올때 권한때문에 적용 안됬었음
        http.headers().frameOptions().sameOrigin();
        // 시큐리티 권한설정 , 권한설정한 페이지는 시큐리티 로그인 없이 이동가능
        http.authorizeRequests()
                .antMatchers("/"
                        , "/member/register"
                        , "/member/email-auth"
                        , "/member/find-password"
                        // 비밀번호 초기화 승인 완료페이지
                        , "/member/reset/password"
                )
                .permitAll();

        // 관리자권한만 접근
        http.authorizeRequests()
                .antMatchers("/admin/**")
                .hasAuthority("ROLE_ADMIN");

        // 제한된 권한접근 처리
        http.exceptionHandling()
                .accessDeniedPage("/error/denied");

        // 로그인페이지 설정
        http.formLogin()
                // 로그인 페이지 주소
                .loginPage("/member/login")
                // 로그인 실패 페이지, 핸들러 추가하여 실패 메세지
                .failureHandler(getFailureHandler())
                .permitAll();

        // 로그아웃페이지 설정
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                // root 페이지 이동
                .logoutSuccessUrl("/")
                // 로그아웃 후 세션 초기화
                .invalidateHttpSession(true);

        super.configure(http);
    }

    @Override
    // 인증을 위한 user정보를 넘겨주는 메소드
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // memberService 가 userDetailsService 를 extend 해야함
        auth.userDetailsService(memberService)
                .passwordEncoder(getPasswordEncoder());

        super.configure(auth);
    }


}
