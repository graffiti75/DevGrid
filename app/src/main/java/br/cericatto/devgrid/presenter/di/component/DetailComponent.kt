package br.cericatto.devgrid.presenter.di.component

import br.cericatto.devgrid.presenter.di.module.DetailModule
import br.cericatto.devgrid.presenter.di.scope.PerActivity
import br.cericatto.devgrid.view.activity.DetailActivity
import dagger.Component

/**
 * DetailComponent.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 29, 2019
 */
@PerActivity
@Component(modules = [DetailModule::class], dependencies = [ApplicationComponent::class])
interface DetailComponent {
    fun inject(target: DetailActivity)
}