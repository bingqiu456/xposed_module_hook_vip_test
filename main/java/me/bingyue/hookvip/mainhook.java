package me.bingyue.hookvip;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class mainhook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        String[] packname = {"com.when.coco", "me.mapleaf.calendar"};
        int a = check_package_name(packname, lpparam.packageName);
        if (a != -1 ){
           XposedBridge.log("hook start....");
           XposedBridge.log("hook vip ok");
           // XposedBridge.log(Integer.toString(a));
           switch (a){
               case 0:
                   hook_coco(lpparam);
               case 1:
                   hook_mapleaf(lpparam);
           }
           // XposedBridge.log("hook vip ok");
        }
    }

    private void hook_coco(XC_LoadPackage.LoadPackageParam lpparam) {
        XC_MethodHook.Unhook a = XposedHelpers.findAndHookMethod("com.when.coco.o0.v0",
                lpparam.classLoader,
                "o",
                new XC_MethodHook(){
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) { }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) {
                        param.setResult(true);
                        // XposedBridge.log("hook vip ok");
                    }
                }
        );

    }

    private void hook_mapleaf(XC_LoadPackage.LoadPackageParam lpparam){
        XC_MethodHook.Unhook b = XposedHelpers.findAndHookMethod(
                "w5.a0",
                lpparam.classLoader,
                "d",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {}
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) {
                        param.setResult(true);
                    }
                }
        );
    }

    public static int check_package_name(String[] array, String name){
        for (int i = 0; i < array.length; i++){
            if(name.equals(array[i])){
                return i;
            }
        }
        return -1;
    }
}