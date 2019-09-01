package com.rsdtm.supermarket.gateway.compent;

import com.rsdtm.supermarket.gateway.client.UmsClient;
import com.rsdtm.supermarket.mbg.model.UmsRole;
import com.rsdtm.supermarket.mbg.vo.UmsMenuVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

//通过当前的请求地址，获取该地址需要的用户角色
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource
{
    @Value("${zuul.prefix}")
    String URL_PREFIX;
    @Autowired
    UmsClient menuClient;
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        FilterInvocation request =  (FilterInvocation) o;
        String url_temp = request.getRequestUrl();

        //错误请求不需要检查权限
        if(url_temp.startsWith("/error"))
            return null;

        url_temp = StringUtils.substringAfterLast(url_temp, URL_PREFIX);
        String url = StringUtils.substringBefore(url_temp,"?");
        String method = ((FilterInvocation) o).getHttpRequest().getMethod();


//        System.out.println(url);
//        System.out.println(method);



        if ("/login_p".contains(request.getRequestUrl())||"/login".contains(request.getRequestUrl())) {
            return null;
        }
        List<UmsMenuVo> allMenu = menuClient.getAll().getBody();
        for (UmsMenuVo menu : allMenu) {
            if (antPathMatcher.match(menu.getUrl(), url)&&menu.getMethod().equals(method)&&menu.getRoles().size()>0) {
                List<UmsRole> roles = menu.getRoles();
                String[] values = roles.stream().map(UmsRole::getName).toArray(String[]::new);
                return SecurityConfig.createList(values);
            }
        }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
