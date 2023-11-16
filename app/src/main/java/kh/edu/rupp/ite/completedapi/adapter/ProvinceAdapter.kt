package kh.edu.rupp.ite.completedapi.view.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.completedapi.api.model.Province
import kh.edu.rupp.ite.completedapi.databinding.ViewHolderProvinceBinding

class ProvinceAdapter(): ListAdapter<Province, ProvinceAdapter.ProvinceViewHolder>(
    object : DiffUtil.ItemCallback<Province>() {
        override fun areItemsTheSame(oldItem: Province, newItem: Province): Boolean {
            return oldItem == newItem;
        }

        override fun areContentsTheSame(oldItem: Province, newItem: Province): Boolean {
            return oldItem == newItem
        }

    }
) {

    var onProvinceClickListener: ((Int, Province) -> Unit)? = null;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        val binding = ViewHolderProvinceBinding.inflate(layoutInflater, parent, false);
        val viewHolder = ProvinceViewHolder(binding);
        return viewHolder;
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        val item = getItem(position);
        holder.bind(item);

        holder.itemView.setOnClickListener {
            onProvinceClickListener?.invoke(position, item)
        }
    }


//    View Holder
    class ProvinceViewHolder(val itemBinding: ViewHolderProvinceBinding): RecyclerView.ViewHolder(itemBinding.root){
        public fun bind(province: Province){
            Picasso.get().load(province.imageUrl).into(itemBinding.imgProvice);
            itemBinding.txtName.text = province.name;
        }
    }
}