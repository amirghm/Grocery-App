package com.amirghm.gerocery.ui.detail

import androidx.lifecycle.ViewModel
import com.amirghm.gerocery.data.model.catalog.CatalogProductModel

class ProductDetailsViewModel : ViewModel() {
    lateinit var productModel: CatalogProductModel
}