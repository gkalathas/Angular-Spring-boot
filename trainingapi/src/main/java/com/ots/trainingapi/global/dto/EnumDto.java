package com.ots.trainingapi.global.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Γενικό dto για enums
 */
@ApiModel("Enum")
public class EnumDto {
    
    @ApiModelProperty("Τιμή")
    private String value;
    
    @ApiModelProperty("Λεκτικό")
    private String label;
    
    @ApiModelProperty("Αριθμός")
    private Integer ordinal;
    
    
    public EnumDto() {
    
    }
    
    public EnumDto(String value, String label, Integer ordinal) {
        this.value = value;
        this.label = label;
        this.ordinal = ordinal;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public Integer getOrdinal() {
        return ordinal;
    }
    
    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }
}
