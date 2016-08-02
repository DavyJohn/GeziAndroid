package com.geziwulian.netlibrary.model.dinner;

/**
 * Created by zzh on 16-4-21.
 */
public class AllCollectionData {
    public   int id;
    public int user_id;
    public int merchant_id;
    public  MerchantData merchant;

    public AllCollectionData(int id, MerchantData merchant) {
        this.id = id;
        this.merchant = merchant;
    }

    public MerchantData getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantData merchant) {
        this.merchant = merchant;
    }
}
