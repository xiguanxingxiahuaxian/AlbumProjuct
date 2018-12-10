package ui.origin.com.libaryalbum.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ui.origin.com.libaryalbum.R;
import ui.origin.com.libaryalbum.bean.ImageModelBean;
import ui.origin.com.libaryalbum.impl.UserCheckListenter;


/**
 * 项目名称：Album
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/11/28 10:24
 * 修改备注
 */
public class gridViewAdapter extends BaseAdapter {

    private int CheckSize = 9;
    private List<ImageModelBean> list;
    private Context context;
    private viewHolder holder;
    private UserCheckListenter userCheckListenter;



    public gridViewAdapter(List<ImageModelBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public gridViewAdapter(List<ImageModelBean> list, Context context, int CheckSize) {
        this.list = list;
        this.context = context;
        this.CheckSize = CheckSize;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View contentview, ViewGroup viewGroup) {

        if (contentview == null) {
            holder = new viewHolder();
            contentview = LayoutInflater.from(context).inflate(R.layout.album_item, null);
            holder.albumcheckimg = contentview.findViewById(R.id.albumcheckimg);
            holder.album_item_img = contentview.findViewById(R.id.album_item_img);
            contentview.setTag(holder);
        } else {
            holder =(viewHolder)contentview.getTag();
        }

        holder.albumcheckimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = isEnable();
                if (b == true) {
                    if (list.get(i).getChecked()) {
                        list.get(i).setChecked(false);
                        holder.albumcheckimg.setBackgroundResource(R.drawable.album_discheck);
                    } else {
                        list.get(i).setChecked(true);
                        holder.albumcheckimg.setBackgroundResource(R.drawable.album_check_r);
                    }

                } else {
                    if (list.get(i).getChecked()) {
                        list.get(i).setChecked(false);
                        holder.albumcheckimg.setBackgroundResource(R.drawable.album_discheck);
                    }else {
                        Toast.makeText(context, "已经超过" + CheckSize + "请上传", Toast.LENGTH_SHORT).show();
                    }
                }
                //获取选择的相片数目
                if(userCheckListenter!=null){
                    userCheckListenter.checkedNum(checkNum());
                }
                notifyDataSetChanged();
            }
        });
        if (list.get(i).getChecked()) {
            holder.albumcheckimg.setBackgroundResource(R.drawable.album_check_r);
        } else {
            holder.albumcheckimg.setBackgroundResource(R.drawable.album_discheck);
        }
        Glide.with(context).load(list.get(i).getPath()).into(holder.album_item_img);
        return contentview;
    }

    public class viewHolder {
        private ImageView album_item_img;
        private ImageView albumcheckimg;
    }

    public boolean isEnable() {
        int checknum=0;
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getChecked() == true) {
                    checknum++;
                }
            }
            if (checknum < CheckSize) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    public int checkNum(){
        int checked=0;
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getChecked()){
                    checked++;
                }
            }
        }
        return checked;
    }
    public List<ImageModelBean> getCheckData() {
        List<ImageModelBean> list_check = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getChecked()) {
                list_check.add(list.get(i));
            }
        }
        return list_check;
    }
    public void userCheckNum(UserCheckListenter userCheckListenter){
        this.userCheckListenter=userCheckListenter;
    }

}
