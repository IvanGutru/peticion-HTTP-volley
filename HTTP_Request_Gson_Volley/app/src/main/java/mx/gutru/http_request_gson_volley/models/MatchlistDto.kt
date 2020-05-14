package mx.gutru.http_request_gson_volley.models

class MatchlistDto (
    val startIndex :Int,
    val totalGames :Int,
    val endIndex :Int,
    val matches : List<MatchReferenceDto>
)