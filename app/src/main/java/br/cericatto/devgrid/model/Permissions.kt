package br.cericatto.devgrid.model

data class Permissions(
    val admin: Boolean,
    val push: Boolean,
    val pull: Boolean
)