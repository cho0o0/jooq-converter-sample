package com.hitoda.jooqconvertersample.infra

import com.hitoda.jooqconvertersample.domain.PersonId
import org.jooq.Converter

class PersonIdConverter: Converter<String, PersonId> {
    override fun from(databaseObject: String) = PersonId(databaseObject)

    override fun to(userObject: PersonId) = userObject.id

    override fun fromType(): Class<String> = String::class.java

    override fun toType(): Class<PersonId> = PersonId::class.java
}