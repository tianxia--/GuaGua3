package paserJson;

import com.yztc.entity.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class Paser_Comment {
    public static List<User> getList(String json){
        List<User> list=new ArrayList<>();
        try {
            JSONObject object=new JSONObject(json);
            JSONObject object1=object.getJSONObject("data");
            JSONArray array=object1.getJSONArray("comment_list");
            for (int i=0;i<array.length();i++){
                JSONObject object2=array.getJSONObject(i);
                String id=object2.getString("id");
                String nickname=object2.getString("nickname");
                String uid=object2.getString("uid");
                String root_id=object2.getString("root_id");
                String parent_id=object2.getString("parent_id");
                String content=object2.getString("content");
                String post_time=object2.getString("post_time");
                String reply_no=object2.getString("reply_no");
                String user_thumb=object2.getString("user_thumb");
                User user=new User(id,nickname,uid,root_id,parent_id,content,post_time,reply_no,user_thumb);
                list.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
