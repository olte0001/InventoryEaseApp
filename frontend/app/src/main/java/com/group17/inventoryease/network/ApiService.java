package com.group17.inventoryease.network;

 /*
 * The ApiService defines the API endpoints between the frontend and backend
 * Source: https://medium.com/@erdi.koc/retrofit-and-okhttp-675d34eb7458
 * */

import com.group17.inventoryease.dtos.CompanyIdRequest;
import com.group17.inventoryease.dtos.CompanyIdResponse;
import com.group17.inventoryease.dtos.ReceiveItemDTO;
import com.group17.inventoryease.dtos.LoginRequest;
import com.group17.inventoryease.dtos.LoginResponse;
import com.group17.inventoryease.dtos.ProductDTO;
import com.group17.inventoryease.dtos.LocationDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/ums/validate-company-identifier")
    Call<CompanyIdResponse> validateCompanyId(@Body CompanyIdRequest request);

    @POST("/ums/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("/inventory/products")
    Call<List<ProductDTO>> getAllProductsWithSuppliers();

    @POST("/inventory/receive")
    Call<Void> receiveItem(@Body ReceiveItemDTO item);
}
