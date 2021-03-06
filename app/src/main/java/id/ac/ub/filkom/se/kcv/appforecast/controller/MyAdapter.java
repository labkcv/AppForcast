package id.ac.ub.filkom.se.kcv.appforecast.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import id.ac.ub.filkom.se.kcv.appforecast.R;

/**
 * Created by selab on 04-Nov-16.
 */

public class MyAdapter extends ArrayAdapter<Kurs> {
    private final Context context;
    private final ArrayList<Kurs> itemsArrayList;

    public MyAdapter(Context context, ArrayList<Kurs> itemsArrayList) {

        super(context, R.layout.row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // 3. Get the two text view from the rowView
        TextView no = (TextView) rowView.findViewById(R.id.no);
        TextView hari = (TextView) rowView.findViewById(R.id.hari);
        TextView hasil = (TextView) rowView.findViewById(R.id.hasil);

        // 4. Set the text for textView
        no.setText(itemsArrayList.get(position).getNo());
        hari.setText(itemsArrayList.get(position).getHari());
        hasil.setText(itemsArrayList.get(position).getHasil());

        // 5. retrn rowView
        return rowView;
    }
}
