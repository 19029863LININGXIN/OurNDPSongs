package sg.edu.rp.c346.id19029863.ourndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Song> al;

    public CustomAdapter(Context context, int resource, ArrayList<Song> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        al = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvYear = rowView.findViewById(R.id.tvYear);
        TextView tvSinger = rowView.findViewById(R.id.tvSinger);
        RatingBar rb = rowView.findViewById(R.id.ratingBar);
        ImageView iv = rowView.findViewById(R.id.iv);

        Song currentVersion = al.get(position);

        int year = currentVersion.getYear();
        tvTitle.setText(currentVersion.getTitle());
        tvYear.setText(String.valueOf(currentVersion.getYear()));
        if(year < 2019)
        {
            iv.setVisibility(View.INVISIBLE);
        }

        tvSinger.setText(currentVersion.getSingers());
        rb.setRating(currentVersion.getStars());

        return rowView;
    }

}