package ru.rzn.myasoedov.testavito.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.rzn.myasoedov.testavito.R;
import ru.rzn.myasoedov.testavito.dto.User;

/**
 * Created by grisha on 16.06.15.
 */
public class UsersAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<User> users;

    public UsersAdapter(Context context, List<User> users) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.users = new ArrayList<>(users);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public User getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return users.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            holder.text = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        User user = getItem(position);
        holder.text.setText(user.getLogin());
        if (!TextUtils.isEmpty(user.getPhotoUrl())) {
            Picasso.with(context).load(user.getPhotoUrl()).into(holder.imageView);
        }

        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView text;
    }

    public void addUsers(Collection<User> collection) {
        users.addAll(collection);
        notifyDataSetChanged();
    }

    public User getLastUser() {
        return getItem(getCount() - 1);
    }

    public List<User> getUsers() {
        return users;
    }
}
