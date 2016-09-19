package paserJson;

import com.google.gson.Gson;
import com.yztc.entity.Conmic;
import com.yztc.entity.Data;
import com.yztc.entity.LastCharpter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */
public class Tuijian_hot {
    public static Data fromJson(String jsonString){
        Data data=null;
        try {
            JSONObject object=new JSONObject(jsonString);
            JSONArray array=object.getJSONArray("data");
            for (int i=0;i<array.length();i++){
                JSONObject object1=array.getJSONObject(i);
                data=new Data();
                String title=object1.getString("title");
                String thumb=object1.getString("thumb");
                data.setTitle(title);
                data.setThumb(thumb);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
    public static List<Conmic>  getListFromJson(String jsonString){
        List<Conmic> list=new ArrayList<>();

        try {
            JSONObject object=new JSONObject(jsonString);
            JSONArray array=object.getJSONArray("data");

            for (int i=0;i<array.length();i++){
                JSONObject object1=array.getJSONObject(i);
                String title=object1.getString("title");
                String thumb=object1.getString("thumb");
                String comicId=object1.getString("comicId");
                JSONObject object2=object1.getJSONObject("lastCharpter");
                String id=object2.getString("id");
                String title_=object2.getString("title");
                LastCharpter lastCharpter=new LastCharpter(id,title_);
                Conmic conmic=null;
                if(jsonString.contains("authorName")){
                    String authorName=object1.getString("authorName");
                    String comicType=object1.getString("comicType");
                    conmic=new Conmic(title,thumb,comicId,lastCharpter,authorName,comicType);
                }else{
                    conmic=new Conmic(title,thumb,comicId,lastCharpter);
                }
                list.add(conmic);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
