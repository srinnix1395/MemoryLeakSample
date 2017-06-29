package com.example.ominext.memoryleaksample.activity

import android.util.Log
import android.widget.TextView
import com.example.ominext.memoryleaksample.retrofit.ApiService
import com.example.ominext.memoryleaksample.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_asynctask.*
import java.lang.ref.WeakReference


/**
 * Created by Ominext on 6/27/2017.
 */
class AsyncTaskActivity : android.support.v7.app.AppCompatActivity() {
    lateinit var task: com.example.ominext.memoryleaksample.activity.AsyncTaskActivity.LeakAsynctask
    lateinit var apiService: ApiService
    var disposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.ominext.memoryleaksample.R.layout.activity_asynctask)

        task = LeakAsynctask(tvResult)

        btnStart.setOnClickListener {
            task.execute()
        }

        apiService = RetrofitClient.getApiService()
        btnStartRetrofit.setOnClickListener {
            callAPITest(WeakReference(tvResult))
        }
    }

    private fun callAPITest(tv: WeakReference<TextView>) {
        disposable.add(apiService.callApiTest("abc")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: String? ->
//                    tv.get()?.text = "xong"
                    tvResult.text = "xong"
                }, { t: Throwable? ->
                    Log.e("error", t?.message ?: "null")
                }))
    }

    override fun onDestroy() {
        super.onDestroy()
//        task.cancel(true)
        if (!disposable.isDisposed) {
            disposable.clear()
        }
    }

    class LeakAsynctask(var tv: TextView) : android.os.AsyncTask<Void, Void, Unit>() {
//        var weakTextView: WeakReference<TextView>
//
//        init {
//            weakTextView = WeakReference<TextView>(tv)
//        }

        override fun doInBackground(vararg params: Void?): Unit? {
            Thread.sleep(10000)
            return null
        }
    }
}