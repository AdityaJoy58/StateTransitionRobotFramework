package robotFramwork;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RuleEngine {

  public RuleEngine(String jsonPath, JSONObject jsonObject2) throws InterruptedException {
    JSONObject input_array = null;
    JSONParser parser = new JSONParser();
    try {
      Object obj = parser.parse(new FileReader(jsonPath));
      JSONObject jsonObject =  (JSONObject) obj;
      String state = "splash_pagekey";
      do {
        JSONArray rule_array = (JSONArray) jsonObject.get(state);
        System.out.println(rule_array.size());
        Iterator<JSONObject> iterator = rule_array.iterator();
        ArrayList<JSONObject> rule_list = new ArrayList<JSONObject>();
        while (iterator.hasNext()) {
         rule_list.add(iterator.next());
        }
        // Need to code for Round Robin
        // Call this Function as RR or Random
        Collections.shuffle(rule_list);
        int index = new Random().nextInt()%rule_list.size();
        index = (index<0)? (-index):index;
        System.out.println(rule_list.get(index).toString());

        if(!rule_list.get(index).get("input").toString().isEmpty()){
          input_array = (JSONObject) rule_list.get(index).get("input");
        }
        String preCondition = rule_list.get(index).get("precondition").toString();
        System.out.println("PreCondition " +preCondition);
        if(preCondition.equals("setValidLoginCredentials") || preCondition.equals("setInvalidLoginCredentials")){
          System.out.println(jsonObject2);
          input_array = jsonObject2;
        }
        new MethodInvoker(preCondition, input_array);

        String event = rule_list.get(index).get("event").toString();
        System.out.println("Event " +event);
        new MethodInvoker(event, null);


        String validation = rule_list.get(index).get("validation").toString();
        System.out.println("Validation " +validation);
        new MethodInvoker(validation, null);

        String resultingState = rule_list.get(index).get("resultingstate").toString();
        System.out.println("Current State => "+ state + " Resulting State => " +resultingState);
        state = resultingState;
        input_array = null;
        if("profile_pagekey".equals(state) || "messaging_pagekey".equals(state) || "people_pagekey".equals(state) || "search_pagekey".equals(state)){
          break;
        }
      }while(true);

      //Logging out for every user after he stays in App for a time/End state
      new GlobalHelper().logout();

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }
  }

}
