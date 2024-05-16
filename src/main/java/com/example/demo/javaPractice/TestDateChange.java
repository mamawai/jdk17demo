package com.example.demo.javaPractice;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TestDateChange {
    public static void main(String[] args) {
        String ltTimeStr = "2024-05-15 10:00:00";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ltTime = LocalDateTime.parse(ltTimeStr, f);
        // 报文时区
        ZoneId of = ZoneId.of("Australia/Melbourne");
        ZonedDateTime zonedDateTime = ltTime.atZone(of);
        ZoneId cstZoneId = ZoneId.systemDefault();
        ZonedDateTime cstZoneTime = zonedDateTime.withZoneSameInstant(cstZoneId);
        LocalDateTime localDateTime = cstZoneTime.toLocalDateTime();
        System.out.println(localDateTime);
    }
}
