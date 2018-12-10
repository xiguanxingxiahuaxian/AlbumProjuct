package ui.origin.com.libaryalbum.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import ui.origin.com.libaryalbum.R;
import ui.origin.com.libaryalbum.adapter.gridViewAdapter;
import ui.origin.com.libaryalbum.bean.ImageModelBean;
import ui.origin.com.libaryalbum.bean.ShowImageCoreBean;
import ui.origin.com.libaryalbum.impl.UserCheckListenter;
import ui.origin.com.libaryalbum.utils.DealImageListUtils;



/**
 * 项目名称：Album
 * 类描述：此类一般通过回调 完成图片的选择
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/11/28 9:52
 * 修改备注
 */
public class AlbumActivity extends AppCompatActivity {

    private ImageView back;
    private GridView gridview;
    private ShowImageCoreBean core;
    private RelativeLayout album_title_color;
    private List<ImageModelBean> list;
    private gridViewAdapter adapter;
    private TextView num_title;
    private TextView finish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.album_layout);
        //初始化控件
        initView();
        //初始化数据
        initData();
    }

    private void initView() {
        back=(ImageView)findViewById(R.id.back);
        finish=(TextView)findViewById(R.id.finish);
        album_title_color=(RelativeLayout)findViewById(R.id.album_title_color);
        num_title=(TextView)findViewById(R.id.num_title);
        gridview=(GridView)findViewById(R.id.gridview);
    }

    private void initData() {
        /**
         * 获取配置bean
         * 此bean对整个相册的页面进行配置
         */
        core=(ShowImageCoreBean)getIntent().getSerializableExtra("corebean");
        //回退事件
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               setResult(0);
               finish();
            }
        });
        //完成事件
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ImageModelBean> data = adapter.getCheckData();
                Intent intent =new Intent();
                intent.putExtra("bean", (Serializable) data);
                if(data.size()==0){
                    setResult(0, intent);
                }else {
                    setResult(1, intent);
                }
                finish();
            }
        });
        //初始化gridView配置
        if(core.getColumSize()!=0) {
            gridview.setNumColumns(core.getColumSize());
        }
        //初始化titile backgroundCore
        if(core.getTitlecolor()!=0) {
            album_title_color.setBackgroundColor(core.getTitlecolor());
        }
        list= DealImageListUtils.getImages(getApplicationContext());
        //初始化adapter
        if(core.getTitlecolor()!=0) {
            album_title_color.setBackgroundColor(core.getTitlecolor());
        }
        if(core.getImageCheckSize()!=0) {
            adapter = new gridViewAdapter(list, getApplicationContext(),core.getImageCheckSize());
        }else{
            //默认为9
            adapter = new gridViewAdapter(list, getApplicationContext(),9);
        }
        //设置adapter
        gridview.setAdapter(adapter);
        //页面标题数据填充
        adapter.userCheckNum(new UserCheckListenter() {
            @Override
            public void checkedNum(int num) {
                num_title.setText(num+"/"+core.getImageCheckSize());
            }
        });
    }

    @Override
    protected void onDestroy() {
        setResult(0);
        super.onDestroy();
    }
}
