package com.example.zhangyuanke.istudyandroid.FCThirdPart;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.zhangyuanke.istudyandroid.FCHttp.FCHttpActivity;
import com.example.zhangyuanke.istudyandroid.FCHttp.FCHttpWebviewActivity;
import com.example.zhangyuanke.istudyandroid.LogUtil;
import com.example.zhangyuanke.istudyandroid.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FCThirdPartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcthird_part);
        setupDataAndView();
    }

    private String[] data = {
            "1.GSON",
            "2.FastJson",
            "3.OKHttp",
            "4.Retrofit(网络请求,基于OKHttp)",
            "5.Universal-Image-Loader",
    };

    // 设置数据和view
    private void setupDataAndView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FCThirdPartActivity.this, R.layout.support_simple_spinner_dropdown_item, data);
        ListView listView = (ListView) findViewById(R.id.list_thirdpart_view);
        listView.setAdapter(adapter);
        // 事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                executeItemForRow(position);
            }
        });
    }

    private void executeItemForRow(int row) {
        Intent intent = null;
        switch (row) {
            case 0:
                gsonTest();
                break;
            case 1:
                fastJsonTest();
                break;
            case 2:
                okHttpTest();
                break;
            case 3:
                RetrofitTest();
                break;
            case 4:
                imageLoader();
                break;
            case 5:
                break;
            default:
                break;
        }
        if (intent != null)
        {
            startActivity(intent);
        }
    }



    // image loader
    // https://github.com/nostra13/Android-Universal-Image-Loader
    private void imageLoader()
    {
//        http://blog.csdn.net/vipzjyno1/article/details/23206387

        /*
        Simple

        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
// Load image, decode it to Bitmap and display Bitmap in ImageView (or any other view
//  which implements ImageAware interface)
        imageLoader.displayImage(imageUri, imageView);
// Load image, decode it to Bitmap and return Bitmap to callback
        imageLoader.loadImage(imageUri, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // Do whatever you want with Bitmap
            }
        });
// Load image, decode it to Bitmap and return Bitmap synchronously
        Bitmap bmp = imageLoader.loadImageSync(imageUri);

        */

        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "imageloader/Cache");
        LogUtil.d("puny",cacheDir.getAbsolutePath());
        // puny: /data/data/com.example.zhangyuanke.istudyandroid/cache
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(FCThirdPartActivity.this)
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .discCacheExtraOptions(480, 800, Bitmap.CompressFormat.JPEG, 75, null) // Can slow ImageLoader, use it carefully (Better don't use it)/设置缓存的详细信息，最好不要设置这个
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100) //缓存的文件数量
                .discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(FCThirdPartActivity.this, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();//开始构建
        // Initialize ImageLoader with configuration.

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);

        imageLoader.loadImage("http://pic32.nipic.com/20130815/10675263_110224052319_2.jpg", new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // Do whatever you want with Bitmap
                Toast.makeText(FCThirdPartActivity.this,"downLoad image ok",Toast.LENGTH_SHORT).show();
            }
        });


        /*
        * Complete

// Load image, decode it to Bitmap and display Bitmap in ImageView (or any other view
//  which implements ImageAware interface)
imageLoader.displayImage(imageUri, imageView, options, new ImageLoadingListener() {
    @Override
    public void onLoadingStarted(String imageUri, View view) {
        ...
    }
    @Override
    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
        ...
    }
    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        ...
    }
    @Override
    public void onLoadingCancelled(String imageUri, View view) {
        ...
    }
}, new ImageLoadingProgressListener() {
    @Override
    public void onProgressUpdate(String imageUri, View view, int current, int total) {
        ...
    }
});
// Load image, decode it to Bitmap and return Bitmap to callback
ImageSize targetSize = new ImageSize(80, 50); // result Bitmap will be fit to this size
imageLoader.loadImage(imageUri, targetSize, options, new SimpleImageLoadingListener() {
    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        // Do whatever you want with Bitmap
    }
});
// Load image, decode it to Bitmap and return Bitmap synchronously
ImageSize targetSize = new ImageSize(80, 50); // result Bitmap will be fit to this size
Bitmap bmp = imageLoader.loadImageSync(imageUri, targetSize, options);

*/
    }

    // Retrofit
    /*
    * 特点
性能最好，处理最快
使用REST API时非常方便；
传输层默认就使用OkHttp；
支持NIO；
拥有出色的API文档和社区支持
速度上比volley更快；
如果你的应用程序中集成了OKHttp，Retrofit默认会使用OKHttp处理其他网络层请求。
默认使用Gson*/

    private void RetrofitTest()
    {
        /*
        * import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;


        public interface PersonService {
            @Headers({
                    "Cache-Control: max-age=640000",
                    "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko"
            })
            //通过注解设置请求头
            @GET("/{test}/rest.php")
                //设置请求方法为get，相对路径为注解内内容，其中{test}会被@Path注解指定内容替换
            Person getPerson(@Path("test") String dir,@Query("name") String name);
            //@Query用于指定参数

            @FormUrlEncoded
            //urlencode
            @POST("/test/rest1.php")
                //post提交
            Person updatePerson(@Field("name") String name,@Field("age") int age);
            //@Field提交的域


            @POST("/test/rest1.php")
            void updatePerson(@Field("name") String name,@Field("age") int age, Callback<Person> callback);
            //异步回调，不能指定返回值
        }

        GET
        使用时，通过RestAdapter的实例获得一个接口的实例，其本质是动态代理，注意含有返回值的方法是同步的，不能UI线程中调用，应该在子线程中完成
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://121.41.119.107")
                .build();
        PersonService personService=restAdapter.create(PersonService.class);
        Person person=personService.getPerson("test","zhangsan");
        Log.d("TAG",person.toString());

        POST
        POST的调用同Get，获得adapter后获得一个代理对象，然后通过这个代理对象进行网络请求

        Person person1=personService.updatePerson("lizhangqu", 12);
        Log.d("TAG",person1.toString());

        异步请求
        如果要使用异步请求，需要将接口中的方法返回值修改会void，再加入回调参数Callback，就如PersonService中第三个方法一样，请求完成后会回调该callback对象的success或者fail方法。

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://121.41.119.107")
                .build();
        PersonService personService=restAdapter.create(PersonService.class);
        personService.updatePerson("lizhangqu",23, new Callback<Person>() {
            @Override
            public void success(Person person, Response response) {
                Log.d("TAG", person.toString());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        */
    }

    // OKHttp
    // http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0824/3355.html

    private void okHttpTest()
    {
        // GET
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url("http://www.baidu.com").build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {

            /*
            * 以上就是发送一个get请求的步骤，首先构造一个Request对象，参数最起码有个url，当然你可以通过Request.Builder设置更多的参数比如：header、method等。
然后通过request的对象去构造得到一个Call对象，类似于将你的请求封装成了任务，既然是任务，就会有execute()和cancel()等方法。
最后，我们希望以异步的方式去执行请求，所以我们调用的是call.enqueue，将call加入调度队列，然后等待任务执行完成，我们在Callback中即可得到结果。
看到这，你会发现，整体的写法还是比较长的，所以封装肯定是要做的，不然每个请求这么写，得累死。

ok，需要注意几点：

onResponse回调的参数是response，一般情况下，比如我们希望获得返回的字符串，可以通过response.body().string()获取；如果希望获得返回的二进制字节数组，则调用response.body().bytes()；如果你想拿到返回的inputStream，则调用response.body().byteStream()
看到这，你可能会奇怪，竟然还能拿到返回的inputStream，看到这个最起码能意识到一点，这里支持大文件下载，有inputStream我们就可以通过IO的方式写文件。不过也说明一个问题，这个onResponse执行的线程并不是UI线程。的确是的，如果你希望操作控件，还是需要使用handler等，例如：
*/
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(FCThirdPartActivity.this,"error",Toast.LENGTH_SHORT).show();
//                        mTv.setText(res);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String res = response.body().string();
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Toast.makeText(FCThirdPartActivity.this,res.substring(0,30),Toast.LENGTH_SHORT).show();
//                        mTv.setText(res);
                    }

                });
            }
        });

        /**
         * Request request = buildMultipartFormRequest(
         url, new File[]{file}, new String[]{fileKey}, null);
         FormEncodingBuilder builder = new FormEncodingBuilder();
         builder.add("username","张鸿洋");

         Request request = new Request.Builder()
         .url(url)
         .post(builder.build())
         .build();
         mOkHttpClient.newCall(request).enqueue(new Callback(){});
         * */


        /*
        * （三）基于Http的文件上传
接下来我们在介绍一个可以构造RequestBody的Builder，叫做MultipartBuilder。当我们需要做类似于表单上传的时候，就可以使用它来构造我们的requestBody。

File file = new File(Environment.getExternalStorageDirectory(), "balabala.mp4");

RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);

RequestBody requestBody = new MultipartBuilder()
     .type(MultipartBuilder.FORM)
     .addPart(Headers.of(
          "Content-Disposition",
              "form-data; name=\"username\""),
          RequestBody.create(null, "张鸿洋"))
     .addPart(Headers.of(
         "Content-Disposition",
         "form-data; name=\"mFile\";
         filename=\"wjd.mp4\""), fileBody)
     .build();

Request request = new Request.Builder()
    .url("http://192.168.1.103:8080/okHttpServer/fileUpload")
    .post(requestBody)
    .build();

Call call = mOkHttpClient.newCall(request);
call.enqueue(new Callback()
{
    //...
});
上述代码向服务器传递了一个键值对username:张鸿洋和一个文件。我们通过MultipartBuilder的addPart方法可以添加键值对或者文件。
        * */


        // http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0824/3355.html
