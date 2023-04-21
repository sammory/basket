package com.zerobase.zerolms.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
@Slf4j
public class LogAop {

    // LogAop 클래스에 로거 객체를 생성
    private final static Logger logger = LoggerFactory.getLogger(LogAop.class);

    // zerolms 패키지 하위의 모든 메서드를 대상으로 AOP적용
    @Pointcut("execution(* com.zerobase.zerolms..*.*(..))")
    private void cut() {
    }

    // 앞에서 정의한 포인트컷을 Around 어노테이션을 이용해 적용하고, 해당 메서드가 실행되기 전후에 로그를 출력하도록 구현한다.
    @Around("cut()")
    public Object beforeParameterLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 현재 클래스명을 로그에 출력하기 위해 사용
        String className = new Object() {
        }.getClass().getEnclosingClass().getName();

        // StopWatch 클래스를 이용하여 해당 메서드가 실행되는 시간을 측정한다.
        StopWatch stopWatch = new StopWatch();
        logger.info(className + "stopWatch.start() ===========");
        stopWatch.start();

        // 해당 메서드를 실행한다.
        Object proceed = joinPoint.proceed();

        // 해당 메서드 실행 후에 StopWatch를 멈추고, 해당 메서드의 처리시간을 로그에 출력한다.
        stopWatch.stop();
        logger.info(className + "stopWatch.stop() ============");
        System.out.println("[LOG] 처리시간: " + (stopWatch.getTotalTimeMillis() / 1000.0) + "초");

        // 해당 메서드의 반환값을 반환한다.
        return proceed;
    }
}
