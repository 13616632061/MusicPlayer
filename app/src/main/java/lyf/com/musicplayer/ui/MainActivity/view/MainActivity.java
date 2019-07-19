package lyf.com.musicplayer.ui.MainActivity.view;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import lyf.com.musicplayer.Adapter.MusicGridAdapter;
import lyf.com.musicplayer.Adapter.MusicListAdapter;
import lyf.com.musicplayer.R;
import lyf.com.musicplayer.base.BaseActivity;
import lyf.com.musicplayer.bean.MusicInfo;
import lyf.com.musicplayer.ui.MainActivity.presenter.MainActivityPresenter;
import lyf.com.musicplayer.ui.PlayMusicActivity.view.PlayMusicActivity;
import lyf.com.musicplayer.utils.RecyclerViewItemDecoration;
import lyf.com.musicplayer.view.PlayMusic;

public class MainActivity extends BaseActivity implements IMainActivityView {


    @InjectView(R.id.rl_grid)
    RecyclerView rlGrid;
    @InjectView(R.id.rl_list)
    RecyclerView rlList;

    private MusicGridAdapter musicGridAdapter;
    private List<MusicInfo> mMusicGridData = new ArrayList<>();
    private MainActivityPresenter mPresenter;
    private MusicListAdapter mMusicListAdapter;

    @Override
    public int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        initTitle(false, "音乐列表");
        mPresenter = new MainActivityPresenter(this);
        mPresenter.getMusicGridData();
    }

    @Override
    public void setGridAdapter(final List<MusicInfo> mMusicGridData) {
        musicGridAdapter = new MusicGridAdapter(this, R.layout.item_grid_music, mMusicGridData);
        rlGrid.setAdapter(musicGridAdapter);
        rlGrid.setNestedScrollingEnabled(false);
        rlGrid.addItemDecoration(new RecyclerViewItemDecoration(3, 15, true, 0));
        rlGrid.setLayoutManager(new GridLayoutManager(this, 3));
        musicGridAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MainActivity.this, PlayMusicActivity.class);
                intent.putExtra("musicInfo", mMusicGridData.get(position));
                startActivity(intent);
            }
        });

    }


    @Override
    public void setLinnerAdapter(final List<MusicInfo> mMusicGridData) {
        mMusicListAdapter = new MusicListAdapter(this, R.layout.item_list_music, mMusicGridData, rlList);
        rlList.setAdapter(mMusicListAdapter);
        rlList.setNestedScrollingEnabled(false);
        rlList.setLayoutManager(new LinearLayoutManager(this));
        mMusicListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MainActivity.this, PlayMusicActivity.class);
                intent.putExtra("musicInfo", mMusicGridData.get(position));
                startActivity(intent);
            }
        });

    }
}
