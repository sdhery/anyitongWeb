package com.framework.common.web;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * Created by hqq on 2015/8/27.
 */
@Component
public class WebContextFactory implements ServletContextAware {
    public static ServletContext servletContext;
    public static String localUploadPath;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
        localUploadPath = servletContext.getRealPath("/") + File.separatorChar + "upload" + File.separatorChar;
    }
}
