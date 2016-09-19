package paserJson;

import com.yztc.entity.ComicSrc;
import com.yztc.entity.Conmic_Details;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class Paser_Conmic {
    public static Conmic_Details getListfronJson(String jsonString){
        Conmic_Details conmic_details=null;
        List<ComicSrc> list1=new ArrayList<>();
        try {
            JSONObject object=new JSONObject(jsonString);
            JSONObject object1=object.getJSONObject("data");
            String title=object1.getString("title");
            long comicId=object1.getLong("comicId");
            String thumb=object1.getString("thumb");
            String authorName=object1.getString("authorName");
            String comicType=object1.getString("comicType");
            String areaName=object1.getString("areaName");
            String updateTime=object1.getString("updateTime");
            String tucaos=object1.getString("tucaos");
            String intro=object1.getString("intro");
            JSONArray array=object1.getJSONArray("comicSrc");
            for (int i=0;i<array.length();i++){
                JSONObject object2=array.getJSONObject(i);
                String title2=object2.getString("title");
                String id=object2.getString("id");
                String lastCharpterTitle=object2.getString("lastCharpterTitle");
                String lastCharpterId=object2.getString("lastCharpterId");
                long lastCharpterUpdateTime=object2.getLong("lastCharpterUpdateTime");
                ComicSrc comicSrc=new ComicSrc(title2,id,lastCharpterTitle,lastCharpterId,lastCharpterUpdateTime);
                list1.add(comicSrc);
            }
            conmic_details=new Conmic_Details(title,comicId,thumb,authorName,comicType,
                    areaName,updateTime,list1,tucaos,intro);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return conmic_details;
    }
}
