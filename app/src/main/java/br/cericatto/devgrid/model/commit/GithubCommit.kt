package br.cericatto.devgrid.model.commit

data class GithubCommit(
    val commit: Commit,
    val sha: String,
    val url: String
)