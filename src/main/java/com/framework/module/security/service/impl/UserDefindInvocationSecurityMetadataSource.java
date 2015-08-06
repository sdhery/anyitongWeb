package com.framework.module.security.service.impl;

import com.framework.module.security.domain.SysResource;
import com.framework.module.security.service.ISysRoleService;
import com.framework.module.security.domain.SysRole;
import com.framework.module.security.service.ISysResourceService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：此类在初始化时，应该取到所有资源及其对应角色的定义
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/20 10:49
 * 修改人：Administrator
 * 修改时间：2015/4/20 10:49
 * 修改备注：
 */
public class UserDefindInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {
    private static ConcurrentHashMap<String, List<ConfigAttribute>> resourceMap = null;
    private ConcurrentHashMap<String, Pattern> patterns = new ConcurrentHashMap<String, Pattern>();


    private ISysResourceService sysResourceService;

    private ISysRoleService sysRoleService;

    public void setSysResourceService(ISysResourceService sysResourceService) {
        this.sysResourceService = sysResourceService;
    }

    public void setSysRoleService(ISysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    public UserDefindInvocationSecurityMetadataSource(ISysResourceService sysResourceService,ISysRoleService sysRoleService) {
        setSysResourceService(sysResourceService);
        setSysRoleService(sysRoleService);
        intResource();
    }

    private void intResource() {
        resourceMap = new ConcurrentHashMap<String, List<ConfigAttribute>>();
        List<SysResource> sysResources = sysResourceService.findAll();
        for (SysResource sysResource : sysResources) {
            String resourceUrl = sysResource.getResourceUrl();
            List<SysRole> sysRoles = sysRoleService.finRoleByResourceId(sysResource.getResourceId());

            List<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();

            if (sysRoles != null && sysRoles.size() > 0) {
                for (SysRole sysRole : sysRoles) {
                    ConfigAttribute ca = new SecurityConfig(sysRole.getRoleValue());
                    configAttributes.add(ca);
                }
            }
            resourceMap.put(resourceUrl, configAttributes);
        }
    }

    public void afterPropertiesSet() throws Exception {

    }

    public Collection<ConfigAttribute> getAttributes(Object filter) throws IllegalArgumentException {
        Collection<ConfigAttribute> result = new ArrayList<ConfigAttribute>();
        FilterInvocation filterInvocation = (FilterInvocation) filter;
        HttpServletRequest request = filterInvocation.getRequest();
        for(Map.Entry<String, List<ConfigAttribute>> entry : resourceMap.entrySet()){
            String resourceUrl = entry.getKey();
            List<ConfigAttribute> configAttributeList = entry.getValue();

            /*Pattern pattern = patterns.get(resourceUrl);
            if (pattern == null) {
                pattern = Pattern.compile(resourceUrl);
                patterns.put(resourceUrl, pattern);
            }*/

            RequestMatcher matcher = new AntPathRequestMatcher(resourceUrl);
            boolean match = matcher.matches(request);
            if (match) {
                result.addAll(configAttributeList);
            }
        }
        return result;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<ConfigAttribute>();
    }

    public boolean supports(Class<?> aClass) {
        return true;
    }
}
