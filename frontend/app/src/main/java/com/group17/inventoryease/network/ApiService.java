package com.group17.inventoryease.network;

 /*
 * The ApiService defines the API endpoints between the frontend and backend
 * Source: https://medium.com/@erdi.koc/retrofit-and-okhttp-675d34eb7458
 * */

import com.group17.inventoryease.dtos.CompanyIdRequest;
import com.group17.inventoryease.dtos.CompanyIdResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/ums/validate-company-identifier")
    Call<CompanyIdResponse> validateCompanyId(@Body CompanyIdRequest request);
}
