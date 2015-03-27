package com.xiaoma.piccut.activity;

import java.io.File;

import com.xiaoma.piccut.demo.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

/*����п���������������QQͷ��Ĳü��ϴ�����ôʵ�ֵģ����С��Ҳû����������֮��ѧϰ�£�������ǰ��Ŀ�������͵Ĺ��ܣ���Ϲٷ��ĵ�����Ľ���
 ���͸������ˣ���ĩ������ææд�ģ���¼�ڲ����ϣ�������ҽ���ѧϰ��Ҳ��������ܽ��С���ڴ���ע������������ʣ�������û���˻ش�С����
 лл�ˣ�һ���ģ��ȿ���Ч��ͼ��Ч��ͼС�������ˣ�ֱ����ˮд��ȥ��С����ֱ����ģ������д�ģ���֤�������ʹ�ã���Ϊ�ܼ򵥣����ٿ���������ôʵ�ֵģ�
 һ�������ֽ���
 ��������ؼ������¼���Ч��ͼ
 ����������֮��Ч��ͼ
 �ģ��ü�����Ч��ͼ
 �壺������󷵻ص�ͼƬЧ��ͼ
 �����ü�������PICK�ı�����Ч��ͼ


 ���С����СDEMOԴ����ڸ������棬����Ҫ�����ѿ���������������ͬ����ѧϰ��Ҳ������˻ش���С��������ע������������⣬лл��������С����ææ�ڼ�д�ģ�
 �����ɷ��ģ���...�������ж��ù���������꣬�������꣬�����Ҿ����ƣ���������ģ�˳���������£���𣬸����ʱ���������棬�ù�����ʱ�������Ĺ���������ʱ���
 ���˲���ڣ���𣬼��ͼ��ͣ���ҹ�����Ҳע�����彡�����ٺ٣��㶮�ģ�������...����

 */

/**
 * @Title: PicCutDemoActivity.java
 * @Package com.xiaoma.piccut.demo
 * @Description: ͼƬ�ü����ܲ���
 * @author XiaoMa
 */
public class PicCutDemoActivity extends Activity implements OnClickListener {

