package edu.uchicago.gerber.favs.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Search {

    @SerializedName("Id")
    @Expose
    var id: String? = ""

    @SerializedName("Title")
    @Expose
    var title: String? = null

    @SerializedName("Year")
    @Expose
    var year: String? = null

    @SerializedName("imdbID")
    @Expose
    var imdbID: String? = null

    @SerializedName("Type")
    @Expose
    var type: String? = null

    @SerializedName("Poster")
    @Expose
    var poster: String? = null
}
