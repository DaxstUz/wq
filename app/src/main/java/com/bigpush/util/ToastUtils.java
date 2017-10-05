package com.bigpush.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bigpush.R;

/**
 * 弹窗工具类
 * Created by daxstuz on 2016/5/10.
 */
public class ToastUtils {

    private static boolean tempCanceable = true; //点击弹窗以外，弹窗是否消失标记
    private static boolean isShowing = false;
    private static Context mContext = null;

    /**
     * 2个选择弹出框
     *
     * @param context 上下文
     */
    public static void askToast(Context context) {
        initDialog(false, context, null, null, null,null);
    }

    /**
     *  2个选择弹出框
     * @param context 上下文
     * @param msg 显示内容
     */
    public static void askToast(Context context, String msg) {
        initDialog(false, context, null, msg, null,null);
    }

    /**
     *  2个选择弹出框
     * @param context 上下文
     * @param msg 显示内容
     * @param cancelable 是否可点击弹窗外部消失
     */
    public static void askToast(Context context, String msg, boolean cancelable) {
        tempCanceable = cancelable;
        initDialog(false, context, null, msg, null,null);
    }

    /**
     *  2个选择弹出框
     * @param context 上下文
     * @param title 标题
     * @param msg 显示内容
     */
    public static void askToast(Context context, String title, String msg) {
        initDialog(false, context, title, msg, null,null);
    }

    /**
     *  2个选择弹出框
     * @param context 上下文
     * @param msg 显示内容
     * @param listener 监听事件
     */
    public static void askToast(Context context, String msg, ToalstListener listener) {
        initDialog(false, context, null, msg, listener,null);
    }

    /**
     * @param context
     * @param msg
     * @param listener
     * @param opmsg 操作按钮信息
     */

    public static void askToast(Context context, String msg, ToalstListener listener,String[] opmsg) {
        initDialog(false, context, null, msg, listener,opmsg);
    }

    /**
     *  2个选择弹出框
     * @param context 上下文
     * @param msg 显示内容
     * @param listener 监听事件
     * @param cancelable 是否可点击弹窗外部消失
     */
    public static void askToast(Context context, String msg, ToalstListener listener, boolean cancelable) {
        tempCanceable = cancelable;
        initDialog(false, context, null, msg, listener,null);
    }

    /**
     * 2个选择弹出框
     * @param context 上下文
     * @param title 标题
     * @param msg 显示内容
     * @param listener 监听事件
     */
    public static void askToast(Context context, String title, String msg, ToalstListener listener) {
        tempCanceable = false;
        initDialog(false, context, title, msg, listener,null);
    }

    /**
     *  2个选择弹出框
     * @param context 上下文
     * @param msg 显示内容
     * @param listener 监听事件
     * @param cancelable 是否可点击弹窗外部消失
     */
    public static void askToast(Context context, String title, String msg, ToalstListener listener, boolean cancelable) {
        tempCanceable = cancelable;
        initDialog(false, context, title, msg, listener,null);
    }

    /**
     *  2个选择弹出框
     * @param context 上下文
     * @param msg 显示内容
     * @param listener 监听事件
     * @param cancelable 是否可点击弹窗外部消失
     */
    public static void askToast(Context context, String title, String msg, ToalstListener listener, boolean cancelable,String opmsg[]) {
        tempCanceable = cancelable;
        initDialog(false, context, title, msg, listener,opmsg);
    }


    /**
     * 单个选择弹窗
     *
     * @param context 上下文
     * @param msg     显示内容
     */
    public static void askToastSingle(Context context, String msg) {
        initDialog(true, context, null, msg, null,null);
    }
    /**
     * 单个选择弹窗
     *
     * @param context 上下文
     * @param msg     显示内容
     */
    public static void askToastSingle(Context context, String msg,ToalstSingleListener listerner) {
        initDialog(true, context, null, msg, listerner,null);
    }


    /**
     * 单个选择弹窗
     *
     * @param context 上下文
     * @param msg     显示内容
     */
    public static void askToastSingle(Context context,String title, String msg,boolean cancel,ToalstSingleListener listerner) {
        tempCanceable = cancel;
        initDialog(true, context, title, msg, listerner,null);
    }
    /**
     * 单个选择弹窗
     *
     * @param context 上下文
     * @param msg     显示内容
     */
    public static void askToastSingle(Context context, String msg,boolean cancel) {
        tempCanceable = cancel;
        initDialog(true, context, null, msg, null,null);
    }

    /**
     * 单个选择弹窗
     *
     * @param context 上下文
     * @param title   标题
     * @param msg     消息
     */
    public static void askToastSingle(Context context, String title, String msg,ToalstSingleListener listerner) {
        tempCanceable = false;
        initDialog(true, context, title, msg, listerner,null);
    }

    /**
     * 单个选择弹窗
     *
     * @param context 上下文
     */
    public static void askToastSingle(Context context, String msg,boolean cancelable,ToalstSingleListener listerner) {
        tempCanceable = cancelable;
        initDialog(true, context, null, msg, listerner,null);
    }

