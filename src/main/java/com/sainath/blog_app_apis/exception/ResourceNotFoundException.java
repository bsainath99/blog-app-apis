package com.sainath.blog_app_apis.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
String resourceName;
String fieldName;
long fieldValue;
public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
	super(String.format("%s not found with %s : %s", resourceName,fieldName,fieldValue));
	this.resourceName = resourceName;
	this.fieldName = fieldName;
	this.fieldValue = fieldValue;
}

}
