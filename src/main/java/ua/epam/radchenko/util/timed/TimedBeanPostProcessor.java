package ua.epam.radchenko.util.timed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
public class TimedBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(TimedBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> currentBean = bean.getClass();
        if (currentBean.isAnnotationPresent(Timed.class)) {
            return Proxy.newProxyInstance(currentBean.getClassLoader(), currentBean.getInterfaces(),
                    (proxy, method, args) -> {
                        long start = System.nanoTime();
                        Object returnValue = method.invoke(bean, args);
                        long end = System.nanoTime();
                        String result = "Executing " + method.getName() + " finished in " + (end-start) + " ns";
                        LOGGER.debug(result);
                        return returnValue;
                    });
        }
        return bean;
    }

}
