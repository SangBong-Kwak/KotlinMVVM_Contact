package com.dreamer_sb.kotlinmvvm_contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.dreamer_sb.kotlinmvvm_contact.room.Contact

class InfoActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel
    private var id: Long? = null

    val nameEditText : EditText by lazy {
        findViewById<EditText>(R.id.nameEditText)
    }
    val numberEditText : EditText by lazy{
        findViewById<EditText>(R.id.numberEditText)
    }

    val addButton : Button by lazy {
        findViewById<Button>(R.id.addButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        // intent 수신 확인
        if(intent != null && intent.hasExtra(EXTRA_CONTACT_NAME) && intent.hasExtra(EXTRA_CONTACT_NUMBER) && intent.hasExtra(EXTRA_CONTACT_ID)){
            nameEditText.setText(intent.getStringExtra(EXTRA_CONTACT_NAME))
            numberEditText.setText(intent.getStringExtra(EXTRA_CONTACT_NUMBER))
            id = intent.getLongExtra(EXTRA_CONTACT_ID, -1)
        }

        addButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val number = numberEditText.text.toString()
            if(name.isEmpty() || number.isEmpty()){
                Toast.makeText(this, "입력정보가 없습니다", Toast.LENGTH_SHORT).show()
            }
            else{
                val initial = name[0].toUpperCase()
                val contact = Contact(id, name, number, initial)
                contactViewModel.insert(contact)
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_CONTACT_NAME = "EXTRA_CONTACT_NAME"
        const val EXTRA_CONTACT_NUMBER = "EXTRA_CONTACT_NUMBER"
        const val EXTRA_CONTACT_ID = "EXTRA_CONTACT_ID"
    }
}