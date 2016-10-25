package com.example.zhangyuanke.istudyandroid.FCFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhangyuanke.istudyandroid.LogUtil;
import com.example.zhangyuanke.istudyandroid.R;

public class FCFragmentLeftFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 当碎片和活动建立关联的时候调用
        LogUtil.d("puny","FCFragmentLeftFragment:onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("puny","FCFragmentLeftFragment:onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LogUtil.d("puny","FCFragmentLeftFragment:onCreateView");
        return inflater.inflate(R.layout.fragment_fcfragment_left, container, false);
    }
    public void test()
    {
        // 获取当前碎片相关联的活动的实例
        FCFragmentTestACtivity testACtivity = (FCFragmentTestACtivity)getActivity();
        // 通过Activity获取另一个关联的Fragment的实例
        FCFragmentRightFragment rightFragment = testACtivity.getRightFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.d("puny","FCFragmentLeftFragment:onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.d("puny","FCFragmentLeftFragment:onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d("puny","FCFragmentLeftFragment:onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.d("puny","FCFragmentLeftFragment:onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.d("puny","FCFragmentLeftFragment:onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.d("puny","FCFragmentLeftFragment:onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("puny","FCFragmentLeftFragment:onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.d("puny","FCFragmentLeftFragment:onDetach");
    }

}
