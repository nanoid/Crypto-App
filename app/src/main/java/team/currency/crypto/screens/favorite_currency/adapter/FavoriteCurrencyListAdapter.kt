package team.currency.crypto.screens.currency_list.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.favorite_currency_list_item.view.*
import team.currency.crypto.R
import team.currency.crypto.data.entity.Currency

/**
 * Created by root on 15.02.18.
 */
class FavoriteCurrencyListAdapter(private val mOnCurrencyClickCallback: OnFavoriteCurrencyClickCallback?, private val mCurrencyList: List<Currency>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    internal var TAG = CurrencyListAdapter::class.java.simpleName


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        when (viewType) {
            0 -> return CurrencyHolder(LayoutInflater.from(
                    parent.context).inflate(R.layout.favorite_currency_list_item, parent, false))
        }
        return null
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> bindCurrency(holder as CurrencyHolder, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getItemCount(): Int {
        return mCurrencyList.size
    }


    fun bindCurrency(currencyHolder: CurrencyHolder, position: Int) {

        val context = currencyHolder.itemView.context
        val currencyResponse = mCurrencyList[position]
        currencyHolder.bindCurrency(currencyResponse, position)
    }


    interface OnFavoriteCurrencyClickCallback {

        fun onCurrencyClick(currency: Currency?, position: Int)
    }


    inner class CurrencyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        internal var mPosition: Int = 0
        private var mCurrency: Currency? = null


        fun bindCurrency(currency: Currency, position: Int) {
            mCurrency = currency
            val context = itemView.context
            showGeneralInfo(context, position)
        }


        private fun showGeneralInfo(context: Context, position: Int) {
            mPosition = position
            if(mCurrency!!.imageUrl!=null){
            Glide.with(context).load<Any>(mCurrency!!.imageUrl).into(itemView.image_view)}

            itemView.text_view.text = mCurrency!!.name
            itemView.cost_text_view.text="$"+ mCurrency!!.cost
            itemView.hour_text_view.text= mCurrency!!.change_one_hour.toString()+"%"
            itemView.day_text_view.text= mCurrency!!.change_twenty_four_hour.toString()+"%"
            itemView.seven_day_text_view.text= mCurrency!!.change_one_week.toString()+"%"

            itemView.currency_linear_layout.setOnClickListener(View.OnClickListener { v->
            mOnCurrencyClickCallback!!.onCurrencyClick(mCurrency,position)

            })
            /*    Glide.with(context).load<Any>("http://work.iuic.info/" + mCategory!!.mobile).into(itemView.icon_image_view)
            itemView.category_text_view.setText(mCategory!!.title)

            itemView.category_container_relative_layout.setOnClickListener(View.OnClickListener { v ->
                mOnCategoryClickCallback?.onCategoryClick(mCategory, mPosition)
            })
        }*/
        }


    }
}