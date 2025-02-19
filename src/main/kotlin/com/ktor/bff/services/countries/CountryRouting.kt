import com.zinc.money.bff.services.countries.repository.CountriesRepository
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.countryRoutes() {
    route("/countries") {
        get {
            call.respondText(CountriesRepository.getAllCountries())
        }
    }
}