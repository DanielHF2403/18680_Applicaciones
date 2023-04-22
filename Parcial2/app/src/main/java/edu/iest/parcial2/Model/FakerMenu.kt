package edu.iest.parcial2.Model

import edu.iest.parcial2.R

class FakerMenu {
    fun getMenu (): ArrayList<Menu>{
        val menus : ArrayList<Menu>
        menus = arrayListOf<Menu>()

        menus.add(Menu("Gatos", R.drawable.cat))
        menus.add(Menu("Perfil", R.drawable.profile))
        menus.add(Menu("Configuración", R.drawable.config))
        menus.add(Menu("Cerrar", R.drawable.close))

        return menus
    }
}