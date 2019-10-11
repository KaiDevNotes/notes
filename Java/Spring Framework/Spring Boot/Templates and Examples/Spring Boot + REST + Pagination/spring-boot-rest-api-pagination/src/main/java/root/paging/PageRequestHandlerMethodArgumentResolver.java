package root.paging;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortArgumentResolver;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PageRequestHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver 
{
    private static final String LIMIT_PARAMETER_NAME = "limit";
    private static final String OFFSET_PARAMETER_NAME = "offset";
    private final SortArgumentResolver sortResolver;

    @Override
    public boolean supportsParameter(MethodParameter parameter)
    {
        return PageRequest.class.equals(parameter.getParameterType());
    }

    @Override
    public PageRequest resolveArgument(MethodParameter methodParameter, 
                                       ModelAndViewContainer mavContainer,
                                       NativeWebRequest webRequest, 
                                       WebDataBinderFactory binderFactory) 
    {
        String offsetString = webRequest.getParameter(OFFSET_PARAMETER_NAME);
        String limitString = webRequest.getParameter(LIMIT_PARAMETER_NAME);

        Integer limit = checkAndParseLimit(limitString);
        Long offset = checkAndParseOffset(offsetString);
        Sort sort = sortResolver.resolveArgument(methodParameter, mavContainer, webRequest, binderFactory);

        if (limit == null && offset == null && sort.isUnsorted()) 
        {
            return PageRequest.unpaged();
        } 
        else 
        {
            return new PageRequest(limit == null ? Integer.MAX_VALUE : limit, offset == null ? 0 : offset, sort);
        }
    }

    private Integer checkAndParseLimit(String limitString)
    {
        if (!StringUtils.hasText(limitString))
        {
            return null;
        }

        try 
        {
            Integer parsed = Integer.parseInt(limitString);
            return parsed < 1 ? null : parsed;
        } 
        catch (NumberFormatException e)
        {
            return null;
        }
    }

    private Long checkAndParseOffset(String offsetString)
    {
        if (!StringUtils.hasText(offsetString))
        {
            return null;
        }

        try
        {
            Long parsed = Long.valueOf(offsetString);
            return parsed < 0 ? null : parsed;
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }
}
