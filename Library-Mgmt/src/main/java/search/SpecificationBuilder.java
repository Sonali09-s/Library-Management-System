package search;

import java.util.ArrayList;
import java.util.List;

public class SpecificationBuilder<T> {

    private final List<SearchCriteria> params;

    public SpecificationBuilder() {
        params = new ArrayList<>();
    }

    public SpecificationBuilder<T> with(String key, SearchOperation operation, Object value, String joinEntity) {
        params.add(new SearchCriteria(key, operation, value, joinEntity));
        return this;
    }

    public GenericSpecification<T> build() {
        GenericSpecification<T> specification = new GenericSpecification<>();
        for (SearchCriteria param : params) {
            specification.add(param);
        }
        return specification;
    }
}
