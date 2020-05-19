package com.donald.wj_back.filter;

import com.donald.wj_back.service.AdminPermissionService;
import com.donald.wj_back.utils.SpringContextUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author Donald
 * @data 19/05/2020 15:24
 */
public class URLPathMatchingFilter extends PathMatchingFilter {
    @Autowired
    private AdminPermissionService adminPermissionService;
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

//        options request
        if(HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())){
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        if(null==adminPermissionService){
            adminPermissionService = SpringContextUtils.getContext().getBean(AdminPermissionService.class);
        }

        String requestApi = getPathWithinApplication(request);
        System.out.println("api" + requestApi);

        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            System.out.println("don't login");
            return false;
        }

        boolean needFilter = adminPermissionService.needFilter(requestApi);
        if(!needFilter){
            System.out.println("api"+requestApi+"no need authorization");
            return true;
        }else{
            System.out.println("validate"+requestApi);
            boolean hasPermission = false;
            String username = subject.getPrincipal().toString();
            Set<String> permissionApis = adminPermissionService.listPermissionURLByUser(username);
            for (String permissionApi : permissionApis) {
                if(permissionApi.equals(requestApi)){
                    hasPermission = true;
                    break;
                }
            }
            if(hasPermission){
                System.out.println("validte"+requestApi+"success");
                return true;
            }else{
                System.out.println("validte"+requestApi+"failed");
                return false;
            }
        }
    }
}
