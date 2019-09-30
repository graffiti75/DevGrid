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
    fun initDataSet(service: ApiService)
    fun showData(repos: List<Repo>)
    fun showErrorMessage()
}