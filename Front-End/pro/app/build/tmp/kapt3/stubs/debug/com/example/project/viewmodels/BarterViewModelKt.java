package com.example.project.viewmodels;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.example.project.api.BarterConfirmList;
import com.example.project.api.BarterConfirmPageResponseDTO;
import com.example.project.api.BarterConfirmRequestDTO;
import com.example.project.api.BarterCreateDTO;
import com.example.project.api.BarterDetailResponseDTO;
import com.example.project.api.BarterFilterDTO;
import com.example.project.api.BarterService;
import com.example.project.api.StoreConfirmPageResponseDTO;
import com.example.project.api.StoreConfirmRequestDTO;
import com.example.project.api.TradeBarterRequestDTO;
import com.example.project.api.UpdateBarterDTO;
import com.example.project.di.CustomException;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0002\u00a8\u0006\u0003"}, d2 = {"getSampleData", "", "Lcom/example/project/viewmodels/BarterItemData;", "app_debug"})
public final class BarterViewModelKt {
    
    private static final java.util.List<com.example.project.viewmodels.BarterItemData> getSampleData() {
        return null;
    }
}