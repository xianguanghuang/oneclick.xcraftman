package impatient.springboot.hello;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public final class FooBarHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(FooBar.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        String bar = nativeWebRequest.getParameter("bar");
        String foo = nativeWebRequest.getParameter("foo");

        if (isNotSet(bar)) {
            bar = "defaultBar";
        }

        if (isNotSet(foo)) {
            foo = "defaultFoo";
        }

        return new FooBar(bar, foo);
    }

    private boolean isNotSet(String value) {
        return value == null;
    }
}