package yusuke.saito.pendingintentflag

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import yusuke.saito.pendingintentflag.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // 追加情報がある場合はトーストを表示する
        // 追加情報がない場合は何もしない
        val additionalInfo = intent.getStringExtra("additional_info")
        additionalInfo?.let {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        // PendingIntentで起動されるActivity
        val activityIntent = Intent(
                this,
                MainActivity::class.java
        ).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        // FLAG_IMMUTABLEのPendingIntent
        val flagImmutablePendingIntent = PendingIntent.getActivity(
                this,
                0,
                activityIntent,
                PendingIntent.FLAG_IMMUTABLE
        )
        // FLAG_IMMUTABLEのIntent
        val flagImmutableIntent = Intent(
                this,
                SubActivity::class.java
        ).apply {
            putExtra("pending_intent", flagImmutablePendingIntent)
        }

        // FLAG_MUTABLEのPendingIntent
        val flagMutablePendingIntent = PendingIntent.getActivity(
                this,
                0,
                activityIntent,
                PendingIntent.FLAG_MUTABLE
        )
        // FLAG_MUTABLEのIntent
        val flagMutableIntent = Intent(
                this,
                SubActivity::class.java
        ).apply {
            putExtra("pending_intent", flagMutablePendingIntent)
        }

        viewBinding.flagImmutable.setOnClickListener {
            startActivity(flagImmutableIntent)
        }
        viewBinding.flagMutable.setOnClickListener {
            startActivity(flagMutableIntent)
        }
    }
}
