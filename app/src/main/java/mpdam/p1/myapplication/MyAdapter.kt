package mpdam.p1.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val historyList : ArrayList<String>):
    RecyclerView.Adapter<MyAdapter.ItemHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.historyitem,parent,false)
        return ItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val currentItem=historyList[position]
        holder.item.text = currentItem
    }
    class ItemHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        var item :TextView = itemView.findViewById(R.id.historyItem)

    }
}