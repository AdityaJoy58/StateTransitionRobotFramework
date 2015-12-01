package robotFramwork;
import java.lang.reflect.Method;
import org.json.simple.JSONObject;

public class MethodInvoker {

  public MethodInvoker(String methodName, JSONObject input_array) {
  //no paramater
    Class noparams[] = {};

    //String parameter
    Class[] paramString = new Class[1];
    paramString[0] = JSONObject.class;

    //int parameter
    Class[] paramInt = new Class[1];
    paramInt[0] = Integer.TYPE;
    Method method;
    try{
      //load the GlobalHelper at runtime
      Class cls = Class.forName("robotFramwork.GlobalHelper");
      Object obj = cls.newInstance();

      //call the Appropriate method
      if(input_array == null){
        method = cls.getDeclaredMethod(methodName, noparams);
        method.invoke(obj, null);
      }else{
      //call the printItString method, pass a String param
      method = cls.getDeclaredMethod(methodName, paramString);
      method.invoke(obj, input_array);
      }

    }catch(Exception ex){
      ex.printStackTrace();
    }
  }

}
