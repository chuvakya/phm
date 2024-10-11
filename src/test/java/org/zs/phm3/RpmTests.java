package org.zs.phm3;

import org.zs.phm3.dto.ptl.DtoIdIntName;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.rpm.*;
import org.zs.phm3.repository.rpm.ProductCalcRepository;
import org.zs.phm3.repository.rpm.TotalCalcRepository;
import org.zs.phm3.repository.unit.UnitRepository;
import org.zs.phm3.service.rpm.GjbFeignProxyClient;
import org.zs.phm3.service.rpm.GjbRestClient;
import org.zs.phm3.service.rpm.RpmCommonService;
import org.zs.phm3.util.mapping.PhmUtil;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RpmTests {

    @Autowired
    TotalCalcRepository totalCalcRepository;
    @Autowired
    ProductCalcRepository productCalcRepository;
    @Autowired
    UnitRepository unitRepository;
    @Autowired
    RpmCommonService rpmCommonService;
    @Autowired
    GjbRestClient gjbRestClient;
    @Autowired
    GjbFeignProxyClient gjbFeignProxyClient;
    @Test
    public void n1Save() {
        TotalCalc newItem = new TotalCalc(
                new CalcId(PhmUtil.toShortUUID("74add9fb-0499-11eb-9f43-dd42a99abb49"), ParamCode.Λ),
                20,
                0.08724);
        totalCalcRepository.save(newItem);

        ProductCalc newItem2 = new ProductCalc(
                new CalcId(PhmUtil.toShortUUID("74add9fb-0499-11eb-9f43-dd42a99abb49"), ParamCode.Λ),
//                UnitEntity.convertToShortUnitId("74add9fb-0499-11eb-9f43-dd42a99abb49"),20,
                "", 3, 0.08724, CalculatedType.CALCULATED_TYPE, "----");

        productCalcRepository.save(newItem2);
    }

    @Test
    public void n2getData() {
        String unitId = "bf583e8a-09f9-11eb-9568-b7e2db38c9b0";
        RpmDtoOutput readingData = rpmCommonService.getData(unitId);
    }

    @Test
    public void n3SaveData() throws PhmException {
        ProductCalc productCalc = new ProductCalc(new CalcId("bf68e05b-09f9-11eb-9568-4f3baa93d19d", ParamCode.Λ),
                "bf583e8a-09f9-11eb-9568-b7e2db38c9b0", 2, 0.033,
                CalculatedType.MANUAL_TYPE, "---");
        RpmDtoIntput input = new RpmDtoIntput(productCalc, null);
        rpmCommonService.saveData(input);

/*        List<ProductCalc> productCalcList=new ArrayList<>();
        productCalcList.add(new ProductCalc(new CalcId("bf68e05b-09f9-11eb-9568-4f3baa93d19d", ParamCode.Λ),
                "bf583e8a-09f9-11eb-9568-b7e2db38c9b0", 2,0.033,
                CalculatedType.MANUAL_TYPE,"---"));
        productCalcList.add(new ProductCalc(new CalcId("bf6de96c-09f9-11eb-9568-21264a100d11", ParamCode.Λ),
                "bf583e8a-09f9-11eb-9568-b7e2db38c9b0",1,0.176,
                CalculatedType.MANUAL_TYPE,"---"));
        rpmCommonService.saveData(productCalcList);*/
    }

    @Test
    public void n4GjbGetStatus() {
        gjbRestClient.getGjbStatus();
    }

    @Test
    public void n5_GetGjbResult() {

        Map<String, String> items = new LinkedHashMap<>();
        // * ElementClasses = 3

/*        {
            "lambda_b/lambda_b/paramPrimary/121": "15",
                "lambda_b/lambda_b/paramSecondary/69": "0.1",
                "lambda_b/lambda_b/paramThird/1": "NPN",
                "pi_E/RefCommonEnvironmentalType/name/7": "MP",
                "pi_Q/RefCommonQualityType/name/3": "A3",
                "pi_A/RefCommonUtilizationType/name/10": "Linear zoom",
                "pi_S2/pi_S2/condition/4": "0.8 < S2<= 0.9",
                "pi_R/pi_R/condition/3": "1 <= P < 5",
                "pi_C/RefCommonStructureType/name/7": "Matched pair"
        }*/

        items.put("lambda_b/lambda_b/paramPrimary/121", "15");
        items.put("lambda_b/lambda_b/paramSecondary/69", "0.1");
        items.put("lambda_b/lambda_b/paramThird/1", "NPN");
        items.put("pi_E/RefCommonEnvironmentalType/name/7", "MP");
        items.put("pi_Q/RefCommonQualityType/name/3", "A3");
        items.put("pi_A/RefCommonUtilizationType/name/10", "Linear zoom");
        items.put("pi_S2/pi_S2/condition/4", "0.8 < S2<= 0.9");
        items.put("pi_R/pi_R/condition/3", "1 <= P < 5");
        items.put("pi_C/RefCommonStructureType/name/7", "Matched pair");

//        gjbRestClient.getGjbResult(3, 1, items);
        System.out.println(gjbFeignProxyClient.getGjbResult(3, 1, items));
        items.clear();

        items.put("lambda_b/lambda_b/paramPrimary/1", "0.0");
        items.put("lambda_b/lambda_b/paramSecondary/1", "0.1");
        items.put("pi_E/RefCommonEnvironmentalType/name/1", "Ground benign");
        items.put("pi_Q/RefCommonQualityType/name/3", "A3");
        items.put("pi_A/RefCommonUtilizationType/name/3", "Switch");
        items.put("pi_C/RefCommonStructureType/name/3", "Single tube");
        items.put("pi_K/pi_K/type_value/1", "Knot");
        items.put("pi_r/pi_r/condition/6", "P <= 0.5");
//        gjbRestClient.getGjbResult(6, 2, items);
        System.out.println(gjbFeignProxyClient.getGjbResult(6, 2, items));
    }

    @Test
    public void n7testFeign(){
//        rpmCommonService.testFeign();
        gjbFeignProxyClient.getGjbStatus();
    }

    @Test
    public void n8_testParameters(){
        Map<String, String> items = new LinkedHashMap<>();
        items.put("lambda_b/lambda_b/paramPrimary/1", "0.0");
        String result=gjbFeignProxyClient.testParameters(2, 100, items);
        byte a=0;
    }

    @Test
    public void n6GGjbResultStored() {
        Map<String, String> items = new LinkedHashMap<>();
        items.put("ready/ready/RefCommonEnvironmentalType/name/16", "Helicopter");
        Double result=gjbRestClient.getGjbResult(227, 1, items);
        byte a =0;
/*        {
            "inputParametersValuesList":{
            "ready/ready/RefCommonEnvironmentalType/name/16":"Helicopter"
        },
            "productCalc":{
            "calcId":{
                "paramCode":"Λ",
                        "unitId":"bf68e05b-09f9-11eb-9568-4f3baa93d19d"
            },
            "calculatedType":"CALCULATED_TYPE",
                    "parameters":"string",
                    "parentId":"bf583e8a-09f9-11eb-9568-b7e2db38c9b0",
                    "productId":227,
                    "quantity":2,
                    "value":0.9999
        }
        }*/

    }
/*
[
  {
    "calcId": {
      "paramCode": "Λ",
      "unitId": "bf68e05b-09f9-11eb-9568-4f3baa93d19d"
    },
    "calculatedType": "MANUAL_TYPE",
    "parameters": "string",
    "parentId": "bf583e8a-09f9-11eb-9568-b7e2db38c9b0",
    "productId": 0,
    "quantity": 3,
    "value": 0.33
  }
,
  {
    "calcId": {
      "paramCode": "Λ",
      "unitId": "bf6de96c-09f9-11eb-9568-21264a100d11"
    },
    "calculatedType": "MANUAL_TYPE",
    "parameters": "string",
    "parentId": "bf583e8a-09f9-11eb-9568-b7e2db38c9b0",
    "productId": 0,
    "quantity": 1,
    "value": 0.725
  }
]

{
  "inputParametersValuesList": {
  },
  "productCalc": {
    "calcId": {
      "paramCode": "Λ",
      "unitId": "bf6de96c-09f9-11eb-9568-21264a100d11"
    },
    "calculatedType": "MANUAL_TYPE",
    "parameters": "string",
    "parentId": "bf583e8a-09f9-11eb-9568-b7e2db38c9b0",
    "productId": 0,
    "quantity": 1,
    "value": 0.725
  }
}

{
  "inputParametersValuesList": {
     "lambda_b/lambda_b/paramPrimary/121": "15",
     "lambda_b/lambda_b/paramSecondary/69": "0.1",
     "lambda_b/lambda_b/paramThird/1": "NPN",
     "pi_E/RefCommonEnvironmentalType/name/7": "MP",
     "pi_Q/RefCommonQualityType/name/3": "A3",
     "pi_A/RefCommonUtilizationType/name/10": "Linear zoom",
     "pi_S2/pi_S2/condition/4": "0.8 < S2<= 0.9",
     "pi_R/pi_R/condition/3": "1 <= P < 5",
     "pi_C/RefCommonStructureType/name/7": "Matched pair"
  },
  "productCalc": {
    "calcId": {
      "paramCode": "Λ",
      "unitId": "bf68e05b-09f9-11eb-9568-4f3baa93d19d"
    },
    "calculatedType": "CALCULATED_TYPE",
    "parameters": "string",
    "parentId": "bf583e8a-09f9-11eb-9568-b7e2db38c9b0",
    "productId": 3,
    "quantity": 2,
    "value": 0.9999
  }
  }


  {
  "inputParametersValuesList": {
     "lambda_b/lambda_b/paramPrimary/1": "0.0",
     "lambda_b/lambda_b/paramSecondary/1": "0.1",
     "pi_E/RefCommonEnvironmentalType/name/1": "Ground benign",
     "pi_Q/RefCommonQualityType/name/3": "A3",
     "pi_A/RefCommonUtilizationType/name/3": "Switch",
     "pi_C/RefCommonStructureType/name/3": "Single tube",
     "pi_K/pi_K/type_value/1": "Knot",
     "pi_r/pi_r/condition/6": "P <= 0.5"
  },
  "productCalc": {
    "calcId": {
      "paramCode": "Λ",
      "unitId": "bf6de96c-09f9-11eb-9568-21264a100d11"
    },
    "calculatedType": "CALCULATED_TYPE",
    "parameters": "string",
    "parentId": "bf583e8a-09f9-11eb-9568-b7e2db38c9b0",
    "productId": 6,
    "quantity": 1,
    "value": 0
  }
  }
}

    */
}
