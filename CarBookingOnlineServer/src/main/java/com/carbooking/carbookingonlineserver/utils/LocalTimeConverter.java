package com.carbooking.carbookingonlineserver.utils;

import com.opencsv.bean.AbstractBeanField;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

    public class LocalTimeConverter extends AbstractBeanField<LocalTime, String> {

        @Override
        protected Object convert(String value) {
            // Check if the time string contains AM or PM
            if (value.matches(".*[APap][Mm]")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
                return LocalTime.parse(value, formatter);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
                return LocalTime.parse(value, formatter);
            }
        }
    }
