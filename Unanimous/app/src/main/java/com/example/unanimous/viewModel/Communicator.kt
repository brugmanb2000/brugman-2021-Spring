
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Communicator : ViewModel(){

    val message = MutableLiveData<Any>()

    companion object {
        private lateinit var instance: Communicator

        val companionInstance: Communicator
        get() {
            if (instance == null) {
                instance = Communicator()
            }
            return instance
        }
    }

    fun setMsgCommunicator(msg:String){
        message.setValue(msg)
    }
}