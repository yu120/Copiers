package com.github.trang.copiers.test;

import com.github.trang.copiers.BeanMapCopier;
import com.github.trang.copiers.bean.User;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author trang
 */
public class BeanMapTest {
    //trang object
    private User trang = new User();
    private User meng = new User();

    @Before
    public void before() {
        trang.setName("trang");
        trang.setSex((byte) 0);
        trang.setAge(23);
        trang.setHeight(1.73);
        trang.setWeight(68L);
        trang.setHobbits(ImmutableList.of("coding"));
        trang.setHouse(ImmutableMap.<String, Object>of("home", "home"));

        meng.setName("meng");
        meng.setSex((byte) 1);
        meng.setAge(22);
        meng.setWeight(60L);
        meng.setHandsome(true);
        meng.setHobbits(ImmutableList.of("beautiful"));
        meng.setHouse(ImmutableMap.<String, Object>of("home", "home"));

        trang.setWife(meng);
    }

    @Test
    public void beanToMap() {
        Map<String, Object> map = BeanMapCopier.beanToMap(trang);
        System.out.println(map);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("value", "value");
        map2.put("name", "name");
        BeanMapCopier.beanToMap(trang, map2);
        System.out.println(map2);
    }

    @Test
    public void beansToMap() {
        List<User> users = Lists.newArrayList(trang, meng);
        List<Map<String, Object>> list = BeanMapCopier.beansToMap(users);
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }
    }

    @Test
    public void mapToBean() {
        Map<String, Object> map = BeanMapCopier.beanToMap(trang);
        map.remove("handsome");
        System.out.println(map);

        User user = BeanMapCopier.mapToBean(map, User.class);
        System.out.println(user);

        User u2 = new User();
        u2.setName("name");
        u2.setHandsome(true);
        BeanMapCopier.mapToBean(map, u2);
        System.out.println(u2);

    }

    @Test
    public void mapToBeans() {
        List<User> users = Lists.newArrayList(trang, meng);
        List<Map<String, Object>> map = BeanMapCopier.beansToMap(users);
        List<User> list = BeanMapCopier.mapToBeans(map, User.class);
        System.out.println(list);
    }

    @Test
    public void propertiesToMap() {
        Properties properties = new Properties();
        properties.setProperty("0", "false");
        properties.setProperty("1", "true");
        Map<String, Object> map = ((Map) properties);
        System.out.println(map);
    }
}
