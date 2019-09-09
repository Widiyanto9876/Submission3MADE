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
import android.widget.Toast;

import com.widiyanto.kamusbahasa.Adapter.IdEnAdapter;
import com.widiyanto.kamusbahasa.Database.KamusHelper;
import com.widiyanto.kamusbahasa.Model.KamusModelIE;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class IdEngFragment extends Fragment {

    RecyclerView rvIdEn;
    IdEnAdapter adapter;
    KamusHelper kamusHelper;
    EditText edtCari;
    Button btnCari;
    static final String EXTRAS_CARI = "CARI";

    Context context;


    public IdEngFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_id_eng, container, false);

        context = container.getContext();

        rvIdEn = (RecyclerView)view.findViewById(R.id.rv_id_en);
        kamusHelper = new KamusHelper(this.getActivity());

        edtCari = (EditText)view.findViewById(R.id.edt_id_en);
        btnCari = (Button)view.findViewById(R.id.btn_id_en);
        btnCari.setOnClickListener(myListener);

        showRecyclerListAll();
        return view;
    }

    private void showRecyclerListSearch(String text) {
        rvIdEn.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new IdEnAdapter(this.getActivity());
        rvIdEn.setAdapter(adapter);

        kamusHelper.open();
        final ArrayList<KamusModelIE> kamusModelsIE = kamusHelper.getDataIEByText(text);
        kamusHelper.close();
        adapter.addItem(kamusModelsIE);

        ItemClickSupport.addTo(rvIdEn).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedItem(kamusModelsIE.get(position));
            }
        });
    }

    private void showRecyclerListAll() {
        rvIdEn.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new IdEnAdapter(this.getActivity());
        rvIdEn.setAdapter(adapter);

        kamusHelper.open();
        final ArrayList<KamusModelIE> kamusModelsIE = kamusHelper.getAllDataIE();
        kamusHelper.close();
        adapter.addItem(kamusModelsIE);

        ItemClickSupport.addTo(rvIdEn).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedItem(kamusModelsIE.get(position));
            }
        });
    }



    private void showSelectedItem(KamusModelIE model){
        Intent detailText = new Intent(getView().getContext(), DetailActivity.class);
        detailText.putExtra(DetailActivity.Text, model.getText());
        detailText.putExtra(DetailActivity.Detail, model.getDetail());
        getView().getContext().startActivity(detailText);
        Toast.makeText(context, model.getText(), Toast.LENGTH_SHORT).show();

    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_id_en:
                    adapter.clearData();
                    String text = edtCari.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString(EXTRAS_CARI,text);
                    if (TextUtils.isEmpty(text)) showRecyclerListAll();
                    else {
                        showRecyclerListSearch(text);
                    }
                    break;
            }
        }
    };

}
