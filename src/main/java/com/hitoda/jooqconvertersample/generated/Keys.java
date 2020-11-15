/*
 * This file is generated by jOOQ.
 */
package com.hitoda.jooqconvertersample.generated;


import com.hitoda.jooqconvertersample.generated.tables.Person;
import com.hitoda.jooqconvertersample.generated.tables.records.PersonRecord;

import javax.annotation.processing.Generated;

import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>sample</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<PersonRecord> KEY_PERSON_PRIMARY = UniqueKeys0.KEY_PERSON_PRIMARY;
    public static final UniqueKey<PersonRecord> KEY_PERSON_ID = UniqueKeys0.KEY_PERSON_ID;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<PersonRecord> KEY_PERSON_PRIMARY = Internal.createUniqueKey(Person.PERSON, "KEY_person_PRIMARY", Person.PERSON.ID);
        public static final UniqueKey<PersonRecord> KEY_PERSON_ID = Internal.createUniqueKey(Person.PERSON, "KEY_person_id", Person.PERSON.ID);
    }
}