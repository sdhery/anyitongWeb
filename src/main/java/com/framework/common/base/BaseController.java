package com.framework.common.base;

import com.framework.common.domain.BaseDomain;
import com.framework.common.service.IService;
import com.framework.common.service.impl.BaseService;
import com.framework.tag.easyui.DataGrid;
import com.framework.tag.easyui.TagUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/5/4 17:35
 * 修改人：Administrator
 * 修改时间：2015/5/4 17:35
 * 修改备注：
 */
public abstract class BaseController<T extends BaseDomain, ID extends Serializable> {
    @Autowired(required=false)
    protected HttpServletRequest request;
    @Autowired(required=false)
    protected HttpServletResponse response;
    @Autowired(required=false)
    protected HttpSession session;

    protected abstract IService getBaseService();

    protected abstract  String getPrefix();

    protected  final String UPDATE=getPrefix()+"form";
    protected  final String ADD=getPrefix()+"form";
    protected  final String LIST=getPrefix()+"list";

    public void copyProperties(Object target, Object source) {
        try {
            BeanUtils.copyProperties(target, source);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void setSuccess(ModelMap model) {
        model.put("success", true);
    }

    public void setFailure(ModelMap model) {
        model.put("success", false);
    }

    public void setErrMsg(ModelMap model,String errMsg) {
        model.put("errMsg", errMsg);
    }

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Date.class,
                new PropertyEditorSupport() {
                    public void setAsText(String value) {
                        try {
                            setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                    .parse(value));
                        } catch (Exception e) {
                            try {
                                setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm")
                                        .parse(value));
                            } catch (ParseException e1) {
                                try{
                                    setValue(new SimpleDateFormat("yyyy-MM-dd")
                                            .parseObject(value));
                                }catch (ParseException e2) {
                                    setValue(null);
                                }
                            }
                        }
                    }

                    public String getAsText() {
                        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .format((Date) getValue());
                    }

                });

        dataBinder.registerCustomEditor(Long.class,
                new PropertyEditorSupport() {
                    public void setAsText(String value) {
                        try {
                            setValue(Long.parseLong(value));
                        } catch (Exception e) {
                            setValue(-1L);
                        }
                    }

                    public String getAsText() {
                        return (String) getValue();
                    }
                });

        dataBinder.registerCustomEditor(long.class,
                new PropertyEditorSupport() {
                    public void setAsText(String value) {
                        try {
                            setValue(Long.parseLong(value));
                        } catch (Exception e) {
                            setValue(0L);
                        }
                    }

                    public String getAsText() {
                        return (String) getValue();
                    }
                });

        dataBinder.registerCustomEditor(Integer.class,
                new PropertyEditorSupport() {
                    public void setAsText(String value) {
                        try {
                            setValue(new Double(value).intValue());
                        } catch (Exception e) {
                            setValue(null);
                        }
                    }

                    public String getAsText() {
                        return (String) getValue();
                    }
                });

        dataBinder.registerCustomEditor(int.class,
                new PropertyEditorSupport() {
                    public void setAsText(String value) {
                        try {
                            setValue(new Double(value).intValue());
                        } catch (Exception e) {
                            setValue(0);
                        }
                    }

                    public String getAsText() {
                        return (String) getValue();
                    }
                });
    }

    public void returnDataGrid(IService<T, Integer> service,DataGrid dataGrid){

    }

    /**
     * 功能说明：通用保存页面
     * @author ducc
     * @updated
     * @param
     * @param item
     */
    @RequestMapping(value="save",method= RequestMethod.POST)
    @ResponseBody
    public ModelMap save(@ModelAttribute T item){
        ModelMap map = new ModelMap();
        setFailure(map);

        try{
            if(item.getUpdateId()==null){
                getBaseService().save(item);
            }else {
                getBaseService().update(item);
            }
            setSuccess(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value="/update/{id}")
    public String edit(Model model,@PathVariable("id") Integer id){
        model.addAttribute("item", getBaseService().findById(id));
        return UPDATE;
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(){
        return LIST;
    }

    @RequestMapping(value = "list",method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public void list(DataGrid dataGrid,@ModelAttribute T item){
        Page<T> page = PageHelper.startPage(dataGrid.getPage(), dataGrid.getRows());
        List<T> result = getBaseService().findListBy(item,"createTime DESC,lastModifiedTime DESC",null);
       /* ModelMap map = new ModelMap();
        map.put("total",page.getTotal());
        map.put("rows",result);
        return map;*/
        dataGrid.setResults(result);
        dataGrid.setTotal((int)page.getTotal());
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-store");
        JSONObject object = TagUtils.getJson(dataGrid);
        try {
            PrintWriter pw=response.getWriter();
            pw.write(object.toString());
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String form(ModelMap map){
        return ADD;
    }

}
