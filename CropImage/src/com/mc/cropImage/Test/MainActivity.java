package com.mc.cropImage.Test;

import com.mc.cropImageFromCustom.CropImageView;
import com.pnl.cropimage.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity {
 private CropImageView mView;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  mView = (CropImageView) findViewById(R.id.cropimage);
  //设置资源和默认长宽
  mView.setDrawable(getResources().getDrawable(R.drawable.ic_launcher), 300,
    300);
  //调用该方法得到剪裁好的图片
  
//  mView.postDelayed(new Runnable() {
//	
//	@Override
//	public void run() {
//		 doRefact();
//		 
//	}
//},500);
  
 
 }
 
 @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	 MenuInflater inflater =  new MenuInflater(this);
	 inflater.inflate(R.menu.launcher, menu);
	 return true;
	}
 
 @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	 doRefact();
		return super.onOptionsItemSelected(item);
	}

private void doRefact() {
	Bitmap mBitmap= mView.getCropImage();
	 
//	 mView.setBackground(new BitmapDrawable(mBitmap));
	mView.setDrawable(new BitmapDrawable(mBitmap), 300, 300);
}
 

}