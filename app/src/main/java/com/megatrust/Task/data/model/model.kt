package com.megatrust.Task.data.model

import com.google.gson.annotations.SerializedName

class model {

    data class Response(

        @field:SerializedName("Response")
        val response: List<ResponseItem?>? = null
    )

    data class ResponseItem(

        @field:SerializedName("company_logo")
        var companyLogo: String? = "",

        @field:SerializedName("how_to_apply")
        var howToApply: String? = "",

        @field:SerializedName("created_at")
        var createdAt: String? = "",

        @field:SerializedName("description")
        var description: String? = "",

        @field:SerializedName("company")
        var company: String? = "",

        @field:SerializedName("company_url")
        var companyUrl: String? = "",

        @field:SerializedName("location")
        var location: String? = "",

        @field:SerializedName("id")
        var id: String? = "",

        @field:SerializedName("type")
        var type: String? = "",

        @field:SerializedName("title")
        var title: String? = "",

        @field:SerializedName("url")
        var url: String? = ""
    )

}