package com.example.atsi.maed;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class hotelAdapter  extends BaseExpandableListAdapter {
    private Context cxt;
    private HashMap<String,List<String>> movies_categories;
    private List<String> movies_list;

    public hotelAdapter( Context cxt, HashMap<String,List<String>> movies_categories, List<String> movies_list)
    {
        this.cxt=cxt;
        this.movies_categories =movies_categories;
        this.movies_list=movies_list;

    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public View getChildView(int parent, int child, boolean LastChild, View convertView, ViewGroup parentView) {
        String child_title=(String) getChild(parent,child);
        if(convertView==null){
            LayoutInflater inflater =(LayoutInflater) cxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.child_layout,parentView,false);

        }
        TextView child_TextView=(TextView) convertView.findViewById(R.id.child_text);
        child_TextView.setText(child_title);
        return convertView;


    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View convertView, ViewGroup parentView) {
        String grouptitle=(String)getGroup(parent);
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)cxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =inflater.inflate(R.layout.parent_layout,parentView,false);
        }
        TextView parent_textView=(TextView) convertView.findViewById(R.id.parent_text);
        parent_textView.setTypeface(null, Typeface.BOLD);
        parent_textView.setText(grouptitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int parent, int child) {
        return  movies_categories.get(movies_list.get(parent)).get(child);
    }

    @Override
    public Object getGroup(int groupPosition) {
        return movies_list.get(groupPosition);
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return  movies_categories.get(movies_list.get(groupPosition)).size();
    }

    @Override
    public int getGroupCount() {
        return movies_list.size();
    }


}
