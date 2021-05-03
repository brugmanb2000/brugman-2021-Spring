package com.example.project2.ItemActivity


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.project2.ItemActivity.Fragments.*
import com.example.project2.ItemActivity.RetrofitApi.APIService
import com.example.project2.ItemActivity.ViewModels.ItemViewModel
import com.example.project2.JSONReturnObjects.NicknameReturn
import com.example.project2.JSONReturnObjects.ReturnStatusJSON
import com.example.project2.MainActivity.MainActivity
import com.example.project2.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemActivity() : AppCompatActivity() {

    companion object {
        fun newInstance(): ItemActivity {
            return ItemActivity()
        }
    }


    val viewModel: ItemViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
            onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.removePlayer()
                    val intent = Intent(applicationContext, MainActivity.newInstance()::class.java)
                    startActivity(intent)
                    finish()
                }
            })


        val joinButton = findViewById<Button>(R.id.joinButton)
        val hostButton = findViewById<Button>(R.id.hostButton)
        val createButton = findViewById<Button>(R.id.sessionCreateJoin)
        val nicknameField = findViewById<EditText>(R.id.nicknameField)


        var fragMan = supportFragmentManager.beginTransaction()
        fragMan.replace(R.id.fragLayout, ItemActivityConnectionFragJoin.newInstance())
        fragMan.commit()

        joinButton.setOnClickListener {
            viewModel.changePlayerStatusPlayer()
            viewModel.addPIN(-1)
            createButton.text = "Join Session"
            viewModel.addNickname("")
            fragMan = supportFragmentManager.beginTransaction()
            fragMan.replace(R.id.fragLayout, ItemActivityConnectionFragJoin.newInstance())
            fragMan.addToBackStack(null)
            fragMan.commit()
        }

        hostButton.setOnClickListener {
            viewModel.changePlayerStatusHost()
            viewModel.addPIN(-1)
            viewModel.addNickname("")
            createButton.text = "Create Session"
            fragMan = supportFragmentManager.beginTransaction()
            fragMan.replace(R.id.fragLayout, ItemActivityConnectionFragHost.newInstance())
            fragMan.addToBackStack(null)
            fragMan.commit()
        }

        nicknameField.setOnClickListener {
            nicknameField.setText("")
        }



        createButton.setOnClickListener {
            viewModel.nickname = nicknameField.text.toString()
            when {
                viewModel.getGamestate() == ItemViewModel.GameState.join_host -> {
                    when (viewModel.playerStatus) {
                        ItemViewModel.PlayerStatusEnum.host -> {

                            if (viewModel.getPIN() == -1) {
                                Toast.makeText(
                                    applicationContext,
                                    "Enter a valid PIN",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        ItemViewModel.PlayerStatusEnum.player -> {
                            val editText: EditText = findViewById(R.id.sessionPINEditText2)
                            if (editText.text.toString().toIntOrNull() != null) {
                                viewModel.addPIN(editText.text.toString().toInt())
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "Enter a valid PIN",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                    if (viewModel.nickname == "" || viewModel.nickname == "enter nickname") {
                        Toast.makeText(
                            applicationContext,
                            "Enter a nickname for yourself =)",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        checkPinAvailability(viewModel.getPIN(), viewModel.nickname)
                        if (viewModel.playerStatus == ItemViewModel.PlayerStatusEnum.player) {
                            val fragMan1 = supportFragmentManager.beginTransaction()
                            fragMan1.replace(R.id.fragLayout, ItemActivityAddFrag.newInstance())
                            fragMan1.addToBackStack(null)
                            fragMan1.commit()
                            joinButton.visibility = View.INVISIBLE
                            hostButton.visibility = View.INVISIBLE
                            nicknameField.visibility = View.INVISIBLE
                            createButton.text = "Done Adding Items"
                            viewModel.changeGamestate(ItemViewModel.GameState.add)


                        } else {
                            val fragMan2 = supportFragmentManager.beginTransaction()
                            fragMan2.replace(R.id.fragLayout, LobbyFragHost.newInstance())
                            fragMan2.addToBackStack(null)
                            fragMan2.commit()
                            joinButton.visibility = View.INVISIBLE
                            hostButton.visibility = View.INVISIBLE
                            createButton.text = "Start Session";
                            nicknameField.visibility = View.INVISIBLE
                            viewModel.changeGamestate(ItemViewModel.GameState.lobbyHost)
                        }
                    }

                }



                viewModel.getGamestate() == ItemViewModel.GameState.lobbyHost -> {
                    fragMan = supportFragmentManager.beginTransaction()
                    fragMan.replace(R.id.fragLayout, ItemActivityAddFrag.newInstance())
                    fragMan.addToBackStack(null)
                    fragMan.commit()
                    createButton.text = "Done Adding Items"
                    viewModel.changeGamestate(ItemViewModel.GameState.add)

                }

                viewModel.getGamestate() == ItemViewModel.GameState.add -> {
                    createButton.text = "Vote on Items"
                    fragMan = supportFragmentManager.beginTransaction()
                    fragMan.replace(R.id.fragLayout, LobbyFragPlayer.newInstance())
                    fragMan.addToBackStack(null)
                    fragMan.commit()
                    viewModel.changeGamestate(ItemViewModel.GameState.lobby)

                }

                viewModel.getGamestate() == ItemViewModel.GameState.lobby -> {
                    createButton.text = "See Results"
                    fragMan = supportFragmentManager.beginTransaction()
                    fragMan.replace(R.id.fragLayout, ItemActivityVoteFrag.newInstance())
                    fragMan.addToBackStack(null)
                    fragMan.commit()
                    viewModel.changeGamestate(ItemViewModel.GameState.vote)
                    }

                viewModel.getGamestate() == ItemViewModel.GameState.vote -> {
                    createButton.text = "Vote on Items"
                    fragMan = supportFragmentManager.beginTransaction()
                    fragMan.replace(R.id.fragLayout, LobbyFragPlayer.newInstance())
                    fragMan.addToBackStack(null)
                    fragMan.commit()
                    viewModel.changeGamestate(ItemViewModel.GameState.lobby2)
                }

                viewModel.getGamestate() == ItemViewModel.GameState.lobby2 -> {
                    fragMan = supportFragmentManager.beginTransaction()
                    fragMan.replace(R.id.fragLayout, ItemActivityResults.newInstance())
                    fragMan.addToBackStack(null)
                    fragMan.commit()
                    viewModel.changeGamestate(ItemViewModel.GameState.results)
                    createButton.text = "Finish"
                }

                viewModel.getGamestate() == ItemViewModel.GameState.results -> {
                    clearSession()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }


            }
        }


    }


    fun checkPinAvailability(gamePIN: Int, nickname: String) {
        val viewModel: ItemViewModel by viewModels()


        if (viewModel.getPIN() == -1) {
            Toast.makeText(applicationContext, "Make sure to add a PIN!", Toast.LENGTH_SHORT)
            return
        }
        val params: HashMap<String?, String?> = HashMap<String?, String?>()
        params["gamePIN"] = gamePIN.toString()
        params["request"] = "testPin"
        val api = APIService.create().testPIN(params)

        when (viewModel.playerStatus) {
            ItemViewModel.PlayerStatusEnum.host -> {
                api.enqueue(
                    object : Callback<ReturnStatusJSON> {
                        override fun onResponse(
                            call: Call<ReturnStatusJSON>,
                            response: Response<ReturnStatusJSON>
                        ) {

                            when (response.body()?.get(0)?.ReturnStatus) {
                                "true" -> {
                                    Toast.makeText(
                                        applicationContext,
                                        "PIN is available, starting session",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    Log.e("ViewModelPin", viewModel.getPIN().toString())
                                    Log.e(
                                        "ViewModelPlayerStatus",
                                        viewModel.playerStatus.toString()
                                    )
                                    addGameSession(gamePIN.toString(), nickname, "host")
                                    Log.e("Next Frag Test", "Testing Lobby Frag")

                                }

                                "false" -> {
                                    Toast.makeText(
                                        applicationContext,
                                        "PIN is currently in use. Try a different PIN",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                else -> {
                                    Toast.makeText(
                                        applicationContext,
                                        "Error verifying PIN",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }

                        override fun onFailure(call: Call<ReturnStatusJSON>, t: Throwable) {
                            Toast.makeText(
                                applicationContext,
                                "Error reading JSON",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                )
            }
            ItemViewModel.PlayerStatusEnum.player -> {

                val editText: EditText = findViewById(R.id.sessionPINEditText2)
                if (editText.text.toString().toIntOrNull() != null) {
                    viewModel.addPIN(editText.text.toString().toInt())
                } else {
                    Toast.makeText(applicationContext, "Enter a valid PIN", Toast.LENGTH_SHORT)
                        .show()
                }
                api.enqueue(
                    object : Callback<ReturnStatusJSON> {
                        override fun onResponse(
                            call: Call<ReturnStatusJSON>,
                            response: Response<ReturnStatusJSON>
                        ) {

                            when (response.body()?.get(0)?.ReturnStatus) {
                                "true" -> {
                                    Toast.makeText(
                                        applicationContext,
                                        "PIN is not currently in use. Try again.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                                "false" -> {
                                    Toast.makeText(
                                        applicationContext,
                                        "PIN found. Joining session",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    addNickname(gamePIN.toString(), nickname, "player")
                                    Log.e("ViewModelPin", viewModel.getPIN().toString())
                                    Log.e(
                                        "ViewModelPlayerStatus",
                                        viewModel.playerStatus.toString()
                                    )
                                    Log.e("Next Frag Test", "Testing Lobby Frag")


                                }
                                else -> {
                                    Toast.makeText(
                                        applicationContext,
                                        "Error verifying PIN",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }


                        override fun onFailure(call: Call<ReturnStatusJSON>, t: Throwable) {
                            Toast.makeText(
                                applicationContext,
                                "Error reading JSON",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                )
            }
        }
    }

    fun addGameSession(gamePIN: String, nickname: String, userType: String) {

        try {
            val params: HashMap<String?, String?> = HashMap()
            params["gamePIN"] = gamePIN
            params["request"] = "addPin"
            params["nickname"] = nickname


            val api = APIService.create().addPIN(params)

            api.enqueue(
                object : Callback<ReturnStatusJSON> {
                    override fun onResponse(
                        call: Call<ReturnStatusJSON>,
                        response: Response<ReturnStatusJSON>
                    ) {
                        Log.e("Adding Game Session:", "Connected and added.")
                        addNickname(gamePIN, nickname, userType)
                    }

                    override fun onFailure(call: Call<ReturnStatusJSON>, t: Throwable) {
                        Toast.makeText(applicationContext, "BAD TEST", Toast.LENGTH_SHORT)

                        Log.e(
                            "Game Session creation:",
                            "Failed/LocalMessage: " + t.localizedMessage
                        )
                        Log.e("Game Session creation:", "Failed/Call: $call")
                    }
                }
            )


        } catch (e: Exception) {
            Log.e("Adding Game Session:", "Failed: " + e.localizedMessage)

        }
    }



    fun addNickname(gamePIN: String, nickname: String, userType: String) {
        try {
            val params: HashMap<String?, String?> = HashMap()
            params["gamePIN"] = gamePIN
            params["request"] = "addNickname"
            params["nickname"] = nickname
            params["userType"] = userType

            val api = APIService.create().addNickname(params)

            api.enqueue(
                object : Callback<ReturnStatusJSON> {
                    override fun onResponse(
                        call: Call<ReturnStatusJSON>,
                        response: Response<ReturnStatusJSON>
                    ) {
                        Log.e("Nickname Added", response.body()?.get(0)?.ReturnStatus.toString())
                    }

                    override fun onFailure(call: Call<ReturnStatusJSON>, t: Throwable) {
                        Toast.makeText(applicationContext, "BAD TEST", Toast.LENGTH_SHORT)

                        Log.e("Nickname Failed to add", t.localizedMessage)
                    }
                }
            )


        } catch (e: Exception) {
            Log.e("Adding nickname ", "Failed: " + e.localizedMessage)

        }
    }

    fun getNicknames(gamePIN: String) {
        try {
            val params: HashMap<String?, String?> = HashMap()
            params["gamePIN"] = gamePIN
            params["request"] = "getNicknames"

            val api = APIService.create().getNicknames(params)

            api.enqueue(
                object : Callback<NicknameReturn> {
                    override fun onResponse(
                        call: Call<NicknameReturn>,
                        response: Response<NicknameReturn>
                    ) {
                        Log.e("Nickname Added", response.body().toString())
                    }

                    override fun onFailure(call: Call<NicknameReturn>, t: Throwable) {
                        Toast.makeText(applicationContext, "BAD TEST", Toast.LENGTH_SHORT)

                        Log.e("Nickname Failed to add", t.localizedMessage)
                    }
                }
            )


        } catch (e: Exception) {
            Log.e("Adding nickname ", "Failed: " + e.localizedMessage)

        }
    }


    fun waitForHost() {
        val viewModel: ItemViewModel by viewModels()
        while (viewModel.getGamestate() == ItemViewModel.GameState.lobby) {
            getGameState()
            if (viewModel.getGamestate() == ItemViewModel.GameState.vote) {
                viewModel.changeGamestate(ItemViewModel.GameState.vote)
                val fragMan = supportFragmentManager.beginTransaction()
                fragMan.replace(R.id.fragLayout, ItemActivityAddFrag.newInstance())
                fragMan.addToBackStack(null)
                fragMan.commit()
            }
            Thread.sleep(5000)
        }

    }

    private fun updateGameSession() {
        val viewModel: ItemViewModel by viewModels()
        try {
            val params: HashMap<String?, String?> = HashMap()
            params["gamePIN"] = viewModel.getPIN().toString()
            params["request"] = "updateGameState"

            val api = APIService.create().getGameState(params)

            api.enqueue(
                object : Callback<ReturnStatusJSON> {
                    override fun onResponse(
                        call: Call<ReturnStatusJSON>,
                        response: Response<ReturnStatusJSON>
                    ) {
                        Toast.makeText(
                            applicationContext,
                            "Moving to Vote Session",
                            Toast.LENGTH_SHORT
                        )
                    }

                    override fun onFailure(call: Call<ReturnStatusJSON>, t: Throwable) {

                        Log.e("Could not get game state", t.localizedMessage)
                    }
                }
            )
        } catch (e: Exception) {
        Log.e("Update game session ", "Failed: " + e.localizedMessage)

    }
    }

    private fun clearSession() {
        val viewModel: ItemViewModel by viewModels()
            val params: HashMap<String?, String?> = HashMap()
            params["gamePIN"] = viewModel.getPIN().toString()
            params["request"] = "clearSession"

            val api = APIService.create().clearSession(params)

            api.enqueue(
                object : Callback<ReturnStatusJSON> {
                    override fun onResponse(
                        call: Call<ReturnStatusJSON>,
                        response: Response<ReturnStatusJSON>
                    ) {
                    }

                    override fun onFailure(call: Call<ReturnStatusJSON>, t: Throwable) {

                    }
                }
            )}

    private fun getGameState() {
        val viewModel: ItemViewModel by viewModels()
        try {
            val params: HashMap<String?, String?> = HashMap()
            params["gamePIN"] = viewModel.getPIN().toString()
            params["request"] = "getGameState"

            val api = APIService.create().getGameState(params)

            api.enqueue(
                object : Callback<ReturnStatusJSON> {
                    override fun onResponse(
                        call: Call<ReturnStatusJSON>,
                        response: Response<ReturnStatusJSON>
                    ) {
                        if (response.body()?.get(0)?.ReturnStatus.equals("1")) {
                            Log.e("Updated Game State", "Moving to vote frag")
                            Toast.makeText(applicationContext, "Host has started the game", Toast.LENGTH_SHORT)
                            viewModel.changeGamestate(ItemViewModel.GameState.vote)

                        }
                    }

                    override fun onFailure(call: Call<ReturnStatusJSON>, t: Throwable) {

                        Log.e("Could not get game state", t.localizedMessage)
                    }
                }
            )


        } catch (e: Exception) {
            Log.e("Adding nickname ", "Failed: " + e.localizedMessage)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        clearSession()
    }
}