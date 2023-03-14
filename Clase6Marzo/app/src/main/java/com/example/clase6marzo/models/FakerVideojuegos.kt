package com.example.clase6marzo.models

import com.example.clase6marzo.R

class FakerVideojuegos {

    fun getVideogames() : ArrayList<Videojuego>{
        var videogames : ArrayList<Videojuego>
        videogames = arrayListOf<Videojuego>()

        videogames.add(Videojuego(1, "Pokemon Violet", 1300F, "Nintendo Switch", R.drawable.poke))
        videogames.add(Videojuego(2, "Resident Evil 5", 500F, "Xbox 360", R.drawable.resident5))
        videogames.add(Videojuego(3, "Scarlet Nexus", 900F, "Xbox One", R.drawable.scarletnexus))
        videogames.add(Videojuego(4, "The Last of Us", 900F, "PS4", R.drawable.thelastofus))
        videogames.add(Videojuego(5, "Borderlands 2", 400F, "PC", R.drawable.borderlands))
        videogames.add(Videojuego(6, "Code Vein", 1000F, "Xbox One", R.drawable.codevein))
        videogames.add(Videojuego(7, "Gears Of War 3", 300F, "Xbox 360", R.drawable.gears3))
        videogames.add(Videojuego(8, "Halo Reach", 300F, "Xbox 360", R.drawable.haloreach))
        return videogames
    }

}