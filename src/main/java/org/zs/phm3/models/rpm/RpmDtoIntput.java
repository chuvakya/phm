package org.zs.phm3.models.rpm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(value = {"new"})
public class RpmDtoIntput {
//    private List<TotalCalc> totalCalcList;
    private ProductCalc productCalc;
    private Map<String, String> inputParametersValuesList;
//    private Map<String, String> inputParametersValuesList;

    public RpmDtoIntput(ProductCalc productCalc, Map<String, String> inputParametersValuesList) {
//        this.totalCalcList = totalCalcList;
        this.productCalc = productCalc;
        this.inputParametersValuesList = inputParametersValuesList;
    }

    public ProductCalc getProductCalc() {
        return productCalc;
    }

    public void setProductCalc(ProductCalc productCalc) {
        this.productCalc = productCalc;
    }

    public Map<String, String> getInputParametersValuesList() {
        return inputParametersValuesList;
    }

    public void setInputParametersValuesList(Map<String, String> inputParametersValuesList) {
        this.inputParametersValuesList = inputParametersValuesList;
    }

    /*    public Map<String, String> getInputParametersValuesList() {
        return inputParametersValuesList;
    }

    public void setInputParametersValuesList(Map<String, String> inputParametersValuesList) {
        this.inputParametersValuesList = inputParametersValuesList;
    }*/
}
