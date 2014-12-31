package com.hromadske.tv.ck.adapters;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.db.HromContentProvider;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

import static com.hromadske.tv.ck.utils.SystemUtils.IMAGELOADER;

/**
 * Created by cheb on 31.12.2014.
 */
public class EntityCursorAdapter extends CursorAdapter {
    private static final String TAG = EntityCursorAdapter.class.getSimpleName();

    public EntityCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_entity, parent, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.txtName = (TextView)view.findViewById(R.id.txt_title);
        viewHolder.imgIcon = (ImageView)view.findViewById(R.id.img_icon);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        String title = cursor.getString(cursor.getColumnIndex(HromContentProvider._TITLE));
        viewHolder.txtName.setText(title);
        String image = cursor.getString(cursor.getColumnIndex(HromContentProvider._IMAGE));
        ImageAware imageAware = new ImageViewAware(viewHolder.imgIcon);
        IMAGELOADER.displayImage(image, imageAware);
    }

    static class ViewHolder {
        TextView txtName;
        ImageView imgIcon;
    }
}
