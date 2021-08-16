package com.example.moviesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies_table")
class Movie (
    @ColumnInfo(name = "adult") @SerializedName("adult") var adult : Boolean,
    @ColumnInfo(name = "backdrop_path") @SerializedName("backdrop_path") var backdropPath : String,
    //@ColumnInfo(name = "genre_ids") @SerializedName("genre_ids") var genreIds : ArrayList<Int>,
    @PrimaryKey(autoGenerate = false) @SerializedName("id") var id : Int,
    @ColumnInfo(name = "media_type") @SerializedName("media_type") var mediaType : String,
    @ColumnInfo(name = "original_language") @SerializedName("original_language") var originalLanguage : String,
    @ColumnInfo(name = "original_title") @SerializedName("original_title") var originalTitle : String,
    @ColumnInfo(name = "overview") @SerializedName("overview") var overview : String,
    @ColumnInfo(name = "popularity") @SerializedName("popularity") var popularity : Double,
    @ColumnInfo(name = "poster_path") @SerializedName("poster_path") var posterPath : String,
    @ColumnInfo(name = "release_date") @SerializedName("release_date") var releaseDate : String,
    @ColumnInfo(name = "title") @SerializedName("title") var title : String,
    @ColumnInfo(name = "video") @SerializedName("video") var video : Boolean,
    @ColumnInfo(name = "vote_average") @SerializedName("vote_average") var voteAverage : Double,
    @ColumnInfo(name = "vote_count") @SerializedName("vote_count") var voteCount : Int
)
