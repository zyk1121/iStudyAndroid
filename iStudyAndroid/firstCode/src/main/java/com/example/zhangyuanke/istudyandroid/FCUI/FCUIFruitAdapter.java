package com.example.zhangyuanke.istudyandroid.FCUI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        final FCUIFruit fruit = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView)view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);
            viewHolder.checkBox = (CheckBox)view.findViewById(R.id.fruit_checkbox);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("puny",""+position + fruit.selected);
                fruit.selected = !fruit.selected;
            }
        });

//        ImageView fruitImage = (ImageView)view.findViewById(R.id.fruit_image);
//        TextView fruitName = (TextView) view.findViewById(R.id.fruit_name);
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        viewHolder.checkBox.setChecked(fruit.selected);

        return view;
    }
    class ViewHolder
    {
        ImageView fruitImage;
        TextView fruitName;
        CheckBox checkBox;
    }
}
