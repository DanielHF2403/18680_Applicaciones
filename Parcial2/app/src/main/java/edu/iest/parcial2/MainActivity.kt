package edu.iest.parcial2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.iest.parcial2.Model.FakerMenu
import edu.iest.parcial2.adapters.adapter
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {
    private lateinit var recycler : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menus = FakerMenu.getMenu()
        recycler = findViewById<RecyclerView>(R.id.recyclerMenus)


        val administradorDeLayouts = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        recycler.layoutManager = administradorDeLayouts
        recycler.adapter = adapter(menus, this)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menupantalla, menu)
        return super.onCreateOptionsMenu(menu)
    }

}