//        进行了封装
    }


    // FastJson
    private void fastJsonTest()
    {
        // 1
//        FFStudent student = new FFStudent(0, "Aaron", 24);
//        System.out.println(JSON.toJSONString(student));
        // 2
//        List<FFStudent> students = new ArrayList<FFStudent>();
//        for(int i=0;i<5;i++) {
//            FFStudent stu = new FFStudent(i, "Student" + i, 18 +i);
//            students.add(stu);
//        }

        // 3
        /*
        List<FFTeacher> teaList = new ArrayList<FFTeacher>();
        long time = System.currentTimeMillis();
        for(int i=0;i<10;i++) {
            FFTeacher teacher = new FFTeacher(i, "Teacher " + i);
            List<FFStudent> stus = new ArrayList<FFStudent>();
            for(int j = 0 ;j<4;j++) {
                FFStudent s = new FFStudent(j, "Student" + j, 18 +j);
                stus.add(s);
            }
            teacher.setStudents(stus);
            teaList.add(teacher);
        }
        String jsonTeach = JSON.toJSONString(teaList);
        System.out.println("fastjson = " + jsonTeach);
        */
        // 4
        /*
        FFStudent student = new FFStudent(0, "Aaron", 24);
        System.out.println(JSON.toJSONString(student,true));
        */

        List<FFStudent> students = new ArrayList<FFStudent>();
        for(int i=0;i<5;i++) {
            FFStudent stu = new FFStudent(i, "Student" + i, 18 +i);
            students.add(stu);
        }
        // 过滤哪些属性需要转换
//      SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Student.class, "id","age");
//      String jsonStu =JSON.toJSONString(students,filter);
        String jsonStu =JSON.toJSONString(students);
        System.out.println(jsonStu);

        List<FFStudent> stu =JSON.parseObject(jsonStu, new TypeReference<List<FFStudent>>(){});
        for(int i=0;i<stu.size();i++)
        {
            System.out.println(stu.get(i));
        }
    }

    // GSON
    private void gsonTest()
    {
        //
        Gson gson = new Gson();
//        Person person = gson.fromJson("",Person.class);

//        List<Person> people = gson.fromJson("",new TypeToken<List<Person>>().getType());

        String str = "[{'id':'5','version':'5.7','name':'angry birds'}," +
                "{'id':'6','version':'4.7','name':'hey day'}]";

        List<FCHttpActivity.App> appList = gson.fromJson(str,new TypeToken<List<FCHttpActivity.App>>(){}.getType());
        for (FCHttpActivity.App app : appList)
        {
            LogUtil.d("puny","app name:" + app.getName());
        }

    }

    public class App{
        private String id;
        private String name;
        private String version;

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getVersion()
        {
            return version;
        }

        public void setVersion(String version)
        {
            this.version = version;
        }
    }

}
