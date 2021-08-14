package com.example.moviesapp

import com.google.gson.annotations.SerializedName

data class Example (
    @SerializedName("average_rating") var averageRating : Double,
    @SerializedName("backdrop_path") var backdropPath : String,
    //@SerializedName("comments") var comments : Comments,
    //@SerializedName("created_by") var createdBy : CreatedBy,
    @SerializedName("description") var description : String,
    @SerializedName("id") var id : Int,
    @SerializedName("iso_3166_1") var iso31661 : String,
    @SerializedName("iso_639_1") var iso6391 : String,
    @SerializedName("name") var name : String,
    //@SerializedName("object_ids") var objectIds : ObjectIds,
    @SerializedName("page") var page : Int,
    @SerializedName("poster_path") var posterPath : String,
    @SerializedName("public") var public : Boolean,
    @SerializedName("results") var results : List<Results>,
    @SerializedName("revenue") var revenue : Long,
    @SerializedName("runtime") var runtime : Int,
    @SerializedName("sort_by") var sortBy : String,
    @SerializedName("total_pages") var totalPages : Int,
    @SerializedName("total_results") var totalResults : Int
)