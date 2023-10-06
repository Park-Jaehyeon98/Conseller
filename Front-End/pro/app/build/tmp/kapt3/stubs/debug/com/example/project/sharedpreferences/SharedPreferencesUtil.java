package com.example.project.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\b\u0010\r\u001a\u0004\u0018\u00010\fJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0011\u001a\u00020\u000fJ\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000fJ\u000e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0017\u001a\u00020\u0013J\u000e\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\nJ\u000e\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\fJ\u000e\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u001f"}, d2 = {"Lcom/example/project/sharedpreferences/SharedPreferencesUtil;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "getUserId", "", "getUserNickname", "", "getUserToken", "isFingerPermissionsChecked", "", "isLoggedIn", "isPermissionsChecked", "resetPreferences", "", "setFingerPermissionsChecked", "b", "setLoggedInStatus", "setPermissionsChecked", "setUserId", "useridx", "setUserNickname", "nickname", "setUserToken", "token", "Companion", "app_debug"})
public final class SharedPreferencesUtil {
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    public static final com.example.project.sharedpreferences.SharedPreferencesUtil.Companion Companion = null;
    private static final java.lang.String PREF_NAME = "my_pref";
    private static final java.lang.String IS_LOGGED_IN = "is_logged_in";
    private static final java.lang.String USER_IDX = "1111111111";
    private static final java.lang.String USER_NICKNAME = "user_nickname";
    private static final java.lang.String USER_TOKEN = "user_token";
    private static final java.lang.String PERMISSIONS_CHECKED = "general_permissions_checked";
    private static final java.lang.String FINGERPERMISSIONS_CHECKED = "finger_permissions_checked";
    
    @javax.inject.Inject
    public SharedPreferencesUtil(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    private final android.content.SharedPreferences getSharedPreferences() {
        return null;
    }
    
    public final void setLoggedInStatus(boolean isLoggedIn) {
    }
    
    public final boolean isLoggedIn() {
        return false;
    }
    
    public final void setUserId(long useridx) {
    }
    
    public final long getUserId() {
        return 0L;
    }
    
    public final void setUserNickname(@org.jetbrains.annotations.NotNull
    java.lang.String nickname) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUserNickname() {
        return null;
    }
    
    public final void setUserToken(@org.jetbrains.annotations.NotNull
    java.lang.String token) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUserToken() {
        return null;
    }
    
    public final void resetPreferences() {
    }
    
    public final void setPermissionsChecked() {
    }
    
    public final boolean isPermissionsChecked() {
        return false;
    }
    
    public final void setFingerPermissionsChecked(boolean b) {
    }
    
    public final boolean isFingerPermissionsChecked() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/project/sharedpreferences/SharedPreferencesUtil$Companion;", "", "()V", "FINGERPERMISSIONS_CHECKED", "", "IS_LOGGED_IN", "PERMISSIONS_CHECKED", "PREF_NAME", "USER_IDX", "USER_NICKNAME", "USER_TOKEN", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}