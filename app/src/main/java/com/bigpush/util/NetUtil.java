package  com.bigpush.util;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.RequestQueue;

public class NetUtil {

    public static int NET_WHAT=0;

    public static RequestQueue rqueue= NoHttp.newRequestQueue(5);
}
