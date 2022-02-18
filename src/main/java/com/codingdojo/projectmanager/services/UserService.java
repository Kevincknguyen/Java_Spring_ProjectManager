package com.codingdojo.projectmanager.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.projectmanager.models.LoginUser;
import com.codingdojo.projectmanager.models.User;
import com.codingdojo.projectmanager.repositories.UserRepository;

@Service
public class UserService {

	
	
	private final UserRepository userRepo;
	public UserService(UserRepository userRepo) {
		this.userRepo=userRepo;
	}
	
	
	
//	REGISTER
	public User register(User newUser, BindingResult result) {
		
		String emailEntered=newUser.getEmail();
		String passwordEntered=newUser.getPassword();
		String confirmEntered=newUser.getConfirm();
		
		
		Optional<User> isUser=userRepo.findByEmail(emailEntered);
		if (isUser.isPresent()) {
			System.out.println("Email is present");
			result.rejectValue("email","Taken","This email is already registered");
		}
		if (!passwordEntered.equals(confirmEntered)) {
			System.out.println("Passwords did not match");
			result.rejectValue("confirm","Matches","The passwords do not match");
		}
		
		if (result.hasErrors()) {
			return null;
		}
		else {
			System.out.println("Creating new user");
			String hashed = BCrypt.hashpw(passwordEntered, BCrypt.gensalt());
			newUser.setPassword(hashed);
			userRepo.save(newUser);
			return newUser;
		}
		
	}
	
	
//	LOGIN
	public User login(LoginUser newLoginObject,BindingResult result) {
		
		String emailEntered=newLoginObject.getEmail();
		String passwordEntered=newLoginObject.getPassword();
		
		Optional<User> isUser=userRepo.findByEmail(emailEntered);
		
		if (!isUser.isPresent()) {
			result.rejectValue("email", "None", "Invalid Login/Password!");
			return null;
			}
		if(!BCrypt.checkpw(passwordEntered, isUser.get().getPassword())) {
		    result.rejectValue("password", "password", "Invalid Login/Password!");
		    return null;
		}

		else {
			System.out.println("Login Pass verification");
			User checkUser=isUser.get();
				return checkUser;
		
		}
	}

	
//	FIND USER BY ID
	
	public User findUserById(Long id) {
		Optional<User> optionalUser=userRepo.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}
		else return null;
	}
}
