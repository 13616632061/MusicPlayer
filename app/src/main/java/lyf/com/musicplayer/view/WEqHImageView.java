package lyf.com.musicplayer.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2019/7/18.
 */

public class WEqHImageView extends AppCompatImageView {


    public WEqHImageView(Context context) {
        super(context);
    }

    public WEqHImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WEqHImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //将参数宽替换高 即可实现宽高相等
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
