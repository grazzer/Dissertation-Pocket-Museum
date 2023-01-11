package com.example.nationalmuseum

import retrofit2.Response
import retrofit2.http.*


interface NatMuseumApi {

    @GET("/artefact/{ArtId}")
    suspend fun getArtefact (@Path("ArtId") artid: Int): Response<Artifact>

    @GET("/user/{userId}")
    suspend fun getUser (@Path("userId") userid: Int): Response<User>

    @GET("/collection/{userId}")
    suspend fun getCollection (@Path("userId") userid: Int): Response<List<Artifact>>

    @PATCH("/user/{userId}")
    suspend fun patchArtifact (@Path("userId") userid: Int, @Body artefactId: Artifact)

}