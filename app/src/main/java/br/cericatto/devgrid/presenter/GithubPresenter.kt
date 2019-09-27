package br.cericatto.devgrid.presenter

import br.cericatto.devgrid.model.Repo
import br.cericatto.devgrid.presenter.api.ApiService

/**
 * GithubPresenter.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
interface GithubPresenter {
    fun initDataSet(service: ApiService)
    fun showData(repos: List<Repo>)
    fun showErrorMessage()
}