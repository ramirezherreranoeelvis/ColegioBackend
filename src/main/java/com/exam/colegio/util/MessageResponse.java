package com.exam.colegio.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponse<T> {
        private String message;

        private T result;

        public static <T> MessageResponse<T> message(String message) {
                var msg = new MessageResponse<T>();
                msg.setMessage(message);
                return msg;
        }

        public static <T> MessageResponse<T> message(String message, T result) {
                var msg = new MessageResponse<T>();
                msg.setMessage(message);
                msg.setResult(result);
                return msg;
        }
}
