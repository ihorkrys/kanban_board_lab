package edu.duan.app.kanban.mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DateTimeMapper {
    public static Timestamp toTimestamp(LocalDateTime localDateTime) {
        return (localDateTime != null) ? Timestamp.valueOf(localDateTime) : null;
    }

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return (timestamp != null) ? timestamp.toLocalDateTime() : null;
    }
}
