package br.cericatto.devgrid.presenter.di.component

import br.cericatto.devgrid.presenter.di.module.TestModule
import br.cericatto.devgrid.presenter.di.scope.PerActivity
import br.cericatto.devgrid.view.TestActivity
import dagger.Component

/**
 * MainComponent.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
@PerActivity
@Component(modules = [TestModule::class], dependencies = [ApplicationComponent::class])
interface TestComponent {
    fun inject(target: TestActivity)
}