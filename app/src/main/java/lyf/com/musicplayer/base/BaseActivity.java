package lyf.com.musicplayer.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import lyf.com.musicplayer.R;

/**
 * Created by Administrator on 2019/7/18.
 */

public abstract class BaseActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initContentView());
        ButterKnife.inject(this);
        initView();
    }


    public abstract int initContentView();

    public void initView() {};

    public void initTitle(boolean isLeftShow, String title) {
        TextView tvBack = findViewById(R.id.tv_back);
        TextView tvTitle = findViewById(R.id.tv_title);
        if (isLeftShow) {
            tvBack.setVisibility(View.VISIBLE);
        }
        tvTitle.setText(title);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