    /**
     * @param context  上下文
     * @param title    标题
     * @param msg      消息内容
     * @param listener 监听事件
     */
    private static void initDialog(boolean isSingle, Context context, String title, String msg, final MyListener listener,String[] opmsg) {
        if(mContext !=null && mContext == context && isShowing){
            return;
        }
        mContext = context;

        //判断activity是否被销毁，被销毁后，不弹出dialog
        if (mContext instanceof Activity) {
            if (((Activity) mContext).isFinishing())
                return;
        }

        View viewMain = LayoutInflater.from(context).inflate(R.layout.dialog_common, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.dialogOperate)
                .setCancelable(tempCanceable)
                .show();
        alertDialog.setContentView(viewMain);
        alertDialog.show();

        isShowing = true;
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                isShowing = false;
            }
        });

        /*设置消息内容*/
        TextView tv_title = (TextView) viewMain.findViewById(R.id.tv_title);
        if (null != title) {
            FrameLayout fl_title = (FrameLayout) viewMain.findViewById(R.id.fl_title);
            fl_title.setVisibility(View.VISIBLE);
            tv_title.setText(title);
        }

        /*设置消息内容*/
        TextView tv_msg = (TextView) viewMain.findViewById(R.id.tv_msg);
        if (null != msg) {
//            tv_msg.setText(msg);
            tv_msg.setText(Html.fromHtml(msg));
        }

        TextView tv_sure = (TextView) viewMain.findViewById(R.id.tv_sure);
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowing = false;
                if (listener != null) {
                    ((ToalstListener)listener).clickRight(alertDialog);
                } else {
                    alertDialog.cancel();
                }
            }
        });

        TextView tv_canser = (TextView) viewMain.findViewById(R.id.tv_canser);
        tv_canser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowing = false;
                if (listener != null) {
                    ((ToalstListener)listener).clickLeft(alertDialog);
                } else {
                    alertDialog.cancel();
                }
            }
        });

        TextView tv_sure2 = (TextView) viewMain.findViewById(R.id.tv_sure2);
        tv_sure2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowing = false;
                if(listener!=null){
                    ((ToalstSingleListener)listener).clickSure(alertDialog);
                }else{
                    alertDialog.cancel();
                }
            }
        });

        LinearLayout ll_con = (LinearLayout) viewMain.findViewById(R.id.ll_con);

        /*根据是否有取消按钮，显示不同的功能界面*/
        if (isSingle) {
            tv_sure2.setVisibility(View.VISIBLE);
        } else {
            ll_con.setVisibility(View.VISIBLE);
        }

        if (opmsg!=null){
            tv_canser.setText(opmsg[0]);
            tv_sure.setText(opmsg[1]);
        }
    }

    /**
     * @param context 上下文
     */
    public static void initFullFlashDialog(final Activity context, String imgUrl, final String toUrl, final String title, final String conent, final MyListener listener) {
        if (mContext != null && mContext == context && isShowing) {
            return;
        }
        mContext = context;
        //判断activity是否被销毁，被销毁后，不弹出dialog
        if (mContext instanceof Activity) {
            if (((Activity) mContext).isFinishing())
                return;
        }

        View viewMain = LayoutInflater.from(context).inflate(R.layout.dialog_full_flash, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.dialogfull)
                .setCancelable(false)
                .show();
        alertDialog.setContentView(viewMain);
        alertDialog.show();

        isShowing = true;
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                isShowing = false;
            }
        });

        TextView tv_canser = (TextView) viewMain.findViewById(R.id.tv_home_flash_cancel);
        tv_canser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowing = false;
                alertDialog.cancel();
            }
        });

//        ImageButton imageButton = (ImageButton) viewMain.findViewById(R.id.imgbtn_home_flash);
//        ImageLoader.getInstance().displayImage(imgUrl, imageButton, UIUtils.options);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                isShowing = false;
//                alertDialog.cancel();
//                if (listener != null)
//                    ((FullFlashListener) listener).clickSure(toUrl, title, conent);
//            }
//        });
        tv_canser.setVisibility(View.VISIBLE);

    }

    /**
     * @param context 上下文
     */
    public static void initFullFlashDialog(final Activity context, final Bitmap loadedImage, final String toUrl, final String title, final String conent, final MyListener listener) {
        if (mContext != null && mContext == context && isShowing) {
            return;
        }
        mContext = context;
        //判断activity是否被销毁，被销毁后，不弹出dialog
        if (mContext instanceof Activity) {
            if (((Activity) mContext).isFinishing())
                return;
        }
        View viewMain = LayoutInflater.from(context).inflate(R.layout.dialog_full_flash, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.dialogfull)
                .setCancelable(false)
                .show();
        alertDialog.setContentView(viewMain);
        alertDialog.show();

        isShowing = true;
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                isShowing = false;
            }
        });

        TextView tv_canser = (TextView) viewMain.findViewById(R.id.tv_home_flash_cancel);
        tv_canser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowing = false;
                alertDialog.cancel();
            }
        });

        ImageButton imageButton = (ImageButton) viewMain.findViewById(R.id.imgbtn_home_flash);
        imageButton.setImageBitmap(loadedImage);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowing = false;
                alertDialog.cancel();
                if (listener != null)
                    ((FullFlashListener) listener).clickSure(toUrl, title, conent);
            }
        });
        tv_canser.setVisibility(View.VISIBLE);

    }

    /**
     * 父点击事件
     */
    private interface MyListener{}

    /**
     * 两个点击事件
     */
    public interface ToalstListener  extends  MyListener{
        public void clickLeft(AlertDialog alertDialog);
        public void clickRight(AlertDialog alertDialog);
    }

    /**
     * 单个点击事件
     */
    public interface ToalstSingleListener  extends  MyListener{
        public void clickSure(AlertDialog alertDialog);
    }

    /**
     * 单个点击事件
     */
    public interface FullFlashListener extends MyListener {
        public void clickSure(String toUrl, final String title, final String conent);
    }
}
