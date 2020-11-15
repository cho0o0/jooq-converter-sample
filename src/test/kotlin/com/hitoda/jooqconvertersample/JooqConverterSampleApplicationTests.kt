package com.hitoda.jooqconvertersample

import com.hitoda.jooqconvertersample.domain.Gender
import com.hitoda.jooqconvertersample.domain.Person
import com.hitoda.jooqconvertersample.generated.tables.Person.PERSON
import org.ajbrown.namemachine.NameGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jooq.DSLContext
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@ExtendWith(EmbeddedDatabaseTestCase::class)
class JooqConverterSampleApplicationTests(@Autowired private val dslContext: DSLContext) {

    @Test
    fun crud() {
        NameGenerator().generateName().let { check(Person(name = it.toString(), gender = Gender.valueOf(it.gender.name))) }
        check(Person(name = NameGenerator().generateName().toString(), gender = null, birthday = null))
    }

    private fun check(person: Person) {
        println("start to check $person")

        dslContext.insertInto(PERSON)
                .set(PERSON.ID, person.id)
                .set(PERSON.NAME, person.name)
                .set(PERSON.GENDER, person.gender)
                .set(PERSON.BIRTHDAY, person.birthday)
                .execute()

        val fetchedPerson = dslContext.selectFrom(PERSON)
                .where(PERSON.ID.eq(person.id))
                .fetch()
                .apply { println(this) }
                .into(Person::class.java)
        assertThat(fetchedPerson.size).isEqualTo(1)

        val createdPerson = fetchedPerson[0]
        assertThat(createdPerson).isEqualTo(person)
    }
}
