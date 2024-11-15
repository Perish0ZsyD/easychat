package com.easychat.aspect;

import com.easychat.annotation.GlobalInterceptor;
import com.easychat.entity.constants.Constants;
import com.easychat.entity.dto.TokenUserInfoDto;
import com.easychat.entity.enums.ResponseCodeEnum;
import com.easychat.exception.BusinessException;
import com.easychat.redis.RedisUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @ClassName GlobalOperationAspect
 * @Description
 * @Author Siyuan
 * @Date 2024/11/15/20:55
 * @Version 1.0
 */
/*
*  @Description : GlobalOperationAspect 类是一个切面类，用于拦截所有使用了 @GlobalInterceptor 注解的方法。
*  @Before 注解表示在目标方法执行之前执行，通过 @annotation 注解指定了拦截的注解类型。
*  @ClassName GlobalOperationAspect
*  @Pointcut 注解定义了一个切入点，用于拦截所有使用了 @GlobalInterceptor 注解的方法。
* */
@Component("operationAspect")
@Aspect
public class GlobalOperationAspect {


    @Resource
    private RedisUtils redisUtils;

    private static Logger logger = LoggerFactory.getLogger(GlobalOperationAspect.class);

    /**
     * Intercepts methods annotated with @GlobalInterceptor before they are executed.
     *
     * @param point the join point representing the method being intercepted
     */
    @Before("@annotation(com.easychat.annotation.GlobalInterceptor)") // 自己定义的注解，全局拦截
    public void interceptorDo(JoinPoint point) {
        try {
            Method method = ((MethodSignature) point.getSignature()).getMethod();
            GlobalInterceptor interceptor = method.getAnnotation(GlobalInterceptor.class);
            if (null == interceptor) {
                return;
            }
            /**
             * 校验登录
             */
            if (interceptor.checkLogin() || interceptor.checkAdmin()) {
                checkLogin(interceptor.checkAdmin());
            }
        } catch (BusinessException e) { // 避免一些抽象的业务异常（表不存在等等）这些报错
            logger.error("全局拦截器异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("全局拦截器异常", e);
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        } catch (Throwable e) {
            logger.error("全局拦截器异常", e);
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        }
    }

    //校验登录
    /*
     * RequestContextHolder学习点: 定义了ThreadLocal变量，为什么采用ThreadLocal变量呢？ 结合特点：线程隔离
     * 为什么可以拿到request对象呢？因为在请求进来的时候，SpringMVC会将request对象放到ThreadLocal中，所以在任何地方都可以拿到
     *
     * Checks if the user is logged in and optionally if the user is an admin.
     *
     * @param checkAdmin if true, also checks if the user is an admin
     * @throws BusinessException if the user is not logged in or not an admin when required
     */
    private void checkLogin(Boolean checkAdmin) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        TokenUserInfoDto tokenUserInfoDto = (TokenUserInfoDto) redisUtils.get(Constants.REDIS_KEY_WS_TOKEN + token);
        if (tokenUserInfoDto == null) {
            throw new BusinessException(ResponseCodeEnum.CODE_901); // 未登录
        }
        if (checkAdmin && !tokenUserInfoDto.getAdmin()) { // 没权限，请求这个接口
            throw new BusinessException(ResponseCodeEnum.CODE_404);
        }
    }
}