	// private ImageButton ib = null;
	private ImageView iv = null;
	private Button btn = null;
	private String tp = null;
	Dialog dialog;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ʼ��
		init();
	}

	/**
	 * ��ʼ������ʵ��
	 */
	private void init() {

		iv = (ImageView) findViewById(R.id.imageView1);
		btn = (Button) findViewById(R.id.getpicture);

		iv.setOnClickListener(this);
		btn.setOnClickListener(this);
	}

	/**
	 * �ؼ�����¼�ʵ��
	 * 
	 * ��Ϊ�������ʲ�ͬ�ؼ��ı���ͼ�ü���ôʵ�֣� �Ҿ�������ط����������ؼ���ֻΪ���Լ���¼ѧϰ ��Ҿ���û�õĿ���������
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {

//		case R.id.imageView1:
//			showDialog();
//			break;
		case R.id.getpicture:
			showDialog();
			break;

		default:
			break;
		}
	}

	public void on_click(View v) {
		switch (v.getId()) {
		case R.id.openCamera:
			 openCamera();

			break;
		case R.id.openPhones:
			 openPhones();

			break;
		case R.id.cancel:
			dialog.cancel();
			break;
		default:
			break;
		}
	}

	private void openCamera() {
		dialog.dismiss();
		/**
		 * ������仹�������ӣ����ÿ������չ��ܣ�����Ϊʲô�п������գ���ҿ��Բο����¹ٷ�
		 * �ĵ���you_sdk_path/docs/guide/topics/media/camera.html
		 * �Ҹտ���ʱ����Ϊ̫�������濴����ʵ�Ǵ�ģ�����������õ�̫���ˣ����Դ�Ҳ�Ҫ��Ϊ
		 * �ٷ��ĵ�̫���˾Ͳ����ˣ���ʵ�Ǵ�ģ�����ط�С��Ҳ���ˣ��������
		 */
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// �������ָ������������պ����Ƭ�洢��·��
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
				Environment.getExternalStorageDirectory(), "xiaoma.jpg")));
		startActivityForResult(intent, 2);
	}

	private void openPhones() {
		dialog.dismiss();
		/**
		 * �տ�ʼ�����Լ�Ҳ��֪��ACTION_PICK�Ǹ���ģ�����ֱ�ӿ�IntentԴ�룬
		 * ���Է�������ܶණ����Intent�Ǹ���ǿ��Ķ��������һ����ϸ�Ķ���
		 */
		Intent intent2 = new Intent(Intent.ACTION_PICK, null);

		/**
		 * ������仰����������ʽд��һ����Ч��������� intent.setData(MediaStore.Images
		 * .Media.EXTERNAL_CONTENT_URI); intent.setType(""image/*");������������
		 * ���������Ҫ�����ϴ�����������ͼƬ����ʱ����ֱ��д�� ��"image/jpeg �� image/png�ȵ�����"
		 * ����ط�С���и����ʣ�ϣ�����ֽ���£������������URI������ΪʲôҪ��������ʽ��дѽ����ʲô����
		 */
		intent2.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				"image/*");
		startActivityForResult(intent2, 1);
	}

	private void showDialog() {
		View view = getLayoutInflater().inflate(R.layout.photo_choose_dialog,
				null);
		dialog = new Dialog(this, R.style.transparentFrameWindowStyle);
		dialog.setContentView(view, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		Window window = dialog.getWindow();
		// ������ʾ����
		window.setWindowAnimations(R.style.main_menu_animstyle);
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = getWindowManager().getDefaultDisplay().getHeight();
		// ������������Ϊ�˱�֤��ť����ˮƽ����
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

		// ������ʾλ��
		dialog.onWindowAttributesChanged(wl);
		// ���õ����Χ��ɢ
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		// �����ֱ�Ӵ�����ȡ
		case 1:
			startPhotoZoom(data.getData());
			break;
		// ����ǵ����������ʱ
		case 2:
			File temp = new File(Environment.getExternalStorageDirectory()
					+ "/xiaoma.jpg");
			startPhotoZoom(Uri.fromFile(temp));
			break;
		// ȡ�òü����ͼƬ
		case 3:
			/**
			 * �ǿ��жϴ��һ��Ҫ��֤���������֤�Ļ��� �ڼ���֮��������ֲ����⣬Ҫ���²ü�������
			 * ��ǰ����ʱ���ᱨNullException��С��ֻ ������ط����£���ҿ��Ը��ݲ�ͬ����ں��ʵ� �ط����жϴ����������
			 * 
			 */
			if (data != null) {
				setPicToView(data);
			}
			break;
		default:
			break;

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * �ü�ͼƬ����ʵ��
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {
		/*
		 * �����������Intent��ACTION����ô֪���ģ���ҿ��Կ����Լ�·���µ�������ҳ
		 * yourself_sdk_path/docs/reference/android/content/Intent.html
		 * ֱ��������Ctrl+F�ѣ�CROP ��֮ǰС��û��ϸ��������ʵ��׿ϵͳ���Ѿ����Դ�ͼƬ�ü�����, ��ֱ�ӵ����ؿ�ģ�С����C C++
		 * ���������ϸ�˽�ȥ�ˣ������Ӿ������ӣ������о���������ô ��������...���
		 */
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// �������crop=true�������ڿ�����Intent��������ʾ��VIEW�ɲü�
		intent.putExtra("crop", "true");
		// aspectX aspectY �ǿ�ߵı���
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY �ǲü�ͼƬ���
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 3);
	}

	/**
	 * ����ü�֮���ͼƬ����
	 * 
	 * @param picdata
	 */
	private void setPicToView(Intent picdata) {
		Bundle extras = picdata.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			Drawable drawable = new BitmapDrawable(photo);

			/**
			 * ����ע�͵ķ����ǽ��ü�֮���ͼƬ��Base64Coder���ַ���ʽ�� ������������QQͷ���ϴ����õķ������������
			 */

			/*
			 * ByteArrayOutputStream stream = new ByteArrayOutputStream();
			 * photo.compress(Bitmap.CompressFormat.JPEG, 60, stream); byte[] b
			 * = stream.toByteArray(); // ��ͼƬ�����ַ�����ʽ�洢����
			 * 
			 * tp = new String(Base64Coder.encodeLines(b));
			 * ����ط���ҿ���д�¸��������ϴ�ͼƬ��ʵ�֣�ֱ�Ӱ�tpֱ���ϴ��Ϳ����ˣ� ����������ķ����Ƿ������Ǳߵ����ˣ����
			 * 
			 * ������ص��ķ����������ݻ�����Base64Coder����ʽ�Ļ������������·�ʽת�� Ϊ���ǿ����õ�ͼƬ���;�OK��...���
			 * Bitmap dBitmap = BitmapFactory.decodeFile(tp); Drawable drawable
			 * = new BitmapDrawable(dBitmap);
			 */

			iv.setBackgroundDrawable(drawable);
		}
	}

}