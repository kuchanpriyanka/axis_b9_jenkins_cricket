package com.axis.springbootdemo.Controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.axis.springbootdemo.Entity.Cricketer;

@RestController
public class MyContoller {
	
	private static ArrayList<Cricketer> cricketerList; 
	static {
		cricketerList = new ArrayList<>();
		cricketerList.add(new Cricketer(1001,"Mithali Raj",111,12,5,4,73));
		cricketerList.add(new Cricketer(1002,"M.S Dhoni",123,15,24,14,17));
		cricketerList.add(new Cricketer(1003,"Rohit Sharma",131,25,44,14,6));
		cricketerList.add(new Cricketer(1004,"Virat Kohli",186,35,17,34,8));
	}


	@GetMapping("/message")
	public String getMessage() {
		return "Hello..\n First Spring Boot Application\n Good Afternoon Spring";
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Lets Start Spring Boot Application";
	}
	//get all the cricketer list
	@GetMapping("/cricketer")
	public ArrayList<Cricketer> getCricketer() {
		return cricketerList;
	}
	
	//get the cricketer by Id
	@GetMapping("cricketer/{cricketerId}")
	public Cricketer getCricketerById(@PathVariable int cricketerId) {
		for(Cricketer ck: cricketerList) {
			if(ck.getCricketerId()== cricketerId) {
				return ck; //return cricketer if Id is found
			}
		}
		return null; // return null if cricketer is not found
	}
	
	//add cricketer
	@PostMapping("/cricketer")
	public ResponseEntity<String> addCricketer(@RequestBody Cricketer cricketer) {
		cricketerList.add(cricketer);
		return new ResponseEntity<String>("Cricketer Added Successfully...", HttpStatus.OK);//httpStatus.OK is response status
		
	}
	//update request
	@PutMapping("/cricketer/update/{cricketerId}")
	public ResponseEntity<String>updateCricketer(@PathVariable int cricketerId, @RequestBody Cricketer updateCricketer){
		if(cricketerId != updateCricketer.getCricketerId()) {
			return new ResponseEntity<String>("Cricketer id's don't match!!!", HttpStatus.BAD_REQUEST);
		}
		int index = cricketerList.indexOf(updateCricketer);
		if(index == -1) {
			return new ResponseEntity<String>("Cricketer with id "+ cricketerId+" is not found!", HttpStatus.NOT_FOUND);
		} else {
			cricketerList.get(index).setBalls(updateCricketer.getBalls());
			cricketerList.get(index).setRunScored(updateCricketer.getRunScored());
			cricketerList.get(index).setFours(updateCricketer.getFours());
			cricketerList.get(index).setSixes(updateCricketer.getSixes());
			cricketerList.get(index).setStrikeRate(updateCricketer.getStrikeRate());
			return new ResponseEntity<String>("Cricketer Data Updated Successfully!",HttpStatus.OK);
		}
	}
	
	//DeleteMapping
	@DeleteMapping("/cricketer/delete/{cricketerId}")
	public ResponseEntity<String> deleteCricketer(@PathVariable int cricketerId){
		Cricketer cricketer = getCricketerById(cricketerId);
		if(cricketer == null) {
			return new ResponseEntity<String>("Cricketer with id "+ cricketerId+" is not found!", HttpStatus.NOT_FOUND);
		} else {
			cricketerList.remove(cricketer);
			return new ResponseEntity<String>("Cricketer with id "+ cricketerId+" is Deleted Successfully!", HttpStatus.OK);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
