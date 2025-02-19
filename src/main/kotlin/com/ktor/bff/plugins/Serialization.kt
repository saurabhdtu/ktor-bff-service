import com.google.gson.FieldNamingPolicy
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.defaultheaders.*

fun Application.configureSerialization(){
    install(ContentNegotiation){
        gson{
            // Set field naming policy to snake_case
            setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            // Set pretty printing for better readability
            setPrettyPrinting()
        }
    }
}