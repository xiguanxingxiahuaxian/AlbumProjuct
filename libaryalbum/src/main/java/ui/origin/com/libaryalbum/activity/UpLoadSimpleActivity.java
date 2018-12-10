package ui.origin.com.libaryalbum.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ui.origin.com.libaryalbum.R;
import ui.origin.com.libaryalbum.adapter.ViewAdapter;
import ui.origin.com.libaryalbum.api.FileApi;
import ui.origin.com.libaryalbum.api.UrlContants;
import ui.origin.com.libaryalbum.bean.ImageBean;
import ui.origin.com.libaryalbum.bean.ImageModelBean;
import ui.origin.com.libaryalbum.bean.ShowImageCoreBean;
import ui.origin.com.libaryalbum.impl.UploadService;
import ui.origin.com.libaryalbum.utils.FileUploadRequestBody;

/**
 * 项目名称：Album
 * 类描述：演示上传缴费，目前是内网地址
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/12/10 10:27
 * 修改备注
 */
public class UpLoadSimpleActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private List<ImageModelBean> list;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private GridView gridview;
    private ViewAdapter adapter;
    private RelativeLayout upload;
    public static final String TAG = "..A";
    private LinearLayout title_insert;
    private ShowImageCoreBean corebean;
    private TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_simple);
        //获取授权
        verifyStoragePermissions(this);
        corebean=(ShowImageCoreBean)getIntent().getSerializableExtra("corebean");
        gridview = (GridView) findViewById(R.id.gridview);

        title_insert = (LinearLayout) findViewById(R.id.title_insert);
        list = new ArrayList<>();
        upload = (RelativeLayout)findViewById(R.id.upload);
        //设置默认的图片
        ImageModelBean imageModelBean=new ImageModelBean();
        imageModelBean.setDefaultpic(R.drawable.album_add_t);
        list.add(imageModelBean);
        adapter = new ViewAdapter(list, getApplicationContext());
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==list.size()-1){
                    //打开相册 图片选择 用户将提供如下的参数

                    Intent intent = new Intent(getApplicationContext(), AlbumActivity.class);
                    intent.putExtra("corebean",corebean);
                    startActivityForResult(intent, 1);
                }
            }
        });
        //上传代码
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //存在几个文件就上传几个

                Call<ImageBean> call = new Retrofit.Builder().client(new OkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create(new Gson()))
                        .baseUrl(UrlContants.urlContent)
                        .build().create(FileApi.class).uploadFilesWithParts("", fromdata(list));
                call.enqueue(new Callback<ImageBean>() {
                    @Override
                    public void onResponse(Call<ImageBean> call, Response<ImageBean> response) {

                    }

                    @Override
                    public void onFailure(Call<ImageBean> call, Throwable t) {

                    }
                });
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            List<ImageModelBean> list_data = (List<ImageModelBean>) data.getSerializableExtra("bean");
            if (list.size() != 0) {
                list.clear();
            }
            ImageModelBean imageModelBean=new ImageModelBean();
            imageModelBean.setDefaultpic(R.drawable.album_add_t);
            list_data.add(imageModelBean);
            list.addAll(list_data);
            adapter.notifyDataSetChanged();
            upload.setVisibility(View.VISIBLE);
        } else if (requestCode == 1 && resultCode == 0) {
            Log.i(TAG, "用户未选择图片");
        }
    }

    //权限获取
    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<MultipartBody.Part> fromdata(final List<ImageModelBean> list) {
        final List<MultipartBody.Part> parts = new ArrayList<>();
        for (int i = 0; i < list.size()-1; i++) {
            final File file = new File(list.get(i).getPath());
            list.get(i).setFilename(file.getName());
            final RequestBody requestFile =
                    new FileUploadRequestBody(file, new UploadService() {
                        @Override
                        public void onProgress(Long contentlength, Long contentTotal, boolean finish) {
                            Log.i(file.getName(), contentlength + "");
                            Log.i(file.getName(), contentTotal + "");

                            if (contentTotal >= 62914560) {
                                Toast.makeText(UpLoadSimpleActivity.this, "请上传少于60M的图片", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Message message = new Message();
                            message.what = 1;

                            message.obj = "";
                            Bundle bundle = new Bundle();
                            bundle.putInt("progrcess", getDouble(Double.valueOf(contentlength) / Double.valueOf(contentTotal)));
                            bundle.putString("filename", file.getName());
                            message.setData(bundle);
                            mhander.sendMessage(message);

                        }
                    });

            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("files", file.getName(), requestFile);

            parts.add(body);
        }
        return parts;
    }

    public int getDouble(Object d) {
        DecimalFormat df = new DecimalFormat("#0.00");
        Double dou = Double.parseDouble(df.format(d)) * 100;
        int i = Integer.parseInt(new DecimalFormat("0").format(dou));
        return i;
    }

    private Handler mhander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Bundle data = msg.getData();
                    for (int j = 0; j < list.size()-1; j++) {
                        if (list.get(j).getFilename().equals(data.getString("filename"))) {
                            list.get(j).setProgress((Integer) data.getInt("progrcess"));
                            adapter.notifyDataSetChanged();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
