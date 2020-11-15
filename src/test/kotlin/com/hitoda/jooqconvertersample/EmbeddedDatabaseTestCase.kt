package com.hitoda.jooqconvertersample

import com.wix.mysql.EmbeddedMysql
import com.wix.mysql.config.MysqldConfig
import com.wix.mysql.distribution.Version
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

class EmbeddedDatabaseTestCase : BeforeAllCallback, AfterAllCallback {
    private var mysqld: EmbeddedMysql? = null

    override fun beforeAll(context: ExtensionContext?) {
        if (mysqld != null ) return

        val mysqlConfig = MysqldConfig.aMysqldConfig(Version.v5_7_latest)
                .withPort(3306)
                .build()
        try {
            mysqld = EmbeddedMysql.anEmbeddedMysql(mysqlConfig).start()
            Flyway.configure()
                    .dataSource(
                            "jdbc:mysql://localhost:3306/sample?useSSL=false&useUnicode=true&characterEncoding=utf8&createDatabaseIfNotExist=true",
                            "root", "")
                    .load().migrate()
        } catch (exception: Exception) {
            println(exception)
            mysqld?.stop()
        }
    }

    override fun afterAll(context: ExtensionContext?) {
        mysqld?.stop()
        mysqld = null
    }
}