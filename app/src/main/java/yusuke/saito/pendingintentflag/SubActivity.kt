package yusuke.saito.pendingintentflag

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import yusuke.saito.pendingintentflag.databinding.ActivityMainBinding
import yusuke.saito.pendingintentflag.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.subButton.setOnClickListener {
            val pendingIntent = intent.getParcelableExtra<PendingIntent>("pending_intent")
            val returnIntent = Intent().apply { putExtra("additional_info", "hello") }
            pendingIntent?.send(this, 0, returnIntent)
        }
    }
}
