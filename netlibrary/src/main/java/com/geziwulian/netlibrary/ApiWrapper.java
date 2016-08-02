package com.geziwulian.netlibrary;


import com.geziwulian.netlibrary.model.Avatar;
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

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by yyx on 16/3/1.
 */
public class ApiWrapper extends HttpClient {

    /*************测试块*************/
    /**
     * 基本测试4
     *
     * @return
     */
//    public Observable<Demo> test(String name, int age) {
//        return getService().test(name, age)
//                .compose(this.<Demo>applySchedulers());
//    }

    /*************测试块*************/

    /**
     * 获取短信验证码接口
     *
     * @param phone
     * @param device
     * @param device_token
     * @param sign
     * @return
     */
    public Observable<String> getSmsCode(String phone, String device, String device_token, String sign) {
        return getService().getSmsCode(phone, device, device_token, sign)
                .compose(this.<String>applySchedulers());
    }

    /**
     * 登陆接口
     *
     * @param userName
     * @param password
     * @return
     */
    public Observable<Token> login(String userName, String password) {
        return getService().login(userName, password)
                .compose(this.<Token>applySchedulers());
    }

    /**
     * 刷新Token 重新登录
     *
     * @return
     */
    public Observable<String> reLogin() {
        return getService().reLogin()
                .compose(this.<String>applySchedulers());
    }

    //我
    public Observable<Me> getUser() {
        return getService().Me()
                .compose(this.<Me>applySchedulers());
    }

    //修改用户名
    public Observable<UpdateName> UpDateAvatar(String name) {
        return getService().UpDateAvatar(name)
                .compose(this.<UpdateName>applySchedulers());

    }


    //首页
    public Observable<List<BannerData>> loadHomeBanner() {
        return getService().loadHomeBanner().compose(this.<List<BannerData>>applySchedulers());
    }

//    public Observable<List<BannerData>> loadHomeList() {
//        return getService().loadHomeList().compose(this.<List<BannerData>>applySchedulers());
//    }

    //获取首页区块图
    public Observable<List<BannerData>> mTile() {
        return getService().mTile().compose(this.<List<BannerData>>applySchedulers());
    }

    //订餐轮播图
    public Observable<List<BannerData>> loadBanner() {
        return getService().loadBanner().compose(this.<List<BannerData>>applySchedulers());
    }

    //获取推荐商家列表
    public Observable<List<RecommendShops>> loadShops() {
        return getService().loadShops().compose(this.<List<RecommendShops>>applySchedulers());

    }

    //获取商家信息
    public Observable<ShopDet> loadShop(int id) {
        return getService().loadshop(id).compose(this.<ShopDet>applySchedulers());

    }


    //所有分类
    public Observable<List<Classification>> loadClass() {
        return getService().loadClass().compose(this.<List<Classification>>applySchedulers());

    }

    //收藏
    public Observable<Collection> save(int id) {
        return getService().save(id).compose(this.<Collection>applySchedulers());
    }

    //取消
    public Observable<Integer> DelSave(int id) {
        return getService().DelSave(id).compose(this.<Integer>applySchedulers());
    }

    //获得餐厅查询时的过滤和排序条件
    public Observable<List<Px>> Px() {
        return getService().Px().compose(this.<List<Px>>applySchedulers());
    }

    //订单评价
    public Observable<String> postOrderComment(int id, int ambiance, int service, int flavor, String message) {
        return getService().postOrderComment(id, ambiance, service, flavor, message).compose(this.<String>applySchedulers());

    }

    //查看餐厅评论
    public Observable<CtComment> CtComment(int id) {
        return getService().CtComment(id).compose(this.<CtComment>applySchedulers());

    }

    //查询餐厅
    public Observable<AllShops> SearchCg(String id, String area_id, String orderby, String searchKey) {
        return getService().SearchCg( id, area_id, orderby, searchKey).compose(this.<AllShops>applySchedulers());

    }

    /**
     * 传入所有商店url
     * */

    public Observable<AllShops> allshopWithUrl(String url){
        return getService().allshopWithUrl(url).compose(this.<AllShops>applySchedulers());
    }

    public Observable<Order> Order(int merchant_id, String name, String phone, int people_number, String dinner_time
            , int need_box, int box_hall , String message) {
        return getService().Order(merchant_id, name, phone, people_number, dinner_time, need_box, box_hall, message).compose(this.<Order>applySchedulers());
    }

    public Observable<OrderDetail> getOrderDetail(int id){
        return getService().getOrderDetail(id).compose(this.<OrderDetail>applySchedulers());
    }

    public Observable<OrderList> getOrderList(){
        return getService().getOrderList().compose(this.<OrderList>applySchedulers());
    }

    public Observable<Integer> CancelOrder(int id){
        return getService().CancelOrder(id).compose(this.<Integer>applySchedulers());
    }

    public Observable<AllCollection> allCollection(){
        return  getService().allCollection().compose(this.<AllCollection>applySchedulers());
    }

    public Observable<String[]> allcollection(boolean sync){
        return getService().allCollection(sync).compose(this.<String[]>applySchedulers());
    }

    public Observable<AllCollection> allConectionUrl(String url){
        return getService().allCollectionWithFullUrl(url).compose(this.<AllCollection>applySchedulers());
    }

    public Observable<NewsCentre> newcentre(){
        return getService().newscentre().compose(this.<NewsCentre>applySchedulers());
    }

    public Observable<NewCentreData> readedMessage(int id, int flag){
        return getService().readedMessage(id,flag).compose(this.<NewCentreData>applySchedulers());
    }

    public Observable<ResponseBody> deleteMessage(int id){
        return getService().deleteMessage(id).compose(this.<ResponseBody>applySchedulers());
    }

    /**
     * JPUSHt推送*/

    public Observable<String> JPUSH(String to,String title,String content,String extras){
        return getService().JPSH(to,title,content,extras).compose(this.<String>applySchedulers());
    }

    /**
     * 标记已读
     * */
    public Observable<Read> read(int id ,int read){
        return getService().Readed(id , read).compose(this.<Read>applySchedulers());
    }

    /**
     * 传入收藏id 数组 取消收藏
     * */
    public Observable<String []> deleteAllshopIds(String [] id){
        return getService().deleteAllshops(id).compose(this.<String []>applySchedulers());
    }
}
