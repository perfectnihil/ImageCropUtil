package com.mc.cropImage.Test;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mc.cropImageFromSystem.CropImageActivity;
import com.pnl.cropimage.R;

public class TestActivity extends CropImageActivity{
	
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.mmainlayout);
	}
	
	@Override
	public void doHandleCropedResult(Bitmap cropBitmap, String cropedPath) {
		
		ImageView iv_main = (ImageView) findViewById(R.id.iv_main); 
		
		iv_main.setImageBitmap(cropBitmap);
	}
	
	
	public void doFromAlbum(View viw){
		doCropImageFromAlbum();
	}
	public void doFromCamera(View viw){
		doCropImageFromCamera();
	}
	
	

}
