package br.cericatto.devgrid.model.commit

data class Commit(
    val author: Author,
    val committer: Committer,
    val message: String,
    val tree: Tree,
    val url: String,
    val comment_count: Int
)