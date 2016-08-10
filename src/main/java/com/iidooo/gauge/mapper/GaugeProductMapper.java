package com.iidooo.gauge.mapper;

import com.iidooo.gauge.model.po.GaugeProduct;

public interface GaugeProductMapper {
    int deleteByPrimaryKey(Integer productID);

    /**
     * 插入一个新的GaugeProduct
     * @param record GaugeProduct对象
     * @return 插入成功返回的行号
     */
    int insert(GaugeProduct record);

    GaugeProduct selectByPrimaryKey(Integer productID);
    
    /**
     * 通过产品型号，编号查询
     * @param product 产品查询条件
     * @return 所获得的GaugeProduct对象
     */
    GaugeProduct selectByProductModelCode(GaugeProduct product);

    int updateByPrimaryKeySelective(GaugeProduct record);

    int updateByPrimaryKey(GaugeProduct record);
}