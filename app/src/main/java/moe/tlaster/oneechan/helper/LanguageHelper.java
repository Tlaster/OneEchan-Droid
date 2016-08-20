package moe.tlaster.oneechan.helper;

import java.util.Locale;

/**
 * Created by Tlaster on 2016/8/19.
 */
public class LanguageHelper {
    public static String prefLang(){
        String lang = Locale.getDefault().getLanguage().toLowerCase();
        if (lang.contains("en")) lang = "en-US";
        else if (lang.contains("zh")) lang = "zh-TW";
        else if (lang.contains("ru")) lang = "ru-RU";
        else lang = "ja-JP";
        return lang;
    }
}
