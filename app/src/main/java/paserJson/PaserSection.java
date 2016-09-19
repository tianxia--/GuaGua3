package paserJson;

import com.yztc.entity.Section;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/14.
 */
public class PaserSection {
    public static List<Section> getListFromJson(String json){
        List<Section> list=new ArrayList<>();
        try {
            JSONObject object=new JSONObject(json);
            JSONArray array=object.getJSONArray("data");
            for (int i=0;i<array.length();i++){
                JSONObject object1=array.getJSONObject(i);
                String title=object1.getString("title");
                int sid= object1.getInt("sid");
                long id= object1.getLong("id");
                float size= (float) object1.getDouble("size");
                int counts= object1.getInt("counts");
                int iszm= object1.getInt("iszm");
                String surl=object1.getString("surl");
                Section section=new Section(title,sid,id,size,counts,iszm,surl);
                list.add(section);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
