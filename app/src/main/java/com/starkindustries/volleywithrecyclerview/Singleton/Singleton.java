package com.starkindustries.volleywithrecyclerview.Singleton;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
public class Singleton
{
    public static Singleton instance;
    public Context context;
    public RequestQueue requestQueue;
    public RequestQueue getRequestQueue(Context context)
    {
        if(requestQueue==null)
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        return requestQueue;
    }
    public static synchronized Singleton singleTon(Context context)
    {
        if(instance==null)
            instance=new Singleton(context);
        return instance;
    }
    public <T> void addRequest(Request<T> request)
    {
        if(requestQueue==null)
            requestQueue=getRequestQueue(context);
        requestQueue.add(request);
    }
    public Singleton(Context context)
    {
        this.context=context;
        this.requestQueue=getRequestQueue(context);
    }
}
