package android.myapplication.mywallpaper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIutilities {
    private static Retrofit retrofit = null;
    public static final String Authkey="563492ad6f917000010000014811404778104455a5b1f72d84f8640d";
    public static APInterface getAPIinterface(){
        if(retrofit==null){
            retrofit= new Retrofit.Builder().baseUrl(APInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(APInterface.class);
    }
}
