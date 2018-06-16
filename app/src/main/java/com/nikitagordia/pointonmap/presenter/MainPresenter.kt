package com.nikitagordia.pointonmap.presenter

import com.nikitagordia.pointonmap.model.Model
import com.nikitagordia.pointonmap.model.remote.jsongenerator.MainModel
import com.nikitagordia.pointonmap.view.ViewInterface
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

/**
 * Created by nikitagordia on 6/16/18.
 */

class MainPresenter(val view: ViewInterface) : Presenter {

    val data: Model = MainModel()
    var job: Job? = null

    override fun fetchData() {
        job?.cancel()
        job = launch(CommonPool) {
            val result = data.getPoints()
            launch(UI) {
                if (result == null) view.onError() else view.onData(result)
            }
        }
    }

    override fun stop() {
        job?.cancel()
    }
}