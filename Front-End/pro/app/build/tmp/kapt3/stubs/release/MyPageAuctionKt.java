
import android.util.Log;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.navigation.NavHostController;
import com.example.project.R;
import com.example.project.viewmodels.MyPageViewModel;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aH\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\bH\u0007\u001a\u0010\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001a\u001c\u0010\r\u001a\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\bH\u0007\u001aN\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018H\u0007\u001aV\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018H\u0007\u001a\b\u0010\u001c\u001a\u00020\u0001H\u0007\u00a8\u0006\u001d"}, d2 = {"AuctionOption", "", "text", "", "id", "", "selectedOption", "onSelectionChanged", "Lkotlin/Function1;", "onOptionClicked", "MypageAuction", "navController", "Landroidx/navigation/NavHostController;", "SelectAuction", "ShowMyAuction", "image", "name", "gifticonTime", "auctionTime", "isDeposit", "", "upperprice", "nowprice", "onItemClick", "Lkotlin/Function0;", "ShowMyAuctionBid", "myprice", "status", "showNothingActionImage", "app_release"})
public final class MyPageAuctionKt {
    
    @androidx.compose.runtime.Composable
    public static final void MypageAuction(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController navController) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void SelectAuction(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onSelectionChanged) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void AuctionOption(@org.jetbrains.annotations.NotNull
    java.lang.String text, int id, int selectedOption, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onSelectionChanged, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onOptionClicked) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ShowMyAuction(@org.jetbrains.annotations.NotNull
    java.lang.String image, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonTime, @org.jetbrains.annotations.NotNull
    java.lang.String auctionTime, boolean isDeposit, int upperprice, int nowprice, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onItemClick) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ShowMyAuctionBid(@org.jetbrains.annotations.NotNull
    java.lang.String image, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonTime, @org.jetbrains.annotations.NotNull
    java.lang.String auctionTime, boolean isDeposit, int upperprice, int myprice, @org.jetbrains.annotations.NotNull
    java.lang.String status, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onItemClick) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void showNothingActionImage() {
    }
}