package io.github.roelvanendhoven.zrbarcodescanner;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by roelvanendhoven on 2/23/17.
 */

public interface RefineryApiService {

    @GET("permits/{barcode}")
    Call<Permit> getPermit(@Path("barcode") String permit);

    @GET("employees/{barcode}")
    Call<Employee> getPermit(@Path("barcode") String employee);
}
