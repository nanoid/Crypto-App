package team.currency.crypto.screens.currency_list.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.currency_check_list_item.view.*
import kotlinx.android.synthetic.main.currency_list_item.view.*
import team.currency.crypto.R
import team.currency.crypto.data.entity.Currency

/**
 * Created by root on 15.02.18.
 */
class CurrencyListAdapter(private val mOnCurrencyClickCallback: OnCurrencyClickCallback?, private val mCurrencyList: ArrayList<Currency>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    internal var TAG = CurrencyListAdapter::class.java.simpleName


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        when (viewType) {
            0 -> return CurrencyHolder(LayoutInflater.from(
                    parent.context).inflate(R.layout.currency_list_item, parent, false))
            1 -> return CurrencyCheckHolder(LayoutInflater.from(
                    parent.context).inflate(R.layout.currency_check_list_item, parent, false))

        }
        return null
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> bindCurrency(holder as CurrencyHolder, position)
            1 -> bindCheckCurrency(holder as CurrencyCheckHolder, position)

        }
    }

    override fun getItemViewType(position: Int): Int {
        return mCurrencyList.get(position).type
    }

    override fun getItemCount(): Int {
        return mCurrencyList.size
    }


    fun bindCurrency(currencyHolder: CurrencyHolder, position: Int) {

        val context = currencyHolder.itemView.context
        val currencyResponse = mCurrencyList[position]
        currencyHolder.bindCurrency(currencyResponse, position)
    }


    fun bindCheckCurrency(currencyHolder: CurrencyCheckHolder, position: Int) {

        val context = currencyHolder.itemView.context
        val currencyResponse = mCurrencyList[position]
        currencyHolder.bindCurrency(currencyResponse, position)
    }



    interface OnCurrencyClickCallback {

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

            if (mCurrency!!.imageUrl != null) {
                Glide.with(context).load<Any>(mCurrency!!.imageUrl).into(itemView.image_view)
            }else{
            }

            itemView.text_view.text = mCurrency!!.name
                itemView.currency_linear_layout.setOnClickListener(View.OnClickListener { v->
                    mOnCurrencyClickCallback!!.onCurrencyClick(mCurrency,position)

                })
         }


    }



    inner class CurrencyCheckHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        internal var mPosition: Int = 0
        private var mCurrency: Currency? = null


        fun bindCurrency(currency: Currency, position: Int) {
            mCurrency = currency
            val context = itemView.context
            showGeneralInfo(context, position)
        }


        private fun showGeneralInfo(context: Context, position: Int) {
            mPosition = position

            if (mCurrency!!.imageUrl != null) {
                Glide.with(context).load<Any>(mCurrency!!.imageUrl).into(itemView.check_image_view)
            }else{
            }

            itemView.check_text_view.text = mCurrency!!.name
            itemView.check_currency_linear_layout.setOnClickListener(View.OnClickListener { v->
                mOnCurrencyClickCallback!!.onCurrencyClick(mCurrency,position)
             })
        }


    }
}