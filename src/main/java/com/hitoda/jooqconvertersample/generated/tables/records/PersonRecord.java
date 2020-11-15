/*
 * This file is generated by jOOQ.
 */
package com.hitoda.jooqconvertersample.generated.tables.records;


import com.hitoda.jooqconvertersample.domain.Gender;
import com.hitoda.jooqconvertersample.domain.PersonId;
import com.hitoda.jooqconvertersample.generated.tables.Person;

import java.time.ZonedDateTime;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PersonRecord extends UpdatableRecordImpl<PersonRecord> implements Record4<PersonId, String, Gender, ZonedDateTime> {

    private static final long serialVersionUID = 1958845814;

    /**
     * Setter for <code>sample.person.id</code>.
     */
    public void setId(PersonId value) {
        set(0, value);
    }

    /**
     * Getter for <code>sample.person.id</code>.
     */
    public PersonId getId() {
        return (PersonId) get(0);
    }

    /**
     * Setter for <code>sample.person.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>sample.person.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>sample.person.gender</code>.
     */
    public void setGender(Gender value) {
        set(2, value);
    }

    /**
     * Getter for <code>sample.person.gender</code>.
     */
    public Gender getGender() {
        return (Gender) get(2);
    }

    /**
     * Setter for <code>sample.person.birthday</code>.
     */
    public void setBirthday(ZonedDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>sample.person.birthday</code>.
     */
    public ZonedDateTime getBirthday() {
        return (ZonedDateTime) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<PersonId> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<PersonId, String, Gender, ZonedDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<PersonId, String, Gender, ZonedDateTime> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<PersonId> field1() {
        return Person.PERSON.ID;
    }

    @Override
    public Field<String> field2() {
        return Person.PERSON.NAME;
    }

    @Override
    public Field<Gender> field3() {
        return Person.PERSON.GENDER;
    }

    @Override
    public Field<ZonedDateTime> field4() {
        return Person.PERSON.BIRTHDAY;
    }

    @Override
    public PersonId component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Gender component3() {
        return getGender();
    }

    @Override
    public ZonedDateTime component4() {
        return getBirthday();
    }

    @Override
    public PersonId value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public Gender value3() {
        return getGender();
    }

    @Override
    public ZonedDateTime value4() {
        return getBirthday();
    }

    @Override
    public PersonRecord value1(PersonId value) {
        setId(value);
        return this;
    }

    @Override
    public PersonRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public PersonRecord value3(Gender value) {
        setGender(value);
        return this;
    }

    @Override
    public PersonRecord value4(ZonedDateTime value) {
        setBirthday(value);
        return this;
    }

    @Override
    public PersonRecord values(PersonId value1, String value2, Gender value3, ZonedDateTime value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PersonRecord
     */
    public PersonRecord() {
        super(Person.PERSON);
    }

    /**
     * Create a detached, initialised PersonRecord
     */
    public PersonRecord(PersonId id, String name, Gender gender, ZonedDateTime birthday) {
        super(Person.PERSON);

        set(0, id);
        set(1, name);
        set(2, gender);
        set(3, birthday);
    }
}