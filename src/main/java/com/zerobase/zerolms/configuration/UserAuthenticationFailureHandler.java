package com.zerobase.zerolms.configuration;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 라이브러리 핸들러 상속받아 실패 시 핸들러 구현
public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String msg = "로그인에 실패하였습니다.";
        // 이메일 활성화 exception 에 대한 메세지
        if (exception instanceof InternalAuthenticationServiceException) {
            msg = exception.getMessage();
        }

        setUseForward(true);
        // 로그인실패 시 보낼곳
        setDefaultFailureUrl("/member/login?error=true");
        // error 메세지 화면에 띄움
        request.setAttribute("errorMessage", msg);

        System.out.println("로그인에 실패하였습니다.");

        super.onAuthenticationFailure(request, response, exception);
    }
}
