package com.hitoda.jooqconvertersample.infra

import org.jooq.Converter
import java.sql.Timestamp
import java.time.ZoneOffset
import java.time.ZonedDateTime

class ZonedDatetimeConverter: Converter<Timestamp, ZonedDateTime> {
    // Assume that timezone in database is UTC
    override fun from(databaseObject: Timestamp?) = databaseObject?.toLocalDateTime()?.atZone(ZoneOffset.UTC)

    override fun to(userObject: ZonedDateTime?) = userObject?.withZoneSameInstant(ZoneOffset.UTC)
            ?.toLocalDateTime()?.let { Timestamp.valueOf(it) }

    override fun fromType(): Class<Timestamp> = Timestamp::class.java

    override fun toType(): Class<ZonedDateTime> = ZonedDateTime::class.java
}