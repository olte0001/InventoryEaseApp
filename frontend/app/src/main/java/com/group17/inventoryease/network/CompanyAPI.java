package com.group17.inventoryease.network;

import com.group17.inventoryease.dtos.Company;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface CompanyAPI {

    @POST("/company")
    Call<Company> findCompanyById(@Body Company company);
}
