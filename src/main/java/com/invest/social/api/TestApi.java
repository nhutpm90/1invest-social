package com.invest.social.api;

import java.util.Collections;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
	String token = "24cd69c88d8feca4b328497dc8a3c3b911c619197a78b6b0ef1694dc91b27f9d";
	@Autowired
    RestTemplate restTemplate;
	@PostMapping("/login")
	public UserAuthenticate getLogin(@RequestBody LoginForm form) {
		  String username = form.getUsername();
		  String password = form.getPassword();
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      UserAuthenticate user = restTemplate.exchange( domain + "user_authenticate?"+"api_key_token="+ token +"&username="+username+"&password="+password, HttpMethod.POST, entity, UserAuthenticate.class).getBody();
	      user.setApi_key_token(token);
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
	      return restTemplate.exchange(domain+"user_add?"+"api_key_token="+ token
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
			@RequestParam(required = false) String api_key_token,
			@RequestParam String guid,
			@RequestParam(required = false, defaultValue = "1") String offset,
			@RequestParam(required = false, defaultValue = "10") String count) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(domain
	    		  + "wall_list_home?"
	    		  + "api_key_token="+api_key_token
	    		  + "&guid="+guid
	    		  + "&offset="+offset
	    		  + "&count="+count,
	    		  HttpMethod.POST, entity, PostListResponse.class).getBody();
	}
	@PostMapping("/post")
	public PostResponse getPort(
			@RequestParam(required = false) String api_key_token,
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
	    		  + "api_key_token="+api_key_token
	    		  + "&owner_guid="+owner_guid
	    		  + "&poster_guid="+poster_guid
	    		  + "&post="+post
	    		  + "&friends="+friends
	    		  + "&location="+location
	    		  + "&privacy="+privacy
	    		  + "&type="+type, 
	    		  HttpMethod.POST, entity, PostResponse.class).getBody();
	}
	@PostMapping("/post/comment")
	public JSONObject addComment(
			@RequestParam(required = false) String api_key_token,
			@RequestParam String subject_guid,
			@RequestParam String type,
			@RequestParam String uguid,
			@RequestParam String comment,
			@RequestParam("image_file") MultipartFile file) throws ParseException {
		  Resource invoicesResource = file.getResource();
		  LinkedMultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		  parts.add("image_file", invoicesResource);
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	      HttpEntity <MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(parts,headers);
	      String comments = restTemplate.exchange(domain
	    		  + "comment_add?"
	    		  + "api_key_token="+api_key_token
	    		  + "&subject_guid="+subject_guid
	    		  + "&type="+type
	    		  + "&uguid="+uguid
	    		  + "&comment="+comment,
	    		  HttpMethod.POST, entity, String.class).getBody();
	 	  JSONParser parser = new JSONParser();
	      JSONObject jsonComment = (JSONObject) parser.parse(comments);
	      return jsonComment ;
	}
	@PostMapping("/post/comment/delete")
	public JSONObject deleteComment(
			@RequestParam(required = false) String api_key_token,
			@RequestParam String id,
			@RequestParam String guid) throws ParseException {
		  HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      String deleteComment = restTemplate.exchange(domain
	    		  + "comment_delete?"
	    		  + "api_key_token="+api_key_token
	    		  + "&id="+id
	    		  + "&guid="+guid,
	    		  HttpMethod.POST, entity, String.class).getBody();
	 	  JSONParser parser = new JSONParser();
	      JSONObject jsonDeleteComment = (JSONObject) parser.parse(deleteComment);
	      return jsonDeleteComment ;
	}
	@PostMapping("/post/comment/list")
	public JSONObject getCommentList(
			@RequestParam(required = false) String api_key_token,
			@RequestParam String uguid,
			@RequestParam String guid,
			@RequestParam String page_limit,
			@RequestParam String limit,
			@RequestParam String offset,
			@RequestParam String type) throws ParseException {
		  HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      String commentsList = restTemplate.exchange(domain
	    		  + "comments_list?"
	    		  + "api_key_token="+api_key_token
	    		  + "&uguid="+uguid
	    		  + "&guid="+guid
	    		  + "&page_limit="+page_limit
	       		  + "&limit="+limit
	       		  + "&offset="+offset
	       		  + "&type="+type,
	    		  HttpMethod.POST, entity, String.class).getBody();
	 	  JSONParser parser = new JSONParser();
	      JSONObject jsonCommentList = (JSONObject) parser.parse(commentsList);
	      return jsonCommentList ;
	}
	@PostMapping("/post/comment/edit")
	public JSONObject getCommentEdit(
			@RequestParam(required = false) String api_key_token,
			@RequestParam String guid,
			@RequestParam String comment) throws ParseException {
		  HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      String commentsEdit = restTemplate.exchange(domain
	    		  + "comment_edit?"
	    		  + "api_key_token="+api_key_token
	    		  + "&guid="+guid
	    		  + "&comment="+comment,
	    		  HttpMethod.POST, entity, String.class).getBody();
	 	  JSONParser parser = new JSONParser();
	      JSONObject jsonCommentEdit = (JSONObject) parser.parse(commentsEdit);
	      return jsonCommentEdit ;
	}
	@PostMapping("/like")
	public JSONObject addLike(
			@RequestParam(required = false) String api_key_token,
			@RequestParam String subject_guid,
			@RequestParam String type,
			@RequestParam String uguid) throws ParseException {
		  HttpHeaders headers = new HttpHeaders();
		  headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		  HttpEntity <String> entity = new HttpEntity<String>(headers);
	      String like = restTemplate.exchange(domain
	    		  + "like_add?"
	    		  + "api_key_token="+api_key_token
	    		  + "&subject_guid="+subject_guid
	    		  + "&type="+type
	    		  + "&uguid="+uguid,
	    		  HttpMethod.POST, entity, String.class).getBody();
	 	  JSONParser parser = new JSONParser();
	      JSONObject jsonLike = (JSONObject) parser.parse(like);
	      return jsonLike;
	}
	@PostMapping("/unlike")
	public JSONObject unLike(
			@RequestParam(required = false) String api_key_token,
			@RequestParam String subject_guid,
			@RequestParam String type,
			@RequestParam String uguid) throws ParseException {
		  HttpHeaders headers = new HttpHeaders();
		  headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		  HttpEntity <String> entity = new HttpEntity<String>(headers);
	      String unlike = restTemplate.exchange(domain
	    		  + "unlike_set?"
	    		  + "api_key_token="+api_key_token
	    		  + "&subject_guid="+subject_guid
	    		  + "&type="+type
	    		  + "&uguid="+uguid,
	    		  HttpMethod.POST, entity, String.class).getBody();
	 	  JSONParser parser = new JSONParser();
	      JSONObject jsonUnLike = (JSONObject) parser.parse(unlike);
	      return jsonUnLike;
	}
	@PostMapping("/friends/list")
	public JSONObject getFriendsList(
			@RequestParam(required = false) String api_key_token,
			@RequestParam String guid) throws ParseException {
		  HttpHeaders headers = new HttpHeaders();
		  headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		  HttpEntity <String> entity = new HttpEntity<String>(headers);
	      String friends = restTemplate.exchange(domain
	    		  + "user_friend_requests?"
	    		  + "api_key_token="+api_key_token
	    		  + "&guid="+guid,
	    		  HttpMethod.POST, entity, String.class).getBody();
	 	  JSONParser parser = new JSONParser();
	      JSONObject jsonFriendsList = (JSONObject) parser.parse(friends);
	      return jsonFriendsList;
	}
	@PostMapping("/friends/add")
	public JSONObject addFriends(
			@RequestParam(required = false) String api_key_token,
			@RequestParam String user_a,
			@RequestParam String user_b) throws ParseException {
		  HttpHeaders headers = new HttpHeaders();
		  headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		  HttpEntity <String> entity = new HttpEntity<String>(headers);
	      String addfriends = restTemplate.exchange(domain
	    		  + "user_add_friend?"
	    		  + "api_key_token="+api_key_token
	    		  + "&user_a="+user_a
	    		  + "&user_b="+user_b,
	    		  HttpMethod.POST, entity, String.class).getBody();
	 	  JSONParser parser = new JSONParser();
	      JSONObject jsonAddFriends = (JSONObject) parser.parse(addfriends);
	      return jsonAddFriends;
	}
	@PostMapping("/friends/remove")
	public JSONObject removeFriends(
			@RequestParam(required = false) String api_key_token,
			@RequestParam String user_a,
			@RequestParam String user_b) throws ParseException {
		  HttpHeaders headers = new HttpHeaders();
		  headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		  HttpEntity <String> entity = new HttpEntity<String>(headers);
	      String removeFriends = restTemplate.exchange(domain
	    		  + "user_remove_friend?"
	    		  + "api_key_token="+api_key_token
	    		  + "&user_a="+user_a
	    		  + "&user_b="+user_b,
	    		  HttpMethod.POST, entity, String.class).getBody();
	 	  JSONParser parser = new JSONParser();
	      JSONObject jsonRemoveFriends = (JSONObject) parser.parse(removeFriends);
	      return jsonRemoveFriends;
	}
	@PostMapping("/friends/check")
	public JSONObject isFriends(
			@RequestParam(required = false) String api_key_token,
			@RequestParam String user_a,
			@RequestParam String user_b) throws ParseException {
		  HttpHeaders headers = new HttpHeaders();
		  headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		  HttpEntity <String> entity = new HttpEntity<String>(headers);
	      String isFriends = restTemplate.exchange(domain
	    		  + "user_is_friend?"
	    		  + "api_key_token="+api_key_token
	    		  + "&user_a="+user_a
	    		  + "&user_b="+user_b,
	    		  HttpMethod.POST, entity, String.class).getBody();
	 	  JSONParser parser = new JSONParser();
	      JSONObject jsonIsFriends = (JSONObject) parser.parse(isFriends);
	      return jsonIsFriends;
	}
}
