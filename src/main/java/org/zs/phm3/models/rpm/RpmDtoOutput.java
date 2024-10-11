package org.zs.phm3.models.rpm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(value = {"new"})
public class RpmDtoOutput {
    private List<TotalCalc> totalCalcList;
    private List<ProductCalc> productCalcList;
//    private Map<String, String> inputParametersValuesList;

    public RpmDtoOutput(List<TotalCalc> totalCalcList, List<ProductCalc> productCalcList) {
        this.totalCalcList = totalCalcList;
        this.productCalcList = productCalcList;
    }

    public List<TotalCalc> getTotalCalcList() {
        return totalCalcList;
    }

    public void setTotalCalcList(List<TotalCalc> totalCalcList) {
        this.totalCalcList = totalCalcList;
    }

    public List<ProductCalc> getProductCalcList() {
        return productCalcList;
    }

    public void setProductCalcList(List<ProductCalc> productCalcList) {
        this.productCalcList = productCalcList;
    }

/*    public Map<String, String> getInputParametersValuesList() {
        return inputParametersValuesList;
    }

    public void setInputParametersValuesList(Map<String, String> inputParametersValuesList) {
        this.inputParametersValuesList = inputParametersValuesList;
    }*/
}
