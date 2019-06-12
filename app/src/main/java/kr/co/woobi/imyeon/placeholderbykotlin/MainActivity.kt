package kr.co.woobi.imyeon.placeholderbykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myApi: MyApi
    var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitClient.instance
        myApi = retrofit.create(MyApi::class.java)

        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(this)

        fetchData()

    }

    private fun fetchData() {
        shimmerLayout.startShimmerAnimation()
        compositeDisposable.add(myApi.data.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { photos -> displayData(photos) })
    }

    private fun displayData(photos: List<Model>?) {
        val adapter = MyAdapter(this, photos!!)
        recyclerview.adapter = adapter
        shimmerLayout.stopShimmerAnimation()
        shimmerLayout.visibility = View.GONE

    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()

    }
}
