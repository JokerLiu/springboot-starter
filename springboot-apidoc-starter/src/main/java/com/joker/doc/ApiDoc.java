package com.joker.doc;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

public class ApiDoc extends Docket {
	
	public ApiDoc(){
		super(DocumentationType.SWAGGER_2);
	}	

	private ApiDoc(DocumentationType documentationType) {
		super(documentationType);		
	}
	
	public ApiSelectorBuilder select() {
		return new ApiSelectorBuilderExt(this);
	}

}
