package com.framework.module.article.web;

import com.framework.common.base.BaseController;
import com.framework.common.service.IService;
import com.framework.common.service.impl.BaseService;
import com.framework.module.article.domain.InfArticle;
import com.framework.module.article.service.IInfArticleService;
import com.framework.tag.easyui.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/30 16:05
 * 修改人：Administrator
 * 修改时间：2015/4/30 16:05
 * 修改备注：
 */
@Controller
@RequestMapping(value = "/admin/info")
public class ArticleController extends BaseController<InfArticle,Integer>{
    public final static String prefix="admin/module/info/";
    @Autowired
    private IInfArticleService iInfArticleService;

    protected IService getBaseService() {
        return iInfArticleService;
    }

    protected String getPrefix() {
        return prefix;
    }
}
