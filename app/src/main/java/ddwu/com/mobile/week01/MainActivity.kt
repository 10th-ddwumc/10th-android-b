package ddwu.com.mobile.week01

import android.graphics.Color
import android.os.Bundlep
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwu.com.mobile.week01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 클릭 시 텍스트 색상 변경
        binding.happyImg.setOnClickListener {
            binding.textView4.setTextColor(Color.parseColor("#FCF0BC"))
        }
        binding.excitedImg.setOnClickListener {
            binding.textView5.setTextColor(Color.parseColor("#D3E6F3"))
        }
        binding.normalImg.setOnClickListener {
            binding.textView6.setTextColor(Color.parseColor("#BFC3EA"))
        }
        binding.nervousImg.setOnClickListener {
            binding.textView7.setTextColor(Color.parseColor("#B8D2BB"))
        }
        binding.angryImg.setOnClickListener{
            binding.textView8.setTextColor(Color.parseColor("#DE908D"))
        }
    }
}