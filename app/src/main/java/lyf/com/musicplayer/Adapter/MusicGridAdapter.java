package lyf.com.musicplayer.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lyf.com.musicplayer.R;
import lyf.com.musicplayer.bean.MusicInfo;

/**
 * Created by Administrator on 2019/7/18.
 */

public class MusicGridAdapter extends BaseQuickAdapter<MusicInfo, BaseViewHolder> {

    private Context mContext;

    public MusicGridAdapter(Context mContext, int layoutResId, @Nullable List<MusicInfo> data) {
        super(layoutResId, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, MusicInfo item) {
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        Glide.with(mContext).load(item.getPicture()).into(iv_icon);

        helper.setText(R.id.tv_count, item.getCount());
        helper.setText(R.id.tv_name, item.getName());
    }
}
