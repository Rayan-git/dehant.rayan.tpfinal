package dehant.rayan.tpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String URL="https://restcountries.eu/";
    private ArrayList<Country> countries=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Menu principal");
        Adapter adapter=new Adapter(this,countries);
        ListView listView =this.findViewById(R.id.list);
        listView.setAdapter(adapter);


        callWithAsyncTask();
        //callWithRetrofit();
        for(int i=0;i<countries.size();i++){
            System.out.println(countries.get(i).getNom());
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country selected=(Country) parent.getItemAtPosition(position);
                Intent intent=new Intent(MainActivity.this,Detail.class);
                intent.putExtra("personne",selected);
                startActivity(intent);
            }
        });

    }

    private void callWithAsyncTask(){
        String myUrl = URL + "/rest/v2/all";
        String result;
        Requete getRequest = new Requete();
        try{
            result = getRequest.execute(myUrl).get();
        }catch (Exception e){
            Log.e("Test","error in request " + e.getLocalizedMessage());
        }
    }


    public void callWithRetrofit(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        MyService service=retrofit.create(MyService.class);
        final Call<List<Country>> listCountries=service.getCountries();
        listCountries.enqueue(new Callback<List<Country>>(){
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                countries.addAll(response.body());
                Log.d("Call","on a retrouvé " + countries.size() + " produits");
            }
            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) { System.out.println("erreur"+t.toString());}
        });

        Call<List<Country>> listCountriesFromCat = service.getCountriesFromCategory("all");
        listCountriesFromCat.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                Log.d("Call","Réponse requête produits de catégorie : " + response.toString());
            }
            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) { System.out.println("erreur"+t.toString());}
        });
    }
}
