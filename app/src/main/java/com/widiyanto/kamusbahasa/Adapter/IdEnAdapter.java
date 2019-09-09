package com.widiyanto.kamusbahasa.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.widiyanto.kamusbahasa.Model.KamusModelIE;
import com.widiyanto.kamusbahasa.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class IdEnAdapter extends RecyclerView.Adapter<IdEnAdapter.IdEnHolder>{
    private ArrayList<KamusModelIE> mData = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;

    public IdEnAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void clearData(){
        mData.clear();
        notifyDataSetChanged();
    }

    @Override
    public IdEnHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kamus, parent,false);
        return new IdEnHolder(view);
    }

    public void addItem(ArrayList<KamusModelIE> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(IdEnHolder holder, int position) {
        Log.e("mData IdEnAdapter",mData.get(position).getText());
        holder.textView.setText(mData.get(position).getText());
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class IdEnHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public IdEnHolder(View itemView) {
            super(itemView);

            textView = (TextView)itemView.findViewById(R.id.txt_kamus);
        }
    }
}
