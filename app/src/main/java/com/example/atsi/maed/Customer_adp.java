package com.example.atsi.maed;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Customer_adp extends ArrayAdapter<productList>
{
    int groupid;
    ArrayList<productList> records;
    Context context;
    public Customer_adp(Context context,int vg ,int id, ArrayList<productList> records)
    {
        super(context, id, vg,records);
        this.context=context;
        groupid=vg;
        this.records=records;
    }
    public View getView(int position,View convertView,ViewGroup parent)
    {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=inflater.inflate(groupid,parent,false);
        TextView textName=(TextView)itemView.findViewById(R.id.product_name);
        textName.setText(records.get(position).getItem_name());
        TextView textPrice=(TextView)itemView.findViewById(R.id.product_uprice);
        textPrice.setText(records.get(position).getItem_price()+"$");
        TextView text=(TextView)itemView.findViewById(R.id.c_id);
        text.setText(records.get(position).getCustomer_id()+"");
        TextView t=(TextView)itemView.findViewById(R.id.h_id);
        t.setText(records.get(position).getHotel_id()+"");
        TextView tx=(TextView)itemView.findViewById(R.id.me_id);
        tx.setText(records.get(position).getMenu_id()+"");

        return itemView;
    }

}