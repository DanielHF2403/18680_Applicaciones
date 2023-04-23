package edu.iest.sqlite.interfaces

import edu.iest.sqlite.models.Anime

interface AnimeInterface {
    fun AnimeEliminado()
    fun editarAnime(anime: Anime)
}