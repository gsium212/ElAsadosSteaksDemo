package com.elasados.steaks.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.elasados.steaks.models.AuthRequest;
import com.elasados.steaks.models.AuthResponse;
import com.elasados.steaks.models.RegisterRequest;
import com.elasados.steaks.services.ApiClient;
import com.elasados.steaks.services.AuthService;
import com.elasados.steaks.services.Prefs;
import com.elasados.steaks.utils.MockData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends AndroidViewModel {
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);
    private final MutableLiveData<String> error = new MutableLiveData<>();
    private final MutableLiveData<AuthResponse> auth = new MutableLiveData<>();

    public AuthViewModel(@NonNull Application app) { super(app); }

    public LiveData<Boolean> getLoading() { return loading; }
    public LiveData<String> getError() { return error; }
    public LiveData<AuthResponse> getAuth() { return auth; }

    public void login(String email, String pass) {
        // If MOCK_MODE is enabled, bypass network and return a fake user
        if (MockData.MOCK_MODE) {
            if (email == null || email.trim().isEmpty()) { error.postValue("Correo requerido"); return; }
            AuthResponse r = new AuthResponse();
            r.token = "mock-token";
            r.user = new com.elasados.steaks.models.User();
            r.user.id = "u1";
            r.user.name = email.split("@")[0];
            r.user.email = email;
            Prefs.saveToken(getApplication(), r.token);
            auth.postValue(r);
            return;
        }

        loading.postValue(true);
        AuthService api = ApiClient.getClient(getApplication()).create(AuthService.class);
        api.login(new AuthRequest(email, pass)).enqueue(new Callback<AuthResponse>() {
            @Override public void onResponse(Call<AuthResponse> call, Response<AuthResponse> res) {
                loading.postValue(false);
                if (res.isSuccessful() && res.body() != null) {
                    Prefs.saveToken(getApplication(), res.body().token);
                    auth.postValue(res.body());
                } else {
                    error.postValue("Credenciales inválidas");
                }
            }
            @Override public void onFailure(Call<AuthResponse> call, Throwable t) {
                loading.postValue(false);
                error.postValue(t.getMessage());
            }
        });
    }

    public void register(String name, String phone, String email, String pass) {
        if (MockData.MOCK_MODE) {
            AuthResponse r = new AuthResponse();
            r.token = "mock-token";
            r.user = new com.elasados.steaks.models.User();
            r.user.id = "u1";
            r.user.name = name;
            r.user.email = email;
            r.user.phone = phone;
            Prefs.saveToken(getApplication(), r.token);
            auth.postValue(r);
            return;
        }

        loading.postValue(true);
        AuthService api = ApiClient.getClient(getApplication()).create(AuthService.class);
        api.register(new RegisterRequest(name, phone, email, pass)).enqueue(new Callback<AuthResponse>() {
            @Override public void onResponse(Call<AuthResponse> call, Response<AuthResponse> res) {
                loading.postValue(false);
                if (res.isSuccessful() && res.body() != null) {
                    Prefs.saveToken(getApplication(), res.body().token);
                    auth.postValue(res.body());
                } else {
                    error.postValue("No se pudo registrar");
                }
            }
            @Override public void onFailure(Call<AuthResponse> call, Throwable t) {
                loading.postValue(false);
                error.postValue(t.getMessage());
            }
        });
    }
}
