package toplib.util

import java.util.*

/**
 * Created by nanoid3 on 14.12.2017.
 */
public class LanguageHelper{

    public fun  getLanguage():String{
        var language = Locale.getDefault().country.toLowerCase();
        return language
    }

}