package ui.origin.com.libaryalbum.widage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * 项目名称：BlueSeaAndroid
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/7/18 13:56
 * 修改备注
 */
public class UploadView extends View {
    private Context context;
    private Paint mPaint;
    private int progress;
    private ImageView video;

    public UploadView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public UploadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public UploadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public void setProgress(int porgress) {
        this.progress = porgress;
        invalidate();
    }


    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(Color.parseColor("#50000000"));//半透明
        canvas.drawRect(0, 0, getWidth(), getHeight() - getHeight() * progress / 100, mPaint);

        mPaint.setColor(Color.parseColor("#00000000"));//全透明
        canvas.drawRect(0, getHeight() - getHeight() * progress / 100, getWidth(), getHeight(), mPaint);

        mPaint.setTextSize(30);
        mPaint.setColor(Color.parseColor("#FFFFFF"));
        mPaint.setStrokeWidth(2);
        Rect rect = new Rect();
        mPaint.getTextBounds("100%", 0, "100%".length(), rect);//确定文字的宽度
        //  canvas.drawText(progress+"%", getWidth()/2-rect.width()/2,getHeight()/2, mPaint);
        canvas.drawText(progress + "%", 40, 40, mPaint);
    }
}
