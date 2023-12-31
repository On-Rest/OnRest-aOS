import com.chobo.onrest.IdTokenDataClass
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/users/login")
    suspend fun sendIdToken(@Body body: IdTokenDataClass): Call<Void>
}