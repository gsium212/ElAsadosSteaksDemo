package com.elasados.steaks.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import com.elasados.steaks.models.Product;
import com.elasados.steaks.services.ApiClient;
import com.elasados.steaks.services.ProductService;
import com.elasados.steaks.utils.MockData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Product>> products = new MutableLiveData<>();

    public ProductsViewModel(@NonNull Application app) { super(app); }

    public LiveData<List<Product>> getProducts() { return products; }

    public void loadRestaurantProducts(String restaurantId) {
        if (MockData.MOCK_MODE) {
            products.postValue(MockData.getProducts());
            return;
        }
        ProductService api = ApiClient.getClient(getApplication()).create(ProductService.class);
        api.getProducts(restaurantId).enqueue(new Callback<List<Product>>() {
            @Override public void onResponse(Call<List<Product>> call, Response<List<Product>> res) {
                if (res.isSuccessful()) products.postValue(res.body());
            }
            @Override public void onFailure(Call<List<Product>> call, Throwable t) {}
        });
    }
}
