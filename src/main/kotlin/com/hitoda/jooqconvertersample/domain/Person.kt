package com.hitoda.jooqconvertersample.domain

import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

data class Person(
        val id: PersonId = PersonId(),
        val name: String,
        val gender: Gender?,
        // Truncate to seconds since Timestamp type doesn't save millisecond info
        val birthday: ZonedDateTime? = ZonedDateTime.now(ZoneOffset.UTC).truncatedTo(ChronoUnit.SECONDS)
)