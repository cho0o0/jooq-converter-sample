package com.hitoda.jooqconvertersample

import com.wix.mysql.EmbeddedMysql
import com.wix.mysql.config.MysqldConfig
import com.wix.mysql.distribution.Version
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class FlywayMigrationTest {

    @Test
    fun test() {
        assertDoesNotThrow {
            val mysqlConfig = MysqldConfig.aMysqldConfig(Version.v5_7_latest)
                    .withFreePort()
                    .build()
            val mysql = EmbeddedMysql.anEmbeddedMysql(mysqlConfig).start()
            Flyway.configure()
                    .dataSource(
                            "jdbc:mysql://localhost:${mysqlConfig.port}/sample?useSSL=false&useUnicode=true&characterEncoding=utf8&createDatabaseIfNotExist=true",
                            "root", "")
                    .load().migrate()
            mysql.stop()
        }
    }
}