package services

import java.sql.{Connection, DriverManager}

import org.jooq.impl.DSL
import org.jooq.{DSLContext, SQLDialect}

object Driver {
	def getDbContext(): DSLContext = DSL.using(getConnection, SQLDialect.POSTGRES)

	def getConnection(): Connection = {
		Class.forName("org.postgresql.Driver")
		DriverManager.getConnection("jdbc:postgresql://localhost:32768/jooq", "jooquser", "jooquser") // Standard JDBC connection
	}
}
