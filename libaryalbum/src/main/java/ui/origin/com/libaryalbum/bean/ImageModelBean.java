package ui.origin.com.libaryalbum.bean;

import java.io.Serializable;

/**
 * 项目名称：Album
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/11/28 9:29
 * 修改备注
 */
public class ImageModelBean implements Serializable {
    private String id;//图片id
    private String path;//路径
    private Boolean isChecked = false;//是否被选中
    private Boolean isEnable = false;//是否被选中
    private int progress;//上传进度
    private String filename;//上传名称
    private int defaultpic;//设置默认的图片
    public ImageModelBean() {

    }

    public int getDefaultpic() {
        return defaultpic;
    }

    public void setDefaultpic(int defaultpic) {
        this.defaultpic = defaultpic;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public ImageModelBean(String id, String path, Boolean isChecked) {
        this.id = id;
        this.path = path;
        this.isChecked = isChecked;
    }

    public ImageModelBean(String id, String path) {
        this.id = id;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
