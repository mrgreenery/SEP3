package com.db.sep3.gRPC.mapper;

import com.google.protobuf.Timestamp;
import java.time.Instant;

public class TimeMapper {
    public static Timestamp toTimestamp(Instant instant) {
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }

    public static Instant fromTimestamp(Timestamp ts) {
        return Instant.ofEpochSecond(ts.getSeconds(), ts.getNanos());
    }
}
