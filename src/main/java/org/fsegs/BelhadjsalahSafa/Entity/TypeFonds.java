package org.fsegs.BelhadjsalahSafa.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TypeFonds {
    DINAR, DEVISE, MONNAIE;

    @JsonCreator
    public static TypeFonds fromString(String value) {
        return TypeFonds.valueOf(value.toUpperCase());
    } 
    }