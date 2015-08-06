package com.framework.module.article.domain;

import com.framework.common.domain.BaseDomain;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/5/4 16:33
 * 修改人：Administrator
 * 修改时间：2015/5/4 16:33
 * 修改备注：
 */
public class InfArticle extends BaseDomain {
    private Integer articleId;
    private String title;
    private String content;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
