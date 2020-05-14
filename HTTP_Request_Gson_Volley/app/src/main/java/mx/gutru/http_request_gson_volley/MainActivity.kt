package mx.gutru.http_request_gson_volley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import mx.gutru.http_request_gson_volley.models.MatchlistDto
import mx.gutru.http_request_gson_volley.models.Summoner

class MainActivity : AppCompatActivity() {

    private val gson = Gson()

    lateinit var  textView: TextView
    lateinit var  listView: ListView

    lateinit var mySummoner: Summoner
    lateinit var myMatchlistDto: MatchlistDto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://la1.api.riotgames.com/lol/summoner/v4/summoners/by-name/frey?api_key=RGAPI-14681261-7497-463f-890b-29d96b1cebf7"
        textView = findViewById(R.id.textView)
        listView = findViewById(R.id.main_listView)

        val queue = Volley.newRequestQueue(this)

        val mapHeaders: MutableMap<String, String> = mutableMapOf()

        val resquestSummoner: GsonRequest<Summoner> = GsonRequest<Summoner>(
            url,
            Summoner::class.java,
            mapHeaders,
            mySummonerRequestListener(),
            myRequestErrorListener()
        )

        //Access the requestQueue through your singleton class
        VolleySingleton.getInstance(this).addToRequestQueue(resquestSummoner)
    }


    private fun mySummonerRequestListener():Response.Listener<Summoner>{
        return Response.Listener<Summoner>{response ->
            Log.d("Summoner", "Success"+ response.name+ ", AccountId: "+ response.accountId)

            mySummoner = response
            textView.text = response.name
            val url = "https://la1.api.riotgames.com/lol/match/v4/matchlists/by-account/-m_H9cT7iBenEPhUPrJd1BbbxRamnpbc5Jw8A8TKMZClkQw"
            val mapHeaders: MutableMap<String,String> = mutableMapOf("X-Riot-Token" to "RGAPI-7a6e9fc8-bbfb-4ba3-af75-ba3f71d69b94")
            val requesMatchlist: GsonRequest<MatchlistDto> = GsonRequest(
                url,
                MatchlistDto::class.java,
                mapHeaders,
                myMatchListRequestListener(),
                myRequestErrorListener()
            )
            VolleySingleton.getInstance(this).addToRequestQueue(requesMatchlist)
        }
    }
    private fun myMatchListRequestListener():Response.Listener<MatchlistDto>{
        return Response.Listener<MatchlistDto> {response ->
            Log.d("Summoner", "Total Games: "+ response.totalGames)
            myMatchlistDto = response
            listView.adapter = MyMainListAdapter(this, myMatchlistDto.matches)
        }
    }
    private fun myRequestErrorListener():Response.ErrorListener{
        return Response.ErrorListener { error ->
            Log.e("Error", error.toString())
        }
    }
}
