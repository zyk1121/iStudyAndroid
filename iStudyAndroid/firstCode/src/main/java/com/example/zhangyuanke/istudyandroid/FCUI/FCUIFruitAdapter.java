package com.example.zhangyuanke.istudyandroid.FCUI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhangyuanke.istudyandroid.FCUI.FCUIFruit;
import com.example.zhangyuanke.istudyandroid.R;

import java.util.List;

/**
 * Created by zhangyuanke on 16/10/4.
 */
// 自定义适配器
public class FCUIFruitAdapter extends ArrayAdapter<FCUIFruit> {

    private int resourceId;
//    public FCUIFruitAdapter(Context context, int resource) {
//        super(context, resource);
//    }
    public FCUIFruitAdapter(Context context, int resourceId, List<FCUIFruit> objects)
    {
        super(context,resourceId,objects);
        this.resourceId = resourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FCUIFruit fruit = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        ImageView fruitImage = (ImageView)view.findViewById(R.id.fruit_image);
        TextView fruitName = (TextView) view.findViewById(R.id.fruit_name);
        fruitImage.setImageResource(fruit.getImageId());
        fruitName.setText(fruit.getName());

        return view;
    }
}
