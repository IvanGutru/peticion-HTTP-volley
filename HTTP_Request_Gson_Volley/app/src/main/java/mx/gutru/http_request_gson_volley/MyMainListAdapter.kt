package mx.gutru.http_request_gson_volley

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import mx.gutru.http_request_gson_volley.models.MatchReferenceDto

class MyMainListAdapter(miContexto:Context, matches: List<MatchReferenceDto>) :BaseAdapter(){
    val contexto = miContexto
    val matchlist= matches

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(contexto)
        val row = layoutInflater.inflate(R.layout.mainlist_inflatter, parent, false)
        val gameId = row.findViewById<TextView>(R.id.gameId)
        val role = row.findViewById<TextView>(R.id.role)
        val champion = row.findViewById<TextView>(R.id.champion)

        Log.d("ListAdapter", "Total matches: "+ matchlist.size)

        gameId.text = matchlist.get(position).gameId.toString()
        role.text = matchlist.get(position).role
        champion.text = matchlist.get(position).champion.toString()
        return  row

    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return matchlist.size
    }
}