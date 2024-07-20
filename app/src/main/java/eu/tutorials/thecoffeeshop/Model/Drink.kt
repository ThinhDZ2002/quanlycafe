package eu.tutorials.thecoffeeshop.Model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Drink (
    val Name: String,
    val Price: Int,
    val Description: String,
    val Recipe: String,
    val ImageURL: Image
) : Parcelable

@Parcelize
data class Image(
    val url: String
) : Parcelable