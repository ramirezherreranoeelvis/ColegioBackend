package com.exam.colegio.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
public class DateFormatUtil {

        private DateFormatUtil() {
                throw new IllegalStateException("Utility class");
        }

        public static String ymd(Date date) {
                var formatter = new SimpleDateFormat("yyyy-MM-dd");
                return formatter.format(date);
        }

        public static String getNameDay(Date date) {
                // Convertir Date a LocalDateTime
                LocalDateTime localDateTime = date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                // Obtener el nombre del día de la semana
                return localDateTime.getDayOfWeek()
                        .getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

        }


}
