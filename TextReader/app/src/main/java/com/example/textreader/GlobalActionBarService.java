package com.example.textreader;


import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Service;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

public class GlobalActionBarService extends AccessibilityService {
    DBHelper db;
    DBHelperinstagram dbi;
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {


        AccessibilityNodeInfo mNodeInfo = event.getSource();
//        Toast.makeText(this, "package name " +mNodeInfo.getPackageName(), Toast.LENGTH_SHORT).show();
//        if(mNodeInfo.getPackageName()=="com.whatsapp") {
        db = new DBHelper(this, "whatsapp");
//            Toast.makeText(this, "Package Name is "+getPackageName(), Toast.LENGTH_SHORT).show();
//        }
//        else {
        dbi = new DBHelperinstagram(this, "instagram");
//            Toast.makeText(this, "package Name is "+getPackageName(), Toast.LENGTH_SHORT).show();
//        }
        printEverything(mNodeInfo, 0);
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {

        System.out.println("onService Connected");
        Toast.makeText(this, "this package name is "+this.getPackageName(), Toast.LENGTH_SHORT).show();
        AccessibilityServiceInfo info=new AccessibilityServiceInfo();
        info.eventTypes=AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
        info.eventTypes=AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType=AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
        info.notificationTimeout=1000;
        info.packageNames=new String[]{"com.whatsapp","com.instagram.android"};
        setServiceInfo(info);

    }
    public void printEverything(AccessibilityNodeInfo node, int depth) {
        if (node == null) return;
        String print = "";
        for (int i = 0; i < depth; i++) print += "\t";
        if(node.getViewIdResourceName()!=null)
            print += "Name/Message:" + node.getViewIdResourceName();
        if(node.getText()!=null) {
            print += " ";
            print += "Name/Message:" + node.getText();
            Log.i("TESTREQ", print);
            if(node.getPackageName().equals("com.whatsapp"))
                db.insertData(print);

            if(node.getPackageName().equals("com.instagram.android"))
                dbi.insertData(print);

        }

        for (int j = 0; j < node.getChildCount(); j++) {
            printEverything(node.getChild(j), depth + 1);
        }
    }
}
