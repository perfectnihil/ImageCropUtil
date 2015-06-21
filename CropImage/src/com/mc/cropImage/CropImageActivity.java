package com.mc.cropImage;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;

public abstract class CropImageActivity extends FragmentActivity {
	private  final int  mc_Request_Album_Image = 062101;
	private  final int  mc_Request_Camera_Image = 062102;
	private  final int  mc_Resuest_Cutted_Image = 062103;
	public void doCropImageFromAlbum() {
		Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
		getImage.addCategory(Intent.CATEGORY_OPENABLE);
		getImage.setType("image/*");
		startActivityForResult(getImage, mc_Request_Album_Image);
	}
	public void doCropImageFromCamera() {
		Intent getImageByCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		imageUri = setOutFileUri();
		getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(getImageByCamera, mc_Request_Camera_Image);

	}
	private static Uri imageUri = null;
	private static final String FILE_NAME_IMAGE_SUFFIX = "";
	public static String FILE_NAME_PREFIX = "";
	public static Uri setOutFileUri() {
		if (!Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED))
			return null;
		FILE_NAME_PREFIX = String.valueOf(System.currentTimeMillis());
		String filePath = Environment.getExternalStorageDirectory()
				+ File.separator + FILE_NAME_PREFIX;
		filePath += FILE_NAME_IMAGE_SUFFIX;
		File out = new File(filePath);
		File outDir = out.getParentFile();
		if (!outDir.exists()) {
			outDir.mkdirs();
		}
		return Uri.fromFile(out);
	}
//	public abstract void setModel(CropModel model);
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
	}
	
	  public void startPhotoZoom(Uri uri) {
	        String imagePath = PathUtil.getImageAbsolutePath(this, uri);
	        Uri path = Uri.fromFile(new File(imagePath));
	        Intent intent = new Intent("com.android.camera.action.CROP");
	        intent.setDataAndType(path, "image/*");
	        intent.putExtra("crop", "true");
	        intent.putExtra("scale", true);
	        intent.putExtra("aspectX", 1);
	        intent.putExtra("aspectY", 1);
	        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
	        intent.putExtra("outputX", 300);
	        intent.putExtra("outputY", 300);
	        intent.putExtra("return-data", false);
	        startActivityForResult(intent, mc_Resuest_Cutted_Image);
	    }
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		  Uri mResultUri;
	        if (resultCode == RESULT_OK) {
	            switch (requestCode) {
	                case mc_Request_Camera_Image:
	                    File file = new File(Environment.getExternalStorageDirectory() + File.separator + FILE_NAME_PREFIX + FILE_NAME_IMAGE_SUFFIX);
	                    mResultUri = Uri.fromFile(file);
	                    startPhotoZoom(mResultUri);
	                    break;
	                case mc_Request_Album_Image:
	                    if (null != data) {//为了取消选取不报空指针用的
	                    	mResultUri = data.getData();
	                        startPhotoZoom(mResultUri);
	                    }
	                    break;
	                case mc_Resuest_Cutted_Image:
	                    if(data.getData()==null){
	                        break;
	                    }
	                    
					doHandlerResult(data);
	                    break;
	                default:
	                    break;
	            }
	        }}
	private void doHandlerResult(Intent data) {
		try {
			String CropedPath =  PathUtil.getImageAbsolutePath(this, data.getData());
			Bitmap
			cropBitmap= BitmapFactory.decodeFile(CropedPath);
			doHandleCropedResult(cropBitmap,CropedPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public abstract void doHandleCropedResult(Bitmap cropBitmap, String cropedPath) ;
	 
}
