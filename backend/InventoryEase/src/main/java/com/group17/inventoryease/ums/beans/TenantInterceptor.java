package com.group17.inventoryease.ums.beans;

/* "The next is TenantInterceptor, an interceptor that reads the tenant identifier from the JWT (or request header in
*   different implementations) and sets the tenant context."
*  Source: https://dzone.com/articles/spring-boot-hibernate-multitenancy-implementation
* */

import com.group17.inventoryease.ums.context.TenantContext;
import com.group17.inventoryease.ums.services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TenantInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtService jwtService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String authToken = request.getHeader(this.tokenHeader);
        if (authToken == null || authToken.isEmpty()) {
            TenantContext.setCurrentTenant("public");
        } else {
            String tenantId = jwtService.extractSchemaName(authToken);
            TenantContext.setCurrentTenant(tenantId);
        }

        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        TenantContext.clear();
    }
}
