package tech.iuic.iuicwork.base

import android.arch.lifecycle.ViewModel

import javax.inject.Inject
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Provider

import javax.inject.Singleton





/**
 * Created by nanoid3 on 27.10.2017.
 */
@Singleton
class ProjectViewModelFactory @Inject
constructor(var creators: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("unknown model class " + modelClass)
        }
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}