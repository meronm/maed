package com.example.atsi.maed;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Customer_Adapter extends ArrayAdapter<product_list> {
    int groupid;
    ArrayList<product_list> records;
    Context context;
    public Customer_Adapter(Context context,int vg ,int id, ArrayList<product_list> records)
    {
        super(context,vg,id,records);
        this.context=context;
        groupid=vg;
        this.records=records;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=inflater.inflate(groupid,parent,false);
        TextView textName=(TextView)itemView.findViewById(R.id.p_name);
        textName.setText(records.get(position).getproName());
        TextView textPrice=(TextView)itemView.findViewById(R.id.p_uprice);
        textPrice.setText(records.get(position).getunitPrice()+"$");
        TextView mId=(TextView)itemView.findViewById(R.id.m_id);
        mId.setText(records.get(position).getId());
        return itemView;
    }

}
