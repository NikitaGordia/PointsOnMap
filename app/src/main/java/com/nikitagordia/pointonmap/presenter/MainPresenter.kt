package com.nikitagordia.pointonmap.presenter

import android.util.Log
import com.nikitagordia.pointonmap.model.Model
import com.nikitagordia.pointonmap.model.remote.jsongenerator.MainModel
import com.nikitagordia.pointonmap.view.ViewInterface
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.CoroutineExceptionHandler
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
        job = launch(CommonPool + CoroutineExceptionHandler({ _, e -> if (e.message != "Job was cancelled normally") onError() })) {
            val result = data.getPoints()
            result?.apply { launch(UI) { view.onData(this@apply) } } ?: onError()
        }
    }

    private fun onError() {
        job?.cancel()
        launch(UI) { view.onError() }
    }

    override fun stop() {
        job?.cancel()
    }
}