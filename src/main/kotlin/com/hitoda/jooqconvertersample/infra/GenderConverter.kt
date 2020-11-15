package com.hitoda.jooqconvertersample.infra

import com.hitoda.jooqconvertersample.domain.Gender
import org.jooq.impl.EnumConverter

class GenderConverter: EnumConverter<String, Gender>(String::class.java, Gender::class.java)