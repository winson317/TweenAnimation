package com.example.tweenanimation;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class TweenAnimation extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final ImageView flower = (ImageView)findViewById(R.id.flower);
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim); //加载第一份动画资源
        anim.setFillAfter(true);  //设置动画结束后保留结束状态
        
        final Animation reverse = AnimationUtils.loadAnimation(this, R.anim.reverse); //加载第二份动画资源
        reverse.setFillAfter(true);  //设置动画结束后保留结束状态
        Button button = (Button)findViewById(R.id.button);
        
        final Handler handler = new Handler(){
        	
        	@Override
        	public void handleMessage(Message msg) {
        		// TODO Auto-generated method stub
        		if (msg.what == 0x123)
        		{
        			flower.startAnimation(reverse);
        		}
        	}
        	
        };
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flower.startAnimation(anim);
				
				//设置3.5秒后启动第二个动画
				new Timer().schedule(new TimerTask() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						handler.sendEmptyMessage(0x123);
					}
				}, 3500);
			}
		});
        
    }

}

