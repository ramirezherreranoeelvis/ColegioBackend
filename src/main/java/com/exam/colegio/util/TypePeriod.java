package com.exam.colegio.util;

public enum TypePeriod {

        BIMESTRE("BIMESTRE"),
        TRIMESTRE("TRIMESTRE"),
        SEMESTRE("SEMESTRE");

        private final String displayName;

        TypePeriod(String displayName) {
                this.displayName = displayName;
        }

        public String getDisplayName() {
                return displayName;
        }
}
