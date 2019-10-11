package root.paging;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PageBuilder 
{
    public static <T> Page<T> build(Stream<T> entities, Pageable page)
    {
    	if (page.getSort().isSorted())
    	{
    	    entities = entities.sorted(CommonSortingUtils.getComparator(page.getSort()));
    	}
    	
        AtomicInteger count = new AtomicInteger(0);
        List<T> pageContent = entities
                .peek(entity -> count.incrementAndGet())
                .skip(page.getOffset())
                .limit(page.getPageSize())
                .collect(Collectors.toList());
        
        return new PageImpl<>(pageContent, page, count.get());
    }

    public static <T> Page<T> build(Collection<T> entities, Pageable page)
    {
    	Stream<T> stream = entities.stream();
    	if (page.getSort().isSorted())
    	{
    	    stream = stream.sorted(CommonSortingUtils.getComparator(page.getSort()));
    	}
        List<T> pageContent = stream
                .skip(page.getOffset())
                .limit(page.getPageSize())
                .collect(Collectors.toList());
        
        return new PageImpl<>(pageContent, page, entities.size());
    }

    private PageBuilder() 
    {	
    }
}
