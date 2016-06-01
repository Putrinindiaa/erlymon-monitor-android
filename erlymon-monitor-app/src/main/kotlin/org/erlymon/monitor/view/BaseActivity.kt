/*
 * Copyright (c) 2016, Sergey Penkovsky <sergey.penkovsky@gmail.com>
 *
 * This file is part of Erlymon Monitor.
 *
 * Erlymon Monitor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Erlymon Monitor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Erlymon Monitor.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.erlymon.monitor.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import org.erlymon.core.model.data.Storage
import org.erlymon.core.presenter.Presenter

/**
 * Created by Sergey Penkovsky <sergey.penkovsky@gmail.com> on 5/12/16.
 */
open class BaseActivity<P : Presenter> : AppCompatActivity(), BaseFragment.ServiceInterface {
    override var storage: Storage? = null
    protected var presenter: P? = null

    override fun onCreate(savedInstanceState :Bundle?) {
        super.onCreate(savedInstanceState)
        storage = Storage(this@BaseActivity, "erlymon-monitor-storage.realm")
        storage?.open()
    }

    override fun onStop() {
        super.onStop()
        if (presenter != null) {
            presenter!!.onStop()
        }
    }

    override fun onDestroy() {
        storage?.close()
        super.onDestroy()
    }

    protected fun makeToast(view: View, text: String) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
    }
}
