package com.jnu.student.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

import com.jnu.student.R;

public class CustomClockView extends View {

    private Paint paint;
    private int centerX;
    private int centerY;
    private int radius;

    private Drawable clockPanImage;
    private Drawable hourPinImage;
    private Drawable minutePinImage;
    private Drawable secondPinImage;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate(); // 刷新视图
            handler.postDelayed(this, 1000); // 每隔一秒钟执行一次
        }
    };

    public CustomClockView(Context context) {
        super(context);
        initView(context);
    }
    public CustomClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    private void initView(Context context){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        clockPanImage = ResourcesCompat.getDrawable(getResources(), R.drawable.clock_pan, context.getTheme());
        hourPinImage = ResourcesCompat.getDrawable(getResources(), R.drawable.hour_pin, context.getTheme());
        minutePinImage = ResourcesCompat.getDrawable(getResources(), R.drawable.minute_pin, context.getTheme());
        secondPinImage = ResourcesCompat.getDrawable(getResources(), R.drawable.second_pin, context.getTheme());

        handler.post(runnable); // 开始定时刷新
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 获取中心点坐标
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        radius = Math.min(centerX, centerY) - 20;

        // 获取当前时间
        long currentTime = System.currentTimeMillis();
        int seconds = (int) (currentTime / 1000) % 60;
        int minutes = (int) ((currentTime / (1000 * 60)) % 60);
        int hours = (int) ((currentTime / (1000 * 60 * 60)) % 12);

        // 获取手机屏幕的宽度和高度
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        // 计算表盘图片的缩放比例
        float scale = Math.min((float) screenWidth / clockPanImage.getIntrinsicWidth(),
                                (float) screenHeight / clockPanImage.getIntrinsicHeight());
        // 缩放表盘图片
        int scaledPanWidth = (int) (clockPanImage.getIntrinsicWidth() * scale); // 设置缩放比例
        int scaledPanHeight = (int) (clockPanImage.getIntrinsicHeight() * scale);
        int panLeft = centerX - scaledPanWidth / 2;
        int panTop = centerY - scaledPanHeight / 2;
        int panRight = centerX + scaledPanWidth / 2;
        int panBottom = centerY + scaledPanHeight / 2;
        clockPanImage.setBounds(panLeft, panTop, panRight, panBottom);

        // 绘制时钟底盘
        clockPanImage.draw(canvas);

        // 绘制时针
        canvas.save();
        canvas.rotate(135, centerX, centerY);
        canvas.rotate(hours * 30 + minutes * 0.5f, centerX, centerY);
        hourPinImage.setBounds(centerX - hourPinImage.getIntrinsicWidth() / 2, centerY - hourPinImage.getIntrinsicHeight() / 2,
                centerX + hourPinImage.getIntrinsicWidth() / 2, centerY + hourPinImage.getIntrinsicHeight() / 2);
        hourPinImage.draw(canvas);
        canvas.restore();

        // 绘制分针
        canvas.save();
        canvas.rotate(135, centerX, centerY);
        canvas.rotate(minutes * 6 + seconds * 0.1f, centerX, centerY);
        minutePinImage.setBounds(centerX - minutePinImage.getIntrinsicWidth() / 2, centerY - minutePinImage.getIntrinsicHeight() / 2,
                centerX + minutePinImage.getIntrinsicWidth() / 2, centerY + minutePinImage.getIntrinsicHeight() / 2);
        minutePinImage.draw(canvas);
        canvas.restore();

        // 绘制秒针
        canvas.save();
        canvas.rotate(135, centerX, centerY);
        canvas.rotate(seconds * 6, centerX, centerY);
        secondPinImage.setBounds(centerX - secondPinImage.getIntrinsicWidth() / 2, centerY - secondPinImage.getIntrinsicHeight() / 2,
                centerX + secondPinImage.getIntrinsicWidth() / 2, centerY + secondPinImage.getIntrinsicHeight() / 2);
        secondPinImage.draw(canvas);
        canvas.restore();
    }
}