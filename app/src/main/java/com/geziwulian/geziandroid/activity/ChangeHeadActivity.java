package com.geziwulian.geziandroid.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import com.geziwulian.geziandroid.AppManager;
import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.netlibrary.ApiWrapper;
import com.geziwulian.netlibrary.model.Avatar;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/8/24.
 */
public class ChangeHeadActivity extends BaseActivity {
    @BindView(R.id.demo_chang_head)
    ImageView view;
    @BindView(R.id.demo_btn)
    Button mbtn;
    @OnClick(R.id.demo_btn)void click(){
        Crop.pickImage(mContext, PHOTO_REQUEST_GALLERY);
    }

    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择

    private String IMAGE_PATH = "";
    private File imageFile;
    private Uri destinationUri;
    private String imageFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chang_head_main_layout);
        AppManager.getAppManager().addActivity(mContext);
        destinationUri = Uri.fromFile(new File(getCacheDir(), "cropimage"));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case PHOTO_REQUEST_GALLERY:
                if (resultCode == RESULT_OK) {
                    Uri imageUri = data.getData();
                    getImagePath(imageUri);
                    imageFile = new File(IMAGE_PATH);
                    Crop.of(imageUri, destinationUri).asSquare().start(mContext);
                }
                break;
            case Crop.REQUEST_CROP:
                if (resultCode == RESULT_OK) {
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.RGB_565;
                        options.inPurgeable = true;
                        options.inInputShareable = true;
                        options.inSampleSize = 10;
                        Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(Crop.getOutput(data)), null, options);
                        if (bitmap != null) {
                            view.setImageBitmap(bitmap);
                        } else if (!bitmap.isRecycled()) {
                            bitmap.recycle();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    upLoadImage(Crop.getOutput(data));
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
//                startActivityForResult(takePictureIntent, PHOTO_REQUEST_TAKEPHOTO);
            }
        }
    }


    private void getImagePath(Uri uri) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null,
                null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        IMAGE_PATH = cursor.getString(column_index);
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }

    private void upLoadImage(Uri uri) {
        File file = new File(uri.getPath());
        if (file != null) {
            RequestBody image = RequestBody.create(MediaType.parse("image/*"), file);
            final ApiWrapper wrapper = new ApiWrapper();
            Subscription subscription = wrapper.Avatar(image)
                    .subscribe(newSubscriber(new Action1<Avatar>() {
                        @Override
                        public void call(Avatar avatar) {
                            System.out.print(avatar);
                        }
                    }));
            mCompositeSubscription.add(subscription);
        }
    }


}
