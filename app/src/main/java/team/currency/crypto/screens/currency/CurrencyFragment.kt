package team.currency.crypto.screens.currency

import android.arch.lifecycle.ViewModelProvider
import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.ChartTouchListener
import com.github.mikephil.charting.listener.OnChartGestureListener
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.currency_fragment.*
import team.currency.crypto.R
import team.currency.crypto.bus.CurrencyBus
import team.currency.crypto.data.entity.ChartHolder
import team.currency.crypto.data.entity.CurrencyHolder
import tech.iuic.iuicwork.base.BaseFragment
import javax.inject.Inject


/**
 * Created by root on 20.02.18.
 */
class CurrencyFragment: BaseFragment(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var viewModel: CurrencyViewModel

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }



    @Inject
    lateinit var currencyBus: CurrencyBus



    lateinit var  currencyHolder: CurrencyHolder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackClickListener(view)
           back_image_button.setOnClickListener({
               activity!!.finish()
           })


        queryCurrency(currencyBus.get())

        one_day_relative_layout.setOnClickListener({
           onQueryCurrency(1)
        })
        seven_day_relative_layout.setOnClickListener ({         onQueryCurrency(7)
        })
        one_month_relative_layout.setOnClickListener ({         onQueryCurrency(31)
        })
        six_month_relative_layout.setOnClickListener ({         onQueryCurrency(180)
        })
        one_year_relative_layout.setOnClickListener ({         onQueryCurrency(360)
        })

    }


    private fun queryCurrency(id:Long) {
        viewModel.queryCurrencyById(id)!!.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe({ s ->
                    currencyHolder = s
                    onQueryCurrency(7)
                    progress_bar_relative_layout.visibility = View.GONE
                    toolbar.visibility = View.VISIBLE
                    currency_fragment.visibility = View.VISIBLE
                })
    }

    private fun onQueryCurrency(limit:Int) {
        viewModel.queryCurrencyForGraph(currencyHolder.shortName,limit,1)!!.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe({ result ->
                  onLinerChart(result)
                 })


        if(currency_text_view!=null){
        currency_text_view.text = currencyHolder.name}
        if(hour_text_view!=null){
         hour_text_view.text= currencyHolder.change_one_hour.toString()+"%"}
        if(day_text_view!=null){
             day_text_view.text = currencyHolder.change_twenty_four_hour.toString()+"%"}
        if(seven_day_text_view!=null){
             seven_day_text_view.text = currencyHolder.change_one_week.toString()+"%"}

        if(cost_text_view!=null){
             cost_text_view.text = "$"+currencyHolder.cost.toString()}

    }

    private fun onLinerChart(result: ChartHolder) {

        //chart.setOnChartGestureListener(this);
        //chart.setOnChartValueSelectedListener(this);
        //chart.setDrawGridBackground(false);
        chart.setDragEnabled(false)
        chart.setScaleEnabled(false)
   setData(result)
    }


    private fun setData(result: ChartHolder) {
        val xVals = ArrayList<String>()
            var counter:Int=0

         var values:ArrayList<Entry> = ArrayList()
         for (item in result.data) {
             counter++
             xVals.add(counter.toString())
              Log.e("uy","uy")
             val result = item.high
            values.add(Entry(counter.toFloat(),item.low  ))
        }
        val set1: LineDataSet
        /*if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            set1 = chart.getData().getDataSetByIndex(0) as LineDataSet
            set1.values = values
            chart.getData().notifyDataChanged()
            chart.notifyDataSetChanged()
            Log.e("1","1")
        } else {*/
            // create a dataset and give it a type
            Log.e("2","2")

            set1 = LineDataSet(values,currencyHolder.name )
            set1.setDrawIcons(false)
            // set the line to be drawn like this "- - - - - -"
            set1.enableDashedLine(10f, 5f, 0f)
            set1.enableDashedHighlightLine(10f, 5f, 0f)
            set1.color = Color.TRANSPARENT
            set1.setCircleColor(Color.TRANSPARENT)
            set1.lineWidth = 1f
            //set1.circleRadius = 3f
            set1.setDrawCircleHole(false)
            set1.valueTextSize = 9f
            set1.setDrawFilled(true)
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 15f

         /*   if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                val drawable = activity!!.resources.getDrawable( R.drawable.logo)
                set1.fillDrawable = drawable
            } else {
                set1.fillColor = Color.BLACK
            }
*/
            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1) // add the datasets

            // create a data object with the datasets
            val data = LineData(dataSets)

            // set data
            chart.data = data
            //chart.notifyDataSetChanged()
            chart.visibility = View.VISIBLE
            chart.animateX(1200)
            chart.invalidate()

    }


    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.currency_fragment, container, false)
    }

}
