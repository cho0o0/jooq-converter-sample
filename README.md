# jOOQ Converter Sample

A sample project shows how to use jOOQ's converter feature to make data type conversion easier, so that the implementation in the infrastructure layer (jOOQ in this case) won't invade our domain-layer's design.

- [ID Conversion](src/main/kotlin/com/hitoda/jooqconvertersample/infra/PersonIdConverter.kt): [PersonId](src/main/kotlin/com/hitoda/jooqconvertersample/domain/PersonId.kt) in the domain layer will be used in the jOOQ's entity though the data type in MySQL is VARCHAR.
- [Enum Conversion](src/main/kotlin/com/hitoda/jooqconvertersample/infra/GenderConverter.kt): [Gender](src/main/kotlin/com/hitoda/jooqconvertersample/domain/Gender.kt) in the domain layer will be used instead of an automatically generated PersonGender class.
- [Timestamp Conversion](src/main/kotlin/com/hitoda/jooqconvertersample/infra/ZonedDatetimeConverter.kt): [ZonedDatetime](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/ZonedDateTime.html) will be used though the data type in MySQL is TIMESTAMP.

## Commands

- **Generate jOOQ entities**: `./gradlew generateJooqEntities`
- **Run test cases**: `./gradlew test`

## Main stacks
- Spring Boot
- Flyway
- jOOQ
- Embedded MySQL (_Port 3306 will be used_) 

## Reference
- [Custom data type conversion](https://www.jooq.org/doc/latest/manual/sql-execution/fetching/data-type-conversion/)
- [Custom data types and type conversion](https://www.jooq.org/doc/latest/manual/code-generation/custom-data-types/)