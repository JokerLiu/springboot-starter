package com.joker.doc;

import org.springframework.util.StringUtils;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.UUID;
import java.util.regex.Pattern;

public class ApiSelectorBuilderExt extends ApiSelectorBuilder {
	private static final String regex = UUID.randomUUID().toString();
	public static final String DEFAULT_INVISIBLE_PROFILE_PATTERN = "release.*|prod.*";
	public final Pattern pattern;

	
	public ApiSelectorBuilderExt(Docket parent) {
		super(parent);
		pattern = Pattern.compile(StringUtils.isEmpty(ApiDocAutoConfiguration.invisible_profile_pattern) ? DEFAULT_INVISIBLE_PROFILE_PATTERN : ApiDocAutoConfiguration.invisible_profile_pattern);
	}

	@Override
	public Docket build() {
		this.paths(PathSelectors.regex("^(?!/error).*"));

		if (isInvisibleProfile())
			this.paths(PathSelectors.regex(regex));

		return super.build();
	}
	
	
	protected boolean isInvisibleProfile() {
		String custom_profile = ApiDocAutoConfiguration.customized_env_profile;
		String[] spring_profiles = StringUtils.isEmpty(ApiDocAutoConfiguration.spring_env_profiles) ? new String[]{} : ApiDocAutoConfiguration.spring_env_profiles.split(",");
		if(!StringUtils.isEmpty(spring_profiles))
			
		if (pattern.matcher(custom_profile).matches())
			return true;
		
		for(String profile : spring_profiles)
			if (pattern.matcher(profile.trim()).matches())
				return true;
		
		return false;
	}
}
