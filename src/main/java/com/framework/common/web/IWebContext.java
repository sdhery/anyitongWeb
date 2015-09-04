package com.framework.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hqq on 2015/8/26.
 */
public interface IWebContext {
    HttpServletRequest getRequest();

    HttpServletResponse getResponse();

    String getBasePath();

    String getContentPath();
}
