package com.example.fitnessapp;

/**
 * Abstract: tests to comment endpoint to display comments
 * Contributors: Alex
 */

import static com.example.fitnessapp.CommentList.getRetrofitResponse;
import static com.example.fitnessapp.EquipmentList.returnResponse;

import android.content.Context;
import android.widget.TextView;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class CommentEndpointInstrumentedTest {
    @Test
    public void getResponseTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        TextView tv = new TextView(appContext);
        Call<CommentResponse> call = getRetrofitResponse();
        Boolean result = CommentList.returnResponse(call, tv);
        assert(result);
    }

    @Test
    public void CommentResponseTest() {
        CommentResults results = new CommentResults();
        results.setComment("Keep it going!!!");

        List<CommentResults> resultsList = new ArrayList<>();
        resultsList.add(results);

        CommentResponse response = new CommentResponse();
        response.setResults(resultsList);

        assert(response.getResults().size() > 0);
        assert(response.getResults().get(0).getComment().equals("Keep it going!!!"));
    }
}
