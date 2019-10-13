import nu.studer.sample.Tables
import nu.studer.sample.Tables.AUTHOR
import nu.studer.sample.tables.Author
import java.sql.DriverManager
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.jooq.DSLContext





fun main() {
    val userName = "postgres"
    val password = "password"
    val url = "jdbc:postgresql://localhost:5432/postgres"

    try {
        DriverManager.getConnection(url, userName, password).use { conn ->
            val create = DSL.using(conn, SQLDialect.POSTGRES)
            val result = create.select().from(AUTHOR)
            (result.forEach { println(it) })

            val fetchOne = create.insertInto(AUTHOR, AUTHOR.ID, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
                .values(4, "Charlotte", "Roche")
                .returning(AUTHOR.ID)
                .fetchOne()
            println(fetchOne)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    // For the sake of this tutorial, let's keep exception handling simple


}