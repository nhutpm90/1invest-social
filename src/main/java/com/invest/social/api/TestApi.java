package com.invest.social.api;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.invest.social.model.LoginForm;
import com.invest.social.model.PostListResponse;
import com.invest.social.model.PostResponse;
import com.invest.social.model.RegisterResponse;
import com.invest.social.model.UserAuthenticate;




@RestController
@RequestMapping("/api")
public class TestApi {
	String domain = "http://localhost/ossn/api/v1.0/";
	String api_key_token = "6877e75ef895ace49e78144f3a9fa8fd7654ce208921d732793f5242aceeb1ee";
	@Autowired
    RestTemplate restTemplate;
	@PostMapping("/login")
	public UserAuthenticate getLogin(@RequestBody LoginForm form) {
		  String username = form.getUsername();
		  String password = form.getPassword();
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      UserAuthenticate user = restTemplate.exchange( domain + "user_authenticate?"+"api_key_token="+ api_key_token +"&username="+username+"&password="+password, HttpMethod.POST, entity, UserAuthenticate.class).getBody();
	      user.setApi_key_token(api_key_token);
	      return  user;
	}
	@PostMapping("/register")
	public RegisterResponse getAddUser(
			@RequestParam String firstname,
			@RequestParam String lastname,
			@RequestParam String email,
			@RequestParam String reemail,
			@RequestParam String gender,
			@RequestParam String birthdate,
			@RequestParam String username,
			@RequestParam String password) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(domain+"user_add?"+"api_key_token="+ api_key_token
	    		  + "&firstname="+firstname
	     		  + "&lastname="+lastname
	     		  + "&email="+email
	    		  + "&reemail="+reemail
	    		  + "&gender="+gender
	    		  + "&birthdate="+birthdate
	    		  + "&username="+username
	    		  + "&password="+password,
	    		  HttpMethod.POST, entity, RegisterResponse.class).getBody();
	}
	@PostMapping("/post/list")
	public PostListResponse getPortList(
			@RequestParam(required = false) String token,
			@RequestParam String guid,
			@RequestParam(required = false, defaultValue = "1") String offset,
			@RequestParam(required = false, defaultValue = "10") String count) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(domain
	    		  + "wall_list_home?"
	    		  + "api_key_token="+token
	    		  + "&guid="+guid
	    		  + "&offset="+offset
	    		  + "&count="+count,
	    		  HttpMethod.POST, entity, PostListResponse.class).getBody();
	}
	@PostMapping("/post")
	public PostResponse getPort(
			@RequestParam(required = false) String token,
			@RequestParam String owner_guid,
			@RequestParam String poster_guid,
			@RequestParam String post,
			@RequestParam String friends,
			@RequestParam String location,
			@RequestParam String privacy,
			@RequestParam String type,
			@RequestParam("ossn_photo") MultipartFile file) {
		  Resource invoicesResource = file.getResource();
		  LinkedMultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		  parts.add("ossn_photo", invoicesResource);
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	      HttpEntity <MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(parts,headers);
	      return restTemplate.exchange(domain
	    		  + "wall_add?"
	    		  + "api_key_token="+token
	    		  + "&owner_guid="+owner_guid
	    		  + "&poster_guid="+poster_guid
	    		  + "&post="+post
	    		  + "&friends="+friends
	    		  + "&location="+location
	    		  + "&privacy="+privacy
	    		  + "&type="+type, 
	    		  HttpMethod.POST, entity, PostResponse.class).getBody();
	}
}
