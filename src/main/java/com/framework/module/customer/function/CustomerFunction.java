package com.framework.module.customer.function;

import com.framework.module.customer.domain.HealthStatus;

import java.util.List;

/**
 * 项目名称：anyitongWeb
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/7/15 16:31
 * 修改人：Administrator
 * 修改时间：2015/7/15 16:31
 * 修改备注：
 */
public class CustomerFunction {
    public static HealthStatus getHealthStatusByType(List<HealthStatus> healthStatusList,int type){
        for(HealthStatus healthStatus : healthStatusList){
            if(healthStatus.getType()==type){
                return healthStatus;
            }
        }
        return null;
    }
}
