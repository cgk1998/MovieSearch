package edu.uchicago.gerber.favs.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieDB {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = ""
    @SerializedName("title")
    @Expose
    var title: String? = ""
    @SerializedName("year")
    @Expose
    var year: String? = ""
    @SerializedName("imdbID")
    @Expose
    var imdbID: String? = ""
    @SerializedName("poster")
    @Expose
    var poster: String? = ""

    @SerializedName("comment")
    @Expose
    var comment: String? = ""


}
