package com.geziwulian.netlibrary;

import android.widget.Toast;

import com.geziwulian.netlibrary.model.Demo;
import com.geziwulian.netlibrary.model.Me;
import com.geziwulian.netlibrary.model.NewCentreData;
import com.geziwulian.netlibrary.model.NewsCentre;
import com.geziwulian.netlibrary.model.UpdateName;
import com.geziwulian.netlibrary.model.dinner.AllCollection;
import com.geziwulian.netlibrary.model.dinner.AllShops;
import com.geziwulian.netlibrary.model.dinner.BannerData;
import com.geziwulian.netlibrary.model.dinner.Classification;
import com.geziwulian.netlibrary.model.dinner.Collection;
import com.geziwulian.netlibrary.model.dinner.CtComment;
import com.geziwulian.netlibrary.model.dinner.Order;
import com.geziwulian.netlibrary.model.dinner.OrderDetail;
import com.geziwulian.netlibrary.model.dinner.OrderList;
import com.geziwulian.netlibrary.model.dinner.Px;
import com.geziwulian.netlibrary.model.dinner.RecommendShops;
import com.geziwulian.netlibrary.model.dinner.ShopDet;
import com.geziwulian.netlibrary.model.login.Token;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by yyx on 16/3/1.
 */
public interface APIService {

    /*************测试块*************/
    /**
     * 基本测试4
     *
     * @return
     */
    @FormUrlEncoded
    @POST("test")
    Call<Demo> test(@Field("name") String name, @Field("age") int age);

    /*************测试块*************/

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("auth/login")
    Observable<Response<Token>> login(@Field("username") String username,
                                      @Field("password") String password);

    /**
     * 获取短信验证码
     *
     * @param phone
     * @param device
     * @param device_token
     * @param sign
     * @return
     */
    @FormUrlEncoded
    @POST("auth/captcha")
    Observable<Response<String>> getSmsCode(@Field("phone") String phone,
                                            @Field("device") String device,
                                            @Field("device_token") String device_token,
                                            @Field("sign") String sign);


    /**
     * 刷新Token 重新登录
     *
     * @return
     */
    @POST("auth/refresh")
    Observable<Response<String>> reLogin();
    //Header
    @POST("auth/refresh")
    Call<String> reFreshToken(@Header("Authorization") String authorization);


    //我
    @GET("auth/me")
    Observable<Response<Me>> Me();

    //修改用户
    @PUT("user/update-name")
    Observable<Response<UpdateName>> UpDateAvatar(@Query("name") String name);

    //获取首页轮播图
    @GET("scene/slider")
    Observable<Response<List<BannerData>>> loadHomeBanner();

    /**
     * 获取首页区块图
     *
     * @return
     */
    @GET("scene/tile")
    Observable<Response<List<BannerData>>> mTile();

    //获取轮播图
    @GET("meal/scene/slider")
    Observable<Response<List<BannerData>>> loadBanner();

    //获取推荐商家
    @GET("meal/scene/home-merchant")
    Observable<Response<List<RecommendShops>>> loadShops();

    //获取商家信息
    @GET("meal/merchant/{id}")
    Observable<Response<ShopDet>> loadshop(@Path("id") int id);

    //获取所有分类
    @GET("meal/merchant/category")
    Observable<Response<List<Classification>>> loadClass();

    //收藏
    @POST("meal/merchant/{id}/favorite")
    Observable<Response<Collection>> save(@Path("id") int id);
    /**
     * 取消单一收藏
     * */
    @DELETE("meal/merchant/{id}/favorite")
    Observable<Response<Integer>> DelSave(@Path("id") int id);

    //获得餐厅查询时的过滤和排序条件
    @GET("meal/merchant/filters")
    Observable<Response<List<Px>>> Px();

    //创建订单
    @FormUrlEncoded
    @POST("meal/order")
    Observable<Response<Order>> Order(@Field("merchant_id") int merchant_id
            , @Field("name") String name
            , @Field("mobile") String mobile
            , @Field("people_number") int people_number
            , @Field("dinner_time") String dinner_time
            , @Field("need_box") int need_box
            , @Field("box_hall") int box_hall
            , @Field("message") String message);

    //订单评价
    @FormUrlEncoded
    @POST("meal/order/{id}/evaluate")
    Observable<Response<String>> postOrderComment(@Path("id") int id
            , @Field("ambiance") int ambiance
            , @Field("service") int service
            , @Field("flavor") int flavor
            , @Field("message") String message);

    //获取餐馆所有评论
    @GET("meal/merchant/{id}/comments")
    Observable<Response<CtComment>> CtComment(@Path("id") int id);

    //查询餐馆
    @GET("meal/merchant")
    Observable<Response<AllShops>> SearchCg(@Query("category_id ") String category_id
            , @Query("area_id") String area_id
            , @Query("orderby") String orderby
            , @Query("s") String searchKey);

    /**
     *
     * 传入整条url获取餐馆*/
    @GET
    Observable<Response<AllShops>> allshopWithUrl(@Url String url);


    /**
     * 获取订单详情*/
    @GET("meal/order/{id}")
    Observable<Response<OrderDetail>> getOrderDetail(@Path("id") int id);

    /**
     * 获取订单列表
     * */
    @GET("meal/order")
    Observable<Response<OrderList>> getOrderList();

    /**
     * 取消订单
     * */
    @DELETE("meal/order/{id}")
    Observable<Response<Integer>> CancelOrder(@Path("id") int id);
    /**
     *获取收藏
     * */
    @GET("meal/favorite")
    Observable<Response<AllCollection>> allCollection();

    /**
     * 传入整条url方法*/
    @GET
    Observable<Response<AllCollection>> allCollectionWithFullUrl(@Url String url);

    /**
     *获取收藏IDS
     * */
    @GET("meal/favorite")
    Observable<Response<String[]>> allCollection(@Query("sync") boolean sync);


    /**
     * 消息中心
     * */

    @GET("notification")
    Observable<Response<NewsCentre>> newscentre();

    @Headers("Cache-Control:no-cache")
    @FormUrlEncoded
    @PUT("notification/{id}")
    Observable<Response<NewCentreData>> readedMessage(@Path("id") int id, @Field("read") int flag);

    @DELETE("notification/{id}")
    Observable<Response<ResponseBody>> deleteMessage(@Path("id") int id);

    /**
     * JPUSH推送
     * */
    @FormUrlEncoded
    @POST("test/push")
    Observable<Response<String>> JPSH(@Field("to") String to
                                        ,@Field("title") String title
                                        ,@Field("content" ) String content
                                        ,@Field("extras") String extras);

    @PUT("notification/{id}")
    Observable<Response<Read>> Readed(@Path("id") int id
                                        ,@Field("read") int read);

    /**
     * 收藏取消可传入数组
     * */

    @DELETE("meal/favorite/{id}")
    Observable<Response<String []>> deleteAllshops(@Path("id") String[] id );
}
