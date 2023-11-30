package com.qp.grocery.qp.config;

import com.qp.grocery.qp.annotation.RoleRequired;
import com.qp.grocery.qp.common.Constant;
import com.qp.grocery.qp.enums.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.Objects;

@Component
public class GroceryInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Role userRole = fetchRoleData(request.getHeader(Constant.USER_TYPE));

        if (Objects.isNull(userRole)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            throw new Exception("Bad Request: Your request is invalid");
            // response.getWriter().write("Bad Request: Your request is invalid");
        }

        if (handler instanceof HandlerMethod handlerMethod) {
            RoleRequired roleRequiredAnnotation = handlerMethod.getMethodAnnotation(RoleRequired.class);

            if (Objects.nonNull(roleRequiredAnnotation)) {
                Role[] requiredRoles = roleRequiredAnnotation.value();

                if (checkUserHasRoles(requiredRoles, userRole)) {
                    return true;
                } else {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("Access Denied: Insufficient Roles");
                    return false;
                }
            }
        }
        return true;
    }

    private Role fetchRoleData(String userType) {
        try {
            return Role.valueOf(userType);
        } catch (Exception e) {
        }
        return null;
    }

    private static boolean checkUserHasRoles(Role[] requiredRoles, Role user) {
        return Arrays.asList(requiredRoles).contains(user);
    }
}
