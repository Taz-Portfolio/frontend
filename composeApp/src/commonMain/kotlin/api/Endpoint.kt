package api

enum class Endpoint(val path: String) {
    INFO("/info"),
    CONNECTIONS("/connections")
}