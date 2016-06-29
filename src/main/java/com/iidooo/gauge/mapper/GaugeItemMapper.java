package com.iidooo.gauge.mapper;

import com.iidooo.gauge.model.po.GaugeItem;

public interface GaugeItemMapper {
    int deleteByPrimaryKey(Integer itemID);

    /**
     * 插入一个检测数据
     * @param record 插入的对象
     * @return 插入成功的返回值
     */
    int insert(GaugeItem record);

    GaugeItem selectByPrimaryKey(Integer itemID);

    int updateByPrimaryKeySelective(GaugeItem record);

    int updateByPrimaryKey(GaugeItem record);
}