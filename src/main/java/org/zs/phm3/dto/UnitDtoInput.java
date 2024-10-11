package org.zs.phm3.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.zs.phm3.dto.ptl.PtlInput;

@JsonIgnoreProperties(value = {"new"})
public class UnitDtoInput extends UnitDto {

    private PtlInput ptlInput;

    public UnitDtoInput() {
    }

/*    public UnitDtoInput(PtlInput ptlInput) {
        this.ptlInput = ptlInput;
    }*/

    public UnitDtoInput(Integer projectId, String name, String description) {
        super(projectId, name, description);
    }

    public UnitDtoInput(Integer projectId, String name, String description, PtlInput ptlInput) {
        super(projectId, name, description);
        this.ptlInput = ptlInput;
    }

    public PtlInput getPtlInput() {
        return ptlInput;
    }

    public void setPtlInput(PtlInput ptlInput) {
        this.ptlInput = ptlInput;
    }
}
