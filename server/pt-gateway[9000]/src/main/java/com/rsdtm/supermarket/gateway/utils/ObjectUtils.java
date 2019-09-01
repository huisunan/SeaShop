package com.rsdtm.supermarket.gateway.utils;

import com.alibaba.fastjson.JSON;
import com.rsdtm.supermarket.mbg.model.UmsRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public class ObjectUtils {

    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static Long toLong(Object obj) {
        if (obj == null) {
            return 0L;
        }
        if (obj instanceof Double || obj instanceof Float) {
            return Long.valueOf(StringUtils.substringBefore(obj.toString(), "."));
        }
        if (obj instanceof Number) {
            return Long.valueOf(obj.toString());
        }
        if (obj instanceof String) {
            return Long.valueOf(obj.toString());
        } else {
            return 0L;
        }
    }

    public static Integer toInt(Object obj) {
        return toLong(obj).intValue();
    }

    public static List<GrantedAuthority> toList(List<?> roles) {
        List<GrantedAuthority> result =new ArrayList<>();
        for (Object role:roles)
        {
            LinkedHashMap<String,String> map = (LinkedHashMap<String,String>)role;
            for (Map.Entry<String,String> entry:map.entrySet())
            {
                result.add(new SimpleGrantedAuthority(entry.getValue()));
            }
        }
        return result;


    }

}