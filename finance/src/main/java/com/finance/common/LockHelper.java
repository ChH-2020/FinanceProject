package com.finance.common;

import com.finance.pojo.user.User;
import com.finance.service.impl.login.LoginServiceImpl;
import com.finance.service.login.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.ws.soap.Addressing;
import java.util.concurrent.ConcurrentHashMap;

public class LockHelper {
    private static Logger log = LoggerFactory.getLogger(LockHelper.class);
    private static Integer count = 0;
    private static ConcurrentHashMap<Integer, HttpSession> helperMap = new ConcurrentHashMap<>();
    public static void userLogout(){
        count--;
    }
    public static ConcurrentHashMap addUser(HttpSession curSession, User user){
        HttpSession preSession = helperMap.get(user.getId());
        // 用户已经存在
        if(preSession!=null){
            helperMap.remove(user.getId());
            preSession.invalidate();
            --count;
        }
        // 不存在该用户，或者存在，再加入新用户
        curSession.setAttribute("loginUser", user);
//        loginService.updateUserOnlineStatus(user,"login");
        helperMap.put(user.getId(),curSession);
        ++count;
        log.info("User: {}上线，当前在线人数: {}",user.getUsername(),count);
        System.out.println(helperMap);
        return helperMap;
    }

    public static ConcurrentHashMap removeSession(User user){
        HttpSession preSession = helperMap.get(user.getId());
        if(preSession==null){
            log.info("User: {}下线，当前在线人数: {}",user.getUsername(),count);
        }else {
            helperMap.remove(user.getId());
//        loginService.updateUserOnlineStatus(user,"logout");//不可使用
            preSession.invalidate();
            --count;
            log.info("User: {}下线，当前在线人数: {}",user.getUsername(),count);
            System.out.println(helperMap);
        }
        return helperMap;
    }


    public static double StringListToInt(String investerm){
        //字符串转换
        investerm = investerm.replace("个","");
        //先转换开头的十
        if(investerm.startsWith("十"))
            investerm = investerm.replace("十","10");
        //再转换1~10
        String[] trans = {"十","一","二","三","四","五","六","七","八","九"};
        for (Integer i = 0; i < trans.length; i++) {
            if(investerm.contains(trans[i]))
                investerm = investerm.replace(trans[i],i.toString());
        }
        if (investerm.contains("两")){
            investerm = investerm.replaceAll("两","2");
        }

        boolean addhalf = false;
        if(investerm.contains("半")){
            investerm = investerm.replace("半","");
            addhalf=true;
            //标记一下，要加上0.5
        };
        String[] form = {"天","日","月","年"};
        for (String item:form) {
            if(investerm.contains(item))
                investerm = investerm.replace(item,"");
        }
        if(addhalf){
            if(investerm==null||investerm.isEmpty()) {
                return 0.5;
            }else{
                double res = Double.valueOf(investerm);
                return res+0.5;
            }
        }else {
            if(investerm == null)//如果此时转化后的没有数字也没有过半字
                return 0;
            else {
                return Double.valueOf(investerm);
            }
        }
    }

    public static void main(String[] args) {
        String test = "二十个半月";
        System.out.println(StringListToInt(test));
    }
}
