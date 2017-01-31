package com.example.nytimes;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Surya on 1/28/2017.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<DataModel> dataList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView section, url, title, abstract_desc, byline, published_date;

        public MyViewHolder(View itemView) {
            super(itemView);
            section = (TextView) itemView.findViewById(R.id.section);
            url = (TextView) itemView.findViewById(R.id.url);
            title = (TextView) itemView.findViewById(R.id.title);
            abstract_desc = (TextView) itemView.findViewById(R.id.abstract_desc);
            byline = (TextView) itemView.findViewById(R.id.byline);
            published_date = (TextView) itemView.findViewById(R.id.published_date);
        }
    }

    public void setDataList(ArrayList<DataModel> dataList){
        this.dataList = dataList;
        notifyItemRangeChanged(0, dataList.size());
    }

    public MyAdapter(List<DataModel> dataList){
        this.dataList = dataList;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_layout, parent, false);

        return new MyViewHolder(view);
    }


    public static ArrayList<DataModel> parsejson(JSONObject response) throws JSONException{
        ArrayList<DataModel> data=new ArrayList<>();
        JSONArray jsonarray=response.getJSONArray("results");
        for(int i=0;i<jsonarray.length();i++){
            JSONObject json_data = jsonarray.getJSONObject(i);
            DataModel modelData = new DataModel();
            modelData.section= json_data.getString("section");
            modelData.title= json_data.getString("title");
            modelData.abstract_desc= json_data.getString("abstract");
            modelData.byline= json_data.getString("byline");
            modelData.published_date= json_data.getString("published_date");
            modelData.url= json_data.getString("url");
            data.add(modelData);
        }
        return data;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        final DataModel dataModel = dataList.get(position);
        holder.section.setText("Section : " + dataModel.getSection());
        final String url = dataModel.getUrl();
        holder.url.setText(url);
        holder.title.setText(dataModel.getTitle());
        holder.abstract_desc.setText(dataModel.getAbstract_desc());
        holder.byline.setText(dataModel.getByline());
        holder.published_date.setText("Published Date : "+dataModel.getPublished_date());
        holder.url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = dataModel.getUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
