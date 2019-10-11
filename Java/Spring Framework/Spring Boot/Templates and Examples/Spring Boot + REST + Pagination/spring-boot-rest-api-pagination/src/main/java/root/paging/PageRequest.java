package root.paging;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class PageRequest implements Pageable
{
    private static final PageRequest unpaged = new PageRequest();

    private boolean paged;
    private int limit;
    private long offset;
    private Sort sort;

    public static PageRequest unpaged()
    {
        return unpaged;
    }

    public PageRequest(boolean paged, int limit, long offset, Sort sort)
    {
        this.paged = paged;
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    public PageRequest(int limit, long offset, Sort sort)
    {
        this(true, limit, offset, sort);
    }

    public PageRequest(int limit, long offset)
    {
        this(true, limit, offset, Sort.unsorted());
    }

    public PageRequest()
    {
        this(false, Integer.MAX_VALUE, 0, Sort.unsorted());
    }

    @Override
    public int getPageNumber()
    {
        if (getOffset() > 0 && getOffset() < getPageSize())
        {
            return 1;
        }
        return (int) getOffset() / getPageSize();
    }

    @Override
    public int getPageSize()
    {
        return limit;
    }

    @Override
    public long getOffset()
    {
        return offset;
    }

    @Override
    public Sort getSort()
    {
        return sort;
    }

    @Override
    public Pageable next()
    {
        return new PageRequest(isPaged(), getPageSize(), getOffset() + getPageSize(), getSort());
    }

    @Override
    public Pageable previousOrFirst()
    {
        if (getOffset() - getPageSize() <= 0)
        {
            return first();
        }
        return new PageRequest(isPaged(), getPageSize(), getOffset() - getPageSize(), getSort());
    }

    @Override
    public boolean isPaged()
    {
        return paged;
    }

    @Override
    public Pageable first()
    {
        if (getOffset() == 0)
        {
            return this;
        }
        return new PageRequest(isPaged(), 0, getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious()
    {
        return getOffset() > 0;
    }
}