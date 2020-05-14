package mx.gutru.http_request_gson_volley.models

class MatchReferenceDto (
    val platformId: String,
    val gameId: Long,
    val champion: Int,
    val queue: Int,
    val season: Int,
    val timestamp: Long,
    val role: String,
    val lane: String
)