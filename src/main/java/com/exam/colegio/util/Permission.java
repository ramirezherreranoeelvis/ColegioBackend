package com.exam.colegio.util;

import lombok.Getter;
/**
 * @author Gatomontes
 */
@Getter
public enum Permission {

        TEACHER("teacher"),
        ALL("all");

        private final String displayName;

        Permission(String displayName) {
                this.displayName = displayName;
        }
}
