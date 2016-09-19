package paserJson;

import com.yztc.entity.Classify;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class Paser_Classify {
    public static List<Classify> fromJson(String json){
        List<Classify> list=new ArrayList<>();
        try {
            JSONObject object=new JSONObject(json);
            JSONArray array=object.getJSONArray("data");
            for (int i=0;i<array.length();i++){
                JSONObject object1=array.getJSONObject(i);
                String title=object1.getString("title");
                String thumb=object1.getString("thumb");
                int  cateId=object1.getInt("cateId");
                Classify classify=new Classify(title,cateId,thumb);
                list.add(classify);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
