package lyf.com.musicplayer.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lyf.com.musicplayer.R;
import lyf.com.musicplayer.bean.MusicInfo;

/**
 * Created by Administrator on 2019/7/18.
 */

public class MusicListAdapter extends BaseQuickAdapter<MusicInfo, BaseViewHolder> {

    private Context mContext;
    private RelativeLayout layout_item;
    private RecyclerView list;
    private boolean isSetHeight = false;

    public MusicListAdapter(Context mContext, int layoutResId, @Nullable List<MusicInfo> data, RecyclerView list) {
        super(layoutResId, data);
        this.mContext = mContext;
        this.list = list;

    }

    @Override
    protected void convert(BaseViewHolder helper, MusicInfo item) {
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        Glide.with(mContext).load(item.getPicture()).into(iv_icon);

        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_person, item.getPsesoner());

        RelativeLayout layout_item = helper.getView(R.id.layout_item);

        setRecyclerViewHeight(layout_item);
    }

    /**
     * 重新计算高度
     */
    public void setRecyclerViewHeight(RelativeLayout layout_item) {
        if (isSetHeight || list == null) return;

        isSetHeight = true;
        //获取item的高度
        ViewGroup.LayoutParams layoutParams = layout_item.getLayoutParams();
        //获取item的数量
        int itemCount = getItemCount();
        //RecyclerView的高度
        int recyclerViewHeight = layoutParams.height * itemCount;

        ViewGroup.LayoutParams layoutParams1 = list.getLayoutParams();
        layoutParams1.height = recyclerViewHeight;
        list.setLayoutParams(layoutParams1);
    }
}
