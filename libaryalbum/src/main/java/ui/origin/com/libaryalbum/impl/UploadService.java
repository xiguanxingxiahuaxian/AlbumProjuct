package ui.origin.com.libaryalbum.impl;

/**
 * 项目名称：BlueSeaAndroid
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/7/18 14:38
 * 修改备注
 */
public interface UploadService {
    void onProgress(Long contentlength, Long contentTotal, boolean finish);
}
