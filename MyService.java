package dehant.rayan.tpfinal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyService {
    @GET("https://restcountries.eu/rest/v2/all") Call<List<Country>> getCountries();
    @GET("https://restcountries.eu/rest/v2/{name}")
    Call<List<Country>> getCountriesFromCategory(@Path("name") String name);
}
