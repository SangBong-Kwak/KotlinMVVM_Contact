package com.dreamer_sb.kotlinmvvm_contact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dreamer_sb.kotlinmvvm_contact.room.Contact

class MainActivity : AppCompatActivity() {

    private val mainRecycleview by lazy {
        findViewById<RecyclerView>(R.id.mainRecycleview)
    }

    private val mainButton by lazy{
        findViewById<Button>(R.id.mainButton)
    }

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ContactAdapter({
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtra(InfoActivity.EXTRA_CONTACT_NAME, it.name)
            intent.putExtra(InfoActivity.EXTRA_CONTACT_NUMBER, it.number)
            intent.putExtra(InfoActivity.EXTRA_CONTACT_ID, it.id)
            startActivity(intent)
        },{
            deleteDialog(it)
        })

        val lm = LinearLayoutManager(this)
        mainRecycleview.adapter = adapter
        mainRecycleview.layoutManager = lm

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(this, Observer<List<Contact>> {
            adapter.setContacts(it)
        })

        mainButton.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }

    }

    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                contactViewModel.delete(contact)
            }
        builder.show()
    }


}