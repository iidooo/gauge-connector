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
            GaugeProduct gaugeProduct = gaugeProductMapper.selectByProductModelCode(receiveGaugeItem.getProduct());       
            
            GaugeItem gaugeItem = new GaugeItem();
            if(gaugeProduct != null){
                gaugeItem.setProductID(gaugeProduct.getProductID());
            } else {
                gaugeItem.setProductID(0);
            }
            gaugeItem.setTemperature(receiveGaugeItem.getTemperature());
            gaugeItem.setPressure(receiveGaugeItem.getPressure());
            gaugeItem.setParticulate(receiveGaugeItem.getParticulate());
            gaugeItem.setErrorCode(receiveGaugeItem.getErrorCode());
            gaugeItem.setCreateTime(new Date());
            gaugeItem.setCreateUserID(1);
            gaugeItem.setUpdateUserID(1);
            gaugeItemMapper.insert(gaugeItem);
        } catch (Exception e) {
            e.printStackTrace();
            logger.fatal(e);
        }
    }
}
