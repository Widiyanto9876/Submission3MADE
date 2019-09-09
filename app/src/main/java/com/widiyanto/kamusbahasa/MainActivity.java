package com.widiyanto.kamusbahasa;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.widiyanto.kamusbahasa.Database.KamusHelper;
import com.widiyanto.kamusbahasa.Model.KamusModelEI;
import com.widiyanto.kamusbahasa.Model.KamusModelIE;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.progress_bar);

        new LoadData().execute();
    }


    private class LoadData extends AsyncTask<Void, Integer, Void> {
        final String TAG = LoadData.class.getSimpleName();
        KamusHelper kamusHelper;
        AppPreference appPreference;
        double progress;
        double maxprogress = 100;

        @Override
        protected void onPreExecute(){
            kamusHelper = new KamusHelper(MainActivity.this);
            appPreference = new AppPreference(MainActivity.this);
        }

        @Override
        protected Void doInBackground(Void... params) {

            Boolean firstRun = appPreference.getFirstRun();

            if (firstRun){
                ArrayList<KamusModelEI> kamusModelsEI = preLoadRawEI();
                ArrayList<KamusModelIE> kamusModelsIE = preLoadRawIE();

                kamusHelper.open();

                progress = 30;
                publishProgress((int) progress);
                Double progressMasInsert = 80.0;
                Double progressDiff = (progressMasInsert - progress)/ kamusModelsEI.size()+kamusModelsIE.size();

                //Mulai Transaction
                kamusHelper.beginTransaction();

                try {
                    for (KamusModelEI model : kamusModelsEI){
                        kamusHelper.insertTransactionEI(model);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }

                    for (KamusModelIE model : kamusModelsIE){
                        kamusHelper.insertTransactionIE(model);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }
                    //jika semua proses telah di set success maka akan si commit ke database
                    kamusHelper.setTransactionSuccess();
                }catch (Exception e){
                    Log.e(TAG, "doInBackground: Exception");
                }

                kamusHelper.endTransaction();
                kamusHelper.close();
                //Selesai transaction

                appPreference.setFirstRun(false);
                publishProgress((int)maxprogress);
            } else {
                try {
                    synchronized (this){
                        this.wait(2000);

                        publishProgress(50);

                        this.wait(2000);
                        publishProgress((int) maxprogress);
                    }
                } catch (Exception e) {
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent i = new Intent(MainActivity.this, KamusActivity.class);
            startActivity(i);
            finish();
        }
    }

    public ArrayList<KamusModelEI> preLoadRawEI() {
        ArrayList<KamusModelEI> kamusModels = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.english_indonesia);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                KamusModelEI kamusModelEI;

                kamusModelEI = new KamusModelEI(splitstr[0], splitstr[1]);
                kamusModels.add(kamusModelEI);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kamusModels;
    }

    public ArrayList<KamusModelIE> preLoadRawIE() {
        ArrayList<KamusModelIE> kamusModels = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.indonesia_english);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                KamusModelIE kamusModelIE;

                kamusModelIE = new KamusModelIE(splitstr[0], splitstr[1]);
                kamusModels.add(kamusModelIE);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kamusModels;
    }

}
