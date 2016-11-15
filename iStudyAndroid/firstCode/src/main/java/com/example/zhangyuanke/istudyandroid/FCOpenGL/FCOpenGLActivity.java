package com.example.zhangyuanke.istudyandroid.FCOpenGL;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLES10;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.LogUtil;
import com.example.zhangyuanke.istudyandroid.R;

import java.nio.Buffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL10;

// http://blog.csdn.net/yanzi1225627/article/details/30096181

public class FCOpenGLActivity extends BaseActivity {

    GLSurfaceView glSurfaceView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        glSurfaceView = new MyGLSurfaceView(this);

        setContentView(glSurfaceView);
    }

    class MyGLSurfaceView extends GLSurfaceView
    {

        public MyGLSurfaceView(Context context)
        {
            super(context);

            try
            {
                // Create an OpenGL ES 1.0 context
                setEGLContextClientVersion(1);

                // Set the Renderer for drawing on the GLSurfaceView
                setRenderer(new MyRenderer());

                // Render the view only when there is a change in the drawing
                // data
                setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

                // 注意上面语句的顺序，反了可能会出错

            }
            catch (Exception e)
            {
                e.printStackTrace();

            }

        }
    }

    public class MyRenderer implements GLSurfaceView.Renderer
    {

        public void onSurfaceCreated(GL10 unused, EGLConfig config)
        {

            // Set the background frame color
//            GLES10.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
//            GLES10.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
            GL10 gl = unused;
            gl.glShadeModel(GL10.GL_SMOOTH);
            gl.glClearColor(1.0f, 1.0f, 1.0f, 0f);
            gl.glClearDepthf(1.0f);
            gl.glEnable(GL10.GL_DEPTH_TEST);
            gl.glDepthFunc(GL10.GL_LEQUAL);
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

            mTriangleBuffer = BufferUtil.floatToBuffer(mTriangleArray);
            mColorBuffer = BufferUtil.floatToBuffer(mColorArray);
            quateBuffer = BufferUtil.floatToBuffer(mQuateArray);
        }

        private float[] mTriangleArray = {
                0f,1f,0f,
                -1f,-1f,0f,
                1f,-1f,0f
        };
        private FloatBuffer mTriangleBuffer;


        private float[] mColorArray={
                1f,0f,0f,1f,     //红
                0f,1f,0f,1f,     //绿
                0f,0f,1f,1f      //蓝
        };
        private FloatBuffer mColorBuffer;

        //正方形的四个顶点
        private FloatBuffer quateBuffer ;
        private float[] mQuateArray = {
                -1f, -1f, 0f,
                1f, -1f, 0f,
                -1f, 1f, 0f,
                1f, 1f, 0f,
        };
        public void onDrawFrame(GL10 unused)
        {
            // Redraw background color
//            GLES10.glClear(GLES10.GL_COLOR_BUFFER_BIT);
            GL10 gl = unused;
            // TODO Auto-generated method stub
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
            //使用数组作为颜色
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);

            //绘制小三角形
            gl.glLoadIdentity();
            gl.glTranslatef(-1.5f, 0.0f, -6.0f);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTriangleBuffer);//数组指向三角形顶点buffer
            gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
//      gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
            gl.glFinish();

            //绘制正方形
            gl.glLoadIdentity();
            gl.glTranslatef(1.5f, 0.0f, -6.0f);
//      gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, quateBuffer);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
            gl.glFinish();

            LogUtil.d("puny","ondrawframe");

        }

        public void onSurfaceChanged(GL10 unused, int width, int height)
        {
//            GLES10.glViewport(0, 0, width, height);
            GL10 gl = unused;
            gl.glViewport(0, 0, width, height);

            float ratio = (float) width / height;
            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();
            gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
        }
    }

//    class MyGLSurfaceView extends GLSurfaceView
//    {
//
//        public MyGLSurfaceView(Context context)
//        {
//            super(context);
//
//            try
//            {
//                // Create an OpenGL ES 2.0 context
//                setEGLContextClientVersion(2);
//
//                // Set the Renderer for drawing on the GLSurfaceView
//                setRenderer(new MyRenderer());
//
//                // Render the view only when there is a change in the drawing
//                // data
//                setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
//
//                // 注意上面语句的顺序，反了可能会出错
//
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//
//            }
//
//        }
//    }
//
//    public class MyRenderer implements GLSurfaceView.Renderer
//    {
//
//        public void onSurfaceCreated(GL10 unused, EGLConfig config)
//        {
//            // Set the background frame color
//            GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
//        }
//
//        public void onDrawFrame(GL10 unused)
//        {
//            // Redraw background color
//            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
//        }
//
//        public void onSurfaceChanged(GL10 unused, int width, int height)
//        {
//            GLES20.glViewport(0, 0, width, height);
//        }
//    }
}
