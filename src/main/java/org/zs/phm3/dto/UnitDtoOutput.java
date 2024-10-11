package org.zs.phm3.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.zs.phm3.dto.ptl.PtlOutput;

@JsonIgnoreProperties(value = {"new"})
public class UnitDtoOutput extends UnitDto {

    private PtlOutput ptlOutput;

    public UnitDtoOutput() {
    }

    public PtlOutput getPtlOutput() {
        return ptlOutput;
    }

    public void setPtlOutput(PtlOutput ptlOutput) {
        this.ptlOutput = ptlOutput;
    }

    @Override
    public String toString() {
        return "UnitDtoOutput{" +
                super.toString()+"\n"+
                "ptlOutput=" + ptlOutput +
                '}';
    }

/*    @Override
    public String toString() {
//        return "UnitDtoOutput{}";
        return super.toString();
    }*/

}
