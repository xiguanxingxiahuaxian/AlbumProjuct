package ui.origin.com.libaryalbum.bean;

import android.view.View;

import java.io.Serializable;

/**
 * 项目名称：Album
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/11/28 9:44
 * 修改备注
 */
public class ShowImageCoreBean implements Serializable{
    //gridView 显示每行数目
    private int columSize;
    //控制image的大小
    private int imageCheckSize;
    //控制标题的颜色
    private int titlecolor;
    //控制bar的颜色
    private int barColor;
    //添加view 可以添加组合的view 显示从第0行开始
    private int  headview;

    private ShowImageCoreBean(Builder builder) {
        setColumSize(builder.columSize);
        setImageCheckSize(builder.imageCheckSize);
        setTitlecolor(builder.titlecolor);
        setBarColor(builder.barColor);
        setHeadview(builder.headview);
    }

    public int getColumSize() {
        return columSize;
    }

    public void setColumSize(int columSize) {
        this.columSize = columSize;
    }

    public int getImageCheckSize() {
        return imageCheckSize;
    }

    public void setImageCheckSize(int imageCheckSize) {
        this.imageCheckSize = imageCheckSize;
    }

    public int getTitlecolor() {
        return titlecolor;
    }

    public void setTitlecolor(int titlecolor) {
        this.titlecolor = titlecolor;
    }

    public int getBarColor() {
        return barColor;
    }

    public void setBarColor(int barColor) {
        this.barColor = barColor;
    }

    public int getHeadview() {
        return headview;
    }

    public void setHeadview(int headview) {
        this.headview = headview;
    }

    public static final class Builder {
        private int columSize;
        private int imageCheckSize;
        private int titlecolor;
        private int barColor;
        private int headview;

        public Builder() {
        }

        public Builder columSize(int val) {
            columSize = val;
            return this;
        }

        public Builder imageCheckSize(int val) {
            imageCheckSize = val;
            return this;
        }

        public Builder titlecolor(int val) {
            titlecolor = val;
            return this;
        }

        public Builder barColor(int val) {
            barColor = val;
            return this;
        }

        public Builder headview(int val) {
            headview = val;
            return this;
        }

        public ShowImageCoreBean build() {
            return new ShowImageCoreBean(this);
        }
    }
}
