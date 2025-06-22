package com.exam.colegio.util;

/**
 *
 * @author Gatomontes
 */
public enum DayOfWeek {

        LUNES("LUNES"),
        MARTES("MARTES"),
        MIERCOLES("MIERCOLES"),
        JUEVES("JUEVES"),
        VIERNES("VIERNES"),
        SABADO("SABADO"),
        DOMINGO("DOMINGO");

        private final String displayName;

        DayOfWeek(String displayName) {
                this.displayName = displayName;
        }

        public String getDisplayName() {
                return displayName;
        }
}
