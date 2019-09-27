package br.cericatto.devgrid.model

data class Owner(
    val login: String,
    val id:String,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: Int,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val received_events_url: String,
    val type:String,
    val site_admin: Boolean
)