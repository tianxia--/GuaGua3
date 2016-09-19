package paserJson;

import com.yztc.entity.ReadConmic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 */
public class Paser_ReadConmic {
    public static ReadConmic getList(String json){
        ReadConmic readConmic=null;
        try {
            JSONObject object=new JSONObject(json);
            JSONObject object1=object.getJSONObject("data");
            String title=object1.getString("title");
            int sid=object1.getInt("sid");
            long updateTime=object1.getLong("updateTime");
            int counts=object1.getInt("counts");
            double size=object1.getDouble("size");
            JSONArray array=object1.getJSONArray("addrs");
            String[] imgs=new String[array.length()];
            for (int i=0;i<array.length();i++){
                imgs[i]=array.getString(i);
            }
            readConmic=new ReadConmic(title,sid,updateTime,counts,size,imgs);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return readConmic;
    }
}
