package moe.tlaster.oneechan.helper;

import android.content.Context;

import com.litesuits.http.LiteHttp;

/**
 * Created by Tlaster on 2016/8/20.
 */
public class HttpHelper {
    private static LiteHttp mLiteHttp;

    public static LiteHttp getLiteHttp() {
        return mLiteHttp;
    }

    public static void Initialize(Context context){
        mLiteHttp = LiteHttp.build(context).create();
    }

}
