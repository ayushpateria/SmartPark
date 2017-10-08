package com.ayush.smartpark.ui.quote;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import com.ayush.smartpark.R;
import com.ayush.smartpark.model.Spot;

public class SpotListFragment extends ListFragment {

    private Callback callback = dummyCallback;
    private MyListAdapter myAdapter;

    /**
     * A callback interface. Called whenever a item has been selected.
     */
    public interface Callback {
        void onItemSelected(String id);
    }

    /**
     * A dummy no-op implementation of the Callback interface. Only used when no active Activity is present.
     */
    private static final Callback dummyCallback = new Callback() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myAdapter = new MyListAdapter();
        setListAdapter(myAdapter);
        setHasOptionsMenu(true);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // notify callback about the selected list item
        callback.onItemSelected(Spot.ITEMS.get(position).id);
    }

    /**
     * onAttach(Context) is not called on pre API 23 versions of Android.
     * onAttach(Activity) is deprecated but still necessary on older devices.
     */
    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    /**
     * Deprecated on API 23 but still necessary for pre API 23 devices.
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    /**
     * Called when the fragment attaches to the context
     */
    protected void onAttachToContext(Context context) {
        if (!(context instanceof Callback)) {
            throw new IllegalStateException("Activity must implement callback interface.");
        }

        callback = (Callback) context;
    }

    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Spot.ITEMS.size();
        }

        @Override
        public Object getItem(int position) {
            return Spot.ITEMS.get(position);
        }

        @Override
        public long getItemId(int position) {
            return Spot.ITEMS.get(position).id.hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_spots, container, false);
            }

            final Spot.SpotItem item = (Spot.SpotItem) getItem(position);
            ((TextView) convertView.findViewById(R.id.spot_title)).setText(item.title);
            String addr = item.addr;
            if (addr.length() > 50)
                    addr = addr.substring(0, 50)+"..";
            ((TextView) convertView.findViewById(R.id.spot_addr)).setText(addr);
            ((TextView) convertView.findViewById(R.id.spot_cost)).setText("₹" + item.cost);
            ((TextView) convertView.findViewById(R.id.spot_distance)).setText(item.distance + " Km");
            final ImageView img = (ImageView) convertView.findViewById(R.id.thumbnail);
            Glide.with(getActivity()).load(item.photoId).asBitmap().fitCenter().into(img);

            return convertView;
        }
    }

    public void refreshAdapter() {
        myAdapter.notifyDataSetChanged();
    }
    public SpotListFragment() {
    }
}