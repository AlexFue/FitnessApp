package com.example.fitnessapp;

import static com.example.fitnessapp.EquipmentList.getRetrofitResponse;
import static com.example.fitnessapp.EquipmentList.returnResponse;

import android.content.Context;
import android.widget.TextView;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EquipmentEndpointInstrumentedTest {
    @Test
    public void getResponseTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        TextView tv = new TextView(appContext);
        Call<EquipmentResponse> call = getRetrofitResponse();
        Boolean result = returnResponse(call, tv);
        assert(result);
    }
}
