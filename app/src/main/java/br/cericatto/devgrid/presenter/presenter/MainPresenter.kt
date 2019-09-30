package br.cericatto.devgrid.presenter.presenter

import br.cericatto.devgrid.model.Repo
import br.cericatto.devgrid.presenter.api.ApiService

/**
 * MainPresenter.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
interface MainPresenter {
    fun getExtras(): Pair<String, String>
    fun initDataSet(service: ApiService, login: String, password: String)
    fun showData(repos: List<Repo>)
    fun showErrorMessage(error: String)
}