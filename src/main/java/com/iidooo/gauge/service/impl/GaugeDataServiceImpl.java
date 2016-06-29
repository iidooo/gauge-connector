package com.iidooo.gauge.service.impl;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.iidooo.gauge.mapper.GaugeItemMapper;
import com.iidooo.gauge.mapper.GaugeProductMapper;
import com.iidooo.gauge.model.po.GaugeItem;
import com.iidooo.gauge.model.po.GaugeProduct;
import com.iidooo.gauge.service.GaugeDataService;
import com.iidooo.gauge.util.MybatisUtil;

public class GaugeDataServiceImpl implements GaugeDataService {
    private static final Logger logger = Logger.getLogger(GaugeDataServiceImpl.class);

    @Override
    public void receiveGuageData(GaugeItem receiveGaugeItem) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSessionFactory().openSession(true);
            GaugeProductMapper gaugeProductMapper = sqlSession.getMapper(GaugeProductMapper.class);
            GaugeItemMapper gaugeItemMapper = sqlSession.getMapper(GaugeItemMapper.class);
            
            // 获取ProductID
            String productCode = receiveGaugeItem.getProduct().getProductCode();
            GaugeProduct gaugeProduct = gaugeProductMapper.selectByProductCode(productCode);
            if (gaugeProduct == null) {
                gaugeProduct = new GaugeProduct();
                gaugeProduct.setProductCode(productCode);
                gaugeProduct.setCreateTime(new Date());
                gaugeProduct.setCreateUserID(1);
                gaugeProduct.setUpdateTime(new Date());
                gaugeProduct.setUpdateUserID(1);
                gaugeProductMapper.insert(gaugeProduct);
            }
            
            GaugeItem gaugeItem = new GaugeItem();
            gaugeItem.setProductID(gaugeProduct.getProductID());
            gaugeItem.setTemperature(receiveGaugeItem.getTemperature());
            gaugeItem.setPressure(receiveGaugeItem.getPressure());
            gaugeItem.setParticulate(receiveGaugeItem.getParticulate());
            gaugeItem.setCreateTime(new Date());
            gaugeItem.setCreateUserID(1);
            gaugeItem.setUpdateTime(new Date());
            gaugeItem.setUpdateUserID(1);
            gaugeItemMapper.insert(gaugeItem);
        } catch (Exception e) {
            e.printStackTrace();
            logger.fatal(e);
        }
    }
}
