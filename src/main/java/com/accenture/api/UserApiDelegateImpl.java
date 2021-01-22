package com.accenture.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.accenture.model.Address;
import com.accenture.model.User;

@Component
public class UserApiDelegateImpl implements UserApiDelegate {
	

    Map<Integer, User> userMap;
	
    public UserApiDelegateImpl() {
        if(userMap==null) {
            userMap = new HashMap<Integer, User>();
            User user1 = new User();
            user1.setId(123);
            user1.setName("User");
            user1.setEmail("user@mail.com");
            user1.setAddress(null);
            List<String> mobiles = new ArrayList<String>();
            mobiles.add("123");
            mobiles.add("1234");
            user1.setMobile(mobiles);
            List<Address> addressList = new ArrayList<Address>();
            Address address1 = new Address();
            address1.setCity("City 1");
            address1.setStreet("Street 1");
            addressList.add(address1);
            Address address2 = new Address();
            address2.setCity("City 2");	
            address2.setStreet("Street 2");
            addressList.add(address2);
            user1.setAddress(addressList);
            
            User user2 = new User();
            user2.setId(1234);
            user2.setName("User1");
            user2.setEmail("user1@mail.com");
            userMap.put(Integer.valueOf(123), user1);
            userMap.put(Integer.valueOf(1234), user2);
            
        }
    }
			
    @Override
    public ResponseEntity<User> userPost(User user) {
        User tmpUser = userMap.get(user.getId());
        if(tmpUser==null) {
            userMap.put(user.getId(), user);	
        } else {
            userMap.replace(user.getId(), user);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<User> userUserIdGet(Integer userId) {
        User user = userMap.get(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}