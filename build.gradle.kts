import com.rohanprabhu.gradle.plugins.kdjooq.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.0"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("com.github.michaelruocco.embedded-mysql-plugin") version "2.1.11"
    id("org.flywaydb.flyway") version "7.2.0"
    id("com.rohanprabhu.kotlin-dsl-jooq") version "0.4.6"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.10"
}

group = "com.hitoda"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

val databaseUsername = "test"
val databasePassword = "password"
val databaseJdbcUrl = "jdbc:mysql://localhost:3306/sample"

repositories {
    mavenCentral()
}

embeddedMysql {
    setUrl(databaseJdbcUrl)
    username = databaseUsername
    password = databasePassword
    setVersion("v5_7_latest")
    schema = "sample"
    serverCharset = "utf8"
    serverCollate = "utf8_general_ci"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.flywaydb:flyway-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("mysql:mysql-connector-java")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.wix:wix-embedded-mysql:4.6.1")
    testImplementation("org.ajbrown:name-machine:1.0.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

apply(plugin = "org.flywaydb.flyway")
flyway {
    url = databaseJdbcUrl
    user = databaseUsername
    password = databasePassword
}

apply(plugin = "com.rohanprabhu.kotlin-dsl-jooq")
jooqGenerator {
    attachToCompileJava = false
    jooqEdition = JooqEdition.OpenSource
    configuration("app", project.sourceSets.getByName("main")) {
        configuration = jooqCodegenConfiguration {
            jdbc {
                username = databaseUsername
                password = databasePassword
                driver = "com.mysql.cj.jdbc.Driver"
                url = databaseJdbcUrl
            }

            generator {
                target {
                    packageName = "com.hitoda.jooqconvertersample.generated"
                    directory = "${project.projectDir}/src/main/java"
                }

                database {
                    name = "org.jooq.meta.mysql.MySQLDatabase"
                    inputSchema = "sample"
                    excludes = "flyway_schema_history"

                    forcedTypes {
                        forcedType {
                            userType = "com.hitoda.jooqconvertersample.domain.PersonId"
                            converter = "com.hitoda.jooqconvertersample.infra.PersonIdConverter"
                            includeExpression = "PERSON\\.ID"
                            includeTypes = "VARCHAR.*"
                        }

                        forcedType {
                            userType = "java.time.ZonedDateTime"
                            converter = "com.hitoda.jooqconvertersample.infra.ZonedDatetimeConverter"
                            includeExpression = "PERSON\\.BIRTHDAY"
                            includeTypes = "TIMESTAMP"
                        }

                        forcedType {
                            userType = "com.hitoda.jooqconvertersample.domain.Gender"
                            converter = "com.hitoda.jooqconvertersample.infra.GenderConverter"
                            includeExpression = ".*\\.GENDER"
                            includeTypes = "ENUM.*"
                        }
                    }
                }
            }

            dependencies {
                jooqGeneratorRuntime("mysql:mysql-connector-java")
            }
        }
    }
}

tasks.register("generateJooqEntities") {
    group = "jooq-codegen"
    setDependsOn(setOf(
            tasks.startEmbeddedMysql,
            tasks.flywayClean,
            tasks.flywayMigrate,
            tasks.getByName("jooq-codegen-app")
    ))
    tasks.getByName("flywayClean").mustRunAfter("startEmbeddedMysql")
    tasks.getByName("flywayMigrate").mustRunAfter("flywayClean")
    tasks.getByName("jooq-codegen-app").mustRunAfter("flywayMigrate")
    setFinalizedBy(setOf(tasks.stopEmbeddedMysql))
}
