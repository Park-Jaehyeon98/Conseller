package com.example.project.viewmodels;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.example.project.api.GifticonRequestDTO;
import com.example.project.api.MyService;
import com.example.project.api.OcrService;
import com.example.project.api.UploadGifticonResponse;
import com.example.project.api.uploadImageResponse;
import com.example.project.di.CustomException;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import okhttp3.MultipartBody;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0002\u00a8\u0006\u0003"}, d2 = {"getSampleData", "", "Lcom/example/project/viewmodels/GifticonData;", "app_debug"})
public final class MygifticonViewModel_ktKt {
    
    private static final java.util.List<com.example.project.viewmodels.GifticonData> getSampleData() {
        return null;
    }
}