package com.km.model;

import java.util.List;
import java.util.Map;
/**
 * @author PipiLu
 * @version 创建时间：2017-9-25 下午4:50:17
 * @Description: 封装查询结果类     
 * @ClassName:    [AnnInfo]
 * @Package:      [com.km.model]    
 * @Author:       [狐狸糊涂]     
 * @CreateDate:   [2017-9-25 下午4:50:17]     
 * @UpdateUser:   [狐狸糊涂]     
 * @UpdateDate:   [2017-9-25 下午4:50:17]     
 * @UpdateRemark: [说明本次修改内容]    
 * @Version:      [v1.0]   
 *      
 */
public class DataSourceResult {
    private long total;
    
    private List<?> data;
    
    private Map<String, Object> aggregates;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Map<String, Object> getAggregates() {
        return aggregates;
    }

    public void setAggregates(Map<String, Object> aggregates) {
        this.aggregates = aggregates;
    }
}
