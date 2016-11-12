package com.example.zhangyuanke.istudyandroid.FCBitmap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.R;

import java.io.InputStream;

public class FCBitmapActivity extends BaseActivity {

    private ImageView imageViewSrc = null;
    private ImageView imageViewDst = null;
    private Button imageButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcbitmap);

        imageViewSrc = (ImageView)findViewById(R.id.src_image);
        imageViewDst = (ImageView)findViewById(R.id.dst_image);
        imageButton = (Button)findViewById(R.id.button_image);
//        testImage();
        initImage();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testImage();
            }
        });
    }

    private void initImage()
    {
        /*
        * 1.从资源文件中获取

1 Bitmap rawBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.img1);
*/
        /*
        Bitmap rawBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.apple_pic);
        imageViewSrc.setImageBitmap(rawBitmap);
        */

        String SDCarePath=Environment.getExternalStorageDirectory().toString();
        String filePath=SDCarePath+"/"+"tempImage.jpg";
        Bitmap rawBitmap1 = BitmapFactory.decodeFile(filePath, null);
        imageViewSrc.setImageBitmap(rawBitmap1);
        /*
        InputStream inputStream=getBitmapInputStreamFromSDCard("haha.jpg");
        Bitmap rawBitmap2 = BitmapFactory.decodeStream(inputStream);
        */
    }

    private void testImage()
    {
//        String SDCarePath=Environment.getExternalStorageDirectory().toString();
//        String filePath=SDCarePath+"/"+"tempImage.jpg";
//        Bitmap rawBitmap1 = BitmapFactory.decodeFile(filePath, null);
//        Bitmap bm = toRoundCorner(rawBitmap1,20);
//        imageViewDst.setImageBitmap(bm);
        Bitmap rawBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.apple_pic);
        imageViewDst.setImageBitmap(rawBitmap);
    }

    // 3.设置图片的圆角，返回设置后的BitMap
    public Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
        Bitmap roundCornerBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(roundCornerBitmap);
        int color = 0xff424242;// int color = 0xff424242;
        Paint paint = new Paint();
        paint.setColor(color);
        // 防止锯齿
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float roundPx = pixels;
        // 相当于清屏
        canvas.drawARGB(0, 0, 0, 0);
        // 先画了一个带圆角的矩形
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // 再把原来的bitmap画到现在的bitmap！！！注意这个理解
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return roundCornerBitmap;
    }

    // 4.将图片高宽和的大小kB压缩
    private void testtt(Bitmap rawBitmap)
    {
        //得到图片原始的高宽
        int rawHeight = rawBitmap.getHeight();
        int rawWidth = rawBitmap.getWidth();
        // 设定图片新的高宽
        int newHeight = 500;
        int newWidth = 500;
        // 计算缩放因子
        float heightScale = ((float) newHeight) / rawHeight;
        float widthScale = ((float) newWidth) / rawWidth;
        // 新建立矩阵
        Matrix matrix = new Matrix();
        matrix.postScale(heightScale, widthScale);
        // 设置图片的旋转角度
        // matrix.postRotate(-30);
        // 设置图片的倾斜
        // matrix.postSkew(0.1f, 0.1f);
        // 将图片大小压缩
        // 压缩后图片的宽和高以及kB大小均会变化
        Bitmap newBitmap = Bitmap.createBitmap(rawBitmap, 0, 0, rawWidth,
                rawWidth, matrix, true);
    }


    // http://www.2cto.com/kf/201408/327978.html




    /*
    * 1         Drawable newBitmapDrawable = new BitmapDrawable(Bitmap);
2         //如果要获取BitMapDrawable中所包装的BitMap对象，可以用getBitMap()方法；
3         Bitmap  bitmap = newBitmapDrawable.getBitmap();

1      if(!bitmap.isRecycled())
2     {
3        bitmap.recycle()
4     }
*/


    /*
    *
    * 2、从资源中获取Bitmap
?
1
2
3
Resources res=getResources();

Bitmap bmp=BitmapFactory.decodeResource(res, R.drawable.pic);
3、Bitmap → byte[]

private byte[] Bitmap2Bytes(Bitmap bm){

ByteArrayOutputStream baos = new ByteArrayOutputStream();

bm.compress(Bitmap.CompressFormat.PNG, 100, baos);

return baos.toByteArray();

}

4、byte[] → Bitmap

private Bitmap Bytes2Bimap(byte[] b){

if(b.length!=0){

return BitmapFactory.decodeByteArray(b, 0, b.length);

}

else {

return null;

}

}
5、保存bitmap

static boolean saveBitmap2file(Bitmap bmp,String filename){

CompressFormat format= Bitmap.CompressFormat.JPEG;

int quality = 100;

OutputStream stream = null;

try {

stream = new FileOutputStream("/sdcard/" + filename);

} catch (FileNotFoundException e) {

// TODO Auto-generated catch block

Generated by Foxit PDF Creator © Foxit Software

http://www.foxitsoftware.com For evaluation only.

e.printStackTrace();

}

return bmp.compress(format, quality, stream);

}

6、将图片按自己的要求缩放

// 图片源

Bitmap bm = BitmapFactory.decodeStream(getResources()

.openRawResource(R.drawable.dog));

// 获得图片的宽高

int width = bm.getWidth();

int height = bm.getHeight();

// 设置想要的大小

int newWidth = 320;

int newHeight = 480;

// 计算缩放比例

float scaleWidth = ((float) newWidth) / width;

float scaleHeight = ((float) newHeight) / height;

// 取得想要缩放的matrix参数

Matrix matrix = new Matrix();

matrix.postScale(scaleWidth, scaleHeight);

// 得到新的图片

Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix,

true);

// 放在画布上

canvas.drawBitmap(newbm, 0, 0, paint);

7：File图片转Bitmap

?
1
Bitmap bt = BitmapFactory.decodeFile("/sdcard/myImage/" + "head.jpg");//图片地址

8：//图片转Bitmap
?
1
2
3
4
5
6
7
8
9
public Bitmap drawableToBitamp(int drawableResource) {<span style="white-space:pre">    </span>//可以取raw里面的资源
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = this.getResources().openRawResource(drawableResource);
        BitmapFactory.decodeStream(is, null, opt);
        return BitmapFactory.decodeStream(is, null, opt);
    }
    * */

}
