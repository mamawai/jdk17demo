package com.example;

import com.example.springStudy.mybatisAop.entity.User;
import com.example.springStudy.mybatisAop.service.UserServiceMybatisAop;
import com.example.springStudy.transactional.UserController;
import com.example.springStudy.transactional.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class AppRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;

    @Autowired
    UserServiceMybatisAop userServiceMybatisAop;

    @Override
    public void run(String... args) throws Exception {

        /*
        *  实际上 Spring Aop 的动态代理
        *  jdk：在JdkDynamicAopProxy invoke 方法内进行增强
        *  cglib：在CglibAopProxy intercept 方法内进行增强
        *  但是 ：在最后的增强调用时，都是使用的拦截器链 也就是增强方法链 来执行的，在ReflectiveMethodInvocation类
        *
        *
        *
        *   下面时的拦截器责任链的调用：
@Override
	@Nullable
	public Object proceed() throws Throwable {
		// We start with an index of -1 and increment early.
		if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1) {
			return invokeJoinpoint();
		}

		Object interceptorOrInterceptionAdvice =
				this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);
		if (interceptorOrInterceptionAdvice instanceof InterceptorAndDynamicMethodMatcher dm) {
			// Evaluate dynamic method matcher here: static part will already have
			// been evaluated and found to match.
			Class<?> targetClass = (this.targetClass != null ? this.targetClass : this.method.getDeclaringClass());
			if (dm.matcher().matches(this.method, targetClass, this.arguments)) {
				return dm.interceptor().invoke(this);
			}
			else {
				// Dynamic matching failed.
				// Skip this interceptor and invoke the next in the chain.
				return proceed();
			}
		}
		else {
			// It's an interceptor, so we just invoke it: The pointcut will have
			// been evaluated statically before this object was constructed.
			return ((MethodInterceptor) interceptorOrInterceptionAdvice).invoke(this);
		}
	}
        *
        *
        * */

        // insert User
        // userController.combineMethod();


        // query User
        User userById = userServiceMybatisAop.getUserById(100L);
        Long id = userById.getId();
        System.out.println(id);

    }
}
