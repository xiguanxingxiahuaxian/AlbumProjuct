package ui.origin.com.libaryalbum.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import ui.origin.com.libaryalbum.R;
import ui.origin.com.libaryalbum.bean.ImageModelBean;
import ui.origin.com.libaryalbum.widage.UploadView;


/**
 * 项目名称：Album
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/11/28 10:24
 * 修改备注
 */
public class ViewAdapter extends BaseAdapter{

    private  int CheckSize=9;
    private List<ImageModelBean> list;
    private Context context;
    private viewHolder holder;

    public ViewAdapter(List<ImageModelBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public ViewAdapter(List<ImageModelBean> list, Context context, int CheckSize) {
        this.list = list;
        //追加一个图片

        this.context = context;
        this.CheckSize=CheckSize;
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
       if(contentview==null){
           holder=new viewHolder();
           contentview=LayoutInflater.from(context).inflate(R.layout.album_simple_gridview_item,null);
           holder.albumcheckimg=contentview.findViewById(R.id.albumcheckimg);
           holder.pic_item=contentview.findViewById(R.id.pic_item);
           holder.album_item_img=contentview.findViewById(R.id.album_item_img);
           holder.view=contentview.findViewById(R.id.view);
           contentview.setTag(holder);
       }else{
           holder=(viewHolder) contentview.getTag();
       }
        if(list.size()>1){
           if(i<list.size()-1) {
               Glide.with(context).load(list.get(i).getPath()).into(holder.album_item_img);
               holder.view.setProgress(list.get(i).getProgress());
               holder.view.setVisibility(View.VISIBLE);
           }else{
               Glide.with(context).load(list.get(i).getDefaultpic()).into(holder.album_item_img);
               //不要遮罩
               holder.view.setVisibility(View.GONE);
           }
        }else{
            Glide.with(context).load(list.get(i).getDefaultpic()).into(holder.album_item_img);
            //不要遮罩
            holder.view.setVisibility(View.GONE);
        }

       return contentview;
    }
    public class  viewHolder{
        private LinearLayout pic_item;
        private ImageView album_item_img;
        private ImageView albumcheckimg;
        private UploadView view;
    }

}
