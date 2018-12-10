package ui.origin.com.libaryalbum.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import ui.origin.com.libaryalbum.bean.ImageModelBean;


/**
 * 项目名称：Album
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/11/28 9:39
 * 修改备注
 */
public class DealImageListUtils {
    /**
     *  作者：kopapai
        来源：CSDN
        原文：https://blog.csdn.net/weihuangcool/article/details/48655229
        版权声明：本文为博主原创文章，转载请附上博文链接！
     * @param context
     * @return
     */
    public static List<ImageModelBean> getImages(Context context){
        List<ImageModelBean> list = new ArrayList<ImageModelBean>();
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA,};
        String sortOrder = MediaStore.Images.Media.DATE_ADDED + " desc";
        Cursor cursor = contentResolver.query(uri, projection, null, null, sortOrder);
        int iId = cursor.getColumnIndex(MediaStore.Images.Media._ID);
        int iPath = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String id = cursor.getString(iId);
            String path = cursor.getString(iPath);
            ImageModelBean imageModel = new ImageModelBean(id,path);
            list.add(imageModel);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

}
