package com.izo.githubuserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var ivAvatar: ImageView
    private lateinit var tvDetailUsername: TextView
    private lateinit var tvDetailCompany: TextView
    private lateinit var tvDetailName: TextView
    private lateinit var tvDetailFollowers: TextView
    private lateinit var tvDetailFollowing: TextView
    private lateinit var tvDetailRepository: TextView
    private lateinit var tvDetailLocation: TextView
    private lateinit var ivBack: ImageView
    private lateinit var btnShare: Button

    companion object {
        const val DATA = "Data"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<User>(DATA)

        ivAvatar = findViewById(R.id.iv_detailAvatar)
        tvDetailUsername = findViewById(R.id.tv_detailUsername)
        tvDetailFollowers = findViewById(R.id.tv_detailFollowers)
        tvDetailFollowing = findViewById(R.id.tv_detailFollowing)
        tvDetailName = findViewById(R.id.tv_detailName)
        tvDetailRepository = findViewById(R.id.tv_detailRepository)
        tvDetailCompany = findViewById(R.id.tv_detailCompany)
        tvDetailLocation = findViewById(R.id.tv_detailLocation)
        ivBack = findViewById(R.id.iv_back)
        btnShare = findViewById(R.id.btn_share)

        data?.avatar?.let { ivAvatar.setImageResource(it) }
        tvDetailUsername.text = data?.username
        tvDetailFollowers.text = data?.followers
        tvDetailFollowing.text = data?.following
        tvDetailName.text = data?.name
        tvDetailRepository.text = data?.repository
        tvDetailCompany.text = data?.company
        tvDetailLocation.text = data?.location

        ivBack.setOnClickListener(this)
        btnShare.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_back -> {
                val moveIntent = Intent(this@DetailActivity, MainActivity::class.java)
                startActivity(moveIntent)
                finish()
            }

            R.id.btn_share -> {

                val data = intent.getParcelableExtra<User>(DATA)

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, """
                        Username : ${data?.username},
                        Name : ${data?.name},
                        Followers : ${data?.followers},
                        Following : ${data?.following},
                        Repository : ${data?.repository},
                        Company : ${data?.company},
                        Location : ${data?.location}
                    """.trimIndent())
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)

            }
        }
    }
}

