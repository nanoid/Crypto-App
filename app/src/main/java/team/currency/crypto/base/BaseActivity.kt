package tech.iuic.iuicwork.base


 import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager



/**
 * Created by nanoid3 on 18.09.2017.
 */
abstract  class BaseActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getViewId())

        window.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        )
    }


    protected fun addFragment(containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        //fragmentTransaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_right);
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }



    protected fun attachFragment(containerViewId: Int, fragment: Fragment){
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        //fragmentTransaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_right);
        fragmentTransaction.replace(containerViewId, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }






    protected abstract fun getViewId(): Int


}