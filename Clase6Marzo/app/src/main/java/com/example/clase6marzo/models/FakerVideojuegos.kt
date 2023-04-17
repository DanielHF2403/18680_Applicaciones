package com.example.clase6marzo.models

import com.example.clase6marzo.R

class FakerVideojuegos {

    fun getVideogames() : ArrayList<Videojuego>{
        var videogames : ArrayList<Videojuego>
        videogames = arrayListOf<Videojuego>()

        videogames.add(Videojuego(1, "Pokemon Violet", 1300F, "Nintendo Switch", R.drawable.poke, "Everyone"))
        videogames.add(Videojuego(2, "Resident Evil 5", 500F, "Xbox 360", R.drawable.resident5, "Mature"))
        videogames.add(Videojuego(3, "Scarlet Nexus", 900F, "Xbox One", R.drawable.scarletnexus, "Teen"))
        videogames.add(Videojuego(4, "The Last of Us", 900F, "PS4", R.drawable.thelastofus, "Mature"))
        videogames.add(Videojuego(5, "Borderlands 2", 400F, "PC", R.drawable.borderlands, "Mature"))
        videogames.add(Videojuego(6, "Code Vein", 1000F, "Xbox One", R.drawable.codevein, "Mature"))
        videogames.add(Videojuego(7, "Gears Of War 3", 300F, "Xbox 360", R.drawable.gears3,"mature"))
        videogames.add(Videojuego(8, "Halo Reach", 300F, "Xbox 360", R.drawable.haloreach, "Mature"))
        return videogames
    }

}