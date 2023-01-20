package com.ots.trainingapi.global.core.enums;

import com.ots.trainingapi.global.utils.EnumUtils;

/**
 * Ναι-Όχι
 */
public enum YesNoEnum {
    
    NO,
    YES;
    
    @Override
    public String toString() {
        return EnumUtils.getMessage(this, "YesNoEnum");
    }
}
