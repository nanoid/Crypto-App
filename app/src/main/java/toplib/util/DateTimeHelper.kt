import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeHelper {

    private val TAG = DateTimeHelper::class.java.simpleName

    fun formatDate(dateString: String?): String {
        if (dateString == null)
            return ""
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ROOT)
        val cal = Calendar.getInstance()
        try {
            val date = format.parse(dateString)
            cal.time = date

        } catch (e: ParseException) {
            Log.e(TAG + "date", e.message)
            //e.printStackTrace();
        }

        //return cal.get(Calendar.DAY_OF_MONTH) + "." + cal.get(Calendar.MONTH) + "." + cal.get(Calendar.YEAR);
        return String.format("%02d.%02d.%02d", cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR))
    }


    fun formatTime(dateString: String?): String {
        // Log.e(TAG + "s", dateString);
        if (dateString == null)
            return ""
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ROOT)
        val cal = Calendar.getInstance()
        try {
            val date = format.parse(dateString)
            cal.time = date

        } catch (e: ParseException) {
            Log.e(TAG + "time", e.message)
        }

        return String.format("%02d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND))

    }


}
