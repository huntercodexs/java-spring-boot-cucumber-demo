package com.huntercodexs.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnumerator {
    USER_NOT_FOUND("No user found with this id"),
    ENTITY_DOES_NOT_BELONG_TO_USER("Entity does not belong to user exception");
    private String exception;

    public static String getException(ExceptionEnumerator exceptionEnumerator) {
        return exceptionEnumerator.getException();
    }
}
