package divas.dazzle.Services;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import divas.dazzle.Objects.TrashAndEdit;

/**
 * Created by Wiseman on 2018-02-12.
 */

public class TrashAndEditJSONParser {
    static List<TrashAndEdit> itemList;

    public static List<TrashAndEdit> parseData(String content) {

        JSONArray items_arry = null;
        TrashAndEdit items = null;
        try
        {
            items_arry = new JSONArray(content);
            itemList = new ArrayList<>();
            for (int i = 0; i < items_arry.length(); i++)
            {
                JSONObject obj = items_arry.getJSONObject(i);
                items = new TrashAndEdit(obj.getInt("vehicle_id"),obj.getString("vehicle_price"),"","","",obj.getString("vehicle_features"),obj.getString("vehicle_make"),"","",obj.getString("vehicle_link"));
                itemList.add(items);
            }
        }
        catch (JSONException ex)
        {
            ex.printStackTrace();
        }
        return itemList;
    }
}
