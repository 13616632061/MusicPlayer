package lyf.com.musicplayer.utils;

import android.content.Context;

/**
 * Created by Administrator on 2019/7/18.
 */

public class ScreenUtil {

    /**
     * px转换成dp
     */
    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
