package com.example.librarydemo;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhangyuanke on 16/11/10.
 */

public class TestClass {

    public static final int id222 = 0x7f020001;
    public void test(Context context) {
        Toast.makeText(context, "Test Jar haha", Toast.LENGTH_SHORT).show();
    }

    public void testView(Context context, ImageView imageView) {
//        int id = MResource.getIdByName(context,"")

        //noinspection ResourceType
        imageView.setImageResource(id222);
    }

    public void testtest(Context context,ImageView imageView)
    {
//        imageView.setImageResource(ResUtil.getDrawableId(context,"cherry_pic"));
//        ResUtil.getDrawableId(context,"cherry_pic");
        Bitmap bitmap = getImageFromAssetsFile(context,"orange_pic.png");
        if (bitmap != null) {
            Toast.makeText(context,"bitmap ok",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"bitmap error",Toast.LENGTH_SHORT).show();

        }
        imageView.setImageBitmap(getImageFromAssetsFile(context,"orange_pic.png"));
    }

    /*
    *
            * 从Assets中读取图片
    */
    private Bitmap getImageFromAssetsFile(Context context,String fileName)
    {
        Bitmap image = null;

        AssetManager am = context.getResources().getAssets();
        try
        {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return image;

    }
}

/*
http://blog.csdn.net/liranke/article/details/16980449
https://my.oschina.net/tnjin/blog/223368
http://blog.csdn.net/qq_23286245/article/details/50344071

* 一.  如果jar引用到了so库，不能将so库一同打包到jar中，而是要按照下面的方法来做：

      1） 首先，将代码打包成jar, 但是一定是只包括src目录，其它的如lib等都不要选中，也就是说,要用到的so库也不要选中；

      2)   其次，将jar包引入到新的工程中；

      3） 最后， 将用到的so库copy到新工程的libs\armeabi目录下，这样，新工程就能够正确引用jar包了。



 否则，可能出现的问题有：

    Exception Ljava/lang/UnsatisfiedLinkError; thrown while initializing Lcom/

           Caused by: java.lang.UnsatisfiedLinkError: Couldn't load mylib-jni: findLibrary returned null



二.   Android中jar包封装及调用(来自于http://www.eoeandroid.com/forum.php?mod=viewthread&tid=164411&page=1#pid1379740):
        在android开发过程中，我们经常会有这种需求，自己开发一个类库jar包，提供给别人调用。 即把项目A封装成jar包，供项目B调用，而在项目B中调用项目A的activity的时候问题就出现了：找不到资源文件（异常：ERROR/AndroidRuntime(3341):Caused by: android.content.res.Resources$NotFoundException: Stringresource ID #0x7f060007）。问题是，只能共享src文件夹下的代码，而不能共享res资源文件，够郁闷！创建一个Androidproject, 用eclipse的export导出，去掉AndroidManifest.xml,res 什么的，这样只共享了代码，而此代码中所调用的res资源文件却不能正常使用，否则会抛出res.Resources$NotFoundException异常。那么如何解决呢？
        1.如果只用共享代码，而共享的代码中不涉及到res资源文件的调用的话，直接在Eclipse中导出jar包，在所需要的项目中引用即可。


        2.如果共享代码，而且共享的代码中涉及到res资源文件的调用，网上通用的方法是把res资源文件放到assets文件夹中，再在src代码中加载处理，然后再用eclipse导出jar包，引用即可(res资源虽然可以打进jar包，但当其他项目调用jar包时jar包中引用ID 指向的是当前项目的资源，关于如何让jar包中的资源引用只想jar包中的res文件暂无解决方案。我的问题已解决，解决方案：把图片资源放入assets包中一并打入jar包，布局就只能用代码了。jar包中代码可以成功引用jar包内的assets文件。
另：JNI层不可被打包入jar包，只能同jar包一同提供给第三方并放入工程目录下的libs包中)。


        3.如果共享代码，而且涉及到res资源文件的调用，而且不想把res资源文件放到assets文件夹中的，直接生成jar包会出现的问题：
                首先，当在别的项目中引用此jar包时，在项目名上出现小红叉，并且在console控制台上直接自动提示：Errorgenerating final archive: Found duplicate file for APK:res/drawable-hdpi/icon.png，通常的解决方法时，在能编译通过的此项目中直接去掉提示的资源文件，再生成jar包。


                其次，把公共的icon等资源文件删除，生成jar包之后，此时在项目中引用，console控制台上无自动报错现象。如果jar包中的activity调用了res资源文件夹中的文件，在当前项目中调用jar包中的acticity时，问题就出现了，activity所引用的res资源，如果引用的是Strings.xml文件中的值，则抛出异常ERROR/AndroidRuntime(3341):Caused by: android.content.res.Resources$NotFoundException:Stringresource ID#0x7f060007。如果引用的是layout文件夹中的文件，能找到layout中的xml，例如layout中ee.xml，可以找到R.layout.ee，但是在ee.xml中定义的控件id却引用不到，抛出异常ERROR/AndroidRuntime(13703):Caused by:java.lang.NullPointerException。如果在当前项目中调用不涉及jar包中的res的调用，一切OK。

        4.如果共享代码，而且共享的代码中涉及到res资源文件的调用，如果封装成jar包，这种方法是达不到要求的（调用jar中封装的activity，还得保证jar包中activity正常调用jar中所封装的res资源，上面说了，jar包中的res资源部分调用为空），那么如何能满足需求呢？把要生成jar包的项目做一下处理，在jar包项目的properties窗口中选择android的选项卡的isLibaray，点击apply按钮或者ok按钮（此时，jar包项目再点击右键选择runas →  AndroidApplication就能正常运行了）。在要引用jar包的项目中，右击项目名称，打开properties框口，选择android的选项卡的isLibaray右侧的Add按钮，添加jar包项目。这样jar包项目中的activity就能正常引用到jar包项目中的res资源了。项目调用jar包项目的activity就正常了。

*/
