package ui.origin.com.album.simple;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ui.origin.com.album.R;
import ui.origin.com.libaryalbum.activity.UpLoadSimpleActivity;
import ui.origin.com.libaryalbum.bean.ShowImageCoreBean;

/**
 * 项目名称：Album
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/12/10 13:48
 * 修改备注
 */
public class Work121001 extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._layout);
        ShowImageCoreBean core =
                new ShowImageCoreBean
                        .Builder()
                        .columSize(2)
                        .imageCheckSize(9)
                        //添加布局成功 但是单击事件不能获取
                       /* .headview( R.layout.view_add)*/
                        .build();
        Intent intent =new Intent(Work121001.this, UpLoadSimpleActivity.class);
        intent.putExtra("corebean",core);
        startActivity(intent);
    }
}
