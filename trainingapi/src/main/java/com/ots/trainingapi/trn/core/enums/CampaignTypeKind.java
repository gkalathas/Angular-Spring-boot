package com.ots.trainingapi.trn.core.enums;

import com.ots.trainingapi.global.utils.EnumUtils;

/**
 * Είδος Τύπου Καμπάνιας
 */
public enum CampaignTypeKind {
        
    PRODUCTION {
        @Override
        public String toString() {
            return "Παραγωγική";
        }
    },

    TESTING {
        @Override
        public String toString() {
            return "Δοκιμαστική";
        }
    }
}
