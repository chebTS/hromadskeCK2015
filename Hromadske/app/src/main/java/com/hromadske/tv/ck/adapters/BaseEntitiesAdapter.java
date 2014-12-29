package com.hromadske.tv.ck.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.entities.BaseEntity;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

import java.util.List;

import static com.hromadske.tv.ck.utils.SystemUtils.IMAGELOADER;

/**
 * Created by cheb on 28.12.2014.
 */
public class BaseEntitiesAdapter extends ArrayAdapter<BaseEntity> {
    private static final String TAG = BaseEntitiesAdapter.class.getSimpleName();
    private LayoutInflater inflater;

    public BaseEntitiesAdapter(Context context, int resource, List<BaseEntity> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_entity, parent, false);
            holder = new ViewHolder();
            holder.txtName = (TextView)convertView.findViewById(R.id.txt_title);
            holder.imgIcon = (ImageView)convertView.findViewById(R.id.img_icon);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        BaseEntity entity = getItem(position);
        holder.txtName.setText(entity.getTitle());
        ImageAware imageAware = new ImageViewAware(holder.imgIcon);
        IMAGELOADER.displayImage(entity.getImage(), imageAware);
        return convertView;
    }

    @Override
    public BaseEntity getItem(int position) {
        return super.getItem(super.getCount() - position - 1);
    }

    static class ViewHolder {
        TextView txtName;
        ImageView imgIcon;
    }
}
