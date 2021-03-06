package com.amirghm.grocery.ui.catalog.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amirghm.grocery.data.model.catalog.CatalogHeaderModel
import com.amirghm.grocery.data.model.catalog.CatalogModel
import com.amirghm.grocery.data.model.catalog.CatalogProductModel
import com.amirghm.grocery.ui.catalog.list.holders.CatalogHeaderViewHolder
import com.amirghm.grocery.ui.catalog.list.holders.ProductViewHolder


class CatalogAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data: ArrayList<CatalogModel> = ArrayList()

    fun updateData(items: List<CatalogModel>) {
        val diffResult = DiffUtil.calculateDiff(CatalogDiffUtilCallback(this.data, items))
        data.clear()
        data.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is CatalogProductModel -> ViewType.TYPE_PRODUCT
            else -> ViewType.TYPE_HEADER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        ViewType.TYPE_HEADER -> CatalogHeaderViewHolder.create(parent)
        else -> ProductViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CatalogHeaderViewHolder -> holder.bind(data[position] as CatalogHeaderModel)
            is ProductViewHolder -> holder.bind(data[position] as CatalogProductModel)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @Target(AnnotationTarget.TYPE, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
    @Retention(AnnotationRetention.RUNTIME)
    internal annotation class ViewType {
        companion object {
            const val TYPE_PRODUCT = 1
            const val TYPE_HEADER = 0
        }
    }

}