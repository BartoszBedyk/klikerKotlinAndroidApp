package pl.example.klikacz

import android.content.pm.PackageManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.MediaStore.Audio.Media
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import pl.example.klikacz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val mainVM by viewModels<MainViewModel> ()
    private lateinit var binding : ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        odswiez()

        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)

        binding.TV1.text = mainVM.Click.toString()


        binding.Button1.setOnClickListener{
            mainVM.Click += 1
            binding.TV1.text = mainVM.Click.toString()
            dzialaj()
        }

        binding.button2.setOnClickListener{
            mainVM.Click += 10
            binding.TV1.text = mainVM.Click.toString()
            dzialaj()
        }

        binding.button3.setOnClickListener{
            mainVM.Click += 100
            binding.TV1.text = mainVM.Click.toString()
            dzialaj()
        }

        binding.button4.setOnClickListener{
            binding.button4.text = "Wykup usługę +30"
            usluga()
            dzialaj()
            //binding.button4.visibility=View.INVISIBLE
        }

    }

    private fun dzialaj() {
        val mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.barka)
        if (mainVM.Click.toInt() == 2137) {
            binding.obraz1.visibility = View.VISIBLE
            mediaPlayer.start()
        } else if (mainVM.Click.toInt() > 2137) {
            mediaPlayer.pause()
            mediaPlayer.stop()
            binding.obraz1.visibility = View.INVISIBLE

        }
    }

    public fun usluga(){
        object : CountDownTimer(60000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                mainVM.Click+=1
            }


            override fun onFinish() {
            usluga()
            }
        }.start()
    }

    public fun odswiez()
    {
        object : CountDownTimer(60000, 50) {

            override fun onTick(millisUntilFinished: Long) {

                binding.TV1.text = mainVM.Click.toString()
            }


            override fun onFinish() {
                odswiez()
            }
        }.start()
    }


}
