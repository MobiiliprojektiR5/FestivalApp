import kotlinx.serialization.Serializable

@Serializable
data class FestivalModel(
    val dateend: String,
    val datestart: String,
    val imageurl: String,
    val infotext: String,
    val locationaddress: String,
    val locationtown: String,
    val name: String,
    val tickets: String,
    val webpage: String
)