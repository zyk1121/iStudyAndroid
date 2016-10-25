package com.example.zhangyuanke.istudyandroid.FCFragment;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhangyuanke.istudyandroid.LogUtil;
import com.example.zhangyuanke.istudyandroid.R;

public class FCFragmentRightFragment extends Fragment {


    public FCFragmentRightFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 当碎片和活动建立关联的时候调用
        LogUtil.d("puny","FCFragmentRightFragment:onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("puny","FCFragmentRightFragment:onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LogUtil.d("puny","FCFragmentRightFragment:onCreateView");
        return inflater.inflate(R.layout.fragment_fcfragment_right, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.d("puny","FCFragmentRightFragment:onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.d("puny","FCFragmentRightFragment:onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d("puny","FCFragmentRightFragment:onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.d("puny","FCFragmentRightFragment:onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.d("puny","FCFragmentRightFragment:onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.d("puny","FCFragmentRightFragment:onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("puny","FCFragmentRightFragment:onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.d("puny","FCFragmentRightFragment:onDetach");
    }
}
