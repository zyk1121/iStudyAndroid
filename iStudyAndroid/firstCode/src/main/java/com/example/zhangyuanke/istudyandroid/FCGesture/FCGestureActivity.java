package com.example.zhangyuanke.istudyandroid.FCGesture;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.LogUtil;
import com.example.zhangyuanke.istudyandroid.R;

// http://www.open-open.com/lib/view/open1422244035095.html

public class FCGestureActivity extends BaseActivity implements View.OnTouchListener,GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private GestureDetector gd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcgesture);
        View view = (View)findViewById(R.id.activity_fcgesture);
        view.setOnTouchListener(this);
        LogUtil.d("puny","gesture");

        //noinspection deprecation
        gd = new GestureDetector(this);
    }

    // View.OnTouchListener
    /*
    * 触摸监听器OnTouchListener
让我们的Activity去现实此接口，并重写onTouch方法。重写OnTouchListener的onTouch方法 此方法在触摸屏被触摸，即发生触摸事件（接触和抚摸两个事件）的时候被调用。示范代码如下：

    @Override
        public boolean onTouch(View v, MotionEvent event) {
            detector.onTouchEvent(event);
            Toast.makeText(this, "onTouch", TIME_OUT).show();
            return true;
        }
        */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        LogUtil.d("puny","onTouch");
        return false;
    }

    /*
    * 今天在看安卓代码的时候发现莫名其妙地onFling执行不到了。
究其原因，原来应该是这样的：
public class Activity2048 extends Activity implements OnGestureListener {
private GestureDetector gd;
...........
protected void onCreate(Bundle savedInstanceState) {
...
gd = new GestureDetector(this);

}

@Override
public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
float velocityY) {
//添加自己处理的代码
}
//重点是添加如下;
@Override
public boolean onTouchEvent(MotionEvent event) {
return gd.onTouchEvent(event);
}
//这样就可以保证执行onFling了。*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gd.onTouchEvent(event);
    }

    /*
* 手势滑动监听器OnGestureListener
让我们的Activity去现实此接口，并重写onFling、onLongPress、onScroll、onDown、onShowPress、onSingleTapUp方法。示范代码如下：
*/
    /*
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
    */

    /**
     * 手势滑动时别调用
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {

        if (e1.getX() - e2.getX() > 10) {
            Toast.makeText(this, "向左滑动", Toast.LENGTH_SHORT).show();
        } else if (e2.getX() - e1.getX() > 10) {
            Toast.makeText(this, "向右滑动", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    /**
     * 长按时被调用
     */
    @Override
    public void onLongPress(MotionEvent e) {
        Toast.makeText(this, "触发长按回调", Toast.LENGTH_SHORT).show();
    }

    /**
     * 滚动时调用
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        Toast.makeText(this, "触发滚动回调", Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 在按下动作时被调用
     */
    @Override
    public boolean onDown(MotionEvent e) {
        Toast.makeText(this, "按下回调", Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 按住时被调用
     */
    @Override
    public void onShowPress(MotionEvent e) {
        Toast.makeText(this, "按住不松回调", Toast.LENGTH_SHORT).show();
    }

    /**
     * 抬起时被调用
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Toast.makeText(this, "触发抬起回调", Toast.LENGTH_SHORT).show();
        return false;
    }


    /*
 * 双击屏幕监听器OnDoubleTapListener
让我们的Activity去现实此接口，并重写onDoubleTap、onDoubleTapEvent、onSingleTapConfirmed方法。示范代码如下：
*/
    /*
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

*/

    @Override
    public boolean onDoubleTap(MotionEvent arg0) {
        Toast.makeText(this, "触发双击回调", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent arg0) {
        Toast.makeText(this, "触发双击的按下跟抬起回调", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent arg0) {
        Toast.makeText(this, "触发单击确认回调", Toast.LENGTH_SHORT).show();
        return false;
    }



}
