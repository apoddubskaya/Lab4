package com.example.lab4

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.example.lab4.presenters.FragmentOnePresenter
import com.example.lab4.views.FragmentOneIView
import kotlinx.android.synthetic.main.fragment_one.*

class FragmentOne() : MvpAppCompatFragment(), FragmentOneIView{

    private var listener: OnListFragmentInteractionListener? = null

    @InjectPresenter(type = PresenterType.GLOBAL, tag = "commonPresenter")
    lateinit var presenter : FragmentOnePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        // Inflate the layout for this
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    private val TAG = "FragmentOne"

    override fun showWeather(weatherList: List<WeatherService.WeatherItem>?) {
        if (swipeLayout.isRefreshing)
            swipeLayout.isRefreshing = false

        Log.d(TAG, "setWeekInfo")
        val items = ArrayList<String>()

        for (i in 0 until weatherList!!.size step 4) {
            val temp = "  ночью " + weatherList[i].temp + "C" +  "  днём" + weatherList[i + 2].temp + "C"
            items.add(weatherList[i].date + temp)
        }

        list.adapter = ArrayAdapter<String>(
            activity!!.applicationContext,
            android.R.layout.simple_list_item_1,
            items)

        list.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            listener!!.onListFragmentInteraction(position)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipeLayout.setOnRefreshListener {
            Log.d(TAG, "refreshhhhh")
            presenter.refresh()
        }
    }

    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach")
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(position: Int)
    }
}
