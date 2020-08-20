package com.luomor.coco.wx.annotation.support;

import com.luomor.coco.wx.annotation.LoginAppId;
import com.luomor.coco.wx.annotation.LoginUser;
import com.luomor.coco.wx.service.UserTokenManager;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


public class LoginAppIdHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    public static final String LOGIN_TOKEN_KEY = "X-Coco-Token";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Integer.class) && parameter.hasParameterAnnotation(LoginAppId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        if (token == null || token.isEmpty()) {
            return null;
        }

        return UserTokenManager.getAppId(token);
    }
}
