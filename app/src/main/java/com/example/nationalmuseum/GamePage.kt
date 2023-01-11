package com.example.nationalmuseum

import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nationalmuseum.games.*


var data :String ="null"

class GamePage : AppCompatActivity() {
    private var nfcAdapter: NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_NationalMuseum)

        this.nfcAdapter = NfcAdapter.getDefaultAdapter(this)?.let { it }

        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
            var tagFromIntent: Tag? = intent?.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            val nfc = Ndef.get(tagFromIntent)
            val msg = nfc.cachedNdefMessage
            val rec = msg.records
            data =  String(rec[3].payload, charset("UTF-8"))
        }

        when (data) {
            "SuttonHooSword" -> suttonHooSword()
            "SuttonHooHelmet" -> suttonHooHemet()
            else -> {
                    MainActivity()
            }
        }
    }
    fun suttonHooSword() {
        val intent = Intent(this, GameSuttonHooSword::class.java)
        startActivity(intent)
        finish()
    }

    fun suttonHooHemet() {
        val intent = Intent(this, GameSuttonHooSword::class.java)
        startActivity(intent)
        finish()
    }
}