package com.geziwulian.netlibrary;



import com.geziwulian.netlibrary.model.Avatar;

import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by zzh on 16-4-17.
 */
public interface FileApiService {
//    //修改头像
    @Multipart
    @POST("user/update-avatar")
    Observable<Response<Avatar>> Avatar(@Part("image\"; filename=\"image.jpg\" ") RequestBody image);
}
