package mx.gutru.myfirst_json_with_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);
        Button buttonObtener = findViewById(R.id.button_obtener);

        requestQueue = Volley.newRequestQueue(this);


        buttonObtener.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                jsonParse();

            }
        });
    }

    private void jsonParse(){
        String url = "https://api.androidhive.info/contacts/";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("contacts");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);
                                String name = employee.getString("name");
                                String mail = employee.getString("email");
                                textViewResult.append(name + ", " + ", " + mail + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }

                    }, new Response.ErrorListener(){
                        @Override
                                public void onErrorResponse(VolleyError error){
                            error.printStackTrace();

                        }
                });
        requestQueue.add(request);
    }
}

