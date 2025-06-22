package com.exam.colegio.exception;

/**
 *
 * @author Gatomontes
 */
public class NullValueException extends RuntimeException {

        public NullValueException() {
        }

        public NullValueException(String msg) {
                super(msg);
        }

        public static void checkForNull(Object obj) {
                if (obj == null) {
                        throw new NullValueException();
                }
        }

}
