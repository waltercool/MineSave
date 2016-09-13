package cl.slash.minesave.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cl.slash.minesave.obj.domain.MineWorld;

/**
 * Created by waltercool on 9/13/16.
 */
public class WorldAdapter extends BaseAdapter {

    private List<MineWorld> items;
    private Context context;

    public WorldAdapter(Context context, List<MineWorld> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public MineWorld getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,
                    parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.getTv().setText(getItem(position).getWorldName());
        return convertView;
    }

    private class ViewHolder {
        TextView tv;

        ViewHolder(View parent) {
            tv = (TextView)parent.findViewById(android.R.id.text1);
        }

        public TextView getTv() {
            return tv;
        }
    }
}
