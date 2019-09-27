package br.cericatto.devgrid.model

data class Book(val id: Int, val name: String) {
    constructor() : this(-1, "")
}