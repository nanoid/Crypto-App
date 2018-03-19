package toplib.util

/**
 * Created by nanoid3 on 06.11.2017.
 */
object StringUtil {

    fun concatString(string: String): String {
        if (string.length != 0)
            return string.substring(0, string.length - 1)
        return ""
    }
}