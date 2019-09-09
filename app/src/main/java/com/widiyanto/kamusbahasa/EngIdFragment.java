package com.widiyanto.kamusbahasa;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.widiyanto.kamusbahasa.Adapter.EnIdAdapter;
import com.widiyanto.kamusbahasa.Database.KamusHelper;
import com.widiyanto.kamusbahasa.Model.KamusModelEI;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EngIdFragment extends Fragment {
    RecyclerView rvEnId;
    EnIdAdapter adapter;
    KamusHelper kamusHelper;


    EditText edtSearch;
    Button btnSearch;
    static final String EXTRAS_SEARCH = "SEARCH";

    Context context;

    public EngIdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eng_id, container, false);

        context = container.getContext();

        rvEnId = (RecyclerView)view.findViewById(R.id.rv_en_id);
        kamusHelper = new KamusHelper(this.getActivity());

        edtSearch = (EditText)view.findViewById(R.id.edt_en_id);
        btnSearch = (Button)view.findViewById(R.id.btn_en_id);
        btnSearch.setOnClickListener(myListener);

        showRecyclerListAll();
        return view;
    }

    private void showRecyclerListSearch(String text) {
        rvEnId.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new EnIdAdapter(this.getActivity());
        rvEnId.setAdapter(adapter);

        kamusHelper.open();
        final ArrayList<KamusModelEI> kamusModels =  kamusHelper.getDataEIByText(text);
        kamusHelper.close();
        adapter.addItem(kamusModels);

        ItemClickSupport.addTo(rvEnId).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedItem(kamusModels.get(position));
            }
        });
    }

    private void showRecyclerListAll() {
        rvEnId.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new EnIdAdapter(this.getActivity());
        rvEnId.setAdapter(adapter);

        kamusHelper.open();
        final ArrayList<KamusModelEI> kamusModels =  kamusHelper.getAllDataEI();
        kamusHelper.close();
        adapter.addItem(kamusModels);

        ItemClickSupport.addTo(rvEnId).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedItem(kamusModels.get(position));
            }
        });
    }

    private void showSelectedItem(KamusModelEI model){
        Intent detailText = new Intent(getView().getContext(), DetailActivity.class);
        detailText.putExtra(DetailActivity.Text, model.getText());
        detailText.putExtra(DetailActivity.Detail, model.getDetail());
        getView().getContext().startActivity(detailText);

    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_en_id:
                    adapter.clearData();
                    String text = edtSearch.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString(EXTRAS_SEARCH,text);
                    if (TextUtils.isEmpty(text)) showRecyclerListAll();
                    else {
                        showRecyclerListSearch(text);
                    }
                    break;
            }
        }
    };

}
