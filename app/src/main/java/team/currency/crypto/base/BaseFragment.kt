package tech.iuic.iuicwork.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



/**
 * Created by nanoid3 on 18.09.2017.
 */
abstract class BaseFragment : Fragment() {


    private val TAG = BaseFragment::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflateView(inflater!!, container!!)

        return view
    }

    protected abstract fun inflateView(inflater: LayoutInflater, container: ViewGroup): View


    protected fun addFragment(containerViewId: Int, fragment: Fragment) {
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        //fragmentTransaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_right);
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


    protected fun attachFragment(fragment: Fragment) {
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        //fragmentTransaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_right);
        fragmentTransaction.attach(fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }



    fun hideFragment(fragment: Fragment) {
        val fragmentManager = activity!!.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.remove(fragment)
        // transaction.disallowAddToBackStack();
        transaction.addToBackStack(null)
        transaction.commit()
    }






    public fun setBackClickListener(view:View){
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener() { v, keyCode, event ->
            Log.e("key","key")
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                activity!!.finish()
            }
            true
        }
    }


}