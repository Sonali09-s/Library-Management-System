package search;


public class SearchCriteria {
    public SearchCriteria(String key, SearchOperation operation, Object value, String joinEntity) {
		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
		this.joinEntity = joinEntity;
	}
	private String key;
    public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public SearchOperation getOperation() {
		return operation;
	}
	public void setOperation(SearchOperation operation) {
		this.operation = operation;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getJoinEntity() {
		return joinEntity;
	}
	public void setJoinEntity(String joinEntity) {
		this.joinEntity = joinEntity;
	}
	private SearchOperation operation;
    private Object value;
    private String joinEntity; // Optional: name of the join entity
}
