package com.ots.trainingapi.config;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CustomSortHandlerMethodArgumentResolver extends SortHandlerMethodArgumentResolver {
    
    private static final String DEFAULT_ORDER = "sord";
    private String sortOrder = DEFAULT_ORDER;
    private String defaultSortOrderValue = "asc";
    private String defaultSortParameterValue = "id";
    
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
    
    public void setDefaultSortOrderValue(String defaultSortOrderValue) {
        this.defaultSortOrderValue = defaultSortOrderValue;
    }
    
    public void setDefaultSortParameterValue(String defaultSortParameterValue) {
        this.defaultSortParameterValue = defaultSortParameterValue;
    }
    
    @Override
    public Sort resolveArgument(MethodParameter parameter,
                                ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest,
                                WebDataBinderFactory binderFactory) {
        
        String directionParameter = webRequest.getParameter(sortOrder);
        
        if(directionParameter == null || directionParameter.isEmpty()) {
            directionParameter = defaultSortOrderValue;
        }
        
        String sortParameter = webRequest.getParameter(getSortParameter(null));
        
        if(sortParameter == null || sortParameter.isEmpty()) {
            sortParameter = defaultSortParameterValue;
        }
        
        if(directionParameter != null && sortParameter != null) {
            return Sort.by(Direction.fromString(directionParameter), sortParameter);
        }
        else {
            return null;
        }
    }
}