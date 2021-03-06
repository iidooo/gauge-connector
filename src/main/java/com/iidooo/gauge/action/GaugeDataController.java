package com.iidooo.gauge.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.iidooo.gauge.model.po.GaugeItem;
import com.iidooo.gauge.service.GaugeDataService;
import com.iidooo.gauge.service.impl.GaugeDataServiceImpl;
import com.iidooo.gauge.util.StringUtil;

public class GaugeDataController {
    private static final Logger logger = Logger.getLogger(GaugeDataController.class);

    private GaugeDataService gaugeDataService;

    public GaugeDataController() {
        this.gaugeDataService = new GaugeDataServiceImpl();
    }

    public boolean receiveGaugeData(String receivedData) {
        try {
            logger.info(receivedData);
            if (!receivedData.startsWith("{")) {
                logger.warn("Is not a json format!");
                return false;
            }
            JSONObject jsonObject = JSONObject.fromObject(receivedData);
            String productModel = "";
            if(jsonObject.containsKey("model")){
                productModel = jsonObject.getString("model");                
            }
            
            String productCode = jsonObject.getString("code");
            String temperature = jsonObject.getString("temp");
            String pressure = jsonObject.getString("press");
            String errorCode = "";
            if(jsonObject.containsKey("err")){
                errorCode = jsonObject.getString("err");
            }
            GaugeItem gaugeItem = new GaugeItem();
            gaugeItem.getProduct().setProductModel(productModel);
            gaugeItem.getProduct().setProductCode(productCode);
            if (StringUtil.isNotBlank(temperature)) {
                gaugeItem.setTemperature(Float.valueOf(temperature));
            }
            if (StringUtil.isNotBlank(pressure)) {
                gaugeItem.setPressure(Float.valueOf(pressure));
            }
            if(StringUtil.isNotBlank(errorCode)){
                gaugeItem.setErrorCode(errorCode);
            }
            
            gaugeDataService.receiveGuageData(gaugeItem);
            return true;
        } catch (Exception e) {
            logger.fatal(e);
            return false;
        }
    }
}
