package root.paging;

import java.util.Comparator;
import java.util.Objects;

import org.hibernate.internal.util.compare.ComparableComparator;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;

@SuppressWarnings({"unchecked", "rawtypes"})
public class CommonSortingUtils
{
    private static final String DEFAULT_FIELD = "name";

    public static <T> Comparator<T> getComparator(Sort sort)
    {
        Sort.Order order = sort.get().findFirst().orElse(new Sort.Order(Sort.Direction.ASC, DEFAULT_FIELD));
        Comparator nullsSafeComparator = Comparator.nullsLast(ComparableComparator.INSTANCE);
        Comparator comparator = Comparator.comparing(obj -> getFieldValue(order.getProperty(), obj), nullsSafeComparator);
        return order.getDirection().equals(Sort.Direction.ASC) ? comparator : comparator.reversed();
    }

    private static <T> Comparable getFieldValue(String field, T obj)
    {
        try
        {
            if (obj instanceof String) return (String) obj;
            return (Comparable) Objects.requireNonNull(BeanUtils.getPropertyDescriptor(obj.getClass(), field)).getReadMethod().invoke(obj);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}