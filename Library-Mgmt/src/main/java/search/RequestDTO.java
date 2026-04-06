package search;

public class RequestDTO {
	    private String search;
	    private String sortBy;
	    private String sortDirection; // "asc" or "desc"
	    private Integer page = 0;
	    public String getSearch() {
			return search;
		}
		public void setSearch(String search) {
			this.search = search;
		}
		public String getSortBy() {
			return sortBy;
		}
		public void setSortBy(String sortBy) {
			this.sortBy = sortBy;
		}
		public String getSortDirection() {
			return sortDirection;
		}
		public void setSortDirection(String sortDirection) {
			this.sortDirection = sortDirection;
		}
		public Integer getPage() {
			return page;
		}
		public void setPage(Integer page) {
			this.page = page;
		}
		public Integer getSize() {
			return size;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
		private Integer size = 50;


}
