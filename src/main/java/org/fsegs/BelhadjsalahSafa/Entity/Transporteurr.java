
package org.fsegs.BelhadjsalahSafa.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Transporteurr {
    IBS, YOSR, EXPRESS;

    @JsonCreator
    public static Transporteurr fromString(String value) {
        return Transporteurr.valueOf(value.toUpperCase());
    }
}